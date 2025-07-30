package com.myfullstackprojects.httpserver;

import com.myfullstackprojects.httpserver.config.Configuration;
import com.myfullstackprojects.httpserver.config.ConfigurationManager;
import com.myfullstackprojects.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Driver class for server
 *
 */
public class HttpServer {
    private final static Logger LOGGER= LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args){
        LOGGER.info("Server starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration conf=ConfigurationManager.getInstance().getConfiguration();
        LOGGER.info("Using Port:" + conf.getPort());
        LOGGER.info("Using Webroot:" + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread=new ServerListenerThread(conf.getPort(),conf.getWebroot());
            serverListenerThread.run();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO Handle later
        }


    }
}
