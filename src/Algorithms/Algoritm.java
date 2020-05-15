package Algorithms;

import Functions.Function;

public abstract class Algoritm {
    protected Integer ofeMaxNumber;
    protected Double radio;
    protected Function function;
    public Solution best;

    public Algoritm(Integer ofeMaxNumber, Double radio, Function function) {
        this.ofeMaxNumber = ofeMaxNumber;
        this.radio = radio;
        this.function = function;
    }

    public abstract void execute();
}
