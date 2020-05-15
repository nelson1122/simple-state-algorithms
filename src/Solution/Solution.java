package Solution;

import java.util.Arrays;

public class Solution {
    public Double[] dimensionsVector;
    public Double quality;

    public Solution(Double[] dimensionsVector) {
        this.dimensionsVector = dimensionsVector;
    }

    public Solution(Double[] dimensionsVector, Double quality) {
        this.dimensionsVector = dimensionsVector;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "array: " + Arrays.toString(this.dimensionsVector) + "\n" +
                "quality: " + this.quality + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        Solution solution = (Solution) obj;
        Double[] dimVector = solution.dimensionsVector;
        for (int i = 0; i < dimensionsVector.length; i++) {
            if (dimensionsVector[i] != dimVector[i]) {
                return false;
            }
        }
        return true;
    }

}
