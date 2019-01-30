package util;

import util.ThreadHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketAccept {

    private static final int PORT = 21;

    public ServerSocketAccept() {
        start();
    }

    private void start() {
        try {
//            InetAddress addr = InetAddress.getByName("192.168.43.234");
            InetAddress addr = InetAddress.getByName("127.0.0.1");

            try (ServerSocket socket = new ServerSocket(PORT, 50, addr))
//            try (ServerSocket socket = new ServerSocket(PORT))
            {
                int i = 1;

                while (true) {
                    Socket inSocket = socket.accept();
                    Runnable r = new ThreadHandler(inSocket);
                    Thread thread = new Thread(r);
                    thread.run();
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
