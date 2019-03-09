import org.apache.log4j.BasicConfigurator;
import util.ServerSocketAccept;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
//        BasicConfigurator.configure();
//        Path currentRelativePath = Paths.get("");
//        String s = currentRelativePath.toAbsolutePath().toString();
//        System.out.println("Current relative path is: " + s);
        new ServerSocketAccept(args);
    }

}
