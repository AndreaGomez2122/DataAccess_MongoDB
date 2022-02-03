package repository;

import dao.Commit;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class CommitRepositoryTest {
    private CommitRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CommitRepository();
    }

    @Test
    void findAllOK() throws SQLException {
        List<Commit> list = repository.findAll();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(list, null),
                () -> Assertions.assertTrue(list.size() > 0)
        );
    }

    @Test
    public void testGet() throws SQLException {
        //Faltaría meter una id real

        ObjectId id = null;
        Commit commit = repository.getById(id);
        Assertions.assertEquals(commit.getId(), id);
    }

    @Test
    public void testCreate() throws SQLException {
        Commit commit = new Commit();
        commit.setTitulo("CommitTest");
        commit.setTexto("Hola Test");
        Assertions.assertEquals(commit, repository.save(commit));
    }

    @Test
    public void testUpdate() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Commit commit = new Commit();
        commit.setId(id);
        commit.setTitulo("CommitTest");
        commit.setTexto("Hola Test");

        Commit commit2 = repository.update(commit);
        Assertions.assertAll(
                () -> Assertions.assertEquals(commit.getId(), id),
                () -> Assertions.assertEquals(commit, commit2)
        );
    }


    @Test
    public void testDelete() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Commit commit = new Commit();
        commit.setId(id);
        commit.setTitulo("CommitTest");
        commit.setTexto("Hola Test");
        Assertions.assertEquals(commit, repository.delete(commit));
    }
}
