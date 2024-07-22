package es.unizar.eina.M42_comidas.database;

import android.icu.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import java.util.Date;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

/** Clase anotada como entidad que representa un Pedido */
@Entity(tableName = "Pedido")
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idPedido")
    private int idPedido;

    @NonNull
    @ColumnInfo(name = "nombreCliente")
    private String nombreCliente;

    @NonNull
    @ColumnInfo(name = "telefonoCliente")
    private String telefonoCliente;

    @NonNull
    @ColumnInfo(name = "fechaRecogida")
    private Date fechaRecogida;

    @NonNull
    @ColumnInfo(name = "estado")
    private String estado;


  

    

    /** Constructor de la clase pedido
     *
     * @param nombre
     * @param telefono
     * @param fechaRecogida
     * @param estado
     */
    public Pedido(@NonNull String nombreCliente, @NonNull String telefonoCliente, @NonNull Date fechaRecogida ,@NonNull String estado) {
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.estado = estado;
        this.fechaRecogida = fechaRecogida;
    }


    /** Devuelve el identificador de la nota 
     * @return idPedido
    */
    public int getIdPedido(){
        return this.idPedido;
    }
    
    /** Permite actualizar el identificador de un pedido 
     * @param idPedido
    */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /** Permite actualizar el nombre del cliente 
     * @param nombreCliente
    */
    public void setNombreCliente(@NonNull String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    /** Devuelve el nombre de cliente que ha realizado el pedido
     * @return nombreCliente
     */
    public String getNombreCliente(){
        return this.nombreCliente;
    }

    /** Devuelve el estado del pedido 
     * @return estado
    */
    public String getEstado(){
        return this.estado;
    }
    /** Permite actualizar el estado de un pedido 
     * @param estado
    */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /** Devuelve el telefono asociado al pedido
     * @return telefonoCliente
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    /** Permite actualizar el telefono asociado al pedido
     * @param telefonoCliente
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    /** Devuelve la fecha de recogida del pedido
     * @return fechaRecogida
     */
    public Date getFechaRecogida() {
        return fechaRecogida;
    }

    /** Permite actualizar la fecha de recogida del pedido
     * @param fechaRecogida
     */
    public void setFechaRecogida(Date fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

}
