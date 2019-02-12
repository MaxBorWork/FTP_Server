package util;

import controller.CommandsController;
import model.Messages;
import org.apache.log4j.Logger;
import model.ReplyCode;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class ThreadHandler implements Runnable {

    final String USER = "USER";
    final String PASS = "PASS";
    final String DELE = "DELE";
    final String RMD = "RMD";
    final String MKD = "MKD";
    final String STORE = "STORE";
    final String PORT = "PORT";
    final String RETR = "RETR";
    final String LIST = "LIST";
    final String CWD = "CWD";
    final String PWD = "PWD";
    final String SYST = "SYST";
    final String TYPE = "TYPE";
    final String PASV = "PASV";

    private static final int DATA_PORT = 20;

    private Socket inSocket;
    private Socket dataSocket;
    private CommandsController controller;

    private static Logger log = Logger.getLogger(ServerSocketAccept.class);

    public ThreadHandler(Socket inSocket) {
        this.inSocket = inSocket;
        controller = new CommandsController();
    }

    public void run() {

        log.info("Created input connection");

        try(InputStream inputStream = inSocket.getInputStream();
            OutputStream outputStream = inSocket.getOutputStream()) {

            Scanner in = new Scanner(inputStream, "UTF-8");

            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),true);

            writer.println(ReplyCode.CODE_220);

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
                log.info("Get command: " + line);
                String[] lineSlpit = line.split(" ");

                if (lineSlpit.length >= 1) {
                    String command = lineSlpit[0];
                    switch (command) {
                        case USER: {
                            String response = controller.userCommand(line);
                            writer.println(response);
                            break;
                        }
                        case PASS: {
                            String response = controller.passwordCommand(line);
                            writer.println(response);
                            break;
                        }
                        case DELE: {
                            String response = controller.deleteCommand(line);
                            writer.println(response);
                            break;
                        }
                        case SYST: {
                            String response = controller.systCommand(line);
                            writer.println(response);
                            break;
                        }
                        case MKD: {
                            String response = controller.makeDirectoryCommand(line);
                            writer.println(response);
                            break;
                        }
                        case STORE: {
                            String response = controller.storeCommand(line);
                            writer.println(response);
                            break;
                        }
                        case PORT: { 
                            String response = controller.portCommand(line);
                            writer.println(response);
                            break;
                        }
                        case RMD: {
                            String response = controller.removeDirectoryCommand(line);
                            writer.println(response);
                            break;
                        }
                        case RETR: {
                            String response = controller.retrieveCommand(line);
                            writer.println(response);
                            break;
                        }
                        case CWD: {
                            String response = controller.changeWorkDirCommand(line);
                            writer.println(response);
                            break;
                        }
                        case PWD: {
                            String response = controller.printWorkDirCommand();
                            writer.println(response);
                            break;
                        }
                        case LIST: {
                            String response = controller.listCommand(line, new Messages(writer));
                            writer.println(response);
                            break;
                        }
                        case TYPE: {
                            String response = controller.getType(line);
                            writer.println(response);
                            break;
                        }
                        case PASV: {
                            String response = controller.pasvResponse();
                            writer.println(response);
                            break;
                        }
                        default: {
                            writer.println(ReplyCode.CODE_500);
                        }
                    }
                } else {
                    writer.println(ReplyCode.CODE_500);
                   done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
