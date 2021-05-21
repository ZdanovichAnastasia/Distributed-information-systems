package DI;

import BusinessLogic.BankService;
import BusinessLogicModels.IBankService;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;

public class DIContainer {
    private static DIContainer container;

    private DIContainer(){}
    public static DIContainer getInstance(){
        if(container == null)
            container = new DIContainer();
        return container;
    }
    public IBankService createBankService() throws MalformedURLException, ServiceException {
        return BankService.getInstance();
    }
}
