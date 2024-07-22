package es.unizar.eina.M42_comidas.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.PedidoPlatoRepository;

/** Clase utilizada como modelo de vista del pedido */
public class PedidoViewModel extends AndroidViewModel {

    private PedidoPlatoRepository mRepository;

    private final LiveData<List<Pedido>> mAllPedidos;


    public PedidoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        mAllPedidos = mRepository.getAllPedidos();
    }

    LiveData<List<Pedido>> getAllPedidos() { return mAllPedidos; }

    /**
     * Devuelve una lista de pedidos ordenados por criterio
     * @param criterio
     * @return una lista de pedidos ordenados por criterio
     */
    public LiveData<List<Pedido>> obtenerPedidosOrdenados(String criterio) {
        switch (criterio) {
            case "Fecha":
                return mRepository.obtenerPedidosOrdenados("FECHA");
            case "Nombre de Cliente":
                return mRepository.obtenerPedidosOrdenados("NOMBRE");
            case "Número de Teléfono":
                return mRepository.obtenerPedidosOrdenados("NUMERO");
            default:
                return mRepository.obtenerPedidosOrdenados("FECHA");
        }
    }

    /**
     * Devuelve una lista de pedidos filtrados por filtro
     * @param filtro
     * @return una lista de pedidos filtrados por filtro
     */
    public LiveData<List<Pedido>> obtenerPedidosFiltrados(String filtro) {
        switch (filtro) {
            case "PREPARADO":
                return mRepository.obtenerPedidosFiltrado("PREPARADO");
            case "SOLICITADO":
                return mRepository.obtenerPedidosFiltrado("SOLICITADO");
            case "RECOGIDO":
                return mRepository.obtenerPedidosFiltrado("RECOGIDO");
            default:
                return mRepository.obtenerPedidosFiltrado("PREPARADO");
        }
    }

    public LiveData<List<Pedido>> obtenerPedidosFiltradosyOrdenados(String filtroSeleccionado, String criterioSeleccionado) {
        return mRepository.obtenerPedidosFiltradoYOrdenado(filtroSeleccionado, criterioSeleccionado);
    }

    /**
     * Inserta un pedido en la base de datos
     * @param pedido
     * @return Id del pedido insertado
     */
    public long insert(Pedido pedido) {  return mRepository.insert(pedido);}

    /**
     * Actualiza un pedido en la base de datos
     * @param pedido
     */
    public void update(Pedido pedido) {mRepository.update(pedido); }

    /**
     * Elimina un pedido de la base de datos
     * @param pedido
     */
    public void delete(Pedido pedido) { mRepository.delete(pedido); }
}
