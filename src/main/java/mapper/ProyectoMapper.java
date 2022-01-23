package mapper;

import dao.Proyecto;
import dto.ProyectoDTO;

public class ProyectoMapper extends BaseMapper<Proyecto, ProyectoDTO> {

    @Override
    public Proyecto fromDTO(ProyectoDTO item) {
        Proyecto proyecto = new Proyecto();
        //proyecto.setters
        return proyecto;
    }

    @Override
    public ProyectoDTO toDTO(Proyecto item) {
        ProyectoDTO proyectoDTO = new ProyectoDTO();
        //proyectoDTO.setters
        return proyectoDTO;
    }
}
