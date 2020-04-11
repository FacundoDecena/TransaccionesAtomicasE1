import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class Server implements Banco{

    private List<Account>accounts;
    private static final int PORT = 3001;
    private static Registry registry;

    public Server() throws java.rmi.RemoteException{
        this.accounts = new ArrayList<>();
        accounts.add(new Account("000-0"));
        accounts.add(new Account("000-1"));
        accounts.add(new Account("000-2"));
        accounts.add(new Account("000-3"));
        accounts.add(new Account("000-4"));
        accounts.add(new Account("000-5"));
        accounts.add(new Account("000-6"));
        accounts.add(new Account("000-7"));
        accounts.add(new Account("000-8"));
        accounts.add(new Account("000-9"));
        registry = LocateRegistry.createRegistry(PORT);
    }

    @Override
    public String TestConnection() {
        System.out.println("Connection established");
        System.out.println();
        return "Connection established";
    }

    @Override
    public boolean write(String accountId, double balance) throws RemoteException {
        System.out.printf("Trying to set new balance for account %s, %f\n", accountId, balance);
        for(Account account : this.accounts) {
            if(account.getId().equals(accountId)){
                System.out.println("Account found");
                account.update(balance);
                System.out.println("New balance set");
                System.out.println();
                account.free();
                return true;
            }
        }
        System.out.printf("Did not find account %s\n", accountId);
        System.out.println();
        return false;
    }

    @Override
    public double read(String accountId, boolean block) throws RemoteException {
        System.out.printf("Trying to read account %s\n", accountId);
        for(Account account : this.accounts) {
            if(account.getId().equals(accountId)){
                if (block)
                    account.block();
                System.out.println("Account found");
                System.out.println("Read performed");
                System.out.println();
                return account.getBalance();
            }
        }
        System.out.printf("Did not find account %s\n", accountId);
        throw new RemoteException("No such ID");
    }

    public static void main(String args[]) {
        try {
            Server obj = new Server();
            Banco stub = (Banco) UnicastRemoteObject.exportObject(obj, 0);
    
            // Bind the remote object's stub in the registry
            registry.bind("Banco", stub);
    
            System.err.println("SERVIDOR LISTO Y ESPERANDO ...");
        } catch (Exception e) {
            System.err.println("Server exception ERROR: " + e.toString());
            e.printStackTrace();
        }
    }
}