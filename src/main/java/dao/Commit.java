package dao;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "commit")
@NamedQueries({
        @NamedQuery(name = "Commit.findAll", query = "SELECT c FROM Commit c"),

})
public class Commit {
    private ObjectId id;
    private String titulo;
    private String texto;
    private Date fecha;
    private Repositorio repositorio;
    private Proyecto proyecto;
    private Programador autor;

    public Commit(String titulo, String texto, Date fecha, Repositorio repositorio, Proyecto proyecto, Programador autor) {
        this.titulo = titulo;
        this.texto = texto;
        this.fecha = fecha;
        this.repositorio = repositorio;
        this.proyecto = proyecto;
        this.autor = autor;

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


    //Un commit se puede hacer sobre un solo repositorio y un repositorio puede tener muchos commits
    @ManyToOne
    @JoinColumn(name = "repositorio_id", referencedColumnName = "id", nullable = false)
    public Repositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }


    //Lo mismo
    @ManyToOne
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }


    //Lo mismo
    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "id", nullable = false)
    public Programador getAutor() {
        return autor;
    }

    public void setAutor(Programador autor) {
        this.autor = autor;
    }


    @Override
    public String toString() {
        return "User{" +
                "id =" + id +
                ", titulo ='" + titulo + '\'' +
                ", texto ='" + texto + '\'' +
                ", fecha ='" + fecha + '\'' +
                ", repositorio ='" + repositorio.getNombre() + '\'' +
                ", proyecto ='" + proyecto.getNombre() + '\'' +
                ", autor ='" + autor + '\'' +
                '}';
    }
}
