package repository;

import dao.Repositorio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class RepositorioRepositoryTest {
    private RepositorioRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RepositorioRepository();
    }

    @Test
    void findAllOK() throws SQLException {
        List<Repositorio> list = repository.findAll();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(list, null),
                () -> Assertions.assertTrue(list.size() > 0)
        );
    }

    @Test
    public void testGet() throws SQLException {
        //Faltaría meter una id real

        ObjectId id = null;
        Repositorio Repositorio = repository.getById(id);
        Assertions.assertEquals(Repositorio.getId(), id);
    }

    @Test
    public void testCreate() throws SQLException {
        Repositorio Repositorio = new Repositorio();
        Repositorio.setNombre("RepositorioTest");
        Assertions.assertEquals(Repositorio, repository.save(Repositorio));
    }

    @Test
    public void testUpdate() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Repositorio Repositorio = new Repositorio();
        Repositorio.setId(id);
        Repositorio.setNombre("RepositorioTest");

        Repositorio Repositorio2 = repository.update(Repositorio);
        Assertions.assertAll(
                () -> Assertions.assertEquals(Repositorio.getId(), id),
                () -> Assertions.assertEquals(Repositorio, Repositorio2)
        );
    }


    @Test
    public void testDelete() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Repositorio Repositorio = new Repositorio();
        Repositorio.setId(id);
        Repositorio.setNombre("RepositorioTest");
        Assertions.assertEquals(Repositorio, repository.delete(Repositorio));
    }
}
