package es.unizar.eina.M42_comidas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.Plato;

/** Pantalla para listar los platos que hay en la aplicación */
public class M42_listarPlatos extends AppCompatActivity {
    private PlatoViewModel mPlatoViewModel;

    private PlatoListAdapter mPlatoAdapter;

    private Spinner spinnerOrdenarPor;
    private String filtroSeleccionado = "";
    private String criterioSeleccionado;

    public static final int ACTIVITY_CREATE = 1;
    public static final int ACTIVITY_EDIT = 2;

    public static final int EDIT_ID = 2;
    
    public static final int DELETE_ID = 3;




    /**
     * Metodo que se ejecuta al crear la actividad que configurará los diferentes elementos
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_platos);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlatos);
        mPlatoAdapter = new PlatoListAdapter(new PlatoListAdapter.PlatoDiff());
        recyclerView.setAdapter(mPlatoAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        mPlatoViewModel.getAllPlatos().observe(this,platos -> {
            mPlatoAdapter.submitList(platos);
        });

        spinnerOrdenarPor = findViewById(R.id.PlatosspinnerOrdenarPor);

        // Crear un ArrayAdapter utilizando el array de recursos opciones_ordenamiento
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.criterios_ordenamiento_platos, android.R.layout.simple_spinner_item);

        // Especificar el diseño a utilizar cuando la lista de opciones aparezca
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinnerOrdenarPor.setAdapter(adapter);

        // Configurar el listener para manejar las selecciones del Spinner
        spinnerOrdenarPor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Aquí puedes manejar la selección del usuario
                criterioSeleccionado = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Método llamado cuando no se ha seleccionado nada
                criterioSeleccionado = "";
            }
        });

        // Añade el listener al botón "Ordenar por"
        Button botonOrdenar = findViewById(R.id.boton_ordenar);
        botonOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FILTRO DE ORDENAR", criterioSeleccionado);

                if (filtroSeleccionado.equals("")) {
                    Log.d("FILTRO SELECCIONADO VACIO", filtroSeleccionado);
                    // Ordena los pedidos según el criterio seleccionado
                    mPlatoViewModel.obtenerPlatosOrdenados(criterioSeleccionado).observe(M42_listarPlatos.this, new Observer<List<Plato>>() {
                        @Override
                        public void onChanged(List<Plato> platos) {
                            // Actualiza tu adaptador con la nueva lista de pedidos
                            Log.d("ACTUALIZO", "PANTALLA");
                            mPlatoAdapter.submitList(platos);
                        }
                    });
                } else {
                    Log.d("FILTRO SELECCIONADO NO VACIO", filtroSeleccionado);
                    mPlatoViewModel.obtenerPlatosFiltradosyOrdenados(filtroSeleccionado, criterioSeleccionado).observe(M42_listarPlatos.this, new Observer<List<Plato>>() {
                        @Override
                        public void onChanged(List<Plato> platos) {
                            // Actualiza tu adaptador con la nueva lista de pedidos
                            Log.d("ACTUALIZO", "PANTALLA");
                            mPlatoAdapter.submitList(platos);
                        }
                    });
                }
            }
        });

        Button BotonfiltroPrimero = findViewById(R.id.boton_filtroPrimero);

        BotonfiltroPrimero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtroSeleccionado = "PRIMERO";
                Log.d("ENTRO EN", "PRIMERO");
                Log.d("FILTRO DE ORDENAR", criterioSeleccionado);
                mPlatoViewModel.obtenerPlatosFiltradosyOrdenados(filtroSeleccionado, criterioSeleccionado).observe(M42_listarPlatos.this, new Observer<List<Plato>>() {
                    @Override
                    public void onChanged(List<Plato> platos) {
                        // Actualiza tu adaptador con la nueva lista de pedidos
                        Log.d("ACTUALIZO", "PANTALLA");
                        mPlatoAdapter.submitList(platos);
                    }
                });
            }
        });

        Button BotonfiltroSegundo = findViewById(R.id.boton_filtroSegundo);

        BotonfiltroSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtroSeleccionado = "SEGUNDO";
                Log.d("ENTRO EN", "SEGUNDO");
                Log.d("FILTRO DE ORDENAR", criterioSeleccionado);
                mPlatoViewModel.obtenerPlatosFiltradosyOrdenados(filtroSeleccionado, criterioSeleccionado).observe(M42_listarPlatos.this, new Observer<List<Plato>>() {
                    @Override
                    public void onChanged(List<Plato> platos) {
                        // Actualiza tu adaptador con la nueva lista de pedidos
                        Log.d("ACTUALIZO", "PANTALLA");
                        mPlatoAdapter.submitList(platos);
                    }
                });
            }
        });

        Button BotonfiltroPostre = findViewById(R.id.boton_filtroPostre);

        BotonfiltroPostre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtroSeleccionado = "POSTRE";
                Log.d("ENTRO EN", "POSTRE");
                Log.d("FILTRO DE ORDENAR", criterioSeleccionado);
                mPlatoViewModel.obtenerPlatosFiltradosyOrdenados(filtroSeleccionado, criterioSeleccionado).observe(M42_listarPlatos.this, new Observer<List<Plato>>() {
                    @Override
                    public void onChanged(List<Plato> platos) {
                        // Actualiza tu adaptador con la nueva lista de pedidos
                        Log.d("ACTUALIZO", "PANTALLA");
                        mPlatoAdapter.submitList(platos);
                    }
                });
            }
        });

    }
    /**
     * Metodo que se ejecuta al volver a la actividad que configurará los diferentes elementos
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        

        if (resultCode != RESULT_OK) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            Bundle extras = data.getExtras();
            double precio = Double.parseDouble(extras.getString(M42_editarPlato.PLATO_PRECIO));;
            switch (requestCode) {
                case ACTIVITY_CREATE:
                    Plato newPlato = new Plato(extras.getString(M42_editarPlato.PLATO_NOMBRE)
                            , extras.getString(M42_editarPlato.PLATO_DESCRIPCION)
                            , precio
                            , extras.getString(M42_editarPlato.PLATO_CATEGORIA));
                    mPlatoViewModel.insert(newPlato);
                    break;
                case ACTIVITY_EDIT:
                    int id = extras.getInt(M42_editarPlato.PLATO_ID);
                     
                    Plato updatedPlato = new Plato(extras.getString(M42_editarPlato.PLATO_NOMBRE)
                            , extras.getString(M42_editarPlato.PLATO_DESCRIPCION)
                            , precio
                            , extras.getString(M42_editarPlato.PLATO_CATEGORIA));
                    updatedPlato.setIdPlato(id);
                    mPlatoViewModel.update(updatedPlato);
                    break;
            }
        }
    }

    /**
     * Metodo que se ejecuta al pulsar un elemento del menu contextual
     * @param view
     */
    public boolean onContextItemSelected(MenuItem item) {
        Plato current = mPlatoAdapter.getCurrent();
        switch (item.getItemId()) {
            case DELETE_ID:
                Toast.makeText(
                        getApplicationContext(),
                        "Deleting " + current.getNombre(),
                        Toast.LENGTH_LONG).show();
                mPlatoViewModel.delete(current);
                return true;
            case EDIT_ID:
                editarPlato(current);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Metodo que se ejecuta al pulsar un elemento de la lista
     * @param view
     */
    private void editarPlato(Plato current) {
        Intent intent = new Intent(this, M42_editarPlato.class);
        intent.putExtra(M42_editarPlato.PLATO_NOMBRE, current.getNombre());
        intent.putExtra(M42_editarPlato.PLATO_DESCRIPCION, current.getDescripcion());
        intent.putExtra(M42_editarPlato.PLATO_CATEGORIA, current.getCategoria());
        intent.putExtra(M42_editarPlato.PLATO_PRECIO, String.valueOf(current.getPrecio()));
        intent.putExtra(M42_editarPlato.PLATO_ID, current.getIdPlato());
        startActivityForResult(intent, ACTIVITY_EDIT);
    }

}
