package DI;

import BusinessLogic.SerialServices;
import BusinessLogicModels.ISerialServices;
import DataAccess.SerialRepository;

import java.nio.file.Path;

public class DIContainer {
    private static DIContainer container;

    private DIContainer(){}
    public static DIContainer getInstance(){
        if(container == null)
            container = new DIContainer();
        return container;
    }

    public ISerialServices createBean(Path path){
        ISerialServices iSerialServices = new SerialServices(SerialRepository.getInstance(path));
        return iSerialServices;
    }
}
