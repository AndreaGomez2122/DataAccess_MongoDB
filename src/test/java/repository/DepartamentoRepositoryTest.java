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
    void findAllOK() {
        List<Departamento> list = repository.findAll();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(list, null),
                () -> Assertions.assertTrue(list.size() > 0)
        );
    }

    @Test
    public void testGet() throws SQLException {
        //Faltaría meter una id real

        ObjectId id = null;
        Departamento dep = repository.getById(id);
        Assertions.assertEquals(dep.getId(), id);
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
        //Faltaría meter una id real
        ObjectId id = null;
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
        //Faltaría meter una id real
        ObjectId id = null;
        Departamento dep = new Departamento();
        dep.setId(id);
        dep.setNombre("departamentoTest");
        dep.setPresupuesto(2000);
        Assertions.assertEquals(dep, repository.delete(dep));
    }
}

