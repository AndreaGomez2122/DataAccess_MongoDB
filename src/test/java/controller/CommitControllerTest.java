package controller;

import dao.Commit;
import dao.Programador;
import dto.CommitDTO;
import mapper.CommitMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.CommitService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class CommitControllerTest {

    //dependencia
    // @Mock
    CommitService service;

    CommitMapper mapper;
    CommitDTO depDTO;
    Commit dep;

    //System under Test
    // @InjectMocks
    CommitController controller;

    @BeforeEach
    public void setUp() {
        this.mapper = new CommitMapper();
        ObjectId id = new ObjectId();
        depDTO = CommitDTO.builder()
                .id(id)
                .titulo("nombre proyecto")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    public void getAllCommits() throws SQLException {
        //given
        List<Commit> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(service.findAll()).thenReturn(list);

        //when
        List<Commit> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));

    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(service.getById(depDTO.getId())).thenReturn(dep);

        //when
        CommitDTO resultadoDTO = service.getCommitById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(service.save(dep)).thenReturn(dep);

        //when
        CommitDTO resultado = service.postCommit(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(service.update(dep)).thenReturn(dep);

        //when
        CommitDTO resultado = service.updateCommit(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(service.delete(dep)).thenReturn(dep);

        //when
        CommitDTO resultado = service.deleteCommit(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }
}
