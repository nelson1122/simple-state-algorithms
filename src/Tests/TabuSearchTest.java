package Tests;

import Algorithms.Solution;
import Algorithms.TabuSearch;
import Functions.*;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import static Tests.Parameters.*;

public class TabuSearchTest {
    public List<Function> FUNCTION_LIST;

    public TabuSearchTest() {
        writeCsvHeaders();
    }

    public void runSingle() {
//        Function function = new Sphere();
//        Function function = new Ackley();
//        Function function = new Schwefel();
        Function function = new Rastrigin();
        function.dimensionsNumber = 20;
        TabuSearch tabuSearch = new TabuSearch(function, 0.6, 50, 20);
        tabuSearch.execute();
    }

    public void runAll() {
        FUNCTION_LIST =
                new ArrayList<Function>() {{
                    add(new Sphere());      // Unimodal Separable
                    add(new Schwefel());    // Unimodal No separable
                    add(new Rastrigin());   // Multimodal separable
                    add(new Ackley());      // Multimodal no separable
                }};

        for (Function function : FUNCTION_LIST) {
            for (int dimensions : DIMENSIONS) {
                for (double tweakRadio : TWEAK_RADIO) {
                    for (int neighborsNumber : NEIGHBORS_NUMBER) {
                        for (int tabuListSize : TABU_LIST_SIZE) {

                            List<Solution> solutionsByTests = new ArrayList<>();
                            function.dimensionsNumber = dimensions;

                            for (int i = 0; i < TESTS_NUMBER; i++) {
                                TabuSearch tabuSearch =
                                        new TabuSearch(function, tweakRadio, tabuListSize, neighborsNumber);
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
                                    String.format("%.4f", summary.getMax()),
                                    String.format("%.4f", summary.getMin()),
                                    String.format("%.4f", summary.getAverage())
                            );

                        }
                    }
                }

            }
        }

    }

    public void writeCsvHeaders() {
        write2Csv("FUNCION",
                "DIMENSIONES",
                "RADIO",
                "VECINOS",
                "TLISTA TABU",
                "MAX F.O.",
                "MIN F.O.",
                "PROMEDIO F.O."
        );
    }

    private void write2Csv(Object... data) {
        try {
            FileWriter fileWriter = new FileWriter("resources/data_ts.csv", true);
            CSVWriter writer = new CSVWriter(fileWriter);
            String[] line = new String[data.length];
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
