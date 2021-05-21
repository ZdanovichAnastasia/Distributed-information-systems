package Controller;

import BusinessLogicModels.IBankService;
import DI.DIContainer;

import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class Controller {
    private IBankService bankService = DIContainer.getInstance().createBankService();

    public Controller() throws MalformedURLException, ServiceException {
    }

    private String readChoices(BufferedReader reader) throws IOException {
        System.out.println("0 - exit");
        System.out.println("1 - enter account number");
        System.out.println("2 - enter sum");
        return reader.readLine();
    }

    public void start() throws ServiceException, IOException {
        String account = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isActive = true;
        while (isActive) {
            String choice = readChoices(reader);
            switch (choice) {
                case "1":
                    System.out.println("Enter account number");
                    String accountNumber = reader.readLine();
                    account = bankService.findByAccountNumber(accountNumber);
                    System.out.println("Here is what was found with account number = " + accountNumber + ":\n" + account + "\n");
                    break;
                case "2":
                    System.out.println("Enter the sum");
                    String accountSum = reader.readLine();
                    account = bankService.findBySum(accountSum);
                    System.out.println("Here is what was found with account sum = " + accountSum + ":\n" + account + "\n");
                    break;
                case "0":
                    isActive = false;
                    break;
                default:
                    System.out.println("Enter valid number");
            }
        }
    }

}
