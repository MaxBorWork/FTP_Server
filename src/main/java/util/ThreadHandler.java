package util;

import controller.CommandsController;
import view.Messages;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadHandler implements Runnable {

    final String USERNAME = "USERNAME";
    final String PASSWORD = "PASSWORD";
    final String DELE = "DELE";
    final String RMD = "RMD";
    final String MKD = "MKD";
    final String STORE = "STORE";
    final String PORT = "PORT";
    final String RETR = "RETR";
    final String LIST = "LIST";
    final String CWD = "CWD";
    final String PWD = "PWD";

    private Socket inSocket;
    private CommandsController controller;

    public ThreadHandler(Socket inSocket) {
        this.inSocket = inSocket;
        controller = new CommandsController();
    }


    public void run() {
        try(InputStream inputStream = inSocket.getInputStream();
            OutputStream outputStream = inSocket.getOutputStream()) {

            Scanner in = new Scanner(inputStream, "UTF-8");

            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),true);

            Messages answer = new Messages(writer);
            answer.printCommandList();

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
                String[] lineSlpit = line.split(" ");

                if (lineSlpit.length > 1) {
                    String command = lineSlpit[0];
                    switch (command) {
                        case USERNAME: {
                            String response = controller.userCommand(line);
                            writer.println(response);
                            break;
                        }
                        case PASSWORD: {
                            String response = controller.passwordCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case DELE: { // _--------------------------------------------------!!!
                            String response = controller.deleteCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case MKD: { // _--------------------------------------------------!!!
                            String response = controller.makeDirectoryCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case STORE: {
                            String response = controller.storeCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case PORT: { 
                            String response = controller.portCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case RMD: { // _--------------------------------------------------!!!
                            String response = controller.removeDirectoryCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case RETR: {
                            String response = controller.retrieveCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case CWD: {
                            String response = controller.changeWorkDirCommand(line);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case PWD: {
                            String response = controller.printWorkDirCommand(answer);
                            writer.println(response);
                            done = true;
                            break;
                        }
                        case LIST: { // _--------------------------------------------------!!!
                            String response = controller.listCommand(line, answer);
                            writer.println(response);
                            done = true;
                            break;
                        }

                        default: {
                            done = true;/// CHANGE!!!!!!!!
                        }
                    }
                } else {
                    answer.commandUnrecognized();
                    done = true;
                }
            }

//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

//            String line = reader.readLine();
//            if(line != null) {
//                System.out.println(line);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
