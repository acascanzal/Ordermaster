
package es.unizar.eina.M42_comidas.ui;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M42_comidas.database.Plato;

/** Adaptador para la lista de platos. */
public class PlatoListAdapter extends ListAdapter<Plato, PlatoViewHolder> {
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
    public PlatoListAdapter(@NonNull DiffUtil.ItemCallback<Plato> diffCallback) {
        super(diffCallback);
    }

    /**
     * Crea un nuevo ViewHolder.
     * @param parent
     * @param viewType
     * @return un nuevo ViewHolder.
     */
    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlatoViewHolder.create(parent);
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
    public void onBindViewHolder(PlatoViewHolder holder, int position) {
        Plato current = getItem(position);
        holder.bind(current.getNombre(),current.getCategoria(),String.valueOf(current.getPrecio())); // Asegúrate de tener un método getNombrePlato en la clase Plato

        holder.itemView.setOnLongClickListener(v -> {
            setPosition(holder.getAdapterPosition());
            return false;
        });
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
