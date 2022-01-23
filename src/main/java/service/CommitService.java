package service;


import dao.Commit;
import dto.CommitDTO;
import mapper.CommitMapper;
import org.bson.types.ObjectId;
import repository.CommitRepository;
import java.sql.SQLException;
import java.util.List;


public class CommitService  extends BaseService<Commit, ObjectId, CommitRepository>{
    CommitMapper mapper = new CommitMapper();

    public CommitService(CommitRepository repository) {
        super(repository);
    }

    public List<CommitDTO> getAllCommits() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public CommitDTO getCommitById(ObjectId id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public CommitDTO postCommit(CommitDTO commitDTO) throws SQLException {
        Commit dep = this.save(mapper.fromDTO(commitDTO));
        return mapper.toDTO(dep);
    }

    public CommitDTO updateCommit(CommitDTO commitDTO) throws SQLException {
        Commit dep = this.update(mapper.fromDTO(commitDTO));
        return mapper.toDTO(dep);
    }

    public CommitDTO deleteCommit(CommitDTO commitDTO) throws SQLException {
        Commit dep = this.delete(mapper.fromDTO(commitDTO));
        return mapper.toDTO(dep);
    }
}
