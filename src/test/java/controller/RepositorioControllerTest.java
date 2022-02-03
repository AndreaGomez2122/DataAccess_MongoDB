package controller;

import dao.Repositorio;
import dto.RepositorioDTO;
import mapper.RepositorioMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.RepositorioService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class RepositorioControllerTest {

    //dependencia
    // @Mock
    RepositorioService service;

    RepositorioMapper mapper;
    RepositorioDTO depDTO;
    Repositorio dep;

    //System under Test
    // @InjectMocks
    RepositorioController controller;

    @BeforeEach
    public void setUp() {
        this.mapper = new RepositorioMapper();
        ObjectId id = new ObjectId();
        depDTO = RepositorioDTO.builder()
                .id(id)
                .nombre("nombre proyecto")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    public void getAllRepositorios() throws SQLException {
        //given
        List<Repositorio> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(service.findAll()).thenReturn(list);

        //when
        List<Repositorio> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));

    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(service.getById(depDTO.getId())).thenReturn(dep);

        //when
        RepositorioDTO resultadoDTO = service.getRepositorioById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(service.save(dep)).thenReturn(dep);

        //when
        RepositorioDTO resultado = service.postRepositorio(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(service.update(dep)).thenReturn(dep);

        //when
        RepositorioDTO resultado = service.updateRepositorio(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(service.delete(dep)).thenReturn(dep);

        //when
        RepositorioDTO resultado = service.deleteRepositorio(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }
}
