package es.unizar.eina.M42_comidas.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M42_comidas.R;

/** Clase ViewHolder para la lista de platos. */
class PlatoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private final TextView mNombreView;
    private final TextView mCategoriaView;
    private final TextView mPrecioView;



    private PlatoViewHolder(View itemView) {
        super(itemView);
        mNombreView = itemView.findViewById(R.id.nombre_plato_lista);
        mCategoriaView = itemView.findViewById(R.id.categoria_plato_lista);
        mPrecioView = itemView.findViewById(R.id.precio_plato_lista);

        itemView.setOnCreateContextMenuListener(this);
    }

    /**
     * Metodo para enlazar los datos de un plato con la vista.
     * @param text
     * @param categoria
     * @param precio
     */
    public void bind(String text, String categoria, String precio) {
        mNombreView.setText(text);
        mCategoriaView.setText(categoria);
        mPrecioView.setText(precio);
    }

    static PlatoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plato_plantilla, parent, false);
        return new PlatoViewHolder(view);
    }

    // En este método esta la logica para mantener pulsado un plato y que aparezca la opción 
    // de eliminar o editar.
    /**
     * Metodo para crear el menu contextual de un plato.
     * @param menu
     * @param v
     * @param menuInfo
     */
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(Menu.NONE, M42_listarPlatos.DELETE_ID, Menu.NONE, R.string.menu_delete);
        menu.add(Menu.NONE, M42_listarPlatos.EDIT_ID, Menu.NONE, R.string.menu_edit);
    }


}
