package mapper;

import dao.Issue;
import dto.IssueDTO;

public class IssueMapper extends BaseMapper<Issue, IssueDTO> {
    @Override
    public Issue fromDTO(IssueDTO item) {
        Issue issue = new Issue();
        issue.setId(item.getId());
        issue.setTitulo(item.getTitulo());
        issue.setFecha(item.getFecha());
        issue.setProyecto(item.getProyecto());
        issue.setProgramadores(item.getProgramadores());
        issue.setTexto(item.getTexto());
        return issue;
    }

    @Override
    public IssueDTO toDTO(Issue item) {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(item.getId());
        issueDTO.setTitulo(item.getTitulo());
        issueDTO.setFecha(item.getFecha());
        issueDTO.setProyecto(item.getProyecto());
        issueDTO.setProgramadores(item.getProgramadores());
        issueDTO.setTexto(item.getTexto());
        return issueDTO;
    }
}
