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
        // Consulta para obtener todos los departamentos dado el id de un usuario
        //@NamedQuery(name = "Departamento.getByUserId", query = "SELECT p FROM Post p WHERE p.user.id = :userId"),
})
@Table(name = "departamento") // Ojo con la minuscula que en la tabla está así
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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


    //El jefe no puede participar en ningun proyecto
    @ManyToOne
    @JoinColumn(name = "jefe_id", referencedColumnName = "id", nullable = true)
    public Programador getJefe() {
        return jefe;
    }

    public void setJefe(Programador jefe) {
        this.jefe = jefe;
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
                ", jefe='" + jefe + '\'' +
                ", presupuesto='" + presupuesto + '\'' +
                ", proyectos terminados =" + proyectos_terminados +
                ", proyectos en curso =" + proyectos_activos +
                ", historico de jefes=" + historico_jefes +

                '}';
    }
}




