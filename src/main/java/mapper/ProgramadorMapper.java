package mapper;

import dao.Programador;
import dto.ProgramadorDTO;

public class ProgramadorMapper extends BaseMapper<Programador, ProgramadorDTO> {

    @Override
    public Programador fromDTO(ProgramadorDTO item) {
        Programador prog = new Programador();
        prog.setId(item.getId());
        prog.setCommits(item.getCommits());
        prog.setIssues(item.getIssues());
        prog.setNombre(item.getNombre());
        prog.setContraseña(item.getContraseña());
        prog.setDepartamento(item.getDepartamento());
        prog.setSalario(item.getSalario());
        prog.setTecnologias(item.getTecnologias());
        prog.setFecha_alta(item.getFecha_alta());
        prog.setProyectos(item.getProyectos());
        return prog;
    }

    @Override
    public ProgramadorDTO toDTO(Programador item) {
        ProgramadorDTO progDTO = new ProgramadorDTO();
        progDTO.setId(item.getId());
        progDTO.setCommits(item.getCommits());
        progDTO.setIssues(item.getIssues());
        progDTO.setNombre(item.getNombre());
        progDTO.setContraseña(item.getContraseña());
        progDTO.setDepartamento(item.getDepartamento());
        progDTO.setSalario(item.getSalario());
        progDTO.setTecnologias(item.getTecnologias());
        progDTO.setFecha_alta(item.getFecha_alta());
        progDTO.setProyectos(item.getProyectos());
        return progDTO;
    }
}
