package com.Equipo3.Backend.Usuario.Dominio;

import com.Equipo3.Backend.Evento.Dominio.EventoVO;
import com.Equipo3.Backend.Shared.Dominio.Audit.AuditableEntity;
import com.Equipo3.Backend.Usuario.Err.PersonaErr;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase UsuarioVO para trabajar con la base de datos
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@With
@Entity(name = "Usuario")
public class UsuarioVO extends AuditableEntity implements Serializable {

    /**
     * id tipo int el cual es el Id en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * nombre tipo String
     */
    @Column(unique = true, nullable = false)
    private String nombre;

    /**
     * password tipo String
     */
    @Column(length = 30, nullable = false)
    private String password;

    /**
     * email tipo String
     */
    @Column(length = 50, nullable = false)
    private String email;

    /**
     * fecha_nacimiento tipo Date
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date fecha_nacimiento;

    /**
     * telefono tipo int
     */
    @Column(length = 50)
    private int telefono;

    /**
     * rol tipo String
     */
    @Column(length = 150, nullable = false)
    private String rol;

    /**
     * amigos tipo List<UsuarioVO>
     */
    @ManyToMany
    @JoinTable(name = "Amigo", joinColumns = @JoinColumn(name = "usuarioId"), inverseJoinColumns = @JoinColumn(name = "amigoId"))
    @NonNull
    private List<UsuarioVO> amigos = new ArrayList<>();

    /**
     * amigosde tipo List<UsuarioVO>
     */
    @ManyToMany
    @JoinTable(name = "Amigo", joinColumns = @JoinColumn(name = "amigoId"), inverseJoinColumns = @JoinColumn(name = "usuarioId"))
    @NonNull
    private List<UsuarioVO> amigosde = new ArrayList<>();

    @OneToMany(mappedBy = "idusuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EventoVO> eventos = new ArrayList<>();

    /**
     * Constructor de UsuarioVO
     * @param id
     * @param nombre
     * @param password
     * @param email
     * @param fecha_nacimiento
     * @param telefono
     * @param rol
     */
    public UsuarioVO(int id, String nombre, String password, String email, Date fecha_nacimiento, int telefono, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.rol = rol;
    }

    /**
     * Constructor de UsuarioVO
     * @param nombre
     * @param password
     * @param email
     * @param fecha_nacimiento
     * @param telefono
     * @param rol
     */
    public UsuarioVO(String nombre, String password, String email, Date fecha_nacimiento, int telefono, String rol) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.rol = rol;
    }

    /**
     * Método para añadir a un amigo a la lista de UsuarioVO
     * @param amigo
     */
    public void addAmigo(UsuarioVO amigo) {
        if (amigos == null)
            amigos = new ArrayList<>();

        amigos.add(amigo); //Usuario agrega a su amigo
    }

    /**
     * Método para eliminar a un amigo a la lista de UsuarioVO
     * @param amigo
     */
    public void eliminarAmigo(UsuarioVO amigo) {
        if (amigos == null)
            throw new PersonaErr("PER.REM.FRIEND.NULL", "REMOVE FRIEND PARAM IS NULL");

        if (!amigos.contains(amigo))
            return;

        amigos.remove(amigo); //Usuario elimina a su amigo
    }

    /**
     * Método equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioVO usuarioVO = (UsuarioVO) o;

        if (id != usuarioVO.id) return false;
        if (telefono != usuarioVO.telefono) return false;
        if (!nombre.equals(usuarioVO.nombre)) return false;
        if (!password.equals(usuarioVO.password)) return false;
        if (!email.equals(usuarioVO.email)) return false;
        //Fecha de momento comentada hasta solucion definitiva
        if (fecha_nacimiento != null ? !fecha_nacimiento.equals(usuarioVO.fecha_nacimiento) : usuarioVO.fecha_nacimiento != null)
            return false;
        return rol.equals(usuarioVO.rol);
    }

    /**
     * Método HashCode
     * @return
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (fecha_nacimiento != null ? fecha_nacimiento.hashCode() : 0);
        result = 31 * result + telefono;
        result = 31 * result + rol.hashCode();
        return result;
    }
}
