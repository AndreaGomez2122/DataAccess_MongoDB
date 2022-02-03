package repository;

import dao.Issue;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class IssueRepositoryTest {
    private IssueRepository repository;

    @BeforeEach
    void setUp() {
        repository = new IssueRepository();
    }

    @Test
    void findAllOK() throws SQLException {
        List<Issue> list = repository.findAll();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(list, null),
                () -> Assertions.assertTrue(list.size() > 0)
        );
    }

    @Test
    public void testGet() throws SQLException {
        //Faltaría meter una id real

        ObjectId id = null;
        Issue Issue = repository.getById(id);
        Assertions.assertEquals(Issue.getId(), id);
    }

    @Test
    public void testCreate() throws SQLException {
        Issue Issue = new Issue();
        Issue.setTitulo("IssueTest");
        Assertions.assertEquals(Issue, repository.save(Issue));
    }

    @Test
    public void testUpdate() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Issue Issue = new Issue();
        Issue.setId(id);
        Issue.setTitulo("IssueTest");

        Issue Issue2 = repository.update(Issue);
        Assertions.assertAll(
                () -> Assertions.assertEquals(Issue.getId(), id),
                () -> Assertions.assertEquals(Issue, Issue2)
        );
    }


    @Test
    public void testDelete() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Issue Issue = new Issue();
        Issue.setId(id);
        Issue.setTitulo("IssueTest");
        Assertions.assertEquals(Issue, repository.delete(Issue));
    }
}

