package mapper;

import dao.Issue;
import dto.IssueDTO;

public class IssueMapper extends BaseMapper<Issue, IssueDTO>{
    @Override
    public Issue fromDTO(IssueDTO item) {
        Issue issue = new Issue();
        //issue.setters
        return issue;
    }

    @Override
    public IssueDTO toDTO(Issue item) {
        IssueDTO issueDTO = new IssueDTO();
        //issueDTO.setters
        return issueDTO;
    }
}
