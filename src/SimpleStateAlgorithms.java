import Tests.RandomSearchTest;
import Tests.TabuSearchTest;

public class SimpleStateAlgorithms {

    public static void main(String[] args) {
        System.out.println("Program has started!");

        TabuSearchTest tabuSearchTest = new TabuSearchTest();
        tabuSearchTest.runAll();
//        tabuSearchTest.runSingle();

        RandomSearchTest randomSearchTest = new RandomSearchTest();
        randomSearchTest.runAll();
//        randomSearchTest.runSingle();

        System.out.println("Program has finished!");
    }

}

//        Solution solution = Collections.min(solutionsByTests, Comparator.comparing(s -> s.quality));