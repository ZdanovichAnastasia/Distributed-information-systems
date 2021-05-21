package DI;

import BusinessLogic.EnterpriseService;
import BusinessLogicModels.IEnterpriseService;


public class Container {
    private static Container container;

    private Container(){}
    public static Container getInstance(){
        if(container == null)
            container = new Container();
        return container;
    }

    public IEnterpriseService createService() {
        IEnterpriseService enterpriseService = new EnterpriseService();
        return enterpriseService;
    }
}
