package com.godaddy.domains;

import akka.actor.UntypedActor;
import com.godaddy.domains.message.Result;
import com.godaddy.domains.message.Work;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose:: This file is supposed to be a foo bar
 */
public class Worker extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Work) {
            Work work = (Work) message;
            double result = calculatePiFor(work.getStart(), work.getNrOfElements());
      //    System.out.println("Result " + result);
            getSender().tell(new Result(result), getSelf());
        } else {
            System.out.println("Unhandled");
                    
            unhandled(message);
        }
    }

    private double calculatePiFor(int start, int nrOfElements) {
        double acc = 0.0;
        for (int i = start * nrOfElements; i <= ((start + 1) * nrOfElements - 1); i++) {
            acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
            
        }
   //     System.out.println("sec" + acc);
        return acc;
    }

}
