package service;



import dao.Departamento;
import dto.DepartamentoDTO;
import mapper.DepartamentoMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DepartamentoRepository;

import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoServiceTest {

    //dependencia
    DepartamentoRepository repository;
    DepartamentoMapper mapper;
    //System under Test
    DepartamentoService service;

    @BeforeEach
    void setUp() {
        //Da un error, dice que no puede mockear esta clase
        //Mockito can only mock non-private & non-final classes.
        this.repository = mock(DepartamentoRepository.class);
        this.service= new DepartamentoService(repository);
        this.mapper=new DepartamentoMapper();
    }

    @Test
    void getDepartamentoByIdTest() throws SQLException {
        //given
        DepartamentoDTO depDTO = new DepartamentoDTO();
        ObjectId id =new ObjectId();
        depDTO.setId(id);

        Departamento dep = mapper.fromDTO(depDTO);

        //Espera un Departamento como entrada, no un dto, por eso he tenido que traer el mapper
        when(repository.getById(id)).thenReturn(dep);

        //when
        DepartamentoDTO resultadoDTO= service.getDepartamentoById(id);

        //then
        assertEquals(resultadoDTO.getId(),depDTO.getId());
    }
}
