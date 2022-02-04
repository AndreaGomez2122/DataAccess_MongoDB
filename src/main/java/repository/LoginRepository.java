package repository;

import dao.Login;
import manager.HibernateController;
import org.bson.types.ObjectId;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

//TODO:Implementar login getByToken
public class LoginRepository implements CrudRepository<Login, ObjectId>{
    @Override
    public List<Login> findAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Login> query = hc.getManager().createNamedQuery("Login.findAll", Login.class);
        List<Login> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Login getById(ObjectId id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Login prog = hc.getManager().find(Login.class, id);
        hc.close();
        if (prog != null)
            return prog;
        throw new SQLException("Error LoginRepository no existe Login con ID: " + id);
    }

    @Override
    public Login save(Login login) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(login);
            hc.getTransaction().commit();
            return login;
        } catch (Exception e) {
            throw new SQLException("Error LoginRepository al insertar Login en BD:" + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Login update(Login login) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(login);
            hc.getTransaction().commit();
            return login;
        } catch (Exception e) {
            throw new SQLException("Error LoginRepository al actualizar Login con id: " + login.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Login delete(Login login) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            login = hc.getManager().find(Login.class, login.getId());
            hc.getManager().remove(login);
            hc.getTransaction().commit();
            return login;
        } catch (Exception e) {
            throw new SQLException("Error LoginRepository al eliminar Login con id: " + login.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }




    public boolean deleteByProgrammerId(ObjectId id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            Login login = hc.getManager().find(Login.class, id);
            // System.out.println(login);
            hc.getManager().remove(login);
            hc.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new SQLException("Error LoginRepository al eliminar login con id: " + id);
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }
}
