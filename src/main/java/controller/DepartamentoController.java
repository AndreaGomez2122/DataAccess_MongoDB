package controller;

import dto.DepartamentoDTO;
import manager.HibernateController;
import org.bson.types.ObjectId;
import repository.DepartamentoRepository;
import service.DepartamentoService;

import java.sql.SQLException;
import java.util.List;

public class DepartamentoController {
    private static DepartamentoController controller = null;

    // Mi Servicio unido al repositorio
    private final DepartamentoService departamentoService;

    // Implementamos nuestro Singleton para el controlador
    private DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    public static DepartamentoController getInstance() {
        if (controller == null) {
            controller = new DepartamentoController(new DepartamentoService(new DepartamentoRepository(HibernateController.getInstance())));
        }
        return controller;
    }

    public List<DepartamentoDTO> getAllDepartamentos() {
        try {
            return departamentoService.getAllDepartamentos();
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en getAllDepartamentos: " + e.getMessage());
            return null;
        }
    }

    public DepartamentoDTO getDepartamentoById(ObjectId id) {
        try {
            return departamentoService.getDepartamentoById(id);
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en getDepartamentoById: " + e.getMessage());
            return null;
        }
    }

    public DepartamentoDTO postDepartamento(DepartamentoDTO departamentoDTO) {
        try {
            return departamentoService.postDepartamento(departamentoDTO);
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en postDepartamento: " + e.getMessage());
            return null;
        }
    }

    public DepartamentoDTO updateDepartamento(DepartamentoDTO departamentoDTO) {
        try {
            return departamentoService.updateDepartamento(departamentoDTO);
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en updateDepartamento: " + e.getMessage());
            return null;
        }
    }

    public DepartamentoDTO deleteDepartamento(DepartamentoDTO departamentoDTO) {
        try {
            return departamentoService.deleteDepartamento(departamentoDTO);
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en deleteDepartamento: " + e.getMessage());
            return null;
        }
    }
}
