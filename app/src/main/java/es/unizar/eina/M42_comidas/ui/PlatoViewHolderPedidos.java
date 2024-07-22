package es.unizar.eina.M42_comidas.ui;

import android.util.Log;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M42_comidas.R;

/** Clase que representa un plato en la lista de platos de un pedido. */
public class PlatoViewHolderPedidos extends RecyclerView.ViewHolder {
    private final TextView mNombreItemView;
    private final TextView mCantidadItemView;

    private Button incrementButton;
    private Button decrementButton;
    GlobalState globalState;
    private ElemEsPedido elemEsPedido;


    
    private PlatoViewHolderPedidos(View itemView) {
        super(itemView);
        mNombreItemView = itemView.findViewById(R.id.nombre_plato);
        mCantidadItemView = itemView.findViewById(R.id.num_cantidad_plato);
        incrementButton = itemView.findViewById(R.id.boton_anyadir);
        decrementButton = itemView.findViewById(R.id.boton_quitar);
        
        
        incrementButton.setOnClickListener(v -> {
            elemEsPedido.cantidad++;
            globalState.agregarAlMapa(elemEsPedido.platoId, elemEsPedido);
            mCantidadItemView.setText(String.valueOf(elemEsPedido.cantidad));
        });

        decrementButton.setOnClickListener(v -> {
            if(elemEsPedido.cantidad > 0){
                elemEsPedido.cantidad--;
                globalState.agregarAlMapa(elemEsPedido.platoId, elemEsPedido);
            }
            mCantidadItemView.setText(String.valueOf(elemEsPedido.cantidad));
        });
        elemEsPedido = new ElemEsPedido();
    }

    /**
     * Metodo para enlazar los datos de un plato con la vista.
     * @param text
     * @param elemEsPedido
     */
    public void bind(String text,ElemEsPedido elemEsPedido) {
        
        globalState = GlobalState.getInstance();
        this.elemEsPedido = elemEsPedido;
        mNombreItemView.setText(text);
        mCantidadItemView.setText(String.valueOf(elemEsPedido.cantidad));

    }
    
    /**
     * Crea un nuevo ViewHolder.
     * @param parent
     * @return un nuevo ViewHolder.
     */
    static PlatoViewHolderPedidos create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seleccionar_plato_plantilla, parent, false);
        return new PlatoViewHolderPedidos(view);
    }
    

}
