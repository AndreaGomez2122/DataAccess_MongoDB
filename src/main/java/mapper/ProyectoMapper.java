package mapper;

import dao.Proyecto;
import dto.ProyectoDTO;

public class ProyectoMapper extends BaseMapper<Proyecto, ProyectoDTO> {

    @Override
    public Proyecto fromDTO(ProyectoDTO item) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(item.getId());
        proyecto.setDepartamento(item.getDepartamento());
        proyecto.setJefe(item.getJefe());
        proyecto.setNombre(item.getNombre());
        proyecto.setFecha_fin(item.getFecha_fin());
        proyecto.setFecha_inicio(item.getFecha_inicio());
        proyecto.setPresupuesto(item.getPresupuesto());
        proyecto.setTecnologias(item.getTecnologias());
        return proyecto;
    }

    @Override
    public ProyectoDTO toDTO(Proyecto item) {
        ProyectoDTO proyectoDTO = new ProyectoDTO();
        proyectoDTO.setId(item.getId());
        proyectoDTO.setDepartamento(item.getDepartamento());
        proyectoDTO.setJefe(item.getJefe());
        proyectoDTO.setNombre(item.getNombre());
        proyectoDTO.setFecha_fin(item.getFecha_fin());
        proyectoDTO.setFecha_inicio(item.getFecha_inicio());
        proyectoDTO.setPresupuesto(item.getPresupuesto());
        proyectoDTO.setTecnologias(item.getTecnologias());
        return proyectoDTO;
    }
}
