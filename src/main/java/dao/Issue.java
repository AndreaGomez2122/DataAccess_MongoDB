package dao;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issue")
@NamedQueries({
        @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),

})
public class Issue {
    private ObjectId id;
    private String titulo;
    private String texto;
    private Date fecha;
    private Set<Programador> programadores; //No se añade al constructor. Se le añaden los programadores más tarde.
    private Proyecto proyecto;
    private Repositorio repositorio;
    private Estado estado;

    public Issue(String titulo, String texto, Date fecha, Proyecto proyecto, Repositorio repositorio, Estado estado, Set<Programador>programadores) {
        this.titulo = titulo;
        this.texto = texto;
        this.fecha = fecha;
        this.proyecto = proyecto;
        this.repositorio = repositorio;
        this.estado = estado;
        this.programadores = programadores;


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
    @Column(name = "titulo", nullable = false, length = 100)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "texto", nullable = false, length = 100)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


    @Basic
    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha_creacion) {
        this.fecha = fecha;
    }


    //Una issue puede pertenecer a un solo proyecto y un proyecto puede tener muchas issues
    @ManyToOne
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }



    @ManyToOne
    @JoinColumn(name = "repositorio_id", referencedColumnName = "id", nullable = false)
    public Repositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }



    //Una issue puede pertenecer a muchos programadores y un programador puede tener muchas issues
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Programador> getProgramadores() {
        return programadores;
    }

    public void setProgramadores(Set<Programador> programadores) {
        this.programadores = programadores;
    }


    @Override
    public String toString() {
        return "User{" +
                "id =" + id +
                ", titulo ='" + titulo + '\'' +
                ", texto ='" + texto + '\'' +
                ", fecha ='" + fecha + '\'' +
                ", proyecto ='" + proyecto.getNombre() + '\'' +
                ", repositorio ='" + repositorio.getNombre() + '\'' +
                ", programadores ='" + programadores + '\'' +
                '}';
    }
}
