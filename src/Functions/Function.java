package Functions;

import Solution.Solution;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Function {
    public Integer upperLimit;
    public Integer lowerLimit;
    public Integer dimensionsNumber;

    public Function(Integer upperLimit, Integer lowerLimit, Integer dimensionsNumber) {
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.dimensionsNumber = dimensionsNumber;
    }

    public Solution getCandidate() {
        Double[] dimVector = new Double[dimensionsNumber];
        for (int i = 0; i < dimVector.length; i++) {
            dimVector[i] =
                    ThreadLocalRandom.current().nextDouble(lowerLimit, upperLimit + 1);
        }
        return new Solution(dimVector);
    }

    public Solution getTweak(Solution solution, Double radio) {
        Double[] dimVector = solution.dimensionsVector.clone();
        for (int i = 0; i < dimVector.length; i++) {
            dimVector[i] = dimVector[i] + ThreadLocalRandom.current().nextDouble(-radio, radio + 0.1);
        }
        return new Solution(dimVector);
    }

    public abstract Solution process(Solution solution);

}
