package Functions;

import Algorithms.Solution;

public class Rastrigin extends Function {

    public Rastrigin() {
        this.name = "Rastrigin";
    }

    @Override
    public Solution process(Solution solution) {
        Double fitness = 0d;
        Double[] dimVector = solution.dimensionsVector;

        if (dimVector.length >= 1) {
            for (int i = 0; i < dimVector.length; i++) {
                fitness += Math.pow(dimVector[i], 2) - 10 * Math.cos(2 * Math.PI * dimVector[i]) + 10;
            }
        }
        return new Solution(dimVector, fitness);
    }

}
