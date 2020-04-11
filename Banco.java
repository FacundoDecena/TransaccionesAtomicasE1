import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banco extends Remote {
    String TestConnection() throws RemoteException;
    boolean write(String accountId, double balance) throws RemoteException;
    double read(String accountId, boolean block) throws RemoteException;
}
