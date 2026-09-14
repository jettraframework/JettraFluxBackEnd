package com.flux.server;

import io.jettra.ee.JettraEE;
import io.jettra.ee.server.JettraEEServer;
import org.eclipse.microprofile.config.ConfigProvider;

public class App {

    public static JettraEEServer serverInstance;

    public static void main(String[] args) {
        if (args != null && args.length > 0 && args[0].equals("-console")) {
            io.jettra.server.autentification.SecurityCLI.main(args);
            return;
        }

        int port = 8080;
        String contextPath = "/";

        try {
            var cfg = ConfigProvider.getConfig();
            port = cfg.getOptionalValue("server.port", Integer.class).orElse(port);
            contextPath = cfg.getOptionalValue("server.contextpath", String.class).orElse(contextPath);
        } catch (Exception ignored) {}

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if ("--port".equals(args[i]) && i + 1 < args.length) {
                    port = Integer.parseInt(args[++i]);
                } else if ("--context-path".equals(args[i]) && i + 1 < args.length) {
                    contextPath = args[++i];
                }
            }
        }

        // Configurar la ruta de redirección en ErrorPage, usando contextpath
        io.jettra.flux.complex.ErrorPage.path = "http://localhost:" + port + contextPath;

        System.out.println("Levantando servidor JettraEE en puerto " + port + "...");

        serverInstance = JettraEE.builder()
                .port(port)
                .contextPath(contextPath)
                .title("JettraFluxBackEnd API")
                .version("1.0.0")
                .scanPackages("com.flux", "jcf")
                .build();

        serverInstance.start();
    }
}
