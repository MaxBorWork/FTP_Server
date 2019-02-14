package util;

import controller.CommandsController;
import org.apache.log4j.Logger;
import model.ReplyCode;
import model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class ThreadHandler implements Runnable {

    private Socket inSocket;
    private CommandsController controller;

    private static Logger log = Logger.getLogger(ServerSocketAccept.class);

    public ThreadHandler(Socket inSocket) {
        this.inSocket = inSocket;
        controller = new CommandsController();
    }

    public void run() {

        log.info(LogMessages.CREATE_CONNECTION_MESSAGE);

        try(InputStream inputStream = inSocket.getInputStream();
            OutputStream outputStream = inSocket.getOutputStream()) {

            Scanner in = new Scanner(inputStream,  Config.UTF_8);

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
                        case Commands.USER: {
                            String response = controller.userCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.PASS: {
                            String response = controller.passwordCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.DELE: {
                            String response = controller.deleteCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.SYST: {
                            String response = controller.systCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.MKD: {
                            String response = controller.makeDirectoryCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.STORE: {
                            String response = controller.storeCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.PORT: {
                            String response = controller.portCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.RMD: {
                            String response = controller.removeDirectoryCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.RETR: {
                            String response = controller.retrieveCommand(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.CWD: {
                            String response = controller.changeWorkDirCommand(line, writer);
                            writer.println(response);
                            break;
                        }
                        case Commands.PWD: {
                            String response = controller.printWorkDirCommand();
                            writer.println(response);
                            break;
                        }
                        case Commands.LIST: {
                            String response = controller.listCommand(line, writer);
                            writer.println(response);
                           // done = true;
                            break;
                        }
                        case Commands.TYPE: {
                            String response = controller.getType(line);
                            writer.println(response);
                            break;
                        }
                        case Commands.PASV: {
                            String response = controller.pasvResponse(line);
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
