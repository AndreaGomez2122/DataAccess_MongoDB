package service;

import dao.Repositorio;
import dto.RepositorioDTO;
import mapper.RepositorioMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.RepositorioRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class RepositorioServiceTest {


    // @Mock
    RepositorioRepository repository;

    RepositorioMapper mapper;
    RepositorioDTO depDTO;
    Repositorio dep;

    //System under Test
    // @InjectMocks
    RepositorioService service;

    @BeforeEach
    public void setUp() {
        // this.service = new RepositorioService(repository);
        this.mapper = new RepositorioMapper();
        ObjectId id = new ObjectId();
        depDTO = RepositorioDTO.builder()
                .id(id)
                .nombre("nombre Repositorio")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    void getAllTest() throws SQLException {
        //given
        List<Repositorio> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(repository.findAll()).thenReturn(list);

        //when
        List<Repositorio> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));
    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(repository.getById(depDTO.getId())).thenReturn(dep);

        //when
        RepositorioDTO resultadoDTO = service.getRepositorioById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(repository.save(dep)).thenReturn(dep);

        //when
        RepositorioDTO resultado = service.postRepositorio(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(repository.update(dep)).thenReturn(dep);

        //when
        RepositorioDTO resultado = service.updateRepositorio(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(repository.delete(dep)).thenReturn(dep);

        //when
        RepositorioDTO resultado = service.deleteRepositorio(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }


}
