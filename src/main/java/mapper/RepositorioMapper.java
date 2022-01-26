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
        RepositorioDTO repositorioDTO = new RepositorioDTO();
        repositorioDTO.setId(item.getId());
        repositorioDTO.setCommits(item.getCommits());
        repositorioDTO.setNombre(item.getNombre());
        repositorioDTO.setIssues(item.getIssues());
        repositorioDTO.setProyecto(item.getProyecto());
        repositorioDTO.setFecha_creacion(item.getFecha_creacion());
        return repositorioDTO;
    }
}
