package repository;

import dao.Proyecto;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class ProyectoRepositoryTest {
    private ProyectoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProyectoRepository();
    }

    @Test
    void findAllOK() throws SQLException {
        List<Proyecto> list = repository.findAll();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(list, null),
                () -> Assertions.assertTrue(list.size() > 0)
        );
    }

    @Test
    public void testGet() throws SQLException {
        //Faltaría meter una id real

        ObjectId id = null;
        Proyecto Proyecto = repository.getById(id);
        Assertions.assertEquals(Proyecto.getId(), id);
    }

    @Test
    public void testCreate() throws SQLException {
        Proyecto Proyecto = new Proyecto();
        Proyecto.setNombre("ProyectoTest");
        Assertions.assertEquals(Proyecto, repository.save(Proyecto));
    }

    @Test
    public void testUpdate() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Proyecto Proyecto = new Proyecto();
        Proyecto.setId(id);
        Proyecto.setNombre("ProyectoTest");

        Proyecto Proyecto2 = repository.update(Proyecto);
        Assertions.assertAll(
                () -> Assertions.assertEquals(Proyecto.getId(), id),
                () -> Assertions.assertEquals(Proyecto, Proyecto2)
        );
    }


    @Test
    public void testDelete() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Proyecto Proyecto = new Proyecto();
        Proyecto.setId(id);
        Proyecto.setNombre("ProyectoTest");
        Assertions.assertEquals(Proyecto, repository.delete(Proyecto));
    }
}

