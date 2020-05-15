package Functions;

import Algorithms.Solution;

public class Sphere extends Function {

    public Sphere() {
        this.name = "Sphere";
    }

    @Override
    public Solution process(Solution solution) {
        Double fitness = 0d;
        Double[] dimVector = solution.dimensionsVector;

        if (dimVector.length >= 1) {
            for (int i = 0; i < dimVector.length; i++) {
                fitness += Math.pow(dimVector[i], 2);
            }
        }
        return new Solution(dimVector, fitness);
    }

}
