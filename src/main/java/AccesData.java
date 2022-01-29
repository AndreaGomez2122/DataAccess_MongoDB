import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import controller.DepartamentoController;
import controller.ProyectoController;
import dao.*;
import dto.DepartamentoDTO;
import dto.ProyectoDTO;
import manager.HibernateController;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

public class AccesData {

    Programador p1;
    Programador p2;

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
        System.out.println("Insertando Departamentos de Ejemplo");
        hc.getTransaction().begin();
        Departamento d1 = new Departamento("Recursos humanos", 2000000);
        Departamento d2 = new Departamento("Diseño gráfico", 50000);
        hc.getManager().persist(d1);
        hc.getManager().persist(d2);
        hc.getTransaction().commit();

        // Programadores
        System.out.println("Insertando Programadores de Ejemplo");
        hc.getTransaction().begin();
        p1 = new Programador("Andrea", new Date(2020, 10, 25), d1, new HashSet<Tecnologia>(Arrays.asList(Tecnologia.JAVA, Tecnologia.ANGULAR)), 2000, "1234");
        p2 = new Programador("Jose", new Date(2020, 10, 20), d2, new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), 1500, "1234");
        hc.getManager().persist(p1);
        hc.getManager().persist(p2);
        hc.getTransaction().commit();

        // Proyectos
        System.out.println("Insertando Proyectos de Ejemplo");
        hc.getTransaction().begin();
        Proyecto pr1 = new Proyecto("Página Web Bankia", p1, 500000, new Date(2021, 01, 20), new Date(2022, 01, 20), new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), d2);
        Proyecto pr2 = new Proyecto("Página Web Santander", p1, 500000, new Date(2021, 01, 20), new Date(2022, 01, 20), new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), d1);
        Proyecto pr3 = new Proyecto("Aplicacion firma", p2, 500000, new Date(2021, 01, 20), new Date(2022, 01, 20), new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), d1);
        hc.getManager().persist(pr1);
        hc.getManager().persist(pr2);
        hc.getManager().persist(pr3);
        hc.getTransaction().commit();

        // Repositorios
        System.out.println("Insertando Repositorios de Ejemplo");
        hc.getTransaction().begin();
        Repositorio r1 = new Repositorio("Bankia web repository", new Date(2021, 01, 20), pr1);
        hc.getManager().persist(r1);
        hc.getTransaction().commit();

        // Commit
        System.out.println("Insertando Commits de Ejemplo");
        hc.getTransaction().begin();
        Commit c1 = new Commit("Actualizacion service", "Se actualizan todas las clases service", new Date(2021, 01, 20), r1, pr1, p1);
        hc.getManager().persist(c1);
        hc.getTransaction().commit();


        // Issue
        System.out.println("Insertando Issues de Ejemplo");
        hc.getTransaction().begin();
        Issue i1 = new Issue("StackOverFlow", "Hay un bucle infinito en la clase AccesDataRepository", new Date(2021, 01, 20), pr1, r1, Estado.PENDIENTE);
        hc.getManager().persist(i1);
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
        MongoDatabase testDB = mongoClient.getDatabase("test");
        testDB.drop(); // Si queremos borrar toda la base de datos
    }


    public void departamentos() {
        System.out.println("INICIO DEPARTAMENTOS");

        DepartamentoController departamentoController = DepartamentoController.getInstance();

        // Iniciamos los departamentos

        System.out.println("GET Todos los departamentos");
        List<DepartamentoDTO> lista = departamentoController.getAllDepartamentos();
        System.out.println(lista);

        System.out.println("GET Departamento con ID: " + lista.get(1).getId()); // Mira en el explorador a ver que categoría hay
        System.out.println(departamentoController.getDepartamentoById(lista.get(1).getId()));


        DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                .nombre("Nuevo departamento")
                .jefe(p1)
                .presupuesto(250000)
                .build();
        departamentoDTO = departamentoController.postDepartamento(departamentoDTO);
        System.out.println(departamentoDTO);


        DepartamentoDTO departamentoDTO1 = DepartamentoDTO.builder()
                .nombre("Nuevo departamento 1")
                .jefe(p1)
                .presupuesto(250000)
                .build();
        departamentoDTO1 = departamentoController.postDepartamento(departamentoDTO1);
        System.out.println(departamentoDTO1);



        //No actualiza el departamento
        System.out.println("UPDATE Departamento con ID:" + departamentoDTO1.getId());
        Optional<DepartamentoDTO> optionalDepartamentoDTO = departamentoController.getDepartamentoByIdOptional(departamentoDTO1.getId());
        if (optionalDepartamentoDTO.isPresent()) {
            optionalDepartamentoDTO.get().setNombre("Departamento actualizado");
            System.out.println(departamentoController.updateDepartamento(optionalDepartamentoDTO.get()));
        }

        //No llega a borrarlo. También hay un stackoverflow.

        System.out.println("DELETE Departamento con ID: " + departamentoDTO.getId());
        optionalDepartamentoDTO = departamentoController.getDepartamentoByIdOptional(departamentoDTO.getId());
        if (optionalDepartamentoDTO.isPresent()) {
            System.out.println(optionalDepartamentoDTO.get().toString());
            System.out.println(departamentoController.deleteDepartamento(optionalDepartamentoDTO.get()));
        }


        System.out.println("FIN DEPARTAMENTOS");
    }


    public void proyectos() {
        System.out.println("INICIO PROYECTOS");

        ProyectoController proyectoController = ProyectoController.getInstance();

        System.out.println("GET Todos los proyectos");
        List<ProyectoDTO> lista = proyectoController.getAllProyectos();
        System.out.println(lista);

        System.out.println("GET Proyecto con ID: " + lista.get(1).getId());
        System.out.println(proyectoController.getProyectoById(lista.get(1).getId()));


        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .nombre("Nuevo proyecto")
                .jefe(p1)
                .presupuesto(250000)
                .build();
        proyectoDTO = proyectoController.postProyecto(proyectoDTO);
        System.out.println(proyectoDTO);


        ProyectoDTO proyectoDTO1 = ProyectoDTO.builder()
                .nombre("Nuevo proyecto 1")
                .jefe(p1)
                .presupuesto(250000)
                .build();
        proyectoDTO1 = proyectoController.postProyecto(proyectoDTO1);
        System.out.println(proyectoDTO1);




        System.out.println("UPDATE Proyecto con ID:" + proyectoDTO1.getId());
        Optional<ProyectoDTO> optionalProyectoDTO = proyectoController.getProyectoByIdOptional(proyectoDTO1.getId());
        if (optionalProyectoDTO.isPresent()) {
            optionalProyectoDTO.get().setNombre("Proyecto actualizado");
            System.out.println(proyectoController.updateProyecto(optionalProyectoDTO.get()));
        }


        System.out.println("DELETE Proyecto con ID: " + proyectoDTO.getId());
        optionalProyectoDTO = proyectoController.getProyectoByIdOptional(proyectoDTO.getId());
        if (optionalProyectoDTO.isPresent()) {
            System.out.println(optionalProyectoDTO.get().toString());
            System.out.println(proyectoController.deleteProyecto(optionalProyectoDTO.get()));
        }


        System.out.println("FIN PROYECTOS");
    }

















}
