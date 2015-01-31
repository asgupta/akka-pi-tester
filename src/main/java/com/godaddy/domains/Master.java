package com.godaddy.domains;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import com.godaddy.domains.message.Calculate;
import com.godaddy.domains.message.PiApproximation;
import com.godaddy.domains.message.Result;
import com.godaddy.domains.message.Work;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose:: This file is supposed to be a foo bar
 */
public class Master extends UntypedActor {

    private final int nrOfMessages;
    private final int nrOfElements;

    private double pi;
    private int nrOfResults;
    private final long start = System.currentTimeMillis();

    private final ActorRef listener;
    private final ActorRef workerRouter;

    public Master(final int nrOfWorkers, int nrOfMessages, int nrOfElements, ActorRef listener) {
        this.nrOfMessages = nrOfMessages;
        this.nrOfElements = nrOfElements;
        this.listener = listener;

        workerRouter =  getContext().actorOf(new RoundRobinPool(nrOfWorkers).props(Props.create(Worker.class)),
                "workerRouter");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof Calculate) {
            for (int start = 0; start < nrOfMessages; start++) {
                workerRouter.tell(new Work(start, nrOfElements), getSelf());
            }
        } else if (message instanceof Result) {
            Result result = (Result) message;
            pi += result.getValue();
            nrOfResults += 1;
            if (nrOfResults == nrOfMessages) {
                // Send the result to the listener
                Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
                listener.tell(new PiApproximation(pi, duration), getSelf());
                // Stops this actor and all its supervised children
                getContext().stop(getSelf());
            }
        } else {
            unhandled(message);
        }
    }
}
