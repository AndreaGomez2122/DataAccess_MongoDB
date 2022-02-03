package service;

import dao.Commit;
import dto.CommitDTO;
import mapper.CommitMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.CommitRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class CommitServiceTest {


    // @Mock
    CommitRepository repository;

    CommitMapper mapper;
    CommitDTO depDTO;
    Commit dep;

    //System under Test
    // @InjectMocks
    CommitService service;

    @BeforeEach
    public void setUp() {
        // this.service = new CommitService(repository);
        this.mapper = new CommitMapper();
        ObjectId id = new ObjectId();
        depDTO = CommitDTO.builder()
                .id(id)
                .titulo("nombre commit")
                .texto("texto commit")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    void getAllTest() throws SQLException {
        //given
        List<Commit> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(repository.findAll()).thenReturn(list);

        //when
        List<Commit> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));
    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(repository.getById(depDTO.getId())).thenReturn(dep);

        //when
        CommitDTO resultadoDTO = service.getCommitById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(repository.save(dep)).thenReturn(dep);

        //when
        CommitDTO resultado = service.postCommit(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(repository.update(dep)).thenReturn(dep);

        //when
        CommitDTO resultado = service.updateCommit(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(repository.delete(dep)).thenReturn(dep);

        //when
        CommitDTO resultado = service.deleteCommit(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }


}
