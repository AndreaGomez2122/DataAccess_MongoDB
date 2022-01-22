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

        // Categorías
        accesData.Categories();

        // Usuarios
        accesData.Users();

        // Login
        accesData.Login();

        // Posts
        accesData.Posts();

        // Comments
        accesData.Comments();
    }
    }



}
