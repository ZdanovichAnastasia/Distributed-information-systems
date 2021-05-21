package BusinessLogicModels;

import org.xml.sax.SAXException;

import javax.xml.soap.SOAPException;
import java.io.IOException;

public interface IEnterpriseService {
    void createSOAPRequest(String fileXml) throws SOAPException;
    void createSOAPResponse() throws SOAPException, IOException, SAXException;
}
