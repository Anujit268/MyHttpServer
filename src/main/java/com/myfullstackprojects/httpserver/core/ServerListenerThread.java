package com.myfullstackprojects.httpserver.core;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;

public class ServerListenerThread extends Thread{
    private final static Logger LOGGER= LoggerFactory.getLogger(ServerListenerThread.class);
    private String webroot;
    private int port;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port,String webroot)throws IOException {
        this.webroot=webroot;
        this.port=port;
        serverSocket=new ServerSocket(this.port);
    }

    @Override
    public void run(){
        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info(" * Connection Accepted: " + socket.getInetAddress());

                HttpConnectionWorkerThread workerThread=new HttpConnectionWorkerThread(socket);
                workerThread.start();

            }
            //serverSocket.close();//TODO-Handle later
        } catch (IOException e) {
            LOGGER.error("Problem with setting socket",e);
        }
        finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
