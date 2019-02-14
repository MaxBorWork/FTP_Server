package util;

import model.Messages;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class DataThreadHandler implements Runnable {

    private Socket inSocket;
    private static Logger log = Logger.getLogger(ServerSocketAccept.class);
    private String dirPath;

    public DataThreadHandler(Socket inSocket, String dirPath) {
        this.inSocket = inSocket;
        this.dirPath = dirPath;
    }

    @Override
    public void run() {
        try (InputStream inputStream = inSocket.getInputStream();
             OutputStream outputStream = inSocket.getOutputStream()) {

            Messages answer = new Messages(outputStream);
            answer.printDirectoryList(dirPath);

            inSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
