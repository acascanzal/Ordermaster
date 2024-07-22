package es.unizar.eina.M42_comidas.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.EsPedido;
import es.unizar.eina.M42_comidas.database.PedidoPlatoRepository;

/** Clase utilizada como modelo de vista del pedido */
public class EsPedidoViewModel extends AndroidViewModel {

    private PedidoPlatoRepository mRepository;


    /**
     * Constructor de EsPedidoViewModel
     * @param application
     */
    public EsPedidoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        

    }

    /**
     * Inserta un esPedido es la base de datos.
     * @param esPedido
     */
    public void insert(EsPedido esPedido) { mRepository.insert(esPedido); }

    /**
     * Actualiza un esPedido es la base de datos.
     * @param esPedido
     */
    public void update(EsPedido esPedido) { mRepository.update(esPedido); }

    /**
     * Elimina un esPedido es la base de datos.
     * @param esPedido
     */
    public void delete(EsPedido esPedido) { mRepository.delete(esPedido); }

    /**
     * Devuelve una lisa de esPedido cuyo idPedido es pedidoId
     * @param pedidoId
     * @return una lisa de esPedido cuyo idPedido es pedidoId
     */
    public LiveData<List<EsPedido>> getAllPlatosFromPedido(int pedidoId) { return mRepository.obtenerEsPedidoPorIdPedido(pedidoId); }
}
