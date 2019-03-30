package controller;

import model.Config;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
                    response.append(line).append("\t\n");
                }
                line = reader.readLine();
            }

            outputStream.write(response.toString().getBytes());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String pasvMessage() {
        return "(" + Config.IP_ADDRESS_STRING_COMMAS + "," +
                        Config.PORT_20_INT / Config.BIT_SHIFT + "," +
                        Config.PORT_20_INT % Config.BIT_SHIFT + ")";
    }

    public void retrieveFile(String fileName, CommandsController controller) {
        String newFileName = copyFileToTmp(fileName);

            try {
                FileUtils.copyFile( new File(fileName),  new File(newFileName));

                if (controller.getCurrentType().equals(Config.TYPE_I)) {
                    retrieveBinaryFile(newFileName);
                } else {
                    retrieveASCIIFile(newFileName);
                }

                Files.delete(Paths.get(newFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    private void retrieveBinaryFile(String newFileName) {
        try {
            byte[] fileAsByteArray = Files.readAllBytes(Paths.get(newFileName));
            outputStream.write(fileAsByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void retrieveASCIIFile(String newFileName) {
        PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII),true);
        StringBuffer buf = null;
        try {
            FileReader fr = new FileReader(newFileName);
            int theChar;
            buf = new StringBuffer();

            while (((theChar = fr.read()) != -1)) {
                buf.append((char) theChar);
            }
            writer.println(buf);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Метод для выбора типа (бинарный или текстовый) сохранения файла на сервер
     * @param fileName
     * @param controller
     */
    public void storeFile(String fileName, CommandsController controller) {
        String newFileName = copyFileToTmp(fileName);
        try {
            Files.createFile(Paths.get(newFileName));
            if (controller.getCurrentType().equals(Config.TYPE_I)) {
                storeBinaryFile(newFileName);
            } else {
                storeASCIIFile(newFileName);
            }
            if( new File(fileName).exists()){
                new File(fileName).delete();
            }
            FileUtils.moveFile( new File(newFileName),  new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, сохраняющий текстовый (ASCII) файл на сервер
     * @param fileName
     */
    private void storeASCIIFile(String fileName) {
        Reader r = null;
        StringBuilder builder = new StringBuilder();
        try {
            r = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
            int intch;
            while ((intch = r.read()) != -1) {
                char ch = (char) intch;
                builder.append(ch);
            }
            Files.write(Paths.get(fileName), Collections.singleton(builder.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void storeBinaryFile(String fileName) {
        byte[] fileAsByteArray;

        try {
            List byteList = new ArrayList();
            int data = inputStream.read();

            while (data != -1) {
                byteList.add((byte) data);
                data = inputStream.read();
            }
            inputStream.close();
            fileAsByteArray = new byte[byteList.size()];
            for (int i = 0; i < byteList.size(); i++) {
                fileAsByteArray[i] = (byte) byteList.get(i);
            }
            Files.write(Paths.get(fileName), fileAsByteArray);
         //   Files.write(Paths.get(fileName), fileAsByteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //медот копирования в промежуточную директорию
    private String copyFileToTmp(String fileName) {
        StringBuilder oldFileName = new StringBuilder(fileName.substring(fileName.lastIndexOf("/") + 1));
        //новое имя файла
        String newFileName = Config.TEMP + "/" + oldFileName;
        int id = 1;
        //копирование файлов
        while (Files.exists(Paths.get(newFileName))) {
            oldFileName.insert(0, String.valueOf(id));
            newFileName = Config.TEMP + "/" + oldFileName;
            id += 1;
        }
        return newFileName;
    }
}

