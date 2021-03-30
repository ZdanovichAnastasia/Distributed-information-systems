package lab1.client;

import lab1.CommonRemote.IChange;
import lab1.Options.OptionsProvider;
import lab1.Options.ServerOptions;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    Client(){}

    private String enterString(){
        StringBuilder sb = new StringBuilder();
        System.out.print("Enter the string: ");
        Scanner in = new Scanner(System.in);
        while(true) {
            String input = in.nextLine();
            if(!input.isEmpty()){
             sb.append(input);
             in.close();
             break;
            }
            System.out.print("Input is empty. Try again: ");
        }
        return sb.toString();
    }
    
    public void startClient() {
        try {
            StringBuilder filePath = new StringBuilder();
            filePath.append("options.json");
            Path path = Paths.get(filePath.toString());
            OptionsProvider optionsProvider = new OptionsProvider();
            ServerOptions sOptions = optionsProvider.getOptions(path);
            Registry registry = LocateRegistry.getRegistry(null, sOptions.getPort());
            IChange stub = (IChange) registry.lookup(sOptions.getName());
            String input = enterString();
            System.out.print("Result: "+ stub.formatString(input));
        }catch (Exception e){
            System.err.println("Client exception: "+ e.toString());
        }
    }
}
