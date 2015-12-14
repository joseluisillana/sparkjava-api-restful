package com.bbva.mike;

import com.bbva.mike.kafka.KafkaConsumer;
import com.bbva.mike.kafka.KafkaProducer;
import kafka.common.FailedToSendMessageException;

import java.nio.channels.ClosedChannelException;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by joseluisillana on 14/12/15.
 */
public class DataToKafka {

    public static void main(String[] args){
        // Endpoint que carga datos de ejemplo en un topic 'test'
        post("/sendTestData/:topic", (request, response) -> {
            // Generamos datos de prueba y insertamos algo
            String topicParam = ((request.params(":topic")!=null)?request.params(":topic"):"test");
            String bodyParam = ((request.body()!=null)?request.body():"esto es un test");

            KafkaProducer kafkaProducer = new KafkaProducer();
            try {
                kafkaProducer.send(topicParam, bodyParam);
                kafkaProducer.close();
                return "200 - OK";
            }catch (Exception e){
                if (e instanceof ClosedChannelException){
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(500);
                    return "500 - Canal cerrado";
                }else if (e instanceof FailedToSendMessageException){
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(500);
                    return "500 - Error en el envío";
                }else{
                    System.out.println("Got: " + e.getClass().getName());   // Print message
                    response.status(500);
                    return "500 - Error del servidor";
                }
            }

            // Insertamos en kafka
        });



        // Endpoint que carga datos de ejemplo en un topic 'test'
        get("/readTestData:topic", (request, response) -> {
            // Generamos datos de prueba y insertamos algo

            String topicParam = ((request.params(":topic")!=null)?request.params(":topic"):"test");

            KafkaConsumer kafkaConsumer = new KafkaConsumer();

            kafkaConsumer.run();

            return "200 - OK";
            // Insertamos en kafka
        });
    }



}
