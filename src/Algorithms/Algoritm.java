package Algorithms;

import Functions.Function;

import static Tests.Parameters.OFE_MAX_NUMBER;

public abstract class Algoritm {
    protected Integer ofeMaxNumber;
    protected Function function;
    public Solution best;

    public Algoritm(Function function) {
        this.ofeMaxNumber = OFE_MAX_NUMBER;
        this.function = function;
    }

    public abstract void execute();
}
