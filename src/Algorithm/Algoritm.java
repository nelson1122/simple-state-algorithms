package Algorithm;

import Functions.Function;

public abstract class Algoritm {
    protected Integer ofeMaxNumber;
    protected Double radio;
    protected Function function;

    public Algoritm(Integer ofeMaxNumber, Double radio, Function function) {
        this.ofeMaxNumber = ofeMaxNumber;
        this.radio = radio;
        this.function = function;
    }

    public Double euclideanDistance(Double[] a, Double[] b) {
        double diff_square_sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            diff_square_sum += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(diff_square_sum);
    }

    public abstract void execute();
}
