package service;


import dao.Departamento;
import dao.Programador;
import dao.Proyecto;
import dto.DepartamentoDTO;
import manager.HibernateController;
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



import static org.junit.jupiter.api.Assertions.*;


public class DepartamentoServiceTest {

    //dependencia
    DepartamentoRepository repository;
    DepartamentoMapper mapper;
    //System under Test
    DepartamentoService service;

    @BeforeEach
    public void setUp() {
        this.repository = Mockito.mock(DepartamentoRepository.class);
        this.service = new DepartamentoService(repository);
        this.mapper = new DepartamentoMapper();
    }

    @Test
    public void getDepartamentoByIdTest() throws SQLException {
        //given
        DepartamentoDTO depDTO = new DepartamentoDTO();
        ObjectId id = new ObjectId();
        depDTO.setId(id);
        depDTO.setNombre("nombre proyecto");
        depDTO.setJefe(new Programador());
        depDTO.setPresupuesto(2000);
        depDTO.setProyectos_terminados(null);
        depDTO.setProyectos_activos(null);
        depDTO.setHistorico_jefes(null);
        Departamento dep = mapper.fromDTO(depDTO);


        //Espera un Departamento como entrada, no un dto, por eso he tenido que traer el mapper
        Mockito.when(repository.getById(id)).thenReturn(dep);

        //when
        DepartamentoDTO resultadoDTO = service.getDepartamentoById(id);

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }
}
