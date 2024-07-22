package es.unizar.eina.M42_comidas.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definicion de un Data Access Object para los pedidos */
@Dao
public interface PedidoDao {

    /**
     * Inserta un pedido en la base de datos
     * @param pedido
     * @return un valor entero con el id del pedido insertado.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Pedido pedido);

    /**
     * Actualiza un pedido en la base de datos
     * @param pedido
     * @return un valor entero con el numero de filas modificadas.
     */
    @Update
    int update(Pedido pedido);


    /**
     * Elimina un pedido de la base de datos
     * @param idPedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    @Delete
    int delete(Pedido pedido);




    /**
     * Elimina todos los pedidos de la base de datos
     */
    @Query("DELETE FROM Pedido")
    void deleteAll();

    /**
     * Devuelve todos los pedidos de la base de datos
     * @return una lista de pedidos
     */
    @Query("SELECT * FROM Pedido")
    LiveData<List<Pedido>> getAllPedidos();


    /**
     * Devuelve todos los pedidos de la base de datos ordenados por nombre
     * @return una lista de pedidos ordenados por nombre
     */
    @Query("SELECT * FROM Pedido ORDER BY nombreCliente ASC")
    LiveData<List<Pedido>> getAllPedidosOrderedByNombre();


    /**
     * Devuelve todos los pedidos de la base de datos ordenados por telefono
     * @return una lista de pedidos ordenados por telefono
     */
    @Query("SELECT * FROM Pedido ORDER BY telefonoCliente ASC")
    LiveData<List<Pedido>> getAllPedidosOrderedByTelefono();


    /**
     * Devuelve todos los pedidos de la base de datos ordenados por fecha
     * @return una lista de pedidos ordenados por fecha
     */
    @Query("SELECT * FROM Pedido ORDER BY fechaRecogida ASC")
    LiveData<List<Pedido>> getAllPedidosOrderedByDate();

    /**
     * Devuelve todos los pedidos de la base de datos filtrados por el estado
     * @return una lista de pedidos filtrados por estado
     */
    @Query("SELECT * FROM Pedido WHERE estado like :filtro ")
    LiveData<List<Pedido>> getAllPedidosFiltered(String filtro);

    /**
     * Devuelve todos los pedidos de la base de datos filtrados por el estado y ordenado por número de teléfono
     * @param filtro, criterio
     * @return una lista de pedidos filtrados por <filtro> y ordenados por numero de telefono
     */
    @Query("SELECT * FROM Pedido WHERE estado = :filtro ORDER BY telefonoCliente ASC")
    LiveData<List<Pedido>> getAllPedidosFilteredAndOrderedByNumber(String filtro);

    /**
     * Devuelve todos los pedidos de la base de datos filtrados por el estado y ordenado por nombre del Cliente
     * @param filtro, criterio
     * @return una lista de pedidos filtrados por <filtro> y ordenados por nombre del Cliente
     */
    @Query("SELECT * FROM Pedido WHERE estado = :filtro ORDER BY nombreCliente ASC")
    LiveData<List<Pedido>> getAllPedidosFilteredAndOrderedByName(String filtro);

    /**
     * Devuelve todos los pedidos de la base de datos filtrados por el estado y ordenado por fecha de recogida
     * @param filtro, criterio
     * @return una lista de pedidos filtrados por <filtro> y ordenados por fecha de recogida
     */
    @Query("SELECT * FROM Pedido WHERE estado = :filtro ORDER BY fechaRecogida ASC")
    LiveData<List<Pedido>> getAllPedidosFilteredAndOrderedByDate(String filtro);

}



