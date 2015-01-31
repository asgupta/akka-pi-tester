package com.godaddy.domains;

/**
 * Author:: agupta
 * Date:: 1/30/2015
 * Purpose:: This file is supposed to be a foo bar
 */
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.godaddy.domains.message.Calculate;

public class Pi {
    
    public static void main(String[] args) {
        Pi pi = new Pi();
        pi.calculate(5, 10000, 10000);
    }
    public void calculate(final int nrOfWorkers, final int nrOfElements, final int nrOfMessages) {
        // Create an Akka system
        ActorSystem system = ActorSystem.create("PiSystem");

        // create the result listener, which will print the result and shutdown the system
        final ActorRef listener = system.actorOf(Props.create(Listener.class), "listener");
        //final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");

        // create the master
        ActorRef master = system.actorOf(Props.create(Master.class, nrOfWorkers, nrOfMessages, nrOfElements, listener), "master");

        // start the calculation
        //calculate is the message which gets sent 
        //no sender is the actor to who the message gets sent...
        
        master.tell(new Calculate(), ActorRef.noSender());

        

    }

}
