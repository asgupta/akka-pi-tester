package com.godaddy.domains;

import akka.actor.UntypedActor;
import com.godaddy.domains.message.PiApproximation;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose:: This file is supposed to be a foo bar
 */
public class Listener extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof PiApproximation) {
            PiApproximation approximation = (PiApproximation) message;
            System.out.println(String.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
                    approximation.getPi(), approximation.getDuration()));
            getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }
}
