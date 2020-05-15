package Tests;

public class Parameters {
    public static final Integer UPPER_LIMIT = 100;                     // limite superior
    public static final Integer LOWER_LIMIT = -100;                    // limite inferior
    public static final Integer[] DIMENSIONS = {20, 50, 100};          // dimensiones vector solucion
    public static final Integer OFE_MAX_NUMBER = 5000;                 // evaluaciones funcion objetivo
    public static final Integer TESTS_NUMBER = 30;                     // nro de pruebas por cada conjunto de parametros

    // parametros lista tabu
    public static final Integer[] TABU_LIST_SIZE = {50, 100, 150};     // tamaño lista tabu (parametro busqueda tabu)
    public static final Integer[] NEIGHBORS_NUMBER = {10, 20};         // nro vecinos (parametro busqueda tabu)
    public static final Double[] TWEAK_RADIO = {0.2d, 0.6d, 1.0d};     // radio tweak
}
