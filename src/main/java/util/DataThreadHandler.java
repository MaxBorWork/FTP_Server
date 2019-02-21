package util;

import controller.DataTransferringController;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class DataThreadHandler implements Runnable {

    private Socket inSocket;
    private static Logger log = Logger.getLogger(ServerSocketAccept.class);
    private String processingString;
    private String flag;

    public DataThreadHandler(Socket inSocket, String processingString, String flag) {
        this.inSocket = inSocket;
        this.processingString = processingString;
        this.flag = flag;
    }

    @Override
    public void run() {
        try (InputStream inputStream = inSocket.getInputStream();
             OutputStream outputStream = inSocket.getOutputStream()) {

            DataTransferringController controller = new DataTransferringController(inputStream, outputStream);
            if (flag.equals("LIST")) {
                controller.printDirectoryList(processingString);
            } else if (flag.equals("RETR")) {
                controller.sendFileToClient(processingString);
            } else if (flag.equals("STOR")) {
                controller.storeFile(processingString);
            }

            inSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
