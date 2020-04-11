import java.rmi.RemoteException;
import java.io.Serializable;

public interface AccountInterface extends Serializable{
    public String getId() throws RemoteException;
    public double getBalance() throws RemoteException;
    public boolean getAvailability() throws RemoteException;
    public void update(double newBalance) throws RemoteException;
    public void block() throws RemoteException;
    public void free() throws RemoteException;
}