
package com.example;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.port(8080);
        Spark.get("/health", (req, res) -> {
            res.status(200);
            return "OK";
        });
        Spark.get("/greet", (req, res) -> {
            res.status(200);
            return "Hello!";
        });
    }
}
