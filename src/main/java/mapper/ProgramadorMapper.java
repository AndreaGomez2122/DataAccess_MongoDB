package mapper;

import dao.Programador;
import dto.ProgramadorDTO;

public class ProgramadorMapper extends BaseMapper<Programador, ProgramadorDTO> {

    @Override
    public Programador fromDTO(ProgramadorDTO item) {
        Programador prog = new Programador();
        //prog.setters
        return prog;
    }

    @Override
    public ProgramadorDTO toDTO(Programador item) {
        ProgramadorDTO progDTO = new ProgramadorDTO();
        //progDTO.setters
        return progDTO;
    }
}
