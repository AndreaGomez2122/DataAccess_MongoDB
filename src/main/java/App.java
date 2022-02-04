import autenticacion.Tokenizer;
import controller.DepartamentoController;
import dto.DepartamentoDTO;
import utils.ApplicationProperties;

import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {

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

        accesData.login();


        //Consulta personalizada : obtener todos los proyectos de un departamento en concreto
        DepartamentoController departamentoController = DepartamentoController.getInstance();
        List<DepartamentoDTO> lista = departamentoController.getAllDepartamentos();
        accesData.getProyectsByDepartment(lista.get(0).getId());
    }
}




