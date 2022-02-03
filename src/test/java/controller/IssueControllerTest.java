package controller;

import dao.Issue;
import dto.IssueDTO;
import mapper.IssueMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.IssueService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
public class IssueControllerTest {

    //dependencia
    // @Mock
    IssueService service;

    IssueMapper mapper;
    IssueDTO depDTO;
    Issue dep;

    //System under Test
    // @InjectMocks
    IssueController controller;

    @BeforeEach
    public void setUp() {
        this.mapper = new IssueMapper();
        ObjectId id = new ObjectId();
        depDTO = IssueDTO.builder()
                .id(id)
                .titulo("nombre proyecto")
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    public void getAllIssues() throws SQLException {
        //given
        List<Issue> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(service.findAll()).thenReturn(list);

        //when
        List<Issue> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));

    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(service.getById(depDTO.getId())).thenReturn(dep);

        //when
        IssueDTO resultadoDTO = service.getIssueById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(service.save(dep)).thenReturn(dep);

        //when
        IssueDTO resultado = service.postIssue(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(service.update(dep)).thenReturn(dep);

        //when
        IssueDTO resultado = service.updateIssue(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(service.delete(dep)).thenReturn(dep);

        //when
        IssueDTO resultado = service.deleteIssue(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }
}
