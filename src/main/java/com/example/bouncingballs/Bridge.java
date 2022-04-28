package com.example.bouncingballs;

public class Bridge {
    private static HelloController helloController = null;
    // Getter
    public static HelloController getHelloController() {
        return helloController;
    }
    // Setter
    public static void setHelloController(HelloController helloController) {
        Bridge.helloController = helloController;
    }
}
