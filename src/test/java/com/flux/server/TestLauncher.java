package com.flux.server;

import io.jettra.ee.JettraEE;
import io.jettra.test.annotation.JettraTestLauncher;

@JettraTestLauncher
public class TestLauncher {

    public void startServer(int port) {
        System.out.println("[TestLauncher] Configurando servidor de prueba en puerto: " + port);
        Thread t = new Thread(() -> {
            try {
                App.serverInstance = JettraEE.builder()
                        .port(port)
                        .contextPath("/")
                        .title("JettraFluxBackEnd Test API")
                        .version("1.0.0")
                        .scanPackages("com.flux", "jcf")
                        .build();

                App.serverInstance.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {
        }
    }

    public void stopServer() {
        System.out.println("[TestLauncher] Deteniendo servidor de prueba...");
        if (App.serverInstance != null) {
            App.serverInstance.stop();
            App.serverInstance = null;
        }
    }
}
