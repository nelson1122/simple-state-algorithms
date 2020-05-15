package Algorithms;

import Functions.Function;

import java.util.LinkedList;
import java.util.Queue;

public class TabuSearch extends Algoritm {
    private Integer l;
    private Integer n;


    public TabuSearch(Integer ofeMaxNumber, Double radio, Function function, Integer l, Integer n) {
        super(ofeMaxNumber, radio, function);
        this.l = l;
        this.n = n;
    }

    @Override
    public void execute() {
        Solution S = function.getCandidate();
        S = function.process(S);

        best = (Solution) S.clone();

        Queue<Solution> tabuList = new LinkedList<Solution>();
        tabuList.add(S);

        int ofe = 0;
        while (ofe < ofeMaxNumber) {

            if (tabuList.size() > l) {
                tabuList.poll();
            }

            Solution R = function.getTweak(S, radio);
            R = function.process(R);

            for (int j = 0; j < n - 1; j++) {
                Solution W = function.getTweak(R, radio);
                W = function.process(W);

                if (!tabuList.contains(W) && ((W.quality < R.quality) || tabuList.contains(R))) {
                    R = W;
                }
                ofe += 2;
            }

            if (!tabuList.contains(R) && R.quality < S.quality) {
                S = R;
                tabuList.add(R);
            }
            ofe += 2;

            if (S.quality < best.quality) {
                best = (Solution) S.clone();
            }
            ofe += 2;

//            System.out.println("Iteration [" + foe + "]");
//            System.out.println(S);
        }
    }
}
