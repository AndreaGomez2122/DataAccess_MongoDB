package mapper;

import dao.Commit;
import dto.CommitDTO;

public class CommitMapper extends BaseMapper<Commit, CommitDTO>{
    @Override
    public Commit fromDTO(CommitDTO item) {
        Commit commit = new Commit();
        //commit.setters
        return commit;
    }

    @Override
    public CommitDTO toDTO(Commit item) {
        CommitDTO commitDTO = new CommitDTO();
        //commitDTO.setters
        return commitDTO;
    }
}
