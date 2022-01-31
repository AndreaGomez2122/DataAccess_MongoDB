package controller;

import dto.ProgramadorDTO;
import dto.RepositorioDTO;
import org.bson.types.ObjectId;
import repository.RepositorioRepository;
import service.RepositorioService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RepositorioController {
    private static RepositorioController controller = null;

    // Mi Servicio unido al repositorio
    private final RepositorioService repositorioService;

    // Implementamos nuestro Singleton para el controlador
    private RepositorioController(RepositorioService repositorioService) {
        this.repositorioService = repositorioService;
    }

    public static RepositorioController getInstance() {
        if (controller == null) {
            controller = new RepositorioController(new RepositorioService(new RepositorioRepository()));
        }
        return controller;
    }

    public List<RepositorioDTO> getAllRepositorios() {
        try {
            return repositorioService.getAllRepositorios();
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en getAllRepositorios: " + e.getMessage());
            return null;
        }
    }

    public RepositorioDTO getRepositorioById(ObjectId id) {
        try {
            return repositorioService.getRepositorioById(id);
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en getRepositorioById: " + e.getMessage());
            return null;
        }
    }

    public RepositorioDTO postRepositorio(RepositorioDTO repositorioDTO) {
        try {
            return repositorioService.postRepositorio(repositorioDTO);
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en postRepositorio: " + e.getMessage());
            return null;
        }
    }

    public RepositorioDTO updateRepositorio(RepositorioDTO repositorioDTO) {
        try {
            return repositorioService.updateRepositorio(repositorioDTO);
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en updateRepositorio: " + e.getMessage());
            return null;
        }
    }

    public RepositorioDTO deleteRepositorio(RepositorioDTO repositorioDTO) {
        try {
            return repositorioService.deleteRepositorio(repositorioDTO);
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en deleteRepositorio: " + e.getMessage());
            return null;
        }
    }

    public Optional<RepositorioDTO> getRepositorioByIdOptional(ObjectId id) {
        try {
            return Optional.of(repositorioService.getRepositorioById(id));
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en getRepositorioById: " + e.getMessage());
            return Optional.empty();
        }
    }
}
