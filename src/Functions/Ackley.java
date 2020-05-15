package Functions;

import Algorithms.Solution;

public class Ackley extends Function {

    public Ackley() {
        this.name = "Ackley";
    }

    @Override
    public Solution process(Solution solution) {
        Double fitness = 0d;
        Double[] dimVector = solution.dimensionsVector;

        if (dimVector.length >= 1) {
            double a = 20;
            double b = 0.2;
            double c = 2 * Math.PI;
            double x = 0;
            double y = 0;

            for (int i = 0; i < dimVector.length; i++) {
                x += Math.pow(dimVector[i], 2);
                y += Math.cos(c * dimVector[i]);
            }
            fitness = -a * Math.exp(-b * Math.sqrt(x / dimVector.length)) - Math.exp(y / dimVector.length) + a + Math.exp(1);
        }

        return new Solution(dimVector, fitness);
    }
}
