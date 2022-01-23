import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dao.*;
import manager.HibernateController;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

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
        //removeData();

        HibernateController hc = HibernateController.getInstance();

        hc.open();


        // Departamentos
        System.out.println("Insertando Departamentos de Ejemplo");

        hc.getTransaction().begin();

        Departamento d1 = new Departamento("Recursos humanos",2000000);
        Departamento d2 = new Departamento("Diseño gráfico",50000);
        hc.getManager().persist(d1);
        hc.getManager().persist(d2);

        hc.getTransaction().commit();

        // Programadores
        System.out.println("Insertando Programadores de Ejemplo");
        hc.getTransaction().begin();
        Programador p1 = new Programador("Andrea", new Date("25-10-2020"), d1,new HashSet<Tecnologia>(Arrays.asList(Tecnologia.JAVA,Tecnologia.ANGULAR)),2000,"1234");
        Programador p2 = new Programador("Jose", new Date("25-10-2020"), d2,new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS,Tecnologia.ANGULAR)),1500,"1234");

        hc.getManager().persist(p1);
        hc.getManager().persist(p2);

        hc.getTransaction().commit();


        // Proyectos
        System.out.println("Insertando Proyectos de Ejemplo");
//    public Proyecto(String nombre, Programador jefe, double presupuesto, Date fecha_inicio, Date fecha_fin, Set<Tecnologia>tecnologias) {
        hc.getTransaction().begin();
        Proyecto pr1 = new Proyecto("Página Web Bankia",p1,500000, new Date("20-01-2021"), new Date("20-01-2022"),new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS,Tecnologia.ANGULAR)));
        hc.getManager().persist(pr1);
        hc.getTransaction().commit();

        // Repositorios
        System.out.println("Insertando Repositorios de Ejemplo");
        //public Repositorio(String nombre, Date fecha_creacion, Proyecto proyecto){        hc.getTransaction().begin();
        Repositorio r1 = new Repositorio("Bankia web repository",new Date("20-01-2021"),pr1);
        hc.getManager().persist(pr1);
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
        MongoDatabase blogDB = mongoClient.getDatabase("test");
        blogDB.drop(); // Si queremos borrar toda la base de datos
    }
}
