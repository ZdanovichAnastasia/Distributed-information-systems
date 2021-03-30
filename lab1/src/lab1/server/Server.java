package lab1.server;

import lab1.CommonRemote.Change;
import lab1.CommonRemote.IChange;
import lab1.Options.OptionsProvider;
import lab1.Options.ServerOptions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    Server(){}

    public void startServer(){
        StringBuilder filePath = new StringBuilder();
        filePath.append("options.json");
        Path path = Paths.get(filePath.toString());
        OptionsProvider optionsProvider = new OptionsProvider();
        ServerOptions sOptions = optionsProvider.getOptions(path);
        try{
            Change change = new Change();
            IChange stub = (IChange) UnicastRemoteObject.exportObject(change,0);
            Registry registry = LocateRegistry.createRegistry(sOptions.getPort());
            registry.bind(sOptions.getName(), stub);
        }catch (Throwable cause){
            System.out.println("couldn't bind cause "+ cause.getMessage());
        }
    }
}
