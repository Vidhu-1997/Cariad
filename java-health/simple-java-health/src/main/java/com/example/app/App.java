
package com.example.app;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(8080);

        get("/health", (req, res) -> {
            res.status(200);
            return "OK";
        });

        get("/greet", (req, res) -> {
            res.status(200);
            return "Hello from Java App";
        });
    }
}
