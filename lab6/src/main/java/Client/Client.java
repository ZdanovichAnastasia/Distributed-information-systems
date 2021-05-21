package Client;

import Controller.Controller;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, ServiceException {
        Controller controller = new Controller();
        controller.start();
    }
}
