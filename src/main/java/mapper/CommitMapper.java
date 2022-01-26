package mapper;

import dao.Commit;
import dto.CommitDTO;

public class CommitMapper extends BaseMapper<Commit, CommitDTO> {
    @Override
    public Commit fromDTO(CommitDTO item) {
        Commit commit = new Commit();
        commit.setId(item.getId());
        commit.setProyecto(item.getProyecto());
        commit.setAutor(item.getAutor());
        commit.setFecha(item.getFecha());
        commit.setRepositorio(item.getRepositorio());
        commit.setTexto(item.getTexto());
        commit.setTitulo(item.getTitulo());
        return commit;
    }

    @Override
    public CommitDTO toDTO(Commit item) {
        CommitDTO commitDTO = new CommitDTO();
        commitDTO.setId(item.getId());
        commitDTO.setProyecto(item.getProyecto());
        commitDTO.setAutor(item.getAutor());
        commitDTO.setFecha(item.getFecha());
        commitDTO.setRepositorio(item.getRepositorio());
        commitDTO.setTexto(item.getTexto());
        commitDTO.setTitulo(item.getTitulo());
        return commitDTO;
    }
}
