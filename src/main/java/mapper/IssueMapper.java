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
        return IssueDTO.builder()
        .id(item.getId())
        .titulo(item.getTitulo())
        .fecha(item.getFecha())
        .proyecto(item.getProyecto())
        .programadores(item.getProgramadores())
        .texto(item.getTexto()).build();
    }
}
