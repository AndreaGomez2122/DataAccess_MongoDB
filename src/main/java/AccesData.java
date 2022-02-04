import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import controller.*;
import dao.*;
import dto.*;
import manager.HibernateController;
import mapper.DepartamentoMapper;
import org.bson.types.ObjectId;
import utils.Cifrador;

import java.io.IOException;
import java.util.*;

public class AccesData {


    Departamento d1, d2;
    Programador p1, p2, p3, p4,p5;
    Proyecto pr1, pr2, pr3;
    Repositorio r1,r2,r3;
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
        p2 = new Programador("Mario", new Date(2020, 10, 20), d2, new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), 1500, "1234");
        p3 = new Programador("Joel", new Date(2020, 10, 20), d2, new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), 1500, "1234");
        p4 = new Programador("Laura", new Date(2020, 10, 20), d2, new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), 1500, "1234");
        p5 = new Programador("Jose", new Date(2020, 10, 20), d2, new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)), 1500, "1234");
        hc.getManager().persist(p1);
        hc.getManager().persist(p2);
        hc.getManager().persist(p3);
        hc.getManager().persist(p4);
        hc.getManager().persist(p5);
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
        r2 = new Repositorio("Santander web repository", new Date(2021, 01, 20), pr2);
        r3 = new Repositorio("app firma repository", new Date(2021, 01, 20), pr2);
        hc.getManager().persist(r1);
        hc.getManager().persist(r2);
        hc.getManager().persist(r3);
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
        i1 = new Issue("StackOverFlow", "Hay un bucle infinito en la clase AccesDataRepository", new Date(2021, 01, 20), pr1, r1, Estado.PENDIENTE,new HashSet<Programador>(Arrays.asList(p1, p2)));
        hc.getManager().persist(i1);
        hc.getTransaction().commit();



        System.out.println("Añadiendo jefes a los departamentos");
        hc.getTransaction().begin();
        d1.setJefe(p1);
        d2.setJefe(p1);
        hc.getManager().persist(d1);
        hc.getManager().persist(d2);
        hc.getTransaction().commit();



        System.out.println("Añadiendo repositorio a los proyectos");
        hc.getTransaction().begin();
        pr1.setRepositorio(r1);
        pr2.setRepositorio(r2);
        pr3.setRepositorio(r3);

        hc.getManager().persist(pr1);
        hc.getManager().persist(pr2);
        hc.getManager().persist(pr3);
        hc.getTransaction().commit();

        hc.close();

    }

    private void removeData() {

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


    public void departamentos() throws IOException {
        DepartamentoMapper dm = new DepartamentoMapper();
        System.out.println("INICIO DEPARTAMENTOS");

        DepartamentoController departamentoController = DepartamentoController.getInstance();

        // Iniciamos los departamentos
        System.out.println("GET Todos los departamentos");
        List<DepartamentoDTO> lista = departamentoController.getAllDepartamentos();
        System.out.println(lista.toString());
        //System.out.println(dm.toJson(lista));


        System.out.println("GET Departamento con ID: " + lista.get(1).getId()); // Mira en el explorador a ver que categoría hay
        System.out.println(departamentoController.getDepartamentoById(lista.get(1).getId()).toString());

        System.out.println("Post departamentos");
        DepartamentoDTO departamentoDTO = DepartamentoDTO.builder()
                .nombre("Nuevo departamento")
                .jefe(p1)
                .presupuesto(250000)
                .proyectos_activos(new HashSet<Proyecto>(Arrays.asList(pr1)))
                .proyectos_terminados((new HashSet<Proyecto>(Arrays.asList(pr2))))
                .build();
        DepartamentoDTO d = departamentoController.postDepartamento(departamentoDTO);
        System.out.println(departamentoDTO.toString());


        DepartamentoDTO departamentoDTO1 = DepartamentoDTO.builder()
                .nombre("Nuevo departamento 1")
                .jefe(p1)
                .presupuesto(250000)
                .proyectos_activos(new HashSet<Proyecto>(Arrays.asList(pr1)))
                .proyectos_terminados((new HashSet<Proyecto>(Arrays.asList(pr2))))
                .build();
        DepartamentoDTO d1 = departamentoController.postDepartamento(departamentoDTO1);
        System.out.println(departamentoDTO1.toString());


        System.out.println("FIN DEPARTAMENTOS");
    }


    public void proyectos() {
        System.out.println("INICIO PROYECTOS");

        ProyectoController proyectoController = ProyectoController.getInstance();
        System.out.println("GET Todos los proyectos");
        List<ProyectoDTO> lista = proyectoController.getAllProyectos();
        System.out.println(lista.toString());

        System.out.println("GET Proyecto con ID: " + lista.get(1).getId());
        System.out.println(proyectoController.getProyectoById(lista.get(1).getId()).toString());


        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .nombre("Nuevo proyecto")
                .jefe(p1)
                .presupuesto(250000)
                .fecha_inicio(new Date(2020,10,12))
                .fecha_fin(new Date(2020,10,12))
                .departamento(d1)
                .repositorio(r1)
                .tecnologias(new HashSet<Tecnologia>(Arrays.asList(Tecnologia.ANGULAR)))
                .build();
        ProyectoDTO p = proyectoController.postProyecto(proyectoDTO);
        //System.out.println(proyectoDTO.toString());


        ProyectoDTO proyectoDTO1 = ProyectoDTO.builder()
                .nombre("Nuevo proyecto 1")
                .jefe(p1)
                .presupuesto(250000)
                .fecha_inicio(new Date(2020,10,12))
                .fecha_fin(new Date(2020,10,12))
                .departamento(d1)
                .repositorio(r1)
                .tecnologias(new HashSet<Tecnologia>(Arrays.asList(Tecnologia.ANGULAR)))
                .build();
        ProyectoDTO p1 = proyectoController.postProyecto(proyectoDTO1);


        System.out.println("FIN PROYECTOS");
    }

    public void programadores() {
        System.out.println("INICIO PROGRAMADORES");

        ProgramadorController programadorController = ProgramadorController.getInstance();

        System.out.println("GET Todos los programadores");
        List<ProgramadorDTO> lista = programadorController.getAllProgramadors();
        System.out.println(lista.toString());

        System.out.println("GET Programador con ID: " + lista.get(1).getId());
        System.out.println(programadorController.getProgramadorById(lista.get(1).getId()));


        ProgramadorDTO programadorDTO = ProgramadorDTO.builder()
                .nombre("Nuevo programador")
                .fecha_alta(new Date(2010, 10, 22))
                .tecnologias(new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)))
                .departamento(d1)
                .proyectos(new HashSet<Proyecto>(Arrays.asList(pr1)))
                .commits(new HashSet<Commit>(Arrays.asList(c1)))
                .issues(new HashSet<Issue>(Arrays.asList(i1)))
                .salario(4000)
                .contraseña(Cifrador.getInstance().SHA256("1234"))
                .build();
        ProgramadorDTO pr = programadorController.postProgramador(programadorDTO);
        //System.out.println(programadorDTO.toString());


        ProgramadorDTO programadorDTO1 = ProgramadorDTO.builder()
                .nombre("Nuevo programador 1")
                .fecha_alta(new Date(2010, 10, 22))
                .tecnologias(new HashSet<Tecnologia>(Arrays.asList(Tecnologia.WORDPRESS, Tecnologia.ANGULAR)))
                .departamento(d1)
                .proyectos(new HashSet<Proyecto>(Arrays.asList(pr1)))
                .commits(new HashSet<Commit>(Arrays.asList(c1)))
                .issues(new HashSet<Issue>(Arrays.asList(i1)))
                .salario(4000)
                .contraseña(Cifrador.getInstance().SHA256("1234"))
                .build();
        ProgramadorDTO pr1 = programadorController.postProgramador(programadorDTO1);
       // System.out.println(programadorDTO1.toString());

        System.out.println("FIN PROGRAMADORES");
    }


    public void repositorios() {
        System.out.println("INICIO REPOSITORIOS");

        RepositorioController repositorioController = RepositorioController.getInstance();

        System.out.println("GET Todos los repositorios");
        List<RepositorioDTO> lista = repositorioController.getAllRepositorios();
        System.out.println(lista.toString());

        System.out.println("GET repositorio con ID: " + lista.get(1).getId());
        System.out.println(repositorioController.getRepositorioById(lista.get(1).getId()).toString());


        RepositorioDTO repositorioDTO = RepositorioDTO.builder()
                .nombre("Nuevo repositorio")
                .fecha_creacion(new Date(2010, 10, 22))
                .proyecto(pr1)
                .commits(new HashSet<Commit>(Arrays.asList(c1)))
                .issues(new HashSet<Issue>(Arrays.asList(i1)))
                .build();
        RepositorioDTO re  = repositorioController.postRepositorio(repositorioDTO);
       // System.out.println(repositorioDTO.toString());


        RepositorioDTO repositorioDTO1 = RepositorioDTO.builder()
                .nombre("Nuevo repositorio 1")
                .fecha_creacion(new Date(2010, 10, 22))
                .proyecto(pr1)
                .commits(new HashSet<Commit>(Arrays.asList(c1)))
                .issues(new HashSet<Issue>(Arrays.asList(i1)))
                .build();
        RepositorioDTO re1 = repositorioController.postRepositorio(repositorioDTO1);
        System.out.println(repositorioDTO1.toString());

        System.out.println("FIN REPOSITORIOS");
    }


    public void commits() {
        System.out.println("INICIO COMMITS");

        CommitController commitController = CommitController.getInstance();

        System.out.println("GET Todos los commits");
        List<CommitDTO> lista = commitController.getAllCommits();
        System.out.println(lista.toString());

        System.out.println("GET commit con ID: " + lista.get(0).getId());
        System.out.println(commitController.getCommitById(lista.get(0).getId()).toString());

/*

        CommitDTO commitDTO = CommitDTO.builder()
                .titulo("Actualizacion clase CommitRepository")
                .texto("Se actualizan los metodos crud de la clase CommitRepository")
                .fecha(new Date(2020, 10, 02))
                .repositorio(r1)
                .proyecto(pr2)
                .autor(p2)
                .build();
        CommitDTO c = commitController.postCommit(commitDTO);
        System.out.println(commitDTO.toString());


        CommitDTO commitDTO1 = CommitDTO.builder()
                .titulo ("Actualizacion clase CommitRepository")
                .texto("Se actualizan los metodos crud de la clase REPO")
                .fecha(new Date(2020, 10, 02))
                .repositorio(r1)
                .proyecto(pr2)
                .autor(p2)
                .build();
        CommitDTO c1 = commitController.postCommit(commitDTO1);
        System.out.println(commitDTO1.toString());
*/

        System.out.println("FIN COMMITS");
    }


    public void issues() {
        System.out.println("INICIO ISSUES");

        IssueController issueController = IssueController.getInstance();

        System.out.println("GET Todos los issues");
        List<IssueDTO> lista = issueController.getAllIssues();
        System.out.println(lista.toString());

        System.out.println("GET issue con ID: " + lista.get(0).getId());
        System.out.println(issueController.getIssueById(lista.get(0).getId()).toString());


        IssueDTO issueDTO = IssueDTO.builder()
                .titulo("Problema clase issueRepositorio")
                .texto("StackOverFlow en metodo edit")
                .fecha(new Date(2020, 10, 02))
                .programadores(new HashSet<Programador>(Arrays.asList(p1, p2)))
                .proyecto(pr2)
                .repositorio(r1)
                .estado(Estado.PENDIENTE)
                .build();
        IssueDTO i = issueController.postIssue(issueDTO);
        System.out.println(issueDTO.toString());


        IssueDTO issueDTO1 = IssueDTO.builder()
                .titulo("Problema clase commitRepo")
                .texto("Bucle infinito en metodo edit")
                .fecha(new Date(2020, 10, 02))
                .programadores(new HashSet<Programador>(Arrays.asList(p1, p2)))
                .proyecto(pr2)
                .repositorio(r1)
                .estado(Estado.PENDIENTE)
                .build();
        IssueDTO i1 = issueController.postIssue(issueDTO1);
        System.out.println(issueDTO1.toString());
        System.out.println("FIN ISSUES");
    }




    public void login() {
        System.out.println("INICIO LOGIN");

        LoginController loginController = LoginController.getInstance();

        System.out.println("Login con un Programador que SI existe");
        Optional<LoginDTO> login = loginController.login(p1.getId(), "1234");
        System.out.println(login.isPresent() ? "Login OK" : "Usuario o password incorrectos");

        System.out.println("Login con un usario que SI existe Y mal Password datos correctos");
        Optional<LoginDTO> login2 = loginController.login(p1.getId(), "12555");
        System.out.println(login2.isPresent() ? "Login OK" : "Usuario o password incorrectos");

        System.out.println("Login con un usario que NO existe o mal Password datos correctos");
        login2 = loginController.login(new ObjectId(), "12555");
        System.out.println(login2.isPresent() ? "Login OK" : "Usuario o password incorrectos");

        // System.out.println("Logout de usuario que está logueado");
        // System.out.println(loginController.logout(login.get().getId())? "Logout OK" : "Usuarios no logueado en el sistema"); // Mirar su ID

        System.out.println("Logout de usuario que no está logueado");
        System.out.println(loginController.logout(p2.getId())? "Logout OK" : "Usuarios no logueado en el sistema");

        System.out.println("FIN LOGIN");
    }







    public void getProyectsByDepartment(ObjectId idDepartamento) {
        System.out.println("CONSULTAS PERSONALIZADAS");
        System.out.println("OBTENER DE UN DEPARTAMENTO LOS PROYECTOS Y LOS PROGRAMADORES ASOCIADOS A CADA PROYECTO");

        DepartamentoController departamentoController = DepartamentoController.getInstance();
        ProyectoController proyectoController = ProyectoController.getInstance();
        Set<Proyecto> proyectsByDepartment = departamentoController.getProyectsByDepartment(idDepartamento);
        Set<String> programadorsByProject = new HashSet();

        for (Proyecto p:proyectsByDepartment
             ) {
            programadorsByProject.addAll(proyectoController.getProgramadorsByProject(p.getId()));
            System.out.println("Proyecto---> " + p.toString() + " programadores -->" + programadorsByProject);
        }
        programadorsByProject.clear();


    }

}
