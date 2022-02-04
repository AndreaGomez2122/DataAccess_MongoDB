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
        proyecto.setRepositorio(item.getRepositorio());
        return proyecto;
    }

    @Override
    public ProyectoDTO toDTO(Proyecto item) {
        return ProyectoDTO.builder()
                .id(item.getId())
                .departamento(item.getDepartamento())
                .jefe(item.getJefe())
                .nombre(item.getNombre())
                .fecha_fin(item.getFecha_fin())
                .fecha_inicio(item.getFecha_inicio())
                .presupuesto(item.getPresupuesto())
                .repositorio(item.getRepositorio())
                .build();
                //.tecnologias(item.getTecnologias()).build();
    }
}
