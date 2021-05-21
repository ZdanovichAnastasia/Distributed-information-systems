package Controller;

import BusinessLogicModels.IEnterpriseService;
import DI.Container;
import org.xml.sax.SAXException;

import javax.xml.soap.SOAPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnterpriseController {
    private IEnterpriseService enterpriseService = Container.getInstance().createService();

    public void start() throws SOAPException, IOException, SAXException {
        String fileXml="";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.println("Enter the XML file:");
            fileXml = br.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }

        enterpriseService.createSOAPRequest(fileXml);
        enterpriseService.createSOAPResponse();
    }

}
