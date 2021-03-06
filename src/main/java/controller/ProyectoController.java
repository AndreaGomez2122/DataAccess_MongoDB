package controller;

import dao.Proyecto;
import dto.DepartamentoDTO;
import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import org.bson.types.ObjectId;
import repository.ProyectoRepository;
import service.ProyectoService;

import java.sql.SQLException;
import java.util.*;

public class ProyectoController {
    private static ProyectoController controller = null;

    // Mi Servicio unido al repositorio
    private final ProyectoService proyectoService;

    // Implementamos nuestro Singleton para el controlador
    private ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    public static ProyectoController getInstance() {
        if (controller == null) {
            controller = new ProyectoController(new ProyectoService(new ProyectoRepository()));
        }
        return controller;
    }

    public List<ProyectoDTO> getAllProyectos() {
        try {
            return proyectoService.getAllProyectos();
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en getAllProyectos: " + e.getMessage());
            return null;
        }
    }

    public ProyectoDTO getProyectoById(ObjectId id) {
        try {
            return proyectoService.getProyectoById(id);
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en getProyectoById: " + e.getMessage());
            return null;
        }
    }

    public ProyectoDTO postProyecto(ProyectoDTO proyectoDTO) {
        try {
            return proyectoService.postProyecto(proyectoDTO);
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en postProyecto: " + e.getMessage());
            return null;
        }
    }

    public ProyectoDTO updateProyecto(ProyectoDTO proyectoDTO) {
        try {
            return proyectoService.updateProyecto(proyectoDTO);
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en updateProyecto: " + e.getMessage());
            return null;
        }
    }

    public ProyectoDTO deleteProyecto(ProyectoDTO proyectoDTO) {
        try {
            return proyectoService.deleteProyecto(proyectoDTO);
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en deleteProyecto: " + e.getMessage());
            return null;
        }
    }


    public Optional<ProyectoDTO> getProyectoByIdOptional(ObjectId id) {
        try {
            return Optional.of(proyectoService.getProyectoById(id));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en getProyectoById: " + e.getMessage());
            return Optional.empty();
        }
    }


    //GET ALL PROGRAMADORES BY PROYECTO
    public Set<String> getProgramadorsByProject(ObjectId idProyecto) {
        ProgramadorController p = ProgramadorController.getInstance();
        ProyectoController proyectoController = ProyectoController.getInstance();

        List<ProgramadorDTO> pr = p.getAllProgramadors();
        Set<String> programadores =new HashSet<>();
        ProyectoDTO proyectoDTO = proyectoController.getProyectoById(idProyecto);
        for (ProgramadorDTO programadorDTO:pr
             ) {
            for (Proyecto pro:programadorDTO.getProyectos()
                 ) {
                if(pro.getId().equals(idProyecto)){
                    programadores.add(programadorDTO.getNombre());
                }

            }

        }
        return programadores;

    }

}
