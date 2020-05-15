import Tests.Test;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program has started!");
        Test test = new Test();
        test.runAll();
        System.out.println("Program has finished!");
    }

}

//        Solution solution = Collections.min(solutionsByTests, Comparator.comparing(s -> s.quality));