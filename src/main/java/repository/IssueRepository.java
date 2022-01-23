package repository;

import dao.Issue;
import manager.HibernateController;
import org.bson.types.ObjectId;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class IssueRepository implements CrudRepository<Issue, ObjectId>{
    @Override
    public List<Issue> findAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Issue> query = hc.getManager().createNamedQuery("Issue.findAll", Issue.class);
        List<Issue> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Issue getById(ObjectId id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Issue prog = hc.getManager().find(Issue.class, id);
        hc.close();
        if (prog != null)
            return prog;
        throw new SQLException("Error IssueRepository no existe Issue con ID: " + id);
    }

    @Override
    public Issue save(Issue issue) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().persist(issue);
            hc.getTransaction().commit();
            return issue;
        } catch (Exception e) {
            throw new SQLException("Error IssueRepository al insertar Issue en BD:" + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Issue update(Issue issue) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(issue);
            hc.getTransaction().commit();
            return issue;
        } catch (Exception e) {
            throw new SQLException("Error IssueRepository al actualizar Issue con id: " + issue.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Issue delete(Issue issue) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            issue = hc.getManager().find(Issue.class, issue.getId());
            hc.getManager().remove(issue);
            hc.getTransaction().commit();
            return issue;
        } catch (Exception e) {
            throw new SQLException("Error IssueRepository al eliminar Issue con id: " + issue.getId() + ": " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }

    }
}
