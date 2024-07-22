package es.unizar.eina.M42_comidas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Date;
import androidx.room.TypeConverters;

@Database(entities = {Plato.class,Pedido.class,EsPedido.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PedidoPlatoRoomDatabase extends RoomDatabase {

    public abstract PlatoDao PlatoDao();

    private static volatile PedidoPlatoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract PedidoDao PedidoDao();
    public abstract EsPedidoDao EsPedidoDao();

    static PedidoPlatoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PedidoPlatoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PedidoPlatoRoomDatabase.class, "PedidoPlato_database")
                            .addCallback(sDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more Platos, just add them.

                PedidoDao daoPedido = INSTANCE.PedidoDao();
                daoPedido.deleteAll();
                
                PlatoDao daoPlato = INSTANCE.PlatoDao();
                daoPlato.deleteAll();

                EsPedidoDao daoEsPedido = INSTANCE.EsPedidoDao();
                daoEsPedido.deleteAll();

                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date fecha = null;
                try {
                    // Parsear la fecha a partir de una cadena
                     fecha = dateFormat.parse("21/12/2023 22:00");

                    // Imprimir la fecha
                    System.out.println(fecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Pedido pedido = new Pedido("Gariko","678903987",fecha,"SOLICITADO");
                daoPedido.insert(pedido);
                pedido = new Pedido("Gariko2","678903982",fecha,"SOLICITADO");
                daoPedido.insert(pedido);

                Plato plato = new Plato("Arroz con pollo", "Muy rico",9.99,"PRIMERO");
                Plato plato2 = new Plato("Pollo frito", "Muy rico",9.0,"PRIMERO");
                Plato plato3 = new Plato("Lentejas", "Muy rico",5.85,"SEGUNDO");
                Plato plato4 = new Plato("Paella de marisco", "Muy rico",19.99,"SEGUNDO");
                Plato plato5 = new Plato("Tacos de carne mixta", "Muy rico",4.75,"SEGUNDO");
                Plato plato6 = new Plato("Algo random", "Muy rico",6.5,"POSTRE");
                daoPlato.insert(plato);


                daoPlato.insert(plato2);
                
                daoPlato.insert(plato3);
                daoPlato.insert(plato4);
                daoPlato.insert(plato5);
                daoPlato.insert(plato6);
            });
        }
    };



}