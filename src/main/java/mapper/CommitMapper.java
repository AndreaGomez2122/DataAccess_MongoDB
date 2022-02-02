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
        return CommitDTO.builder().
                id(item.getId()).
                proyecto(item.getProyecto()).
                autor(item.getAutor()).
                fecha(item.getFecha()).
                repositorio(item.getRepositorio()).
                texto(item.getTexto()).
                titulo(item.getTitulo()).build();
    }
}
