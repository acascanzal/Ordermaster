package es.unizar.eina.M42_comidas.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

/** Definicion de clase relacion entre pedido y plato*/
@Entity(tableName = "EsPedido",
        primaryKeys = { "pedidoId", "platoId" },
        foreignKeys = {
                @ForeignKey(entity = Pedido.class,
                            parentColumns = "idPedido",
                            childColumns = "pedidoId",
                            onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Plato.class,
                            parentColumns = "idPlato",
                            childColumns = "platoId",
                            onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index("pedidoId"),
                @Index("platoId")
        }
)
public class EsPedido {
    @ColumnInfo(name = "pedidoId")
    public int pedidoId;
    
    @ColumnInfo(name = "platoId")
    public int platoId;
    
    @NonNull
    @ColumnInfo(name = "numero")
    public int numero;
    
    @NonNull
    @ColumnInfo(name = "precio")
    public double precio;

    /**
     * Constructor de la clase EsPedido.
     * inicializa los atributos de la clase pedidoId y platoId.
     * @param pedidoId
     * @param platoId
     */
    public EsPedido(final int pedidoId, final int platoId,final int numero,final double precio) {
        this.pedidoId = pedidoId;
        this.platoId = platoId;
        this.numero = numero;
        this.precio = precio;
    }

    /**
     * Setter del atributo numero.
     * @param numero
     */
    public void setNumero(final int numero) {
        this.numero = numero;
    }

    /**
     * Setter del atributo precio.
     * @param precio
     */
    public void setPrecio(final double precio) {
        this.precio = precio;
    }

    /**
     * Getter del atributo pedidoId.
     * @return pedidoId
     */
    public int getPedidoId() {
        return pedidoId;
    }

    /**
     * Getter del atributo platoId.
     * @return platoId
     */
    public int getPlatoId() {
        return platoId;
    }

    /**
     * Getter del atributo numero.
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter del atributo precio.
     * @return precio
     */

    public double getPrecio() {
        return precio;
    }

}