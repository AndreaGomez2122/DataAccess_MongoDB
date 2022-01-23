package controller;

import dto.IssueDTO;
import org.bson.types.ObjectId;
import repository.IssueRepository;
import service.IssueService;

import java.sql.SQLException;
import java.util.List;

public class IssueController {
    private static IssueController controller = null;

    // Mi Servicio unido al repositorio
    private final IssueService issueService;

    // Implementamos nuestro Singleton para el controlador
    private IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    public static IssueController getInstance() {
        if (controller == null) {
            controller = new IssueController(new IssueService(new IssueRepository()));
        }
        return controller;
    }

    public List<IssueDTO> getAllIssues() {
        try {
            return issueService.getAllIssues();
        } catch (SQLException e) {
            System.err.println("Error IssueController en getAllIssues: " + e.getMessage());
            return null;
        }
    }

    public IssueDTO getIssueById(ObjectId id) {
        try {
            return issueService.getIssueById(id);
        } catch (SQLException e) {
            System.err.println("Error IssueController en getIssueById: " + e.getMessage());
            return null;
        }
    }

    public IssueDTO postIssue(IssueDTO issueDTO) {
        try {
            return issueService.postIssue(issueDTO);
        } catch (SQLException e) {
            System.err.println("Error IssueController en postIssue: " + e.getMessage());
            return null;
        }
    }

    public IssueDTO updateIssue(IssueDTO issueDTO) {
        try {
            return issueService.updateIssue(issueDTO);
        } catch (SQLException e) {
            System.err.println("Error IssueController en updateIssue: " + e.getMessage());
            return null;
        }
    }

    public IssueDTO deleteIssue(IssueDTO issueDTO) {
        try {
            return issueService.deleteIssue(issueDTO);
        } catch (SQLException e) {
            System.err.println("Error IssueController en deleteIssue: " + e.getMessage());
            return null;
        }
    }
}
