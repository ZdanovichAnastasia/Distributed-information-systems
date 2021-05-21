package by.bsuir.laba8.service;

import by.bsuir.laba8.service.models.IInputService;
import java.util.Scanner;

public class InputService implements IInputService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Double ReadNumber() {
        double number = 0;
        while(true) {
            String input = scanner.nextLine();
            try{
                if(!input.isEmpty()){
                    number = Double.parseDouble(input);
                    break;
                }
                System.out.print("Input is empty. Try again: ");
            }catch (NumberFormatException e){
                System.out.println("Input incorrect. Try again:");
            }
        }
        return number;
    }
}
