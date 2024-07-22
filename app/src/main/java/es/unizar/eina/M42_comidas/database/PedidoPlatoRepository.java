package es.unizar.eina.M42_comidas.database;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/** Definicion de clase repositorio para los pedidos */
public class PedidoPlatoRepository {
    private final long TIMEOUT = 15000;
    private PedidoDao mPedidoDao;
    private LiveData<List<Pedido>> mAllPedidos;

    private PlatoDao mPlatoDao;
    private LiveData<List<Plato>> mAllDishes;

    private EsPedidoDao mEsPedidoDao;

    
    public PedidoPlatoRepository(Application application) {
        PedidoPlatoRoomDatabase db = PedidoPlatoRoomDatabase.getDatabase(application);
        mPedidoDao = db.PedidoDao();
        //No se si hace falta coger todos por defecto
        mAllPedidos = mPedidoDao.getAllPedidosOrderedByNombre();

        mPlatoDao = db.PlatoDao();
        mAllDishes = mPlatoDao.getAllPlatos();

        mEsPedidoDao = db.EsPedidoDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.


    //-----------------------------------PEDIDOS----------------------------------------------------
    /** Obtiene todos los pedidos
     * @return un valor entero largo con el identificador de los pedidos que se han creado.
     */
    public LiveData<List<Pedido>> getAllPedidos() {
        return mAllPedidos;
    }

    /**
     * Valida si los atributos del pedido son validos.
     * @param pedido
     * @return True si solo los atributos del pedido son válidos.
     */
    private boolean pedidoValido(Pedido pedido) {
        if (pedido.getNombreCliente() == null || pedido.getNombreCliente().equals("")
                || pedido.getFechaRecogida() == null
                || pedido.getIdPedido() < 0 || pedido.getEstado() == null
                || (!pedido.getEstado().equals("SOLICITADO")
                && !pedido.getEstado().equals("PREPARADO") && !pedido.getEstado().equals("RECOGIDO"))
                || pedido.getTelefonoCliente() == null || pedido.getTelefonoCliente().length() != 9
                || pedido.getFechaRecogida() == null || !fechaValida(pedido.getFechaRecogida()))  {
            Log.d("PedidoPlatoRepository", "Pedido no introducido, no es valido");
            return false;


        } else {
            Log.d("PedidoPlatoRepository", "Pedido introducido, es valido");
            return true;

        }
    }

    /**
     * Valida si una fecha es valida, es decir, si el Dia no es Lunes y la hora esta entre 19:30 y 23:00
     * @param fecha
     * @return True si solo si la fecha es valida.
     */
    private boolean fechaValida(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Log.d("PedidoPlatoRepository", "Fecha introducida, no es valida, es lunes");
            return false;

        }
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);
        if ((hora > 19 || (hora == 19 && minutos >= 30)) && hora < 23) {
            Log.d("PedidoPlatoRepository", "Fecha introducida, es valida");
            return true;

        }
        Log.d("PedidoPlatoRepository", "Fecha introducida, no es valida, no esta entre las 19:30 y las 23:00");
        return false;


    }
    /** Inserta un pedido
     * @param pedido
     * @return un valor entero largo con el identificador del pedido que se ha creado.
     */
    public long insert(Pedido pedido) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);
        //final CountDownLatch latch = new CountDownLatch(1); // Para esperar a que se complete la inserción
        //final long[] result = {0};

        if (!pedidoValido(pedido)) {
            return -1;
        }
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value = mPedidoDao.insert(pedido);
            result.set(value);
            resource.release();
            //latch.countDown(); // Liberar el contador
        });
    
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }
    
        return result.get(); // Devolver el id del pedido insertado
    }

    /** Modifica una nota
     * @param pedido
     * @return un valor entero con el numero de filas modificadas.
     */
    public int update(Pedido pedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        if (!pedidoValido(pedido)) {

            return -1;
        }
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mPedidoDao.update(pedido));
            Log.d("PedidoPlatoRepository", "Pedido introducido");

            resource.release();
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e ){
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }
        return result.get();
    }

    /** Elimina un pedido
     * @param pedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(Pedido pedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);
        if (!pedidoValido(pedido)) {
            return -1;
        }
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
           result.set(mPedidoDao.delete(pedido));
           resource.release();
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e ){
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }
        return result.get();
    }

    /**
     * @return Devuelve la lista de Pedidos ordenados por fecha
     */
    public LiveData<List<Pedido>> obtenerPedidosOrdenados(String criterio) {
        switch (criterio) {
            case "FECHA":
                return mPedidoDao.getAllPedidosOrderedByDate();
            case "NUMERO":
                return mPedidoDao.getAllPedidosOrderedByTelefono();
            case "NOMBRE":
                return mPedidoDao.getAllPedidosOrderedByNombre();
            default:
                return mPedidoDao.getAllPedidosOrderedByDate();
        }

    }

    /**
     *
     * @param filtro
     * @return lista de pedidos filtrados.
     */
    public LiveData<List<Pedido>> obtenerPedidosFiltrado(String filtro) {
        return mPedidoDao.getAllPedidosFiltered(filtro);
    }

    /**
     *
     * @param filtro
     * @param criterio
     * @return lista de pedidos filtrados y ordenados.
     */
    public LiveData<List<Pedido>> obtenerPedidosFiltradoYOrdenado(String filtro, String criterio) {
        switch (criterio) {
            case "Fecha":
                return mPedidoDao.getAllPedidosFilteredAndOrderedByDate(filtro);
            case "Nombre de Cliente":
                return mPedidoDao.getAllPedidosFilteredAndOrderedByName(filtro);
            case "Número de Teléfono":
                return mPedidoDao.getAllPedidosFilteredAndOrderedByNumber(filtro);
            default:
                return mPedidoDao.getAllPedidosFilteredAndOrderedByDate(filtro);
        }
    }




    //-----------------------------------PLATOS-----------------------------------------------------

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Plato>> getAllPlatos() {
        return mAllDishes;
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    /** Inserta un plato
     * @param plato
     * @return un valor entero largo con el identificador del plato que se ha creado.
     */
    public long insert(Plato plato) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        if (!platoValido(plato)) {
            return -1;
        }
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value = mPlatoDao.insert(plato);
            result.set(value);
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del plato insertado
    }

    /**
     * Valida si los atributos del plato son validos para insertar en la base de datos.
     * @param plato
     * @return true si solo si, cumple las restricciones para ser un plato valido.
     */
    private boolean platoValido(Plato plato) {
        if (plato.getNombre() == null || plato.getNombre().equals("")
                || plato.getPrecio() == null || plato.getPrecio() < 0.0
                || plato.getCategoria() == null || plato.getDescripcion() == null
                || plato.getIdPlato() < 0 || (!plato.getCategoria().equals("PRIMERO")
                && !plato.getCategoria().equals("SEGUNDO") && !plato.getCategoria().equals("POSTRE")))  {
            return false;
        } else {
            return true;
        }
    }

    /** Modifica una nota
     * @param plato
     * @return un valor entero con el numero de filas modificadas.
     */
    public int update(Plato plato) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        if (!platoValido(plato)) {
            return -1;
        }
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mPlatoDao.update(plato));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del plato actualizado
    }

    /** Elimina una nota
     * @param plato
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(Plato plato) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);
        if (!platoValido(plato)) {
            return -1;
        }
        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mPlatoDao.delete(plato));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del plato eliminado
    }




    /**
     * @return Devuelve la lista de Platos ordenados por nombre
     */
    public LiveData<List<Plato>> obtenerPlatosPorNombre() {
        return mPlatoDao.getOrderedPlatosByName();
    }

    /**
     * @return Devuelve la lista de Platos ordenados por categoria
     */
    public LiveData<List<Plato>> obtenerPlatosPorCategoria() {
        return mPlatoDao.getOrderedPlatosByCategory();
    }

    /**
     * @return Devuelve la lista de Platos ordenados por categoria y nombre
     */
    public LiveData<List<Plato>> obtenerPlatosPorCategoriayNombre() {
        return mPlatoDao.getOrderedPlatosByCategoryAndName();
    }

    public LiveData<List<Plato>> obtenerPlatosFiltradoYOrdenado(String filtro, String criterio) {
        switch (criterio) {
            case "Ambos":
                return mPlatoDao.getAllPlatosFilteredAndOrderedByCategoryAndName(filtro);
            case "Nombre del plato":
                return mPlatoDao.getAllPlatosFilteredAndOrderedByName(filtro);
            case "Categoría del plato":
                return mPlatoDao.getAllPlatosFilteredAndOrderedByCategory(filtro);
            default:
                return mPlatoDao.getAllPlatosFilteredAndOrderedByName(filtro);
        }
    }
    //-----------------------------------ESPEDIDO-----------------------------------------------------------

    /** Inserta un plato en un pedido
     * @param plato
     * @return un valor entero largo con el identificador del plato que se ha creado.
     */
    public long insert(EsPedido esPedido) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value = mEsPedidoDao.insert(esPedido);
            result.set(value);
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del esPedido insertado
    }

    /** Modifica una nota
     * @param esPedido
     * @return un valor entero con el numero de filas modificadas.
     */
    public long update(EsPedido esPedido) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mEsPedidoDao.update(esPedido));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del esPedido actualizado
    }

    /** Elimina la relación entre Pedido y Plato
     * @param esPedido
     * @return un valor entero con el numero de filas eliminadas.
     */
    public int delete(EsPedido esPedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        PedidoPlatoRoomDatabase.databaseWriteExecutor.execute(() -> {
            result.set(mEsPedidoDao.delete(esPedido));
            resource.release();
        });

        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
            //latch.await(); // Esperar hasta que la inserción se complete
        } catch (InterruptedException e) {
            Log.d ( " PedidoPlatoRepository " , " InterruptedException : " + e . getMessage ( ) );
        }

        return result.get(); // Devolver el id del esPedido eliminado
    }

    public LiveData<List<EsPedido>> obtenerEsPedidoPorIdPedido(int idPedido) {
        return mEsPedidoDao.getPlatoPedido(idPedido);
    }


    


}
