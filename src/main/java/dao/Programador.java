package dao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
       // @NamedQuery(name = "User.getMyPosts", query = "SELECT u.posts FROM User u WHERE u.id = :userId")
})
public class Programador {
    private long id;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    // @ColumnTransformer(write = " SHA(?) ")
    // Le decimos que lo transforme con esta función. Nos ahorramos cifrarlo nosotros
    @Column(name = "contraseña", nullable = false, length = 100)
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Basic
    @Column(name = "fecha_alta", nullable = false)
    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fechaAlta) {
        this.fecha_alta = fechaAlta;
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.REMOVE) // Estudiar la cascada
    public Set<Post> getPosts() {
        return posts;
    }

    // No es necesario si no queremos cambiar los post desde usuario
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    // La Cascada
    // http://openjpa.apache.org/builds/2.4.0/apache-openjpa/docs/jpa_overview_meta_field.html#jpa_overview_meta_cascade
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.REMOVE) // cascade = CascadeType.ALL
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    // No es obligatorio, pero al hacerlo podemos tener problemas con la recursividad de las llamadas
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                // ", password='" + password + '\'' + Evitamos
                ", fechaRegistro=" + fechaRegistro +
                // Cuidado aqui con las llamadas recursivas No me interesa imprimir los post del usuario, pueden ser muchos
                // ", posts=" + posts + // Podriamos quitarlos para no verlos
                // Tampoco saco los comentarios
                '}';
    }
}
