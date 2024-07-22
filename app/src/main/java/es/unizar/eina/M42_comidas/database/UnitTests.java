package es.unizar.eina.M42_comidas.database;

import android.app.Application;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import kotlin.Unit;

public class UnitTests {
    private PedidoPlatoRepository repository;

    public UnitTests(Application app) {
        repository = new PedidoPlatoRepository(app);
    }
    // =============================================================================================
    //                  PRUEBAS UNITARIAS DE CAJA NEGRA
    // =============================================================================================

    public void pruebasCajaNegra() {
        Log.d("Pruebas para 'insert' en ", "Plato");
        pruebasCajaNegraInsertPlato();
        Log.d("Pruebas para 'update' en ", "Plato");
        pruebasCajaNegraUpdatePlato();
        Log.d("Pruebas para 'delete' en ", "Plato");
        pruebasCajaNegraDeletetPlato();

        Log.d("Pruebas para 'insert' en ", "Pedido");
        pruebasCajaNegraInsertPedido();
        Log.d("Pruebas para 'update' en ", "Pedido");
        pruebasCajaNegraUpdatePedido();
        Log.d("Pruebas para 'delete' en ", "Pedido");
        pruebasCajaNegraDeletePedido();
    }

    private void pruebasCajaNegraInsertPlato() {
        long resultado = 0;
        try {
            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA VALIDAS
            Plato caso_1 = new Plato("Arroz", "Muy rico", 10.0, "PRIMERO");
            Plato caso_2 = new Plato("Arroz", "Muy rico", 10.0, "SEGUNDO");
            Plato caso_3 = new Plato("Arroz", "Muy rico", 10.0, "POSTRE");

            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA NO VALIDAS
            Plato caso_4 = new Plato(null, "Muy rico", 10.0, "PRIMERO");
            Plato caso_5 = new Plato("", "Muy rico", 10.0, "PRIMERO");
            Plato caso_6 = new Plato("Arroz", null, 10.0, "PRIMERO");
            //Plato caso_7 = new Plato("Arroz", "Muy rico", null, "PRIMERO");
            Plato caso_8 = new Plato("Arroz", "Muy rico", -1.0, "PRIMERO");
            Plato caso_9 = new Plato("Arroz", "Muy rico", 10.0, null);
            Plato caso_10 = new Plato("Arroz", "Muy rico", 10.0, "OTRO");

            if ((resultado = repository.insert(caso_1)) <= 0) {
                throw new Exception("Caso 1: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 1", "Superado");
            if ((resultado = repository.insert(caso_2)) <= 0) {
                throw new Exception("Caso 2: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 2", "Superado");

            if ((resultado = repository.insert(caso_3)) <= 0) {
                throw new Exception("Caso 3: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 3", "Superado");

            if ((resultado = repository.insert(caso_4)) != -1) {
                throw new Exception("Caso 4: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 4", "Superado");
            if ((resultado = repository.insert(caso_5)) != -1) {
                throw new Exception("Caso 5: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 5", "Superado");
            if ((resultado = repository.insert(caso_6)) != -1) {
                throw new Exception("Caso 6: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 6", "Superado");
            /*if (repository.insert(caso_7) != -1) {
                throw new Exception("Caso 7: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
                Log.d("Caso 7", "Superado");
            }*/
            if ((resultado = repository.insert(caso_8)) != -1) {
                throw new Exception("Caso 8: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 8", "Superado");
            if ((resultado = repository.insert(caso_9)) != -1) {
                throw new Exception("Caso 9: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 9", "Superado");
            if ((resultado = repository.insert(caso_10)) != -1) {
                throw new Exception("Caso 10: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 10", "Superado");
        } catch (Throwable e) {
            Log.e("Error ", e.getMessage());
        }
    }

    private void pruebasCajaNegraUpdatePlato() {
        long resultado = 0;
        try {
            Plato base = new Plato("Arroz", "Muy rico", 10.0, "PRIMERO");
            long id = repository.insert(base);

            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA VALIDAS
            Plato caso_1 = new Plato("Arroz", "Muy rico", 10.0, "PRIMERO");
            caso_1.setIdPlato((int) id);
            Plato caso_2 = new Plato("Arroz", "Muy rico", 10.0, "SEGUNDO");
            caso_2.setIdPlato((int) id);
            Plato caso_3 = new Plato("Arroz", "Muy rico", 10.0, "POSTRE");
            caso_3.setIdPlato((int) id);

            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA NO VALIDAS
            Plato caso_4 = new Plato(null, "Muy rico", 10.0, "PRIMERO");
            caso_4.setIdPlato((int) id);
            Plato caso_5 = new Plato("", "Muy rico", 10.0, "PRIMERO");
            caso_5.setIdPlato((int) id);
            Plato caso_6 = new Plato("Arroz", null, 10.0, "PRIMERO");
            caso_6.setIdPlato((int) id);
            //Plato caso_7 = new Plato("Arroz", "Muy rico", null, "PRIMERO");
            Plato caso_8 = new Plato("Arroz", "Muy rico", -1.0, "PRIMERO");
            caso_8.setIdPlato((int) id);
            Plato caso_9 = new Plato("Arroz", "Muy rico", 10.0, null);
            caso_9.setIdPlato((int) id);
            Plato caso_10 = new Plato("Arroz", "Muy rico", 10.0, "OTRO");
            caso_10.setIdPlato((int) id);
            Plato caso_11 = new Plato("Arroz", "Muy rico", 10.0, "PRIMERO");
            caso_11.setIdPlato((int) id);

            if ((resultado = repository.update(caso_1)) <= 0) {
                throw new Exception("Caso 1: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 1", "Superado");
            if ((resultado = repository.update(caso_2)) <= 0) {
                throw new Exception("Caso 2: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 2", "Superado");

            if ((resultado = repository.update(caso_3)) <= 0) {
                throw new Exception("Caso 3: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 3", "Superado");

            if ((resultado = repository.update(caso_4)) != -1) {
                throw new Exception("Caso 4: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 4", "Superado");
            if ((resultado = repository.update(caso_5)) != -1) {
                throw new Exception("Caso 5: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 5", "Superado");
            if ((resultado = repository.update(caso_6)) != -1) {
                throw new Exception("Caso 6: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 6", "Superado");
            /*if (repository.insert(caso_7) != -1) {
                throw new Exception("Caso 7: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
                Log.d("Caso 7", "Superado");
            }*/
            if ((resultado = repository.update(caso_8)) != -1) {
                throw new Exception("Caso 8: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 8", "Superado");
            if ((resultado = repository.update(caso_9)) != -1) {
                throw new Exception("Caso 9: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 9", "Superado");
            if ((resultado = repository.update(caso_10)) != -1) {
                throw new Exception("Caso 10: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 10", "Superado");
        } catch (Throwable e) {
            Log.e("Error ", e.getMessage());
        }
    }

    private void pruebasCajaNegraDeletetPlato() {
        int resultado = 0;
        try {
            Plato base = new Plato("Arroz", "Muy rico", 10.0, "PRIMERO");
            long id_base = repository.insert(base);
            Plato caso_1 = base;
            // CLASE DE EQUIVALENCIA VALIDA
            caso_1.setIdPlato((int) id_base);
            if ((resultado = repository.delete(caso_1)) <= 0) {
                throw new Exception("Caso 1: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 1", "Superado");

            // CLASE DE EQUIVALENCIA NO VALIDA
            Plato caso_2 = base;
            caso_2.setIdPlato((int) -id_base);
            if ((resultado = repository.delete(caso_2)) != -1) {
                throw new Exception("Caso 2: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 1", "Superado");
        } catch (Throwable e) {
            Log.e("Error ", e.getMessage());
        }
    }

    public void pruebasCajaNegraInsertPedido() {
        long resultado = 0;
        SimpleDateFormat martesAlas22 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaMartesAlas22 = new Date();
        Date fechaLunesAlas22 = new Date();
        Date fechaMartesAlas19 = new Date();
        Date fechaMartesAlas2330 = new Date();
        try {
            fechaMartesAlas22 = martesAlas22.parse("19/12/2023 22:00");
            fechaLunesAlas22 = martesAlas22.parse("18/12/2023 22:00");
            fechaMartesAlas19 = martesAlas22.parse("19/12/2023 19:00");
            fechaMartesAlas2330 = martesAlas22.parse("19/12/2023 23:30");
        } catch (ParseException e) {
            Log.e("Error", "Error al parsear la fecha: " + e.getMessage());
        }

        try {
            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA VALIDAS
            Pedido caso_1 = new Pedido("Pedro", "646664464", fechaMartesAlas22, "SOLICITADO");
            Pedido caso_2 = new Pedido("Pedro", "646664464", fechaMartesAlas22, "PREPARADO");
            Pedido caso_3 = new Pedido("Pedro", "646664464", fechaMartesAlas22, "RECOGIDO");
            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA NO VALIDAS
            Pedido caso_4 = new Pedido(null, "646664464", fechaMartesAlas22, "RECOGIDO");
            Pedido caso_5 = new Pedido("", "646664464", fechaMartesAlas22, "RECOGIDO");
            Pedido caso_6 = new Pedido("Pedro", "11111111", fechaMartesAlas22, "RECOGIDO");
            //Pedido caso_7 = new Pedido("Pedro", "-11111111", fechaMartesAlas22, "RECOGIDO");
            Pedido caso_8 = new Pedido("Pedro", null, fechaMartesAlas22, "RECOGIDO");
            Pedido caso_9 = new Pedido("Pedro","646664464" , fechaMartesAlas22, "OTRO");
            Pedido caso_10 = new Pedido("Pedro","646664464" , fechaMartesAlas22, null);
            Pedido caso_11 = new Pedido("Pedro","646664464" , fechaLunesAlas22, "RECOGIDO");
            Pedido caso_12 = new Pedido("Pedro","646664464" , fechaMartesAlas19, "RECOGIDO");
            Pedido caso_13 = new Pedido("Pedro","646664464" , fechaMartesAlas2330, "RECOGIDO");
            Pedido caso_14 = new Pedido("Pedro","646664464" , null, "RECOGIDO");
            if (repository.insert(caso_1) <= 0) {
                throw new Exception("Caso 1: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 1", "Superado");
            if (repository.insert(caso_2) <= 0) {
                throw new Exception("Caso 2: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 2", "Superado");

            if (repository.insert(caso_3) <= 0) {
                throw new Exception("Caso 3: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 3", "Superado");

            if (repository.insert(caso_4) != -1) {
                throw new Exception("Caso 4: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 4", "Superado");
            if (repository.insert(caso_5) != -1) {
                throw new Exception("Caso 5: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 5", "Superado");
            if (repository.insert(caso_6) != -1) {
                throw new Exception("Caso 6: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 6", "Superado");
            /*if (repository.insert(caso_7) != -1) {
                throw new Exception("Caso 7: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
                Log.d("Caso 7", "Superado");
            }*/
            if (repository.insert(caso_8) != -1) {
                throw new Exception("Caso 8: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 8", "Superado");
            if (repository.insert(caso_9) != -1) {
                throw new Exception("Caso 9: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 9", "Superado");
            if (repository.insert(caso_10) != -1) {
                throw new Exception("Caso 10: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 10", "Superado");
            if (repository.insert(caso_11) != -1) {
                throw new Exception("Caso 11: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 11", "Superado");
            if (repository.insert(caso_12) != -1) {
                throw new Exception("Caso 12: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 12", "Superado");
            if (repository.insert(caso_13) != -1) {
                throw new Exception("Caso 13: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 13", "Superado");
            if (repository.insert(caso_14) != -1) {
                throw new Exception("Caso 14: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 14", "Superado");
        } catch (Throwable e) {
            Log.e("Error ", e.getMessage());
        }
    }

    public void pruebasCajaNegraUpdatePedido() {
        long resultado = 0;
        SimpleDateFormat martesAlas22 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaMartesAlas22 = new Date();
        Date fechaLunesAlas22 = new Date();
        Date fechaMartesAlas19 = new Date();
        Date fechaMartesAlas2330 = new Date();
        try {
            fechaMartesAlas22 = martesAlas22.parse("19/12/2023 22:00");
            fechaLunesAlas22 = martesAlas22.parse("18/12/2023 22:00");
            fechaMartesAlas19 = martesAlas22.parse("19/12/2023 19:00");
            fechaMartesAlas2330 = martesAlas22.parse("19/12/2023 23:30");
        } catch (ParseException e) {
            Log.e("Error", "Error al parsear la fecha: " + e.getMessage());
        }

        try {
            Pedido base = new Pedido("Pedro", "646664464", fechaMartesAlas22, "SOLICITADO");
            long id = repository.insert(base);
            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA VALIDAS
            Pedido caso_1 = new Pedido("Pedro", "646664464", fechaMartesAlas22, "SOLICITADO");
            caso_1.setIdPedido((int) id);
            Pedido caso_2 = new Pedido("Pedro", "646664464", fechaMartesAlas22, "PREPARADO");
            caso_2.setIdPedido((int) id);
            Pedido caso_3 = new Pedido("Pedro", "646664464", fechaMartesAlas22, "RECOGIDO");
            caso_3.setIdPedido((int) id);
            // CASOS DE PRUEBA PARA LAS CLASES DE EQUIVALENCIA NO VALIDAS
            Pedido caso_4 = new Pedido(null, "646664464", fechaMartesAlas22, "RECOGIDO");
            caso_4.setIdPedido((int) id);
            Pedido caso_5 = new Pedido("", "646664464", fechaMartesAlas22, "RECOGIDO");
            caso_5.setIdPedido((int) id);
            Pedido caso_6 = new Pedido("Pedro", "11111111", fechaMartesAlas22, "RECOGIDO");
            caso_6.setIdPedido((int) id);
            Pedido caso_7 = new Pedido("Pedro", "-11111111", fechaMartesAlas22, "RECOGIDO");
            caso_7.setIdPedido((int) id);
            Pedido caso_8 = new Pedido("Pedro", null, fechaMartesAlas22, "RECOGIDO");
            caso_8.setIdPedido((int) id);
            Pedido caso_9 = new Pedido("Pedro","646664464" , fechaMartesAlas22, "OTRO");
            caso_9.setIdPedido((int) id);
            Pedido caso_10 = new Pedido("Pedro","646664464" , fechaMartesAlas22, null);
            caso_10.setIdPedido((int) id);
            Pedido caso_11 = new Pedido("Pedro","646664464" , fechaLunesAlas22, "RECOGIDO");
            caso_11.setIdPedido((int) id);
            Pedido caso_12 = new Pedido("Pedro","646664464" , fechaMartesAlas19, "RECOGIDO");
            caso_12.setIdPedido((int) id);
            Pedido caso_13 = new Pedido("Pedro","646664464" , fechaMartesAlas2330, "RECOGIDO");
            caso_13.setIdPedido((int) id);
            Pedido caso_14 = new Pedido("Pedro","646664464" , null, "RECOGIDO");
            caso_14.setIdPedido((int) id);
            Pedido caso_15 = new Pedido("Pedro","646664464" , fechaMartesAlas22, "RECOGIDO");
            caso_15.setIdPedido((int) -id);
            if (repository.update(caso_1) <= 0) {
                throw new Exception("Caso 1: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 1", "Superado");
            if (repository.update(caso_2) <= 0) {
                throw new Exception("Caso 2: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 2", "Superado");

            if (repository.update(caso_3) <= 0) {
                throw new Exception("Caso 3: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 3", "Superado");

            if (repository.update(caso_4) != -1) {
                throw new Exception("Caso 4: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 4", "Superado");
            if (repository.update(caso_5) != -1) {
                throw new Exception("Caso 5: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 5", "Superado");
            if (repository.update(caso_6) != -1) {
                throw new Exception("Caso 6: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 6", "Superado");
            /*if (repository.insert(caso_7) != -1) {
                throw new Exception("Caso 7: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
                Log.d("Caso 7", "Superado");
            }*/
            if (repository.update(caso_8) != -1) {
                throw new Exception("Caso 8: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 8", "Superado");
            if (repository.update(caso_9) != -1) {
                throw new Exception("Caso 9: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 9", "Superado");
            if (repository.update(caso_10) != -1) {
                throw new Exception("Caso 10: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 10", "Superado");
            if (repository.update(caso_11) != -1) {
                throw new Exception("Caso 11: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 11", "Superado");
            if (repository.update(caso_12) != -1) {
                throw new Exception("Caso 12: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 12", "Superado");
        } catch (Throwable e) {
            Log.e("Error ", e.getMessage());
        }
    }

    private void pruebasCajaNegraDeletePedido() {
        int resultado = 0;
        SimpleDateFormat martesAlas22 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaMartesAlas22 = new Date();
        try {
            fechaMartesAlas22 = martesAlas22.parse("19/12/2023 22:00");
        } catch (ParseException e) {
            Log.e("Error", "Error al parsear la fecha: " + e.getMessage());
        }
        try {
            Pedido base = new Pedido("Pedro", "646664464", fechaMartesAlas22, "SOLICITADO");
            long id_base = repository.insert(base);
            Pedido caso_1 = base;
            // CLASE DE EQUIVALENCIA VALIDA
            caso_1.setIdPedido((int) id_base);
            if ((resultado = repository.delete(caso_1)) <= 0) {
                throw new Exception("Caso 1: Se esperaba > 0, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 1", "Superado");

            // CLASE DE EQUIVALENCIA NO VALIDA
            Pedido caso_2 = base;
            caso_2.setIdPedido((int) -id_base);
            if ((resultado = repository.delete(caso_2)) != -1) {
                throw new Exception("Caso 2: Se esperaba -1, se ha obtenido " + String.valueOf(resultado));
            }
            Log.d("Caso 2", "Superado");
        } catch (Throwable e) {
            Log.e("Error ", e.getMessage());
        }
    }

    // =============================================================================================
    //                  PRUEBAS DE VOLUMEN
    // =============================================================================================
    public void pruebasVolumen() {
        // PRUEBA, TENER MAS DE 100 PLATOS.
        pruebaMasDe100Platos();
        pruebaMasDe2000Pedidos();
        Log.i("Prueba de volumen", "finalizada");
    }

    private void pruebaMasDe100Platos() {
        Plato base;
        long resultado = 0;
        for (int i = 0; i < 100; i++) {
            base = new Plato ("Arroz" + i, "Muy rico", 10.0, "PRIMERO");
            resultado = repository.insert(base);
            if (resultado <= 0) {
                Log.e("Error al insertar el plato", "resultado obtenido" + String.valueOf(resultado));
            }

        }
    }

    private void pruebaMasDe2000Pedidos() {
        SimpleDateFormat martesAlas22 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaMartesAlas22 = new Date();
        try {
            fechaMartesAlas22 = martesAlas22.parse("19/12/2023 22:00");
        } catch (ParseException e) {
            Log.e("Error", "Error al parsear la fecha: " + e.getMessage());
        }
        Pedido base;
        long resultado = 0;
        for (int i = 0; i < 2000; i++) {
            base = new Pedido("Pedro" + i, "646664464",fechaMartesAlas22,"SOLICITADO" );
            resultado = repository.insert(base);
            if (resultado <= 0) {
                Log.e("Error al insertar el pedido", "resultado obtenido" + String.valueOf(resultado));
            }

        }
    }

    // =============================================================================================
    //                  PRUEBAS DE SOBRECARGA
    // =============================================================================================

    public void pruebaSobrecarga() {
        for (int longDesc = 1000; longDesc < 2e20; longDesc *= 10) {
            String desc = new String(new char[longDesc]).replace("\0", "a");
            Plato base = new Plato ("Arroz", desc, 10.0, "PRIMERO");
            Log.i("Añadiendo plato con longitud: ", String.valueOf(longDesc));
            repository.insert(base);
        }
    }
}
