# akka-pi-tester

First Akka project 

Caculates the pi digits using worker process

http://doc.akka.io/docs/akka/2.0.2/intro/getting-started-first-java.html <= started with this and cleaned the deprecated code to be more current

this gets the 10000 workers to calculate 10000 elements of pi



 pi.calculate(5, 10000, 10000);
 
 5 is the number of workers in the round robin
 
 10000 = number of worker messages to pass around in calculations
 
 10000 = number of elements to calculate 
 
 .tell sends the message to an actor
 
 
 An actor is essentially nothing more than an object that receives messages and takes actions to handle them. It is decoupled from the source of the message and its only responsibility is to properly recognize the type of message it has received and take action accordingly.
 
 Upon receiving a message, an actor may take one or more of the following actions:
 
 * Execute some operations itself (such as performing calculations, persisting data, calling an external web service, and so on)
 * Forward the message, or a derived message, to another actor
 * Instantiate a new actor and forward the message to it
 
 Alternatively, the actor may choose to ignore the message entirely (i.e., it may choose inaction) if it deems it appropriate to do so.
 
 