package service;

import dao.Issue;
import dto.IssueDTO;
import mapper.IssueMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.IssueRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class IssueServiceTest {


    // @Mock
    IssueRepository repository;

    IssueMapper mapper;
    IssueDTO depDTO;
    Issue dep;

    //System under Test
    // @InjectMocks
    IssueService service;

    @BeforeEach
    public void setUp() {
        // this.service = new IssueService(repository);
        this.mapper = new IssueMapper();
        ObjectId id = new ObjectId();
        depDTO = IssueDTO.builder()
                .id(id)
                .titulo("nombre Issue")
                .texto("texto Issue")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    void getAllTest() throws SQLException {
        //given
        List<Issue> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(repository.findAll()).thenReturn(list);

        //when
        List<Issue> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));
    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(repository.getById(depDTO.getId())).thenReturn(dep);

        //when
        IssueDTO resultadoDTO = service.getIssueById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(repository.save(dep)).thenReturn(dep);

        //when
        IssueDTO resultado = service.postIssue(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(repository.update(dep)).thenReturn(dep);

        //when
        IssueDTO resultado = service.updateIssue(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(repository.delete(dep)).thenReturn(dep);

        //when
        IssueDTO resultado = service.deleteIssue(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }


}