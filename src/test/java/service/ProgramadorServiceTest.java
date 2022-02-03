package service;

import dao.Programador;
import dto.ProgramadorDTO;
import mapper.ProgramadorMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.ProgramadorRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class ProgramadorServiceTest {


    // @Mock
    ProgramadorRepository repository;

    ProgramadorMapper mapper;
    ProgramadorDTO depDTO;
    Programador dep;

    //System under Test
    // @InjectMocks
    ProgramadorService service;

    @BeforeEach
    public void setUp() {
        // this.service = new ProgramadorService(repository);
        this.mapper = new ProgramadorMapper();
        ObjectId id = new ObjectId();
        depDTO = ProgramadorDTO.builder()
                .id(id)
                .nombre("nombre Programador")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    void getAllTest() throws SQLException {
        //given
        List<Programador> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(repository.findAll()).thenReturn(list);

        //when
        List<Programador> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));
    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(repository.getById(depDTO.getId())).thenReturn(dep);

        //when
        ProgramadorDTO resultadoDTO = service.getProgramadorById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(repository.save(dep)).thenReturn(dep);

        //when
        ProgramadorDTO resultado = service.postProgramador(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(repository.update(dep)).thenReturn(dep);

        //when
        ProgramadorDTO resultado = service.updateProgramador(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(repository.delete(dep)).thenReturn(dep);

        //when
        ProgramadorDTO resultado = service.deleteProgramador(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }


}
