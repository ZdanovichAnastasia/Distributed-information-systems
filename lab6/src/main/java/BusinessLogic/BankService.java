package BusinessLogic;

import BusinessLogicModels.IBankService;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BankService implements IBankService {
    private static IBankService bankService;
    private String endpoint = "http://localhost:8080/axis/BankService.jws";
    private Call call;

    public BankService(){
        Service service = new Service();
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(endpoint));
        } catch (ServiceException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static IBankService getInstance() throws MalformedURLException, ServiceException {
        if(bankService == null) bankService = new BankService();
        return bankService;
    }

    public String findByAccountNumber(String account) throws IOException{
        Object[] param = new Object[]{account};
        return (String) call.invoke("findByAccountNumber", param);
    }

    public String findBySum(String account) throws IOException{
        Object[] param = new Object[]{account};
        return (String) call.invoke("findBySum", param);
    }


}
