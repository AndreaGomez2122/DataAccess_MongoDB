package mapper;

import dao.Repositorio;
import dto.RepositorioDTO;

public class RepositorioMapper extends BaseMapper<Repositorio, RepositorioDTO> {

    @Override
    public Repositorio fromDTO(RepositorioDTO item) {
        Repositorio repositorio = new Repositorio();
        repositorio.setId(item.getId());
        repositorio.setCommits(item.getCommits());
        repositorio.setNombre(item.getNombre());
        repositorio.setIssues(item.getIssues());
        repositorio.setProyecto(item.getProyecto());
        repositorio.setFecha_creacion(item.getFecha_creacion());
        return repositorio;
    }

    @Override
    public RepositorioDTO toDTO(Repositorio item) {
        return RepositorioDTO.builder()
        .id(item.getId())
        .commits(item.getCommits())
        .nombre(item.getNombre())
        .issues(item.getIssues())
        .proyecto(item.getProyecto())
        .fecha_creacion(item.getFecha_creacion()).build();
    }
}
