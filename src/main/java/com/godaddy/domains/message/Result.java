package com.godaddy.domains.message;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose:: sent from the Worker actors to the Master actor containing the result from the worker’s calculation
 */
public class Result {
    private final double value;

    public Result(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
