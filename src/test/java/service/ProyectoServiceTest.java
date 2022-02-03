package service;

import dao.Proyecto;
import dto.ProyectoDTO;
import mapper.ProyectoMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.ProyectoRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class ProyectoServiceTest {


    // @Mock
    ProyectoRepository repository;

    ProyectoMapper mapper;
    ProyectoDTO depDTO;
    Proyecto dep;

    //System under Test
    // @InjectMocks
    ProyectoService service;

    @BeforeEach
    public void setUp() {
        // this.service = new ProyectoService(repository);
        this.mapper = new ProyectoMapper();
        ObjectId id = new ObjectId();
        depDTO = ProyectoDTO.builder()
                .id(id)
                .nombre("nombre Proyecto")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    void getAllTest() throws SQLException {
        //given
        List<Proyecto> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(repository.findAll()).thenReturn(list);

        //when
        List<Proyecto> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));
    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(repository.getById(depDTO.getId())).thenReturn(dep);

        //when
        ProyectoDTO resultadoDTO = service.getProyectoById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(repository.save(dep)).thenReturn(dep);

        //when
        ProyectoDTO resultado = service.postProyecto(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(repository.update(dep)).thenReturn(dep);

        //when
        ProyectoDTO resultado = service.updateProyecto(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(repository.delete(dep)).thenReturn(dep);

        //when
        ProyectoDTO resultado = service.deleteProyecto(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }


}
