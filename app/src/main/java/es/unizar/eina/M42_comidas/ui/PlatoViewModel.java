package es.unizar.eina.M42_comidas.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.database.PedidoPlatoRepository;

/** Clase utilizada como modelo de vista del plato */
public class PlatoViewModel extends AndroidViewModel {

    private PedidoPlatoRepository mRepository;

    private final LiveData<List<Plato>> mAllPlatos;

    /**
     * Constructor de la clase
     * @param application
     */
    public PlatoViewModel(Application application) {
        super(application);
        mRepository = new PedidoPlatoRepository(application);
        mAllPlatos = mRepository.getAllPlatos();
    }

    /**
     * Devuelve una lista de platos filtrados por filtro y ordenados por criterio
     * @param filtro
     * @param criterio
     * @return una lista de platos filtrados por filtro y ordenados por criterio
     */
    public LiveData<List<Plato>> obtenerPlatosFiltradosyOrdenados(String filtro, String criterio) {
        return mRepository.obtenerPlatosFiltradoYOrdenado(filtro,criterio);
    }

    /**
     * Devuelve una lista de platos ordenados por criterio
     * @param criterio
     * @return una lista de platos ordenados por criterio
     */
    public LiveData<List<Plato>> obtenerPlatosOrdenados(String criterio) {
        switch (criterio) {
            case "Ambos":
                return mRepository.obtenerPlatosPorCategoriayNombre();
            case "Nombre del plato":
                return mRepository.obtenerPlatosPorNombre();
            case "Categoría del plato":
                return mRepository.obtenerPlatosPorCategoria();
            default:
                return mRepository.obtenerPlatosPorNombre();
        }
    }


    LiveData<List<Plato>> getAllPlatos() { return mAllPlatos; }

    /**
     * Inserta un plato en la base de datos
     * @param plato
     */
    public void insert(Plato plato) { mRepository.insert(plato); }

    /**
     * Actualiza un plato en la base de datos
     * @param plato
     */
    public void update(Plato plato) { mRepository.update(plato); }

    /**
     * Elimina un plato de la base de datos
     * @param plato
     */
    public void delete(Plato plato) { mRepository.delete(plato); }
}
