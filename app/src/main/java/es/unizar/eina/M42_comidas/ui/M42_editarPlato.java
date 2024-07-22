package es.unizar.eina.M42_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import es.unizar.eina.M42_comidas.R;

/** Pantalla para editar o añadir un plato */
public class M42_editarPlato extends AppCompatActivity {
    public static final String PLATO_NOMBRE = "title";
    public static final String PLATO_CATEGORIA = "Primero";
    public static final String PLATO_PRECIO = "10.0";
    public static final String PLATO_DESCRIPCION = "body";
    public static final String PLATO_ID = "id";

    public static final int ACTIVITY_PLATOS_CREATE = 1;

    public static final int ACTIVITY_PLATOS_EDIT = 2;
    private PlatoViewModel mPlatoViewModel;

    private EditText mNombreText;
    private EditText mCategoriaText;
    private EditText mDescripcionText;
    private EditText mPrecio;
    private Integer mId;

    private Spinner spinnerCategoria;
    private String categoriaSeleccionada;

    Button mSaveButton;

    /**
     * Metodo que se ejecuta al crear la actividad que configurará los diferentes elementos
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_platos);

        mNombreText = findViewById(R.id.nombre_plato_crear_plato);
        //mCategoriaText = findViewById(R.id.categoria_plato_crear_plato);
        mPrecio = findViewById(R.id.precio_plato_crear_plato);
        mDescripcionText = findViewById(R.id.descripcion_plato_crear_plato);


        spinnerCategoria = findViewById(R.id.categoria_plato_crear_plato);

        // Crear un ArrayAdapter utilizando el array de recursos opciones_ordenamiento
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_plato, android.R.layout.simple_spinner_item);

        // Especificar el diseño a utilizar cuando la lista de opciones aparezca
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinnerCategoria.setAdapter(adapter);

        // Configurar el listener para manejar las selecciones del Spinner
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Aquí puedes manejar la selección del usuario
                categoriaSeleccionada = parentView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Método llamado cuando no se ha seleccionado nada

            }
        });

        mSaveButton = findViewById(R.id.button2);
        mSaveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mNombreText.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(M42_editarPlato.PLATO_NOMBRE, mNombreText.getText().toString());
                replyIntent.putExtra(M42_editarPlato.PLATO_DESCRIPCION, mDescripcionText.getText().toString());
                replyIntent.putExtra(M42_editarPlato.PLATO_PRECIO,mPrecio.getText().toString()); //COMPROBAR
                replyIntent.putExtra(M42_editarPlato.PLATO_CATEGORIA, categoriaSeleccionada);
                if (mId!=null) {
                    replyIntent.putExtra(M42_editarPlato.PLATO_ID, mId.intValue());
                }
                setResult(RESULT_OK, replyIntent);
            }
            // Se envian los datos a la clase que la invoco.
            finish();
        });
        populateFields ();
    }

    /**
     * Metodo que se ejecuta al crear la actividad para rellenar los campos de la actividad
     */
    private void populateFields () {
        mId = null;
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            mNombreText.setText(extras.getString(M42_editarPlato.PLATO_NOMBRE));
            categoriaSeleccionada = extras.getString(M42_editarPlato.PLATO_CATEGORIA);
            mPrecio.setText(extras.getString(M42_editarPlato.PLATO_PRECIO));
            mDescripcionText.setText(extras.getString(M42_editarPlato.PLATO_DESCRIPCION));
            mId = extras.getInt(M42_editarPlato.PLATO_ID);
        }
    }


}