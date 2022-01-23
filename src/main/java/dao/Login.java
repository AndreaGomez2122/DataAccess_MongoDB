package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login")
@NamedQueries({
        @NamedQuery(name = "Login.findAll", query = "SELECT i FROM Login i"),
// Consulta por si queremos buscar logins por tokens
        @NamedQuery(name = "Login.getByToken", query = "SELECT l FROM Login l WHERE l.token = ?1")
})

public class Login {
    private long id;
    private  Programador programador;
    private Timestamp instante;
    private String token;
    private boolean activo;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    //En este punto necesitamos el id del programador, estamos recibiendo el programador entero, quizá haya que buscar otra manera.
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    public Programador getProgramador() {
        return programador;
    }

    public void setProgramador(Programador programador) {
        this.programador = programador;
    }

    @Basic
    @Column(name = "instante", nullable = false)
    public Timestamp getInstante() {
        return instante;
    }

    public void setInstante(Timestamp instante) {
        this.instante = instante;
    }

    @Basic
    // @ColumnTransformer(write=" UUID() ") // Le decimos que lo transforme con esta función. nos ahorramos hacerlo nosotros
    @Column(name = "token", nullable = false, length = 100)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }




    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                "idProgramador=" + programador.getId() +
                ", instante=" + instante +
                ", token='" + token + '\'' +
                '}';
    }
}
