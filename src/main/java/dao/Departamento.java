package dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
// Consulta para obtener todos
@NamedQueries({
        @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),

})
@Table(name = "departamento")
public class Departamento {
    private ObjectId id;
    private String nombre;
    private Programador jefe;
    private double presupuesto;
    private Set<Proyecto> proyectos_terminados;
    private Set<Proyecto> proyectos_activos;
    private Set<Programador> historico_jefes;

    public Departamento(String nombre, double presupuesto) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        proyectos_terminados = new HashSet<Proyecto>();
        proyectos_activos = new HashSet<Proyecto>();
        historico_jefes = new HashSet<Programador>();

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



    @ManyToOne //un programador solo lo será de un departamento pero un departamento podrá tener muchos programadores
    @JoinColumn(name = "jefe_id", referencedColumnName = "id", nullable = true)
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


    //Un proyecto solo pertenecerá a un departamento pero un dep tendra muchos proyectos
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "departamento", orphanRemoval = true, cascade = CascadeType.MERGE)
    public Set<Proyecto> getProyectos_terminados() {
        return proyectos_terminados;
    }

    public void setProyectos_terminados(Set<Proyecto> proyectos_terminados) {
        this.proyectos_terminados = proyectos_terminados;
    }

    //Un proyecto solo pertenecerá a un departamento pero un dep tendra muchos proyectos
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "departamento", orphanRemoval = true, cascade = CascadeType.MERGE)
    public Set<Proyecto> getProyectos_activos() {
        return proyectos_activos;
    }

    public void setProyectos_activos(Set<Proyecto> proyectos_activos) {
        this.proyectos_activos = proyectos_activos;
    }



    //Un dep tendra muchos jefes pero un jefe solo lo será de un departamento
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "departamento", orphanRemoval = true, cascade = CascadeType.MERGE)
    public Set<Programador> getHistorico_jefes() {
        return historico_jefes;
    }

    public void setHistorico_jefes(Set<Programador> historico_jefes) {
        this.historico_jefes = historico_jefes;
    }

    /*

    // Pongo EAGER porque están en contexto diferentes y debememos conseguirlo
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "departamento", cascade = CascadeType.PERSIST) // cascade = CascadeType.ALL
        public Set<Proyecto> getProyectos_terminados() {
            return proyectos_terminados;
        }

        public void setProyectos_terminados(Set<Proyecto> proyectosTerminados) {
            this.proyectos_terminados = proyectosTerminados;
        }


        // Pongo EAGER porque están en contexto diferentes y debememos conseguirlo
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "departamento", cascade = CascadeType.PERSIST) // cascade = CascadeType.ALL
        public Set<Proyecto> getProyectos_activos() {
            return proyectos_activos;
        }

        public void setProyectos_activos(Set<Proyecto> proyectosActivos) {
            this.proyectos_activos = proyectosActivos;
        }
*/


    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", jefe='" + jefe.getNombre() + '\'' +
                ", presupuesto='" + presupuesto + '\'' +
                ", proyectos terminados =" + proyectos_terminados.stream().map(Proyecto::getNombre) +
                ", proyectos en curso =" + proyectos_activos.stream().map(Proyecto::getNombre) +
                ", historico de jefes=" + historico_jefes.stream().map(Programador::getNombre) +

                '}';
    }
}




