package lab1.CommonRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChange  extends Remote {
    String formatString(String input) throws RemoteException;
}
