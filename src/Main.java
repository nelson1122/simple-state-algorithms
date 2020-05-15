import Algorithm.TabuSearch;
import Functions.SphereFunction;

public class Main {
    static final Integer UPPERLIMIT = 100;  // limite superior
    static final Integer LOWERLIMIT = -100; // limite inferior
    static final Integer DIMENSIONS = 200;  // dimensiones vector solucion
    static final Integer OFEMAXNUMBER = 500;    // evaluaciones funcion objetivo
    static final Integer TABULISTSIZE = 50; // tamaño lista tabu (parametro busqueda tabu)
    static final Integer TWEAKSNUMBER = 20; // nro tweaks (parametro busqueda tabu)
    static final Double RADIO = 0.2;

    public static void main(String[] args) {
        System.out.println("Program has started!");

        SphereFunction sphereFunction = new SphereFunction(UPPERLIMIT, LOWERLIMIT, DIMENSIONS);
        TabuSearch tabuSearch = new TabuSearch(OFEMAXNUMBER, RADIO, sphereFunction, TABULISTSIZE, TWEAKSNUMBER);
        tabuSearch.execute();

        System.out.println("Program has finished!");
    }

}
