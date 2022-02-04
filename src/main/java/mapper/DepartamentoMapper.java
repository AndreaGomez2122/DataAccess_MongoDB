package mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.Departamento;
import dto.DepartamentoDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DepartamentoMapper extends BaseMapper<Departamento, DepartamentoDTO> {

    @Override
    public Departamento fromDTO(DepartamentoDTO item) {
        Departamento dep = new Departamento();
        dep.setId(item.getId());
        dep.setNombre(item.getNombre());
        dep.setJefe(item.getJefe());
        dep.setHistorico_jefes(item.getHistorico_jefes());
        dep.setProyectos_activos(item.getProyectos_activos());
        dep.setProyectos_terminados(item.getProyectos_terminados());
        dep.setPresupuesto(item.getPresupuesto());

        return dep;
    }

    @Override
    public DepartamentoDTO toDTO(Departamento item) {
        return DepartamentoDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .jefe(item.getJefe())
                .historico_jefes(item.getHistorico_jefes())
                .proyectos_activos(item.getProyectos_activos())
                .proyectos_terminados(item.getProyectos_terminados())
                .presupuesto(item.getPresupuesto()).build();
    }


    public String toJson(List<DepartamentoDTO> departamentos) throws IOException {

        Gson g = new GsonBuilder().setPrettyPrinting().create();
        String json = g.toJson(departamentos);
        //Creamos el archivo como se pide en la práctica
        FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+ File.separator+ "src" + File.separator + "main"+
                File.separator+"java" + File.separator + "AllDepartments.json");
        fileWriter.write(json);
        fileWriter.flush();
        fileWriter.close();
        return json;

    }
}
