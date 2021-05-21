package BusinessLogic;

import BusinessLogicModels.IEnterpriseService;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class EnterpriseService implements IEnterpriseService{

    private String URL = "http://localhost:8080/axis/services/EnterpriseService";
    private SOAPMessage reqMess,respMess;

    public void displayMessage(SOAPMessage msg) throws SOAPException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        msg.writeTo(out);
        String strMsg = new String(out.toByteArray());
        if(strMsg.contains("FileNotFoundException")){
            System.out.println("Not found");
        }else
            System.out.println(strMsg);
    }


    public void createSOAPRequest(String fileXml) throws SOAPException {
        MessageFactory factory = MessageFactory.newInstance();
        reqMess = factory.createMessage();
        MimeHeaders mimeHeaders = reqMess.getMimeHeaders();

        mimeHeaders.addHeader("SOAPAction","");

        SOAPPart soapPart = reqMess.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();
        header.detachNode();
        Name bodyName = envelope.createName("GetFile");
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);

        Name name = envelope.createName("file", "", "");
        SOAPElement fileName = bodyElement.addChildElement(name);
        fileName.addTextNode(fileXml);
    }

    public void createSOAPResponse() throws SOAPException, IOException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = soapConnectionFactory.createConnection();

        respMess = connection.call(reqMess, new URL(URL));

        displayMessage(respMess);
    }
}
