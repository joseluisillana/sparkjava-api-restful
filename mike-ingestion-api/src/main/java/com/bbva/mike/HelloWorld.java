package com.bbva.mike;

import spark.Request;
import spark.Response;

import static spark.Spark.*;
import static spark.Spark.get;

/**
 * Created by joseluisillana on 10/12/15.
 */
public class HelloWorld {
    public static void main(String[] args){
        // Valdría con ésta expresión
        // get("/hello", (req, res) -> "Hola, soy tu nuevo juguete :).");
        get("/hello", (Request req, Response res) -> {
            return "Hola, soy tu nuevo juguete :).";
        });

        get("/hello/:name", (request, response) -> {
            return "HOLA: " + request.params(":name") + "¡¡";
        });

        get("/decir/*/a/*", (request, response) -> {
            StringBuffer responseData = new StringBuffer();
            responseData.append("Numero de parametros \'splat\' : " + request.splat().length);
            responseData.append("<br />Parámetro 1: " + request.splat()[0]);
            responseData.append("<br />Parámetro 2: " + request.splat()[1]);

            response.status(200);
            response.body(responseData.toString());

            return response.body();
        });

        // TODO Borrar esto de aqui si no sirve
        /*public static void main(String[] args){
        // Inicializamos el ejemplo de webshocket
        webSocket("/echo", EchoWebSocket.class);
        init();
        }*/


        /**
         * TODO Otros Ejemplos:
         */
        /*
        get("/", (request, response) -> {
            // .. Show something ..
        });

        post("/", (request, response) -> {
            // .. Create something ..
        });

        put("/", (request, response) -> {
            // .. Update something ..
        });

        delete("/", (request, response) -> {
            // .. annihilate something ..
        });

        options("/", (request, response) -> {
            // .. appease something ..
        });

        // matches "GET /hello/foo" and "GET /hello/bar"
        // request.params(":name") is 'foo' or 'bar'
        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });

        // matches "GET /say/hello/to/world"
        // request.splat()[0] is 'hello' and request.splat()[1] 'world'
        get("/say/XX/to/XX", (request, response) -> {
            return "Number of splat parameters: " + request.splat().length;
        });

        */
    }
}
