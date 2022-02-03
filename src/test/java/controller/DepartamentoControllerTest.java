package controller;


import dto.DepartamentoDTO;
import org.bson.types.ObjectId;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DepartamentoControllerTest {

    //dependencia
    @Mock
    DepartamentoService service;

    //System under Test
    @InjectMocks
    DepartamentoController controller;


    @Test
    public void getAllDepartamentos() throws SQLException {
        //given
        List<DepartamentoDTO> list = new ArrayList<>();
      //  DepartamentoDTO dto = new DepartamentoDTO();
      //  dto.setId(new ObjectId());
       // list.add(dto);

        //when
       // Mockito.when(service.getAllDepartamentos()).thenReturn(list);

        //then
      //  assertEquals(dto.getId(), controller.getAllDepartamentos().get(0).getId());
    }

    @Test
    public void getAllDepartamentosException() throws SQLException {
        //given

        //when
       // Mockito.when(service.getAllDepartamentos()).thenThrow(new SQLException());

        //then
       // assertThrows(SQLException.class,()->this.controller.getAllDepartamentos());

    }
}
