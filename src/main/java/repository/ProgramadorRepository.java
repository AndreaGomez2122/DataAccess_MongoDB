package repository;

import dao.Programador;
import manager.HibernateController;
import org.bson.types.ObjectId;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class ProgramadorRepository implements CrudRepository<Programador, ObjectId> {

    @Override
    public List<Programador> findAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Programador> query = hc.getManager().createNamedQuery("Programador.findAll", Programador.class);
        List<Programador> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Programador getById(ObjectId id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Programador prog = hc.getManager().find(Programador.class, id);
        hc.close();
        if (prog != null)
            return prog;
        throw new SQLException("Error ProgramadorRepository no existe programador con ID: " + id);
    }

    @Override
    public Programador save(Programador programador) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(programador);
            hc.getTransaction().commit();
            return programador;
        } catch (Exception e) {
            throw new SQLException("Error ProgramadorRepository al insertar programador en BD:" + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Programador update(Programador programador) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(programador);
            hc.getTransaction().commit();
            return programador;
        } catch (Exception e) {
            throw new SQLException("Error ProgramadorRepository al actualizar programador con id: " + programador.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Programador delete(Programador programador) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            programador = hc.getManager().find(Programador.class, programador.getId());
            hc.getManager().remove(programador);
            hc.getTransaction().commit();
            return programador;
        } catch (Exception e) {
            throw new SQLException("Error ProgramadorRepository al eliminar programador con id: " + programador.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }
}
