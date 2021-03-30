package DataAccess;


import DataAccessModels.CustomerEntity;
import DataAccessModels.ICustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class CustomerRepository implements ICustomerRepository {
    private static ICustomerRepository repository;
    public static ICustomerRepository getInstance(){
        if(repository == null) repository = new CustomerRepository();
        return repository;
    }
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    @PersistenceContext(unitName = "EntityBean")
    EntityManager em = entityManagerFactory.createEntityManager();
    @Override
    public CustomerEntity findById(int customerNr) {
        CustomerEntity customer = null;
        try {
            em.getTransaction().begin();
            customer = em.find(CustomerEntity.class, customerNr);
            em.getTransaction().commit();
            em.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
}
