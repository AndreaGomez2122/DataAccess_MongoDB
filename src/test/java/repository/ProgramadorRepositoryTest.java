package repository;

import dao.Programador;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class ProgramadorRepositoryTest {
    private ProgramadorRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProgramadorRepository();
    }

    @Test
    void findAllOK() throws SQLException {
        List<Programador> list = repository.findAll();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(list, null),
                () -> Assertions.assertTrue(list.size() > 0)
        );
    }

    @Test
    public void testGet() throws SQLException {
        //Faltaría meter una id real

        ObjectId id = null;
        Programador Programador = repository.getById(id);
        Assertions.assertEquals(Programador.getId(), id);
    }

    @Test
    public void testCreate() throws SQLException {
        Programador Programador = new Programador();
        Programador.setNombre("ProgramadorTest");
        Assertions.assertEquals(Programador, repository.save(Programador));
    }

    @Test
    public void testUpdate() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Programador Programador = new Programador();
        Programador.setId(id);
        Programador.setNombre("ProgramadorTest");

        Programador Programador2 = repository.update(Programador);
        Assertions.assertAll(
                () -> Assertions.assertEquals(Programador.getId(), id),
                () -> Assertions.assertEquals(Programador, Programador2)
        );
    }


    @Test
    public void testDelete() throws SQLException {
        //Faltaría meter una id real
        ObjectId id = null;
        Programador Programador = new Programador();
        Programador.setId(id);
        Programador.setNombre("ProgramadorTest");
        Assertions.assertEquals(Programador, repository.delete(Programador));
    }
}

