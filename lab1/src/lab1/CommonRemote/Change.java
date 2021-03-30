package lab1.CommonRemote;
import java.rmi.RemoteException;

public class Change implements IChange {
    @Override
    public String formatString(String input) throws RemoteException{
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<input.length(); i++){
            if (i%2==0) {
                sb.append("ab");
            }
            else{
                sb.append("cd");
            }
        }
        return sb.toString();
    }
}
