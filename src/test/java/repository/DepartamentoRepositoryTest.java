package repository;

import dao.Departamento;
import manager.HibernateController;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class DepartamentoRepositoryTest {
    private DepartamentoRepository repository;
    private static HibernateController hc;

    @BeforeEach
    void setUp() {
        hc = HibernateController.getInstance();
        repository = new DepartamentoRepository(hc);
    }

    @AfterAll
    static void afterAll() {
        if (hc != null)
            hc.close();
    }

    @Test
    void testGetAll() {
        List<Departamento> list = repository.findAll();
        ObjectId id = new ObjectId("5bf142459b72e12b2b1b2cd");
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(list, null),
                () -> Assertions.assertTrue(list.size() > 0),
                () -> Assertions.assertEquals(list.get(0).getId(), id)
        );
    }

    @Test
    public void testGet() throws SQLException {
        ObjectId id = new ObjectId("5bf142459b72e12b2b1b2cd");
        Departamento dep = repository.getById(id);
        Assertions.assertEquals(dep.getId(), id);
    }

    @Test
    void getException() {
        ObjectId id = new ObjectId();
        Exception exception = Assertions.assertThrows(SQLException.class, () -> {
            repository.getById(id);
        });

        String expectedMessage = "Error DepartamentoRepository";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreate() throws SQLException {
        Departamento dep = new Departamento();
        dep.setNombre("departamentoTest");
        dep.setPresupuesto(2000);
        Assertions.assertEquals(dep, repository.save(dep));
    }


    @Test
    public void testUpdate() throws SQLException {
        ObjectId id = new ObjectId("5bf142459b72e12b2b1b2cd");
        Departamento dep = new Departamento();
        dep.setId(id);
        dep.setNombre("departamentoTest");
        dep.setPresupuesto(2000);

        Departamento dep2 = repository.update(dep);
        Assertions.assertAll(
                () -> Assertions.assertEquals(dep.getId(), id),
                () -> Assertions.assertEquals(dep, dep2)
        );
    }


    @Test
    public void testDelete() throws SQLException {
        ObjectId id = new ObjectId("5bf142459b72e12b2b1b2cd");
        Departamento dep = new Departamento();
        dep.setId(id);
        dep.setNombre("departamentoTest");
        dep.setPresupuesto(2000);
        Assertions.assertEquals(dep, repository.delete(dep));
    }

    @Test
    void createException() {
        ObjectId id = new ObjectId();
        Departamento dep = new Departamento();
        dep.setId(id);
        Exception exception = Assertions.assertThrows(SQLException.class, () -> {
            repository.delete(dep);
        });

        String expectedMessage = "Error DepartamentoRepository";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}

