package com.godaddy.domains.message;

import scala.concurrent.duration.Duration;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose:: ent from the Master actor to the Listener actor containing the the final pi result and how long time the calculation took
 */
public class PiApproximation {
    private final double pi;
    private final Duration duration;

    public PiApproximation(double pi, Duration duration) {
        this.pi = pi;
        this.duration = duration;
    }

    public double getPi() {
        return pi;
    }

    public Duration getDuration() {
        return duration;
    }
}
