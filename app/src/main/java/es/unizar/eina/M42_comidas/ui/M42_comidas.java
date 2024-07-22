package es.unizar.eina.M42_comidas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.unizar.eina.M42_comidas.R;
import es.unizar.eina.M42_comidas.database.EsPedido;
import es.unizar.eina.M42_comidas.database.Pedido;
import es.unizar.eina.M42_comidas.database.Plato;
import es.unizar.eina.M42_comidas.database.UnitTests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;


/** Pantalla principal de la aplicacion M42_comidas */
public class M42_comidas extends AppCompatActivity {
    private PlatoViewModel mPlatoViewModel;
    private PedidoViewModel mPedidoViewModel;
    private EsPedidoViewModel mEsPedidoViewModel;
    private GlobalState globalState= GlobalState.getInstance();
        
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public static final int ACTIVITY_PLATOS_CREATE = 1;

    public static final int ACTIVITY_PLATOS_EDIT = 2;

    public static final int ACTIVITY_PEDIDO_CREATE = 3;

    public static final int ACTIVITY_PEDIDO_EDIT = 4;


    static final int INSERT_ID = Menu.FIRST;
    static final int DELETE_ID = Menu.FIRST + 1;
    static final int EDIT_ID = Menu.FIRST + 2;



    /**
     * Metodo que se ejecuta al crear la actividad que configurará los diferentes elementos
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        mPlatoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);
        mEsPedidoViewModel = new ViewModelProvider(this).get(EsPedidoViewModel.class);

        Button button_addPlato = findViewById(R.id.button_addPlato);
        button_addPlato.setOnClickListener(new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(M42_comidas.this, M42_editarPlato.class);
            startActivityForResult(intent, ACTIVITY_PLATOS_CREATE);

        }
        });

        Button button_listarPlato = findViewById(R.id.button_listarPlatos);
        button_listarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M42_comidas.this, M42_listarPlatos.class);
                startActivity(intent);
            }
        });

        Button button_addPedido = findViewById(R.id.button_addPedido);
        button_addPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M42_comidas.this, M42_editarPedido.class);
                globalState.vaciarMapa();
                startActivityForResult(intent, ACTIVITY_PEDIDO_CREATE);
            }
        });

        Button button_listarPedido = findViewById(R.id.button_listarPedidos);
        button_listarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M42_comidas.this, M42_listarPedidos.class);
                startActivity(intent);
            }
        });

        Button button_testCajaNegra = findViewById(R.id.botonTestCajaNegra);
        button_testCajaNegra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               UnitTests test = new UnitTests(getApplication());
               test.pruebasCajaNegra();
            }
        });

        Button button_testSobrecarga = findViewById(R.id.botonTestSobrecarga);
        button_testSobrecarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnitTests test = new UnitTests(getApplication());
                test.pruebaSobrecarga();
            }
        });

        Button button_testVolumen = findViewById(R.id.botonTestVolumen);
        button_testVolumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnitTests test = new UnitTests(getApplication());
                test.pruebasVolumen();
            }
        });
 
    }

    /**
     * Metodo que se ejecuta al volver a la actividad que configurará los diferentes elementos y ejecutará los diferentes metodos
     * @param requestCode
     * @param resultCode
     * @param data
     * 
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
            switch (requestCode) {
                case ACTIVITY_PLATOS_CREATE:
                    Plato newPlato = new Plato(extras.getString(M42_editarPlato.PLATO_NOMBRE)
                            , extras.getString(M42_editarPlato.PLATO_DESCRIPCION)
                            ,  Double.parseDouble(extras.getString(M42_editarPlato.PLATO_PRECIO))
                            , extras.getString(M42_editarPlato.PLATO_CATEGORIA));

                    mPlatoViewModel.insert(newPlato);
                    break;
                case ACTIVITY_PLATOS_EDIT:

                    int id = extras.getInt(M42_editarPlato.PLATO_ID);
                    Plato updatedPlato = new Plato(extras.getString(M42_editarPlato.PLATO_NOMBRE)
                            , extras.getString(M42_editarPlato.PLATO_DESCRIPCION)
                            , Double.parseDouble(extras.getString(M42_editarPlato.PLATO_PRECIO))
                            , extras.getString(M42_editarPlato.PLATO_CATEGORIA));
                    updatedPlato.setIdPlato(id);
                    mPlatoViewModel.update(updatedPlato);
                    break;
                case ACTIVITY_PEDIDO_CREATE:
                    Pedido newPedido = null;
                    try {
                        newPedido = new Pedido(extras.getString(M42_editarPedido.PEDIDO_NOMBRE_CLIENTE)
                                , extras.getString(M42_editarPedido.PEDIDO_TELEFONO)
                                ,  formato.parse(extras.getString(M42_editarPedido.PEDIDO_FECHA_RECOGIDA)),
                                extras.getString(M42_editarPedido.PEDIDO_ESTADO));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    SystemClock.sleep(1000);
                    long idPedido = mPedidoViewModel.insert(newPedido);
                    
                    Map<Integer, ElemEsPedido> map = globalState.getCantidadPlatosMap();

                    for (Map.Entry<Integer, ElemEsPedido> entry : map.entrySet()) {
                        Integer key = entry.getKey();
                        ElemEsPedido value = entry.getValue();
                        EsPedido esPedido = new EsPedido((int)idPedido,key, value.cantidad, value.precio);
                        mEsPedidoViewModel.insert(esPedido);
                    }
                        
                   
                    break;

                
                case ACTIVITY_PEDIDO_EDIT:

                    Pedido newIpdatedPedido = null;
                    try {
                        newIpdatedPedido = new Pedido(extras.getString(M42_editarPedido.PEDIDO_NOMBRE_CLIENTE)
                                , extras.getString(M42_editarPedido.PEDIDO_TELEFONO)
                                ,  formato.parse(extras.getString(M42_editarPedido.PEDIDO_FECHA_RECOGIDA)),
                                extras.getString(M42_editarPedido.PEDIDO_ESTADO));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    mPedidoViewModel.update(newIpdatedPedido);
                    break;
            }
        }
    }


}