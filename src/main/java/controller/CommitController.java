package controller;

import dto.CommitDTO;
import dto.RepositorioDTO;
import org.bson.types.ObjectId;
import repository.CommitRepository;
import service.CommitService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommitController {
    private static CommitController controller = null;

    // Mi Servicio unido al repositorio
    private final CommitService commitService;

    // Implementamos nuestro Singleton para el controlador
    private CommitController(CommitService commitService) {
        this.commitService = commitService;
    }

    public static CommitController getInstance() {
        if (controller == null) {
            controller = new CommitController(new CommitService(new CommitRepository()));
        }
        return controller;
    }

    public List<CommitDTO> getAllCommits() {
        try {
            return commitService.getAllCommits();
        } catch (SQLException e) {
            System.err.println("Error CommitController en getAllCommits: " + e.getMessage());
            return null;
        }
    }

    public CommitDTO getCommitById(ObjectId id) {
        try {
            return commitService.getCommitById(id);
        } catch (SQLException e) {
            System.err.println("Error CommitController en getCommitById: " + e.getMessage());
            return null;
        }
    }

    public CommitDTO postCommit(CommitDTO commitDTO) {
        try {
            return commitService.postCommit(commitDTO);
        } catch (SQLException e) {
            System.err.println("Error CommitController en postCommit: " + e.getMessage());
            return null;
        }
    }

    public CommitDTO updateCommit(CommitDTO commitDTO) {
        try {
            return commitService.updateCommit(commitDTO);
        } catch (SQLException e) {
            System.err.println("Error CommitController en updateCommit: " + e.getMessage());
            return null;
        }
    }

    public CommitDTO deleteCommit(CommitDTO commitDTO) {
        try {
            return commitService.deleteCommit(commitDTO);
        } catch (SQLException e) {
            System.err.println("Error CommitController en deleteCommit: " + e.getMessage());
            return null;
        }
    }


    public Optional<CommitDTO> getCommitByIdOptional(ObjectId id) {
        try {
            return Optional.of( commitService.getCommitById(id));
        } catch (SQLException e) {
            System.err.println("Error CommitController en getCommitBuId: " + e.getMessage());
            return Optional.empty();
        }
    }
}
