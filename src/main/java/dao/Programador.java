package dao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import utils.Cifrador;

import javax.persistence.*;
import java.util.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "programador") // Ojo con la minuscula que en la tabla está así
// Todos los programadores
@NamedQueries({
        @NamedQuery(name = "Programador.findAll", query = "SELECT p FROM Programador p"),
        // Todos los usuarios con id indicados, ojo, no usar parámetros
        @NamedQuery(name = "Programador.getById", query = "SELECT p FROM Programador p WHERE p.id = :id"),
        // Todos los post de un usuario
       // @NamedQuery(name = "User.getMyPosts", query = "SELECT u.p FROM Programador u WHERE u.id = :userId")
})
public class Programador {
    private ObjectId id;
    private String nombre;
    private Date fecha_alta;
    private Departamento departamento;
    private Set<Proyecto> proyectos;
    private Set<Commit> commits;
    private Set<Issue> issues;
    private Set<Tecnologia> tecnologias;
    private double salario;
    private String contraseña;

    public Programador(String nombre, Date fecha_alta, Departamento departamento, Set<Tecnologia>tecnologias, double salario, String contraseña) {
        this.nombre = nombre;
        this.fecha_alta = fecha_alta;
        this.departamento = departamento;
        this.proyectos = new HashSet<Proyecto>();
        this.commits = new HashSet<Commit>();
        this.issues = new HashSet<Issue>();
        this.tecnologias = tecnologias;
        this.salario = salario;
        this.contraseña = Cifrador.getInstance().SHA256(contraseña);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Basic
    @Column(name = "fecha_alta", nullable = false)
    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fechaAlta) {
        this.fecha_alta = fechaAlta;
    }


    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id", nullable = false)
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "jefe", orphanRemoval = true, cascade = CascadeType.PERSIST) // Estudiar la cascada
    public Set<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "autor", orphanRemoval = true, cascade = CascadeType.PERSIST) // Estudiar la cascada
    public Set<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<Commit> commits) {
        this.commits = commits;
    }


    @ManyToMany(mappedBy = "programadores")
    public Set<Issue> getIssues() {
        return issues;
    }
    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }


    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "programador", orphanRemoval = true, cascade = CascadeType.PERSIST) // Estudiar la cascada
    @Enumerated(EnumType.STRING)
    @ElementCollection
    public Set<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(Set<Tecnologia> tecnologias) {
        this.tecnologias = tecnologias;
    }


    @Basic
    @Column(name = "salario", nullable = false)
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }



    @Basic
    // @ColumnTransformer(write = " SHA(?) ")
    // Le decimos que lo transforme con esta función. Nos ahorramos cifrarlo nosotros
    @Column(name = "contraseña", nullable = false, length = 100)
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }





    @Override
    // No es obligatorio, pero al hacerlo podemos tener problemas con la recursividad de las llamadas
    public String toString() {
        return "User{" +
                "id =" + id +
                ", nombre ='" + nombre + '\'' +
                ", fecha de alta ='" + fecha_alta + '\'' +
                ", departamento ='" + departamento.getNombre() + '\'' +
                ", proyectos ='" + proyectos + '\'' +
                //", proyectos ='" + proyectos.stream().map(Proyecto::getNombre) + '\'' +
               // ", commits ='" + commits + '\'' + SON MUCHOS, no interesa
               // ", issues ='" + issues + '\'' + No lo sacamos ya que en las issues ya salen los programadores activos dela misma, evitamos un bucle infinito.
                ", tecnologias ='" + tecnologias + '\'' +
                ", salario ='" + salario + '\'' +
                // ", contraseña='" + contraseña + '\'' + Evitamos

                '}';
    }
}
