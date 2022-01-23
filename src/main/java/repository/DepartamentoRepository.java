package repository;

import dao.Departamento;
import manager.HibernateController;
import org.bson.types.ObjectId;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class DepartamentoRepository implements CrudRepository<Departamento, ObjectId> {

    @Override
    public List<Departamento> findAll() {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Departamento> query = hc.getManager().createNamedQuery("Departamento.findAll", Departamento.class);
        List<Departamento> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Departamento getById(ObjectId id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Departamento user = hc.getManager().find(Departamento.class, id);
        hc.close();
        if (user != null)
            return user;
        throw new SQLException("Error DepartamentoRepository no existe departamento con ID: " + id);
    }

    @Override
    public Departamento save(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(departamento);
            hc.getTransaction().commit();
            return departamento;
        } catch (Exception e) {
            throw new SQLException("Error DepartamentoRepository al insertar departamento en BD:" + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Departamento update(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(departamento);
            hc.getTransaction().commit();
            return departamento;
        } catch (Exception e) {
            throw new SQLException("Error DepartamentoRepository al actualizar departamento con id: " + departamento.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Departamento delete(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            departamento = hc.getManager().find(Departamento.class, departamento.getId());
            hc.getManager().remove(departamento);
            hc.getTransaction().commit();
            return departamento;
        } catch (Exception e) {
            throw new SQLException("Error DepartamentoRepository al eliminar departamento con id: " + departamento.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }
}
