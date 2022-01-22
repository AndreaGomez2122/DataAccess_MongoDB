import utils.ApplicationProperties;

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
       // accesData.Categories();

        // Proyectos
       // accesData.Users();

        // Programadores
       // accesData.Login();

        // Logins
      // accesData.Posts();

        // Repositorios
        //accesData.Comments();

        // Issues
        //accesData.Comments();

        // Commits
       // accesData.Comments();
    }
    }




