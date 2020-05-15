package Algorithms;

public class Solution {
    public Double[] dimensionsVector;
    public Double quality;
    public Double maxD = 2d;

    public Solution(Double[] dimensionsVector) {
        this.dimensionsVector = dimensionsVector;
    }

    public Solution(Double[] dimensionsVector, Double quality) {
        this.dimensionsVector = dimensionsVector;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return
//                "array= " + Arrays.toString(this.dimensionsVector) + "\n" +
                "quality= " + this.quality;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            return new Solution(this.dimensionsVector, this.quality);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Solution solution = (Solution) obj;
        Double[] dimVector = solution.dimensionsVector;
        Double distance = euclideanDistance(dimensionsVector, dimVector);
        if(distance > maxD) {
            return false;
        }
        return true;
    }

    private Double euclideanDistance(Double[] a, Double[] b) {
        double diff_square_sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            diff_square_sum += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(diff_square_sum);
    }
}
