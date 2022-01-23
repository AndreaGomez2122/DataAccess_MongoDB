package mapper;

import dao.Repositorio;
import dto.RepositorioDTO;

public class RepositorioMapper extends BaseMapper<Repositorio, RepositorioDTO> {

    @Override
    public Repositorio fromDTO(RepositorioDTO item) {
        Repositorio repositorio = new Repositorio();
        //repositorio.setters
        return repositorio;
    }

    @Override
    public RepositorioDTO toDTO(Repositorio item) {
        RepositorioDTO repositorioDTO = new RepositorioDTO();
        //repositorioDTO.setters
        return repositorioDTO;
    }
}
