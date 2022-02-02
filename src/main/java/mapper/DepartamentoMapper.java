package mapper;

import dao.Departamento;
import dto.DepartamentoDTO;


public class DepartamentoMapper extends BaseMapper<Departamento, DepartamentoDTO> {

    @Override
    public Departamento fromDTO(DepartamentoDTO item) {
        Departamento dep = new Departamento();
        dep.setId(item.getId());
        dep.setNombre(item.getNombre());
        dep.setJefe(item.getJefe());
        dep.setHistorico_jefes(item.getHistorico_jefes());
        dep.setProyectos_activos(item.getProyectos_activos());
        dep.setProyectos_terminados(item.getProyectos_terminados());
        dep.setPresupuesto(item.getPresupuesto());

        return dep;
    }

    @Override
    public DepartamentoDTO toDTO(Departamento item) {
        return DepartamentoDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .jefe(item.getJefe())
                .historico_jefes(item.getHistorico_jefes())
                .proyectos_activos(item.getProyectos_activos())
                .proyectos_terminados(item.getProyectos_terminados())
                .presupuesto(item.getPresupuesto()).build();
    }
}
