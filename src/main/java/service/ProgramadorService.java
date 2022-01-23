package service;

import dao.Programador;
import dto.ProgramadorDTO;
import mapper.ProgramadorMapper;
import org.bson.types.ObjectId;
import repository.ProgramadorRepository;

import java.sql.SQLException;
import java.util.List;

public class ProgramadorService extends BaseService<Programador, ObjectId, ProgramadorRepository> {
    ProgramadorMapper mapper = new ProgramadorMapper();

    public ProgramadorService(ProgramadorRepository repository) {
        super(repository);
    }

    public List<ProgramadorDTO> getAllProgramadores() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public ProgramadorDTO getProgramadorById(ObjectId id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public ProgramadorDTO postProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador prog = this.save(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(prog);
    }

    public ProgramadorDTO updateProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador prog = this.update(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(prog);
    }

    public ProgramadorDTO deleteProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador prog = this.delete(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(prog);
    }
}
