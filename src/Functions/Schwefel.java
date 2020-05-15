package Functions;

import Algorithms.Solution;

public class Schwefel extends Function {

    public Schwefel() {
        this.name = "Schwefel";
    }

    @Override
    public Solution process(Solution solution) {
        Double fitness = 0d;
        Double[] dimVector = solution.dimensionsVector;

        if (dimVector.length >= 1) {
            for (int i = 0; i < dimVector.length; i++) {
                Double sum = 0d;
                for (int j = 0; j <= i; j++)
                    sum += dimVector[j];
                fitness += Math.pow(sum, 2);
            }
        }
        return new Solution(dimVector, fitness);
    }
}
