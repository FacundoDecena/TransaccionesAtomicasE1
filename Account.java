import java.rmi.RemoteException;

public class Account implements AccountInterface{

    private static final long serialVersionUID = 761044890242705632L;
    private String id;
    private double balance;

    public Account(String id){
        this.id = id;
        this.balance = 0.0;
    }

    @Override
    public double getBalance() throws RemoteException {
        return this.balance;
    }

    @Override
    public String getId() throws RemoteException {
        return this.id;
    }

    @Override
    public void update(double newBalance) throws RemoteException {
        this.balance = newBalance;
    }

}