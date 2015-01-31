package com.godaddy.domains;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.godaddy.domains.message.PiApproximation;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose:: This file is supposed to be a foo bar
 */
public class Listener extends UntypedActor {


    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof PiApproximation) {
            log.info("Final result received by the listener");
            PiApproximation approximation = (PiApproximation) message;
            log.info(String.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
                    approximation.getPi(), approximation.getDuration()));
            
            getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }
}
