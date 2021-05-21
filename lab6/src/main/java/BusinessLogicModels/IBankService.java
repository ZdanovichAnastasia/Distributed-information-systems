package BusinessLogicModels;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

public interface IBankService {
    String findByAccountNumber(String account) throws IOException, ServiceException;
    String findBySum(String account) throws IOException, ServiceException;
}
