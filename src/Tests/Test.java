package Tests;

import Algorithms.Solution;
import Algorithms.TabuSearch;
import Functions.Sphere;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Test {

    static final Integer UPPER_LIMIT = 100;                     // limite superior
    static final Integer LOWER_LIMIT = -100;                    // limite inferior
    static final Integer DIMENSIONS[] = {20, 50, 100};          // dimensiones vector solucion
    static final Integer OFE_MAX_NUMBER = 500;                  // evaluaciones funcion objetivo
    static final Integer TESTS_NUMBER = 30;                     // nro de pruebas por cada conjunto de parametros

    // parametros lista tabu
    static final Integer TABU_LIST_SIZE[] = {50, 100, 150};     // tamaño lista tabu (parametro busqueda tabu)
    static final Integer NEIGHBORS_NUMBER[] = {10, 20};         // nro vecinos (parametro busqueda tabu)
    static final Double TWEAK_RADIO[] = {0.2d, 0.6d, 1.0d};     // radio tweak

    public Test() {
    }

    public void runAll() {
        write2Csv("DIMENSIONES", "RADIO", "VECINOS", "TAMAÑO L", "MAX F.O.", "MIN F.O.", "PROMEDIO F.O.");
        for (int dimensions : DIMENSIONS) {
            for (double tweakRadio : TWEAK_RADIO) {
                for (int neighborsNumber : NEIGHBORS_NUMBER) {
                    for (int tabuListSize : TABU_LIST_SIZE) {

                        List<Solution> solutionsByTests = new ArrayList<>();
                        for (int i = 0; i < TESTS_NUMBER; i++) {
                            Sphere sphere =
                                    new Sphere(UPPER_LIMIT, LOWER_LIMIT, dimensions);
                            TabuSearch tabuSearch =
                                    new TabuSearch(OFE_MAX_NUMBER, tweakRadio, sphere, tabuListSize, neighborsNumber);
                            tabuSearch.execute();
                            solutionsByTests.add(tabuSearch.best);
                        }
                        DoubleSummaryStatistics summary =
                                solutionsByTests.stream()
                                        .mapToDouble(s -> s.quality)
                                        .summaryStatistics();
                        write2Csv(
                                dimensions,
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
