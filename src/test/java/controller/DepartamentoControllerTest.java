package controller;


import dao.Departamento;
import dao.Programador;
import dto.DepartamentoDTO;
import mapper.DepartamentoMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import service.DepartamentoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class DepartamentoControllerTest {

    //dependencia
    @Mock
    DepartamentoService service;

    DepartamentoMapper mapper;
    DepartamentoDTO depDTO;
    Departamento dep;

    //System under Test
    @InjectMocks
    DepartamentoController controller;

    @BeforeEach
    public void setUp() {
        this.mapper = new DepartamentoMapper();
        ObjectId id = new ObjectId();
        depDTO = DepartamentoDTO.builder()
                .id(id)
                .nombre("nombre proyecto")
                .jefe(new Programador())
                .presupuesto(2000)
                .build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    public void getAllDepartamentos() throws SQLException {
        //given
        List<Departamento> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(service.findAll()).thenReturn(list);

        //when
        List<DepartamentoDTO> listResultado = controller.getAllDepartamentos();

        //then
        assertEquals(list.get(0).getId(), listResultado.get(0).getId());

    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(service.getById(depDTO.getId())).thenReturn(dep);

        //when
        DepartamentoDTO resultadoDTO = controller.getDepartamentoById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(service.save(dep)).thenReturn(dep);

        //when
        DepartamentoDTO resultado = controller.postDepartamento(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(service.update(dep)).thenReturn(dep);

        //when
        DepartamentoDTO resultado = controller.updateDepartamento(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(service.delete(dep)).thenReturn(dep);

        //when
        DepartamentoDTO resultado = service.deleteDepartamento(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }
}
