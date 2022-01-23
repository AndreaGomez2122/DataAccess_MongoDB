package repository;


import dao.Repositorio;
import manager.HibernateController;
import org.bson.types.ObjectId;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class RepositorioRepository implements CrudRepository<Repositorio, ObjectId> {
    @Override
    public List<Repositorio> findAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Repositorio> query = hc.getManager().createNamedQuery("Repositorio.findAll", Repositorio.class);
        List<Repositorio> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Repositorio getById(ObjectId id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Repositorio prog = hc.getManager().find(Repositorio.class, id);
        hc.close();
        if (prog != null)
            return prog;
        throw new SQLException("Error RepositorioRepository no existe Repositorio con ID: " + id);
    }

    @Override
    public Repositorio save(Repositorio repositorio) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(repositorio);
            hc.getTransaction().commit();
            return repositorio;
        } catch (Exception e) {
            throw new SQLException("Error RepositorioRepository al insertar Repositorio en BD:" + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Repositorio update(Repositorio repositorio) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(repositorio);
            hc.getTransaction().commit();
            return repositorio;
        } catch (Exception e) {
            throw new SQLException("Error RepositorioRepository al actualizar Repositorio con id: " + repositorio.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Repositorio delete(Repositorio repositorio) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            repositorio = hc.getManager().find(Repositorio.class, repositorio.getId());
            hc.getManager().remove(repositorio);
            hc.getTransaction().commit();
            return repositorio;
        } catch (Exception e) {
            throw new SQLException("Error RepositorioRepository al eliminar Repositorio con id: " + repositorio.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }
}
