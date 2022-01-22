import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dao.Departamento;
import manager.HibernateController;

public class AccesData {


    private static AccesData instance;

    private AccesData() {
    }

    public static AccesData getInstance() {
        if (instance == null) {
            instance = new AccesData();
        }
        return instance;
    }

    public void initDataBase() {
        // Borramos los datos previos
        removeData();

        HibernateController hc = HibernateController.getInstance();
        hc.open();
        // Departamentos
        hc.getTransaction().begin();

        System.out.println("Insertando Departamentos de Ejemplo");

        hc.getTransaction().begin();
        Post p1 = new Post("Post num 1", "http://post1.com", "Este es el post num 1", u1, c1); //10


        hc.getManager().persist(p1);


        hc.getTransaction().commit();


        // Usuarios
        System.out.println("Insertando Usuarios de Ejemplo");

        hc.getTransaction().begin();
        User u1 = new User("Pepe Perez","pepe@pepe.es","1234"); // 5
        User u2 = new User("Ana Anaya","ana@anaya.es","1234"); // 6
        User u3 = new User("Paco Perez","paco@perez.es","1234"); // 7
        User u4 = new User("Son Goku","goku@dragonball.es","1234"); // 8
        User u5 = new User("Chuck Norris","chuck@norris.es","1234");  // 9

        hc.getManager().persist(u1);
        hc.getManager().persist(u2);
        hc.getManager().persist(u3);
        hc.getManager().persist(u4);
        hc.getManager().persist(u5);

        hc.getTransaction().commit();


        // Comentarios
        System.out.println("Insertando Comentarios de Ejemplo");

        hc.getTransaction().begin();
        Comment cm1 = new Comment("Comentario 01,", u1, p1);//15
        Comment cm2 = new Comment("Comentario 02,", u2, p2);//16
        Comment cm3 = new Comment("Comentario 03,", u3, p2);//17
        Comment cm4 = new Comment("Comentario 04,", u1, p3);//18
        Comment cm5 = new Comment("Comentario 05,", u4, p4);//19
        Comment cm6 = new Comment("Comentario 06,", u1, p3);//20
        Comment cm7 = new Comment("Comentario 07,", u4, p4);//21
        Comment cm8 = new Comment("Comentario 08,", u2, p3);//22

        hc.getManager().persist(cm1);
        hc.getManager().persist(cm2);
        hc.getManager().persist(cm3);
        hc.getManager().persist(cm4);
        hc.getManager().persist(cm5);
        hc.getManager().persist(cm6);
        hc.getManager().persist(cm7);
        hc.getManager().persist(cm8);

        hc.getTransaction().commit();

        hc.close();

    }

    private void removeData() {
        // Usando Hibernate
//        transactionManager.begin();
//        // Collection == name of the class being saved ⮧
//        entityManager.createNativeQuery("db.GameCharacter.drop()").executeUpdate();
//        transactionManager.commit();
        // Lo sutyo sería un controlador
        ConnectionString connectionString = new ConnectionString("mongodb://mongoadmin:mongopass@localhost/test?authSource=admin");
        MongoClient mongoClient = MongoClients.create(connectionString);

        // Obtenemos la base de datos que necesitamos
        MongoDatabase blogDB = mongoClient.getDatabase("accesData");
        blogDB.drop(); // Si queremos borrar toda la base de datos
    }
}
