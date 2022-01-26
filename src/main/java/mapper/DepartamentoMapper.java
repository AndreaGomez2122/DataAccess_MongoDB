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
        // dep.setHistorico_jefes();
        // dep.setProyectos_activos();
        // dep.setProyectos_terminados();
        dep.setPresupuesto(item.getPresupuesto());

        return dep;
    }

    @Override
    public DepartamentoDTO toDTO(Departamento item) {
        DepartamentoDTO depDTO = new DepartamentoDTO();
        //depDTO.setters
        return depDTO;
    }
}
