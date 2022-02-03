import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import controller.*;
import dao.*;
import dto.*;
import manager.HibernateController;
import org.bson.types.ObjectId;

import java.util.*;

public class AccesData {

    Departamento d1;
    Departamento d2;
    Programador p1;
    Programador p2;
    Proyecto pr1;
    Proyecto pr2;
    Proyecto pr3;
    Repositorio r1;
    Commit c1;
    Issue i1;


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
        d1 = new Departamento("Recursos humanos", 2000000);
        d2 = new Departamento("Diseño gráfico", 50000);
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
        pr1 = new Proyecto("Página Web Bankia", p1, 500000, new Date(2021, 01, 20), new Date(2022, 01, 20), new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), d2);
        pr2 = new Proyecto("Página Web Santander", p1, 500000, new Date(2021, 01, 20), new Date(2022, 01, 20), new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), d1);
        pr3 = new Proyecto("Aplicacion firma", p2, 500000, new Date(2021, 01, 20), new Date(2022, 01, 20), new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), d1);
        hc.getManager().persist(pr1);
        hc.getManager().persist(pr2);
        hc.getManager().persist(pr3);
        hc.getTransaction().commit();

        // Repositorios
        System.out.println("Insertando Repositorios de Ejemplo");
        hc.getTransaction().begin();
        r1 = new Repositorio("Bankia web repository", new Date(2021, 01, 20), pr1);
        hc.getManager().persist(r1);
        hc.getTransaction().commit();

        // Commit
        System.out.println("Insertando Commits de Ejemplo");
        hc.getTransaction().begin();
        c1 = new Commit("Actualizacion service", "Se actualizan todas las clases service", new Date(2021, 01, 20), r1, pr1, p1);
        hc.getManager().persist(c1);
        hc.getTransaction().commit();


        // Issue
        System.out.println("Insertando Issues de Ejemplo");
        hc.getTransaction().begin();
        i1 = new Issue("StackOverFlow", "Hay un bucle infinito en la clase AccesDataRepository", new Date(2021, 01, 20), pr1, r1, Estado.PENDIENTE);
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


    /****************************************************************************************************************************
     ****************************************************************************************************************************
     ******************************************        OPERACIONES CRUD      ***************************************************
     ****************************************************************************************************************************
     ****************************************************************************************************************************/


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
                .proyectos_activos(new HashSet<Proyecto>(Arrays.asList(pr1)))
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


    public void programadores() {
        System.out.println("INICIO PROGRAMADORES");

        ProgramadorController programadorController = ProgramadorController.getInstance();

        System.out.println("GET Todos los programadores");
        List<ProgramadorDTO> lista = programadorController.getAllProgramadors();
        System.out.println(lista);

        System.out.println("GET Programador con ID: " + lista.get(1).getId());
        System.out.println(programadorController.getProgramadorById(lista.get(1).getId()));


        ProgramadorDTO programadorDTO = ProgramadorDTO.builder()
                .nombre("Nuevo programador")
                .fecha_alta(new Date(2010, 10, 22))
                .tecnologias(new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)))
                .salario(4000)
                .contraseña("1234")
                .build();
        programadorDTO = programadorController.postProgramador(programadorDTO);
        System.out.println(programadorDTO);


        ProgramadorDTO programadorDTO1 = ProgramadorDTO.builder()
                .nombre("Nuevo programador 1")
                .fecha_alta(new Date(2010, 10, 22))
                .tecnologias(new HashSet<Tecnologia>(Arrays.asList(Tecnologia.JAVA, Tecnologia.ANGULAR)))
                .salario(2000)
                .contraseña("1234")
                .build();
        programadorDTO1 = programadorController.postProgramador(programadorDTO1);
        System.out.println(programadorDTO1);


        System.out.println("UPDATE programador con ID:" + programadorDTO1.getId());
        Optional<ProgramadorDTO> optionalProgramadorDTO = programadorController.getProgramadorByIdOptional(programadorDTO1.getId());
        if (optionalProgramadorDTO.isPresent()) {
            optionalProgramadorDTO.get().setNombre("Programador actualizado");
            System.out.println(programadorController.updateProgramador(optionalProgramadorDTO.get()));
        }


        System.out.println("DELETE Programador con ID: " + programadorDTO.getId());
        optionalProgramadorDTO = programadorController.getProgramadorByIdOptional(programadorDTO.getId());
        if (optionalProgramadorDTO.isPresent()) {
            System.out.println(optionalProgramadorDTO.get().toString());
            System.out.println(programadorController.deleteProgramador(optionalProgramadorDTO.get()));
        }


        System.out.println("FIN PROGRAMADORES");
    }


    public void repositorios() {
        System.out.println("INICIO REPOSITORIOS");

        RepositorioController repositorioController = RepositorioController.getInstance();

        System.out.println("GET Todos los repositorios");
        List<RepositorioDTO> lista = repositorioController.getAllRepositorios();
        System.out.println(lista);

        System.out.println("GET repositorio con ID: " + lista.get(1).getId());
        System.out.println(repositorioController.getRepositorioById(lista.get(1).getId()));


        RepositorioDTO repositorioDTO = RepositorioDTO.builder()
                .nombre("Nuevo repositorio")
                .fecha_creacion(new Date(2010, 10, 22))
                .build();
        repositorioDTO = repositorioController.postRepositorio(repositorioDTO);
        System.out.println(repositorioDTO);


        RepositorioDTO repositorioDTO1 = RepositorioDTO.builder()
                .nombre("Nuevo repositorio 1")
                .fecha_creacion(new Date(2010, 10, 22))
                .build();
        repositorioDTO1 = repositorioController.postRepositorio(repositorioDTO1);
        System.out.println(repositorioDTO1);


        System.out.println("UPDATE repositorio con ID:" + repositorioDTO1.getId());
        Optional<RepositorioDTO> optionalRepositorioDTO = repositorioController.getRepositorioByIdOptional(repositorioDTO1.getId());
        if (optionalRepositorioDTO.isPresent()) {
            optionalRepositorioDTO.get().setNombre("Repositorio actualizado");
            System.out.println(repositorioController.updateRepositorio(optionalRepositorioDTO.get()));
        }


        System.out.println("DELETE Repositorio con ID: " + repositorioDTO.getId());
        optionalRepositorioDTO = repositorioController.getRepositorioByIdOptional(repositorioDTO.getId());
        if (optionalRepositorioDTO.isPresent()) {
            System.out.println(optionalRepositorioDTO.get().toString());
            System.out.println(repositorioController.deleteRepositorio(optionalRepositorioDTO.get()));
        }

        System.out.println("FIN REPOSITORIOS");
    }


    public void commits() {
        System.out.println("INICIO COMMITS");

        CommitController commitController = CommitController.getInstance();

        System.out.println("GET Todos los commits");
        List<CommitDTO> lista = commitController.getAllCommits();
        System.out.println(lista);

        System.out.println("GET commit con ID: " + lista.get(1).getId());
        System.out.println(commitController.getCommitById(lista.get(1).getId()));


        CommitDTO commitDTO = CommitDTO.builder()
                .titulo("Actualizacion clase CommitRepository")
                .texto("Se actualizan los metodos crud de la clase CommitRepository")
                .fecha(new Date(2020, 10, 02))
                .repositorio(r1)
                .proyecto(pr2)
                .autor(p2)
                .build();
        commitDTO = commitController.postCommit(commitDTO);
        System.out.println(commitDTO);


        CommitDTO commitDTO1 = CommitDTO.builder()

                .build();
        commitDTO1 = commitController.postCommit(commitDTO1);
        System.out.println(commitDTO1);


        System.out.println("UPDATE commit con ID:" + commitDTO1.getId());
        Optional<CommitDTO> optionalCommitDTO = commitController.getCommitByIdOptional(commitDTO1.getId());
        if (optionalCommitDTO.isPresent()) {
            optionalCommitDTO.get().setTitulo("Commit actualizado");
            System.out.println(commitController.updateCommit(optionalCommitDTO.get()));
        }


        System.out.println("DELETE Commit con ID: " + commitDTO.getId());
        optionalCommitDTO = commitController.getCommitByIdOptional(commitDTO.getId());
        if (optionalCommitDTO.isPresent()) {
            System.out.println(optionalCommitDTO.get().toString());
            System.out.println(commitController.deleteCommit(optionalCommitDTO.get()));
        }


        System.out.println("FIN COMMITS");
    }


    public void issues() {
        System.out.println("INICIO ISSUES");

        IssueController issueController = IssueController.getInstance();

        System.out.println("GET Todos los issues");
        List<IssueDTO> lista = issueController.getAllIssues();
        System.out.println(lista);

        System.out.println("GET issue con ID: " + lista.get(1).getId());
        System.out.println(issueController.getIssueById(lista.get(1).getId()));


        IssueDTO issueDTO = IssueDTO.builder()
                .titulo("Problema clase issueRepositorio")
                .texto("StackOverFlow en metodo edit")
                .fecha(new Date(2020, 10, 02))
                .programadores(new HashSet<Programador>(Arrays.asList(p1, p2)))
                .proyecto(pr2)
                .repositorio(r1)
                .estado(Estado.PENDIENTE)
                .build();
        issueDTO = issueController.postIssue(issueDTO);
        System.out.println(issueDTO);


        IssueDTO issueDTO1 = IssueDTO.builder()

                .build();
        issueDTO1 = issueController.postIssue(issueDTO1);
        System.out.println(issueDTO1);


        System.out.println("UPDATE issue con ID:" + issueDTO1.getId());
        Optional<IssueDTO> optionalIssueDTO = issueController.getIssuetByIdOptional(issueDTO1.getId());
        if (optionalIssueDTO.isPresent()) {
            optionalIssueDTO.get().setTitulo("Commit actualizado");
            System.out.println(issueController.updateIssue(optionalIssueDTO.get()));
        }


        System.out.println("DELETE issue con ID: " + issueDTO.getId());
        optionalIssueDTO = issueController.getIssuetByIdOptional(issueDTO.getId());
        if (optionalIssueDTO.isPresent()) {
            System.out.println(optionalIssueDTO.get().toString());
            System.out.println(issueController.deleteIssue(optionalIssueDTO.get()));
        }


        System.out.println("FIN ISSUES");
    }

    public void getProyectsByDepartment(ObjectId idDepartamento) {
        System.out.println("CONSULTAS PERSONALIZADAS");
        System.out.println("OBTENER DE UN DEPARTAMENTO LOS PROYECTOS Y LOS PROGRAMADORES ASOCIADOS A CADA PROYECTO");

        DepartamentoController departamentoController = DepartamentoController.getInstance();
        ProyectoController proyectoController = ProyectoController.getInstance();
        Set<Proyecto> proyectsByDepartment = departamentoController.getProyectsByDepartment(idDepartamento);
        Set<ProgramadorDTO> programadorsByProject = new HashSet();


        for (Proyecto p:proyectsByDepartment
             ) {
            programadorsByProject.addAll(proyectoController.getProgramadorsByProject(p.getId()));
            System.out.println("Proyecto---> " + p.toString() + " programadores -->" + proyectoController.getProgramadorsByProject(p.getId()));
        }


    }








}
