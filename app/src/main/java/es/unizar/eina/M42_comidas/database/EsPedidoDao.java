package es.unizar.eina.M42_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definicion de un Data Access Object para la tabla N:M entre Plato y Pedido */
@Dao
public interface EsPedidoDao {

    /**
     * Inserta una relacion Plato-Pedido en la base de datos
     * @param esPedido
     * @return un valor entero con el id de la relacion insertada.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(EsPedido esPedido);


    /**
     * Actualiza una relacion Plato-Pedido en la base de datos
     * @param esPedido
     * @return un valor entero con el numero de filas modificadas.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long update(EsPedido esPedido);


    /**
     * Elimina una relacion Plato-Pedido de la base de datos
     * @param esPedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    @Delete
    int delete(EsPedido esPedido);


    /**
     * Elimina todas las relaciones Plato-Pedido de la base de datos
     */
    @Query("DELETE FROM EsPedido")
    void deleteAll();

    /**
     * Obtiene un plato de la base de datos
     * @param idPedido
     * @return Devuelve una lista de idPlato asociados al pedido con idPedido
    */
    @Query("SELECT  pedidoId as pedidoId, platoId as platoId, numero as numero, precio as precio FROM esPedido WHERE pedidoId = :idPedido" )
    LiveData<List<EsPedido>> getPlatoPedido(int idPedido);

    /**
     * Obtiene el precio del todos los platos de la base de datos
     * @param idPedido
     * @return Devuelve el precio total de un pedido
    */
    @Query("SELECT SUM(precio) FROM esPedido WHERE pedidoId = :idPedido" )
    double getPrecioTotal(int idPedido);


}




