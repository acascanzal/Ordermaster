package es.unizar.eina.M42_comidas.ui;

import android.util.Log;


import java.util.HashMap;
import java.util.Map;

import es.unizar.eina.M42_comidas.database.EsPedido;

/** Clase utilizada para seguir el patrón Singleton */
public class GlobalState {
    private static GlobalState instance;
    private Map<Integer, ElemEsPedido> cantidadPlatosMap;

    private GlobalState() {
        cantidadPlatosMap = new HashMap<>();
    }

    /**
     * Devuelve la instancia de la clase
     * @return instancia de la clase
     */
    public static GlobalState getInstance() {
        if (instance == null) {
            synchronized (GlobalState.class) {
                if (instance == null) {
                    instance = new GlobalState();
                }
            }
        }
        return instance;
    }

    /**
     * Devuelve un diccionario con la cantidad de platos
     * @return un diccionario con la cantidad de platos
     */
    public Map<Integer, ElemEsPedido> getCantidadPlatosMap() {
        return cantidadPlatosMap;
    }

    /**
     * Método para agregar un elemento al diccionario
     * @param clave
     * @param valor
     */
    public void agregarAlMapa(Integer clave, ElemEsPedido valor) {
        cantidadPlatosMap.put(clave, valor);

    }

    /**
     * Método para vaciar el diccionario.
     */
    public void vaciarMapa() {
        cantidadPlatosMap.clear();
    }

    /**
     * Dado una clave, obtenemos el id cuya clave es clave
     * @param clave
     * @return el id con clave, clave
     */
    public int obtenerDelMapaId(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return cantidadPlatosMap.get(clave).platoId;
        }else{

            return 0;
        }
    }

    /**
     * Dado una clave, obtenemos la cantidad cuya clave es clave
     * @param clave
     * @return el valor cantidad cuya clave es clave.
     */
    public int obtenerDelMapaCantidad(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return cantidadPlatosMap.get(clave).cantidad;
        }else{

            return 0;
        }
    }

    /**
     * Dado una clave, obtenemos el precio cuya clave es clave
     * @param clave
     * @return el valor precio cuya clave es clave.
     */
    public double obtenerDelMapaPrecio(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return cantidadPlatosMap.get(clave).precio;
        }else{

            return 0;
        }
    }

    /**
     * Comproueba si la clave existe en el diccionario.
     * @param clave
     * @return 1 si solo si, clave, existe en el diccionario.
     */
    public int existeEnMapa(Integer clave) {
        if(cantidadPlatosMap.get(clave) != null){
            return 1;
        }else{
            return 0;
        }
    }

}

