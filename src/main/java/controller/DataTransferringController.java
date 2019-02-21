package controller;

import model.Config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataTransferringController {
    private InputStream inputStream;
    private OutputStream outputStream;

    public DataTransferringController(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void printDirectoryList(String dirName) {
        StringBuilder response = new StringBuilder();
        String command = "ls -l " + dirName;
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                if (!line.contains("итого")) {
                    if (line.contains("Лют")) {
                        line = line.replace("Лют", "Feb");
                    }
                    response.append(line).append("\t\n");
                }
                line=reader.readLine();
            }
            outputStream.write(response.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String pasvMessage() {
        return "(" + Config.IP_ADDRESS_STRING_COMMAS + "," + Config.PORT_20_INT / Config.BIT_SHIFT + "," + Config.PORT_20_INT % Config.BIT_SHIFT + ")";
    }

    public void sendFileToClient(String fileName) {
        try {
            byte[] fileAsByteArray = Files.readAllBytes(Paths.get(fileName));
            outputStream.write(fileAsByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeFile(String fileName) {
        if (!Files.exists(Paths.get(fileName))) {
            try {
                Files.createFile(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] fileAsByteArray = new byte[0];
        try {
            fileAsByteArray = new byte[inputStream.available()];

            inputStream.read(fileAsByteArray);

            Files.write(Paths.get(fileName), fileAsByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
