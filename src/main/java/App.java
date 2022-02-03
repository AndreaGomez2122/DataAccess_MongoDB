import controller.DepartamentoController;
import dto.DepartamentoDTO;
import utils.ApplicationProperties;

import java.util.List;

public class App {

    public static void main(String[] args) {

        ApplicationProperties properties = new ApplicationProperties();
        System.out.println("Bienvenid@s a " +
                properties.readProperty("app.title") + " "
                + properties.readProperty("app.version") + " de " +
                properties.readProperty("app.curso"));

        AccesData accesData = AccesData.getInstance();

        if (properties.readProperty("database.init").equals("true"))
            accesData.initDataBase();

        // Departamentos
        accesData.departamentos();

        // Proyectos
        accesData.proyectos();

        // Programadores
        accesData.programadores();

        // Repositorios
        accesData.repositorios();

        // Commit
        accesData.commits();

        // Issues
        accesData.issues();

        DepartamentoController departamentoController = DepartamentoController.getInstance();
        List<DepartamentoDTO> lista = departamentoController.getAllDepartamentos();
        accesData.getProyectsByDepartment(((DepartamentoDTO)lista.get(0)).getId());
    }
}




