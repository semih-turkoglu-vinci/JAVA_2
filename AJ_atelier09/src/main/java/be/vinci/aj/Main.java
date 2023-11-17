package be.vinci.aj;

import be.vinci.aj.domaine.QueryFactoryImpl;
import be.vinci.aj.server.ProxyServer;


public class Main {
    public static void main(String[] args) {
        ProxyServer proxy = new ProxyServer(new QueryFactoryImpl());
        proxy.startServer();
    }
}

