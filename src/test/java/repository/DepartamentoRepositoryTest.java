package repository;

import dao.Departamento;
import manager.HibernateController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DepartamentoRepositoryTest {
    private DepartamentoRepository repository;
    private static HibernateController hc;

    @BeforeEach
    void setUp() {
        hc= HibernateController.getInstance();
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
    public void testGet() {

    }

    @Test
    public void testCreate() {
        Departamento dep = new Departamento();
        //dep.set
    }

    @Test
    public void testUpdate() {
    }


    @Test
    public void testDelete() {
    }
}
