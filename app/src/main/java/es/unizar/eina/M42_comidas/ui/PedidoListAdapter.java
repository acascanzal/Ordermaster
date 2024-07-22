
package es.unizar.eina.M42_comidas.ui;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.text.SimpleDateFormat;

import es.unizar.eina.M42_comidas.database.Pedido;

/** Adaptado para la lista de pedidos. */
public class PedidoListAdapter extends ListAdapter<Pedido, PedidoViewHolder> {
    private int position;
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /**
     * Devuelve la posición del pedido seleccionado.
     * @return posición del pedido seleccionado.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Establece la posición del pedido seleccionado.
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }


    public PedidoListAdapter(@NonNull DiffUtil.ItemCallback<Pedido> diffCallback) {
        super(diffCallback);
    }

    /**
     * Crea un nuevo ViewHolder.
     * @param parent
     * @param viewType
     * @return un nuevo ViewHolder.
     */
    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PedidoViewHolder.create(parent);
    }

    /**
     * Devuelve el pedido seleccionado.
     * @return pedido seleccionado.
     */
    public Pedido getCurrent() {
        return getItem(getPosition());
    }

    /**
     * Enlaza los datos de un pedido con la vista.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(PedidoViewHolder holder, int position) {
        Pedido current = getItem(position);


        holder.bind(current.getNombreCliente(),current.getTelefonoCliente(),formato.format(current.getFechaRecogida()));

        holder.itemView.setOnLongClickListener(v -> {
            Log.d("PedidoListAdapter", "Evento de click largo");
            setPosition(holder.getAdapterPosition());
            return false;
        });
    }

    static class PedidoDiff extends DiffUtil.ItemCallback<Pedido> {


        @Override
        public boolean areItemsTheSame(@NonNull Pedido oldItem, @NonNull Pedido newItem) {
            return oldItem.getIdPedido() == newItem.getIdPedido();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pedido oldItem, @NonNull Pedido newItem) {
            return oldItem.getNombreCliente().equals(newItem.getNombreCliente()) &&
                oldItem.getTelefonoCliente().equals(newItem.getTelefonoCliente()) &&
                oldItem.getFechaRecogida().equals(newItem.getFechaRecogida());
        }
    }
}
