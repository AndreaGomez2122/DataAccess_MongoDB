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
        return ProgramadorDTO.builder()
        .id(item.getId())
        .commits(item.getCommits())
        .issues(item.getIssues())
        .nombre(item.getNombre())
        .contraseña(item.getContraseña())
        .departamento(item.getDepartamento())
        .salario(item.getSalario())
        .tecnologias(item.getTecnologias())
        .fecha_alta(item.getFecha_alta())
        .proyectos(item.getProyectos()).build();
    }
}
