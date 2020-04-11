import java.rmi.RemoteException;
import java.io.Serializable;

public interface AccountInterface extends Serializable{
    public String getId() throws RemoteException;
    public double getBalance() throws RemoteException;
    public void update(double newBalance) throws RemoteException;
}