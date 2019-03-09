import org.apache.log4j.BasicConfigurator;
import util.ServerSocketAccept;

public class Main {

    public static void main(String[] args) {
//        BasicConfigurator.configure();
        new ServerSocketAccept(args);
    }

}
