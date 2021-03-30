package DataAccessModels;

public interface ICustomerRepository {
    CustomerEntity findById(int customerNr);
}
