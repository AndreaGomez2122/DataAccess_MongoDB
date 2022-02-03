package dto;

import dao.Programador;
import dao.Proyecto;
import dao.Repositorio;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@Builder
public class CommitDTO {
    private ObjectId id;
    private String titulo;
    private String texto;
    private Date fecha;
    private Repositorio repositorio;
    private Proyecto proyecto;
    private Programador autor;

    @Override
    public String toString() {
        return "CommitDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", fecha=" + fecha +
                ", repositorio=" + repositorio.getId() +
                ", proyecto=" + proyecto.getNombre() +
                ", autor=" + autor.getNombre() +
                '}';
    }
}
