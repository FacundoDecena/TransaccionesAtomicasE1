import java.rmi.RemoteException;

public class Account implements AccountInterface {

    private static final long serialVersionUID = 761044890242705632L;
    private String id;
    private double balance;
    private boolean available;

    public Account(String id) {
        this.id = id;
        this.balance = 0.0;
        available = true;
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
    public boolean getAvailability() throws RemoteException {
        return this.available;
    }

    @Override
    public void update(double newBalance) throws RemoteException {
        this.balance = newBalance;
    }

    @Override
    public void block() throws RemoteException {
        this.available = false;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void free() throws RemoteException {
        this.available = true;
        notify();
    }
}