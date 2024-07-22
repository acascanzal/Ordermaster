package es.unizar.eina.M42_comidas.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** Clase anotada como entidad que representa un Plato */
@Entity(tableName = "Plato")
public class Plato {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idPlato")
    private int idPlato;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @NonNull
    @ColumnInfo(name = "precio")
    private Double precio;

    @NonNull
    @ColumnInfo(name = "categoria")
    private String categoria;

    /** Constructor de la clase plato 
     * 
     * @param nombre
     * @param descripcion
     * @param precio
     * @param categoria
    */
    public Plato(@NonNull String nombre, @NonNull String descripcion, @NonNull Double precio, @NonNull String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }


    /**
     * Metodo getter que establece el id del plato
     * @return idPlato
     */
    public int getIdPlato() {
        return this.idPlato;
    }

    /**
     * Metodo setter que establece el id del plato
     * @param idPlato
     */
    public void setIdPlato(int idPlato){
        this.idPlato = idPlato;
    }

    /**
     * Metodo getter que devuelve la nombre del plato
     * @return nombre
     *
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Metodo setter que establece la nombre del plato
     * @param nombre
     */
    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo getter que devuelve la descripcion del plato
     * @return descripcion
     * 
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Metodo setter que establece la descripcion del plato
     * @param descripcion
     */
    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Metodo getter que devuelve el precio del plato
     * @return precio
     */
    public Double getPrecio() {
        return this.precio;
    }

    /**
     * Metodo setter que establece el precio del plato
     * @param precio
     */

    public void setPrecio(@NonNull Double precio) {
        this.precio = precio;
    }
    /**
     * Metodo getter que devuelve la categoria del plato
     * @return categoria
     */
    public String getCategoria() {
        return this.categoria;
    }
    
    /**
     * Metodo setter que establece la categoria del plato
     * @param categoria
     */
    public void setCategoria(@NonNull String categoria) {
        this.categoria = categoria;
    }


}
