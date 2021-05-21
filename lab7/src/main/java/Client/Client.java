package Client;

import Controller.EnterpriseController;
import org.xml.sax.SAXException;

import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws SOAPException, IOException, TransformerException, SAXException {
        EnterpriseController client = new EnterpriseController();
        client.start();
    }
}
