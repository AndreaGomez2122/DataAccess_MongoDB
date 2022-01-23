package mapper;

import dao.Departamento;
import dto.DepartamentoDTO;


public class DepartamentoMapper extends BaseMapper<Departamento, DepartamentoDTO> {

    @Override
    public Departamento fromDTO(DepartamentoDTO item) {
        Departamento dep = new Departamento();
        //dep.setters
        dep.setId(item.getId());
        return dep;
    }

    @Override
    public DepartamentoDTO toDTO(Departamento item) {
        DepartamentoDTO depDTO = new DepartamentoDTO();
        //depDTO.setters
        return depDTO;
    }
}
