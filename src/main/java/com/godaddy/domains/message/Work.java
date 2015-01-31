package com.godaddy.domains.message;

import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose::Work – sent from the Master actor to the Worker actors containing the work assignment
 */
public class Work {
    
    private final int start;
    private final int nrOfElements;
    
    public Work(int start, int nrOfElements) {
        
        this.start = start;
        this.nrOfElements = nrOfElements;
    }

    public int getStart() {
        return start;
    }

    public int getNrOfElements() {
        return nrOfElements;
    }
}
