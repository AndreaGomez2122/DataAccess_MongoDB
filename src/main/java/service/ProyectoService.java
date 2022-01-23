package service;

import dao.Proyecto;
import dto.ProyectoDTO;
import mapper.ProyectoMapper;
import org.bson.types.ObjectId;
import repository.ProyectoRepository;

import java.sql.SQLException;
import java.util.List;

public class ProyectoService extends BaseService<Proyecto, ObjectId, ProyectoRepository> {
    ProyectoMapper mapper = new ProyectoMapper();

    public ProyectoService(ProyectoRepository repository) {
        super(repository);
    }

    public List<ProyectoDTO> getAllProyectos() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public ProyectoDTO getProyectoById(ObjectId id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public ProyectoDTO postProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        Proyecto proyecto = this.save(mapper.fromDTO(proyectoDTO));
        return mapper.toDTO(proyecto);
    }

    public ProyectoDTO updateProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        Proyecto proyecto = this.update(mapper.fromDTO(proyectoDTO));
        return mapper.toDTO(proyecto);
    }

    public ProyectoDTO deleteProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        Proyecto proyecto = this.delete(mapper.fromDTO(proyectoDTO));
        return mapper.toDTO(proyecto);
    }
}
