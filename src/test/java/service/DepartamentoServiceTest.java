package service;


import dao.Departamento;
import dto.DepartamentoDTO;
import mapper.DepartamentoMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.DepartamentoRepository;

import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartamentoServiceTest {

    //dependencia
    @Mock
    DepartamentoRepository repository;

    DepartamentoMapper mapper;
    //System under Test
    DepartamentoService service;

    @BeforeEach
    void setUp() {
        this.service = new DepartamentoService(repository);
        this.mapper = new DepartamentoMapper();
    }

    @Test
    void getDepartamentoByIdTest() throws SQLException {
        //given
        DepartamentoDTO depDTO = new DepartamentoDTO();
        ObjectId id = new ObjectId();
        depDTO.setId(id);
        Departamento dep = mapper.fromDTO(depDTO);


        //Espera un Departamento como entrada, no un dto, por eso he tenido que traer el mapper
        Mockito.when(repository.getById(id)).thenReturn(dep);

        //when
        DepartamentoDTO resultadoDTO = service.getDepartamentoById(id);

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }
}
