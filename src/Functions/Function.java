package Functions;

import Algorithms.Solution;
import Tests.TabuSearchTest;

import java.util.concurrent.ThreadLocalRandom;

import static Tests.Parameters.LOWER_LIMIT;
import static Tests.Parameters.UPPER_LIMIT;

public abstract class Function {
    public String name;
    public Integer upperLimit;
    public Integer lowerLimit;
    public Integer dimensionsNumber;

    public Function() {
        this.upperLimit = UPPER_LIMIT;
        this.lowerLimit = LOWER_LIMIT;
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
