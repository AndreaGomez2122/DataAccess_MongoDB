package service;


import dao.Departamento;
import dao.Programador;
import dto.DepartamentoDTO;
import manager.HibernateController;
import mapper.DepartamentoMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.DepartamentoRepository;

import java.sql.SQLException;



import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartamentoServiceTest {

    //dependencia

    @Mock
    DepartamentoRepository repository;
    //HibernateController controller;
    DepartamentoMapper mapper;
    DepartamentoDTO depDTO;
    //System under Test
    @InjectMocks
    DepartamentoService service;

    @BeforeEach
    public void setUp() {
       // this.service = new DepartamentoService(repository);
        this.mapper = new DepartamentoMapper();

        depDTO = new DepartamentoDTO();
        ObjectId id = new ObjectId();
        depDTO.setId(id);
        depDTO.setNombre("nombre proyecto");
        depDTO.setJefe(new Programador());
        depDTO.setPresupuesto(2000);
        depDTO.setProyectos_terminados(null);
        depDTO.setProyectos_activos(null);
        depDTO.setHistorico_jefes(null);
    }

    @Test
    public void getDepartamentoByIdTest() throws SQLException {
        //given
        Departamento dep = mapper.fromDTO(depDTO);
            //Espera un Departamento como entrada, no un dto, por eso he tenido que traer el mapper
        Mockito.when(repository.getById(depDTO.getId())).thenReturn(dep);

        //when
        DepartamentoDTO resultadoDTO = service.getDepartamentoById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }
}
