package model;

import org.apache.log4j.Logger;
import util.ServerSocketAccept;
import util.ThreadHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DataSocket {
    private static Logger logger = Logger.getLogger(DataSocket.class);

    public void createConnection(  int port, InetAddress addr){
        try (ServerSocket socket = new ServerSocket(port, Config.MAX_CONNECTION_NUMBER, addr)){
            int i = 1;

            while (true) {
                Socket inSocket = socket.accept();
                Runnable r = new ThreadHandler(inSocket);
                Thread thread = new Thread(r);
                thread.run();
                i++;
            }

        } catch (IOException e ) {
                logger.info(LogMessages.CANT_CREATE_SOCKET_MESSAGE + e.getMessage());
                e.printStackTrace();
        }







 }
}
