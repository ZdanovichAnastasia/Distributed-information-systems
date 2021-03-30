package DI;

import BusinessLogic.CustomerService;
import BusinessLogicModels.ICustomerService;
import DataAccess.CustomerRepository;

public class DIContainer {
    private static DIContainer container;

    private DIContainer(){}
    public static DIContainer getInstance(){
        if(container == null)
            container = new DIContainer();
        return container;
    }

    public ICustomerService createService(){
        ICustomerService iCustomerService = new CustomerService(CustomerRepository.getInstance());
        return CustomerService.getInstance(CustomerRepository.getInstance());
    }
}
