package Tests;

import Algorithms.Solution;
import Algorithms.TabuSearch;
import Functions.Ackley;
import Functions.Function;
import Functions.Sphere;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Test {

    public static final Integer UPPER_LIMIT = 100;                     // limite superior
    static final Integer LOWER_LIMIT = -100;                    // limite inferior
    static final Integer DIMENSIONS[] = {20, 50, 100};          // dimensiones vector solucion
    static final Integer OFE_MAX_NUMBER = 5000;                 // evaluaciones funcion objetivo
    static final Integer TESTS_NUMBER = 30;                     // nro de pruebas por cada conjunto de parametros

    // parametros lista tabu
    static final Integer TABU_LIST_SIZE[] = {50, 100, 150};     // tamaño lista tabu (parametro busqueda tabu)
    static final Integer NEIGHBORS_NUMBER[] = {10, 20};         // nro vecinos (parametro busqueda tabu)
    static final Double TWEAK_RADIO[] = {0.2d, 0.6d, 1.0d};     // radio tweak

    public Test() {
    }

    public void runSingle() {
        Ackley function = new Ackley(UPPER_LIMIT, LOWER_LIMIT, 20);
        TabuSearch tabuSearch = new TabuSearch(OFE_MAX_NUMBER, 0.6, function, 50, 20);
        tabuSearch.execute();
    }

    public void runAll() {
        write2Csv("FUNCION","DIMENSIONES", "RADIO", "VECINOS", "TLISTA TABU", "MAX F.O.", "MIN F.O.", "PROMEDIO F.O.");

        List<Function> functionList = new ArrayList<>();
        functionList.add(new Sphere(UPPER_LIMIT, LOWER_LIMIT, 20));
        functionList.add(new Sphere(UPPER_LIMIT, LOWER_LIMIT, 50));
        functionList.add(new Sphere(UPPER_LIMIT, LOWER_LIMIT, 100));
        functionList.add(new Ackley(UPPER_LIMIT, LOWER_LIMIT, 20));
        functionList.add(new Ackley(UPPER_LIMIT, LOWER_LIMIT, 50));
        functionList.add(new Ackley(UPPER_LIMIT, LOWER_LIMIT, 100));

        for (Function function : functionList) {
//            for (int dimensions : DIMENSIONS) {
                for (double tweakRadio : TWEAK_RADIO) {
                    for (int neighborsNumber : NEIGHBORS_NUMBER) {
                        for (int tabuListSize : TABU_LIST_SIZE) {

                            List<Solution> solutionsByTests = new ArrayList<>();
                            for (int i = 0; i < TESTS_NUMBER; i++) {
                                TabuSearch tabuSearch =
                                        new TabuSearch(OFE_MAX_NUMBER, tweakRadio, function, tabuListSize, neighborsNumber);
                                tabuSearch.execute();
                                solutionsByTests.add(tabuSearch.best);
                            }
                            DoubleSummaryStatistics summary =
                                    solutionsByTests.stream()
                                            .mapToDouble(s -> s.quality)
                                            .summaryStatistics();
                            write2Csv(
                                    function.name,
                                    function.dimensionsNumber,
                                    tweakRadio,
                                    neighborsNumber,
                                    tabuListSize,
                                    String.format("%.2f", summary.getMax()),
                                    String.format("%.2f", summary.getMin()),
                                    String.format("%.2f", summary.getAverage())
                            );
                        }
                    }
                }

//            }
        }

    }

    private void write2Csv(Object... data) {
        try {
            FileWriter fileWriter = new FileWriter("resources/data.csv", true);
            CSVWriter writer = new CSVWriter(fileWriter);
            String line[] = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                line[i] = data[i].toString();
            }
            writer.writeNext(line);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
