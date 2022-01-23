package service;

import dao.Departamento;
import dto.DepartamentoDTO;
import mapper.DepartamentoMapper;
import org.bson.types.ObjectId;
import repository.DepartamentoRepository;

import java.sql.SQLException;
import java.util.List;

public class DepartamentoService extends BaseService<Departamento, ObjectId, DepartamentoRepository> {
    DepartamentoMapper mapper = new DepartamentoMapper();

    public DepartamentoService(DepartamentoRepository repository) {
        super(repository);
    }

    public List<DepartamentoDTO> getAllDepartamentos() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public DepartamentoDTO getDepartamentoById(ObjectId id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public DepartamentoDTO postDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        Departamento dep = this.save(mapper.fromDTO(departamentoDTO));
        return mapper.toDTO(dep);
    }

    public DepartamentoDTO updateDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        Departamento dep = this.update(mapper.fromDTO(departamentoDTO));
        return mapper.toDTO(dep);
    }

    public DepartamentoDTO deleteDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        Departamento dep = this.delete(mapper.fromDTO(departamentoDTO));
        return mapper.toDTO(dep);
    }
}
