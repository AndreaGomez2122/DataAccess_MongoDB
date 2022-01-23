package repository;

import dao.Proyecto;
import manager.HibernateController;
import org.bson.types.ObjectId;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class ProyectoRepository implements CrudRepository<Proyecto, ObjectId> {
    @Override
    public List<Proyecto> findAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Proyecto> query = hc.getManager().createNamedQuery("Proyecto.findAll", Proyecto.class);
        List<Proyecto> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Proyecto getById(ObjectId id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Proyecto prog = hc.getManager().find(Proyecto.class, id);
        hc.close();
        if (prog != null)
            return prog;
        throw new SQLException("Error ProyectoRepository no existe Proyecto con ID: " + id);
    }

    @Override
    public Proyecto save(Proyecto proyecto) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(proyecto);
            hc.getTransaction().commit();
            return proyecto;
        } catch (Exception e) {
            throw new SQLException("Error ProyectoRepository al insertar Proyecto en BD:" + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Proyecto update(Proyecto proyecto) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(proyecto);
            hc.getTransaction().commit();
            return proyecto;
        } catch (Exception e) {
            throw new SQLException("Error ProyectoRepository al actualizar Proyecto con id: " + proyecto.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Proyecto delete(Proyecto proyecto) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            proyecto = hc.getManager().find(Proyecto.class, proyecto.getId());
            hc.getManager().remove(proyecto);
            hc.getTransaction().commit();
            return proyecto;
        } catch (Exception e) {
            throw new SQLException("Error ProyectoRepository al eliminar Proyecto con id: " + proyecto.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }
}
