
package es.unizar.eina.M42_comidas.ui;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


import es.unizar.eina.M42_comidas.database.Plato;


/** Adaptador para la lista de platos. */
public class PlatoListAdapterPedidos extends ListAdapter<Plato, PlatoViewHolderPedidos> {
    private int position;
    
    /**
     * Devuelve la posición del plato seleccionado.
     * @return posición del plato seleccionado.
     */
    public int getPosition() {
        return position;
    }
    /**
     * Establece la posición del plato seleccionado.
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /** 
     * Constructor del adaptador.
     */
    public PlatoListAdapterPedidos(@NonNull DiffUtil.ItemCallback<Plato> diffCallback) {
        super(diffCallback);
    }

    /**
     * Crea un nuevo ViewHolder.
     * @param parent
     * @param viewType
     * @return un nuevo ViewHolder.
     */
    @Override
    public PlatoViewHolderPedidos onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlatoViewHolderPedidos.create(parent);
    }

    /**
     * Devuelve el plato seleccionado.
     * @return plato seleccionado.
     */
    public Plato getCurrent() {
        return getItem(getPosition());
    }

    /**
     * Enlaza los datos de un plato con la vista.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(PlatoViewHolderPedidos holder, int position) {
        Plato current = getItem(position);
        ElemEsPedido elemEsPedido = new ElemEsPedido();
        GlobalState globalState = GlobalState.getInstance();
        elemEsPedido.platoId = current.getIdPlato();
        elemEsPedido.cantidad = globalState.obtenerDelMapaCantidad(elemEsPedido.platoId); //luego habrá que hacer una consulta para obtener este dato en el caso de actualizar
        if (globalState.existeEnMapa(elemEsPedido.platoId)==1) {
            elemEsPedido.precio = globalState.obtenerDelMapaPrecio(elemEsPedido.platoId);
        }else{
            elemEsPedido.precio = current.getPrecio();
        }
        

        holder.bind(current.getNombre(),elemEsPedido);

    }

    
    static class PlatoDiff extends DiffUtil.ItemCallback<Plato> {

        @Override
        public boolean areItemsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            return oldItem.getIdPlato() == newItem.getIdPlato();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Plato oldItem, @NonNull Plato newItem) {
            return oldItem.getNombre().equals(newItem.getNombre()) &&
                    oldItem.getDescripcion().equals(newItem.getDescripcion()) &&
                    oldItem.getCategoria().equals(newItem.getCategoria()) &&
                    oldItem.getPrecio() == newItem.getPrecio();

        }


    }
}
