package Algorithm;

import Functions.Function;
import Solution.Solution;

import java.util.LinkedList;
import java.util.Queue;

public class TabuSearch extends Algoritm {
    private Integer l;
    private Integer n;
    private Solution best;
    private Queue<Solution> tabuList;

    public TabuSearch(Integer ofeMaxNumber, Double radio, Function function, Integer l, Integer n) {
        super(ofeMaxNumber, radio, function);
        this.l = l;
        this.n = n;
    }

    @Override
    public void execute() {
        Solution S = function.getCandidate();
        S = function.process(S);

        best = S;

        tabuList = new LinkedList<Solution>();
        tabuList.add(S);

        for (int foe = 0; foe < ofeMaxNumber; foe++) {

            if (tabuList.size() > l) {
                tabuList.poll();
            }

            Solution R = function.getTweak(S, radio);
            R = function.process(R);

            for (int j = 0; j < n - 1; j++) {
                Solution W = function.getTweak(R, radio);
                W = function.process(W);

                if (!tabuList.contains(W) && ((W.quality > R.quality) || tabuList.contains(R))) {
                    R = W;
                    foe += 2;
                }
            }

            if (!tabuList.contains(R) && R.quality > S.quality) {
                S = R;
                tabuList.add(R);
                foe += 2;
            }

            if (S.quality > best.quality) {
                best = S;
                foe += 2;
            }

            System.out.println("Iteration [" + (foe + 1) + "]");
            System.out.println(S);
        }
    }
}
