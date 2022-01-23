package controller;

import dto.ProgramadorDTO;
import org.bson.types.ObjectId;
import repository.ProgramadorRepository;
import service.ProgramadorService;

import java.sql.SQLException;
import java.util.List;

public class ProgramadorController {
    private static ProgramadorController controller = null;

    // Mi Servicio unido al repositorio
    private final ProgramadorService programadorService;

    // Implementamos nuestro Singleton para el controlador
    private ProgramadorController(ProgramadorService programadorService) {
        this.programadorService = programadorService;
    }

    public static ProgramadorController getInstance() {
        if (controller == null) {
            controller = new ProgramadorController(new ProgramadorService(new ProgramadorRepository()));
        }
        return controller;
    }

    public List<ProgramadorDTO> getAllProgramadors() {
        try {
            return programadorService.getAllProgramadores();
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en getAllProgramadores: " + e.getMessage());
            return null;
        }
    }

    public ProgramadorDTO getProgramadorById(ObjectId id) {
        try {
            return programadorService.getProgramadorById(id);
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en getProgramadorById: " + e.getMessage());
            return null;
        }
    }

    public ProgramadorDTO postProgramador(ProgramadorDTO programadorDTO) {
        try {
            return programadorService.postProgramador(programadorDTO);
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en postProgramador: " + e.getMessage());
            return null;
        }
    }

    public ProgramadorDTO updateProgramador(ProgramadorDTO programadorDTO) {
        try {
            return programadorService.updateProgramador(programadorDTO);
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en updateProgramador: " + e.getMessage());
            return null;
        }
    }

    public ProgramadorDTO deleteProgramador(ProgramadorDTO programadorDTO) {
        try {
            return programadorService.deleteProgramador(programadorDTO);
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en deleteProgramador: " + e.getMessage());
            return null;
        }
    }
}
