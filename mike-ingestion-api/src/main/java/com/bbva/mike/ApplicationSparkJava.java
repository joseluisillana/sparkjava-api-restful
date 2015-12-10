package com.bbva.mike;

import spark.Request;
import spark.Response;

import spark.servlet.SparkApplication;

import static spark.Spark.get;

/**
 * Hello world!
 *
 */
public class ApplicationSparkJava implements SparkApplication{

    @Override
    public void init() {

        // Valdría con ésta expresión
        // get("/hello", (req, res) -> "Hola, soy tu nuevo juguete :).");
        get("/hello", (Request req, Response res) -> {
            return "Hola, soy tu nuevo juguete :).";
        });

        get("/hello/:name", (request, response) -> {
            return "HOLA: " + request.params(":name") + "¡¡";
        });

        get("/decir/*/a/*", (request, response) -> {
            StringBuilder responseData = new StringBuilder();
            responseData.append("Numero de parametros \'splat\' : " + request.splat().length);
            responseData.append("<br />Parámetro 1: " + request.splat()[0]);
            responseData.append("<br />Parámetro 2: " + request.splat()[1]);

            response.status(200);
            response.body(responseData.toString());

            return response.body();
        });

    }
}
