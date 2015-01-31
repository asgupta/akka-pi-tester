package com.godaddy.domains;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
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
 * Purpose:: Master gets two types of message
 * Worker and Result.. *
 */
public class Master extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
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
        log.info("Master started with " +nrOfMessages + " messages and workers:: "+nrOfWorkers);
        workerRouter =  getContext().actorOf(new RoundRobinPool(nrOfWorkers).props(Props.create(Worker.class)),
                "workerRouter");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof Calculate) {
            for (int start = 0; start < nrOfMessages; start++) {
                //if the no. of m
                workerRouter.tell(new Work(start, nrOfElements), getSelf());
            }
        } else if (message instanceof Result) {
            Result result = (Result) message;
            pi += result.getValue();
            nrOfResults += 1;
            if (nrOfResults == nrOfMessages) {
                // Send the result to the listener
                Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
                log.info("Sending the final result to the listener");
                listener.tell(new PiApproximation(pi, duration), getSelf());
                // Stops this actor and all its supervised children
                log.info("Stopping the actor and its children in round robin");
              //  getContext().stop(getSelf());

            }
        } else {
            unhandled(message);
        }
    }
}
