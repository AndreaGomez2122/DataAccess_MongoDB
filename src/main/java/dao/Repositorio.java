package dao;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repositorio")
@NamedQueries({
        @NamedQuery(name = "Repositorio.findAll", query = "SELECT r FROM Repositorio r"),

})
public class Repositorio {
    private ObjectId id;
    private String nombre;
    private Date fecha_creacion;
    private Proyecto proyecto;
    private Set<Commit> commits;
    private Set<Issue> issues;

    public Repositorio(String nombre, Date fecha_creacion, Proyecto proyecto) {
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.proyecto = proyecto;
        this.commits = new HashSet<Commit>();
        this.issues = new HashSet<Issue>();
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
    @Column(name = "fecha_creacion", nullable = false)
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }



    //Un repo solo tendrá un proyecto como se ha indicado antes
    @OneToOne
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    //Un repo puede tener muchos commits pero un commit solo se hace sobre un repositorio

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repositorio", orphanRemoval = true, cascade = CascadeType.PERSIST)
    // Estudiar la cascada
    public Set<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<Commit> commits) {
        this.commits = commits;
    }


    //Un repo puede tener varias issues pero una issue es sobre un solo repo
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "repositorio", orphanRemoval = true, cascade = CascadeType.PERSIST)
    // Estudiar la cascada
    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }


    @Override
    // No es obligatorio, pero al hacerlo podemos tener problemas con la recursividad de las llamadas
    public String toString() {
        return "User{" +
                "id =" + id +
                ", nombre ='" + nombre + '\'' +
                ", fecha creacion ='" + fecha_creacion + '\'' +
                ", proyecto ='" + proyecto + '\'' +
                ", commits ='" + commits + '\'' + //Dejamos los commits por probar pero no se necesitan, son muchos e irrelevantes.
                ", issues ='" + issues + '\'' +
                '}';
    }
}
