package service;


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
import repository.DepartamentoRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartamentoServiceTest {
    DepartamentoMapper mapper;
    DepartamentoDTO depDTO;
    Departamento dep;

    //Dependencias
    @Mock
    DepartamentoRepository repository;

    //System under Test
    @InjectMocks
    DepartamentoService service;

    @BeforeEach
    public void setUp() {
        // this.service = new DepartamentoService(repository);
        this.mapper = new DepartamentoMapper();
        ObjectId id = new ObjectId();
        depDTO = DepartamentoDTO.builder()
                .id(id)
                .nombre("nombre proyecto")
                .jefe(new Programador())
                .presupuesto(2000)
                .proyectos_terminados(null)
                .proyectos_activos(null)
                .historico_jefes(null).build();
        dep = mapper.fromDTO(depDTO);
    }


    @Test
    void getAllTest() throws SQLException {

        //given
        List<Departamento> list = new ArrayList<>();
        list.add(dep);
        Mockito.when(repository.findAll()).thenReturn(list);

        //when
        List<Departamento> listResultado = service.findAll();

        //then
        assertEquals(list.get(0), listResultado.get(0));
    }

    @Test
    public void getByIdTest() throws SQLException {
        //given
        Mockito.when(repository.getById(depDTO.getId())).thenReturn(dep);

        //when
        DepartamentoDTO resultadoDTO = service.getDepartamentoById(depDTO.getId());

        //then
        assertEquals(resultadoDTO.getId(), depDTO.getId());
    }

    @Test
    void postTest() throws SQLException {
        //given
        Mockito.when(repository.save(dep)).thenReturn(dep);

        //when
        DepartamentoDTO resultado = service.postDepartamento(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void updateTest() throws SQLException {
        //given
        Mockito.when(repository.update(dep)).thenReturn(dep);

        //when
        DepartamentoDTO resultado = service.updateDepartamento(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }

    @Test
    void deleteTest() throws SQLException {
        //given
        Mockito.when(repository.delete(dep)).thenReturn(dep);

        //when
        DepartamentoDTO resultado= service.deleteDepartamento(depDTO);

        //then
        assertEquals(resultado.getId(), dep.getId());
    }


}
