import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BankService {

    public String findByAccountNumber(String accountNumber) {
        String account = "No account with such account number found", line;
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/repair.txt"))) {
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (accountNumber.equals(data[0])) {
                    account = data[1];
                    break;
                }
            }
        } catch (IOException e) {
            account = "Sorry, serer error occurred";
        }
        return account;
    }

    public String findBySum(String sum) {
        String account = "No account with such sum found", line;
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/repair.txt"))) {
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (sum.equals(data[1])) {
                    account = data[0];
                    break;
                }
            }
        } catch (IOException e) {
            account= "Sorry, serer error occurred";
        }
        return account;
    }
}
