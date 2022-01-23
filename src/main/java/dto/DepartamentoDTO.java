package dto;

import dao.Programador;
import dao.Proyecto;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Set;

@Data
public class DepartamentoDTO {

    private ObjectId id;
    private String nombre;
    private Programador jefe;
    private double  presupuesto;
    private Set<Proyecto> proyectos_terminados;
    private Set<Proyecto> proyectos_activos;
    private Set<Programador> historico_jefes;

    @Override
    public String toString() {
        return "DepartamentoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", jefe=" + jefe +
                ", presupuesto=" + presupuesto +
                ", proyectos_terminados=" + proyectos_terminados +
                ", proyectos_activos=" + proyectos_activos +
                ", historico_jefes=" + historico_jefes +
                '}';
    }
}
