package dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
// Consulta para obtener todos
@NamedQueries({
        @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
        // Consulta para obtener todos los departamentos dado el id de un usuario
        //@NamedQuery(name = "Proyecto.getByUserId", query = "SELECT p FROM Post p WHERE p.user.id = :userId"),
})
@Table(name = "proyecto") // Ojo con la minuscula que en la tabla está así
public class Proyecto {
    private ObjectId id;
    private Programador jefe;
    private String nombre;
    private double presupuesto;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Set<Tecnologia> tecnologias;
    private Repositorio repositorio;
    private Departamento departamento;





    public Proyecto(String nombre, Programador jefe, double presupuesto, Date fecha_inicio, Date fecha_fin, Set<Tecnologia> tecnologias, Departamento departamento) {
        this.nombre = nombre;
        this.jefe = jefe;
        this.presupuesto = presupuesto;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.tecnologias = tecnologias;
        this.departamento = departamento;

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
    @Column(name = "nombre", nullable = false, length = 250)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //El jefe no puede participar en ningun proyecto ni ser jefe del departamento
    @ManyToOne
    @JoinColumn(name = "jefe_id", referencedColumnName = "id", nullable = false)
    public Programador getJefe() {
        return jefe;
    }

    public void setJefe(Programador jefe) {
        this.jefe = jefe;
    }

    @Basic
    @Column(name = "presupuesto", nullable = false, length = 250)
    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Basic
    @Column(name = "fecha_inico", nullable = false)
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fechaInicio) {
        this.fecha_inicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin", nullable = false)
    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fechaFin) {
        this.fecha_fin = fechaFin;
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

    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id", nullable = false)
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @OneToOne
    @JoinColumn(name = "repositorio_id",referencedColumnName = "id", nullable = true)
    public Repositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", jefe='" + jefe.getNombre() + '\'' +
                ", presupuesto='" + presupuesto + '\'' +
                ", fecha inicio =" + fecha_inicio +
                ", fecha fin =" + fecha_fin +
                ", tecnologias=" + tecnologias +
               ", repositorio=" + repositorio.getNombre()+
                ", departamento=" + departamento.getNombre() +
                '}';
    }
}




