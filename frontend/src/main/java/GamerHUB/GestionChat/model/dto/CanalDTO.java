package GamerHUB.GestionChat.model.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 */
public class CanalDTO {


    private IntegerProperty id;
    private StringProperty nombre;
    private ObservableList<Integer> miembros;
    private ObservableList<MensajeDTO> historial;

    /**
     *
     * @param nombre
     * @param miembros
     * @param historial
     */
    public CanalDTO( String nombre, ObservableList<Integer> miembros, ObservableList<MensajeDTO> historial) {
        this.id = new SimpleIntegerProperty((int) Math.abs(System.currentTimeMillis() / 10000));;
        this.nombre = new SimpleStringProperty(nombre);
        this.miembros = FXCollections.observableArrayList(miembros);
        this.historial = FXCollections.observableArrayList(historial);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public ObservableList<Integer> getMiembros() {
        return miembros;
    }

    public void setMiembros(ObservableList<Integer> miembros) {
        this.miembros = miembros;
    }

    public ObservableList<MensajeDTO> getHistorial() {
        return historial;
    }

    public void setHistorial(ObservableList<MensajeDTO> historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "CanalDTO{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", miembros=" + miembros +
                ", historial=" + historial +
                '}';
    }
}