package BusinessLogic;

import BusinessLogicModels.Customer;
import BusinessLogicModels.ICustomerService;
import DataAccess.CustomerRepository;
import DataAccessModels.CustomerEntity;
import DataAccessModels.ICustomerRepository;


public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    private static ICustomerService customerService;
    public static ICustomerService getInstance(ICustomerRepository customerRepository){
        if(customerService == null) customerService = new CustomerService(customerRepository);
        return customerService;
    }

    @Override
    public Customer findByID(int id) {
        CustomerEntity customerEntity = customerRepository.findById(id);
        if(customerEntity!=null) {
            Customer customer = new Customer(customerEntity.getIdcustomer(), customerEntity.getName(), customerEntity.getZip(), customerEntity.getCity());
            return customer;
        }
        return null;
    }
}
