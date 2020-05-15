package Algorithms;

import Functions.Function;

public class RandomSearch extends Algoritm {

    public RandomSearch(Function function) {
        super(function);
    }

    @Override
    public void execute() {
        best = function.getCandidate();
        best = function.process(best);

        int ofe = 0;
        while (ofe < ofeMaxNumber) {
            Solution S = function.getCandidate();
            S = function.process(S);

            if (S.quality < best.quality) {
                best = (Solution) S.clone();
            }
            ofe += 2;

//            System.out.println("Iteration [" + ofe + "]");
//            System.out.println(best);
        }
    }
}
