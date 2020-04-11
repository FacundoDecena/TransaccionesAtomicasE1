import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private static final double EPSILON = 0.0001;

    private static boolean newDeposite(String id, double deposite, Banco stub) {
        try {
            double currentBalance = stub.read(id, true);
            currentBalance += deposite;
            return stub.write(id, currentBalance);

        } catch (RemoteException e) {
            System.err.println("No se encontro la cuenta");
            return false;
        }

    }

    private static void newDepositeView(Banco stub) {
        Scanner scan = new Scanner(System.in);
        String id;
        double deposite = 0;
        boolean ok;
        System.out.println("======== Banco NSL ========");
        System.out.println("Ingrese su numero de cuenta");
        id = scan.nextLine();
        do{
            try{
                System.out.println("Ingrese el dinero");
                deposite = scan.nextDouble();
                ok = true;
            } catch(Exception e){
                System.out.println("Ingrese monto con , para separar decimales");
                scan.nextLine();
                ok = false;
            }   
        } while (ok == false);
        
        if(newDeposite(id, deposite, stub))
            System.out.println("Deposito exitoso");
        else
        System.out.println("No se efectuo el deposito");

        scan.close();
    }

    private static boolean newWithdrawal(String id, double withdrawal, Banco stub){
        try {
            double currentBalance = stub.read(id, true);
            // If currentBalance >= withdrawal
            if(currentBalance > withdrawal || Math.abs(currentBalance - withdrawal) < EPSILON ){
                currentBalance -= withdrawal;
                return stub.write(id, currentBalance);
            }
            stub.forceFree(id);
            System.err.println("Sin fondos suficientes");
            return false;

        } catch (RemoteException e) {
            System.err.println("No se encontro la cuenta");
            return false;
        }
    }

    private static void newWithdrawalView(Banco stub) {
        Scanner scan = new Scanner(System.in);
        String id;
        double deposite = 0;
        boolean ok;
        System.out.println("======== Banco NSL ========");
        System.out.println("Ingrese su numero de cuenta");
        id = scan.nextLine();
        do{
            try{
                System.out.println("Ingrese monto a retirar");
                deposite = scan.nextDouble();
                ok = true;
            } catch(Exception e){
                System.out.println("Ingrese monto a retirar con , para separar decimales");
                scan.nextLine();
                ok = false;
            }   
        } while (ok == false);
        
        if(newWithdrawal(id, deposite, stub)){
            System.out.println("Retire su dinero");
            System.out.printf("[($%.02f)]\n",deposite);
        }
        else
            System.out.println("No se efectuo el retiro");

        scan.close();
    }


    private static double readBalance(String id, Banco stub){
        try {
            return stub.read(id, false);
        } catch (RemoteException e) {
            System.err.println("No se encontro la cuenta");
            return -1;
        }
    }

    private static void readBalanceView(Banco stub){
        Scanner scan = new Scanner(System.in);
        String id;
        System.out.println("======== Banco NSL ========");
        System.out.println("Ingrese su numero de cuenta");
        id = scan.nextLine();
        double currentBalance = readBalance(id, stub);
        if(currentBalance > 0 || Math.abs(currentBalance - 0) < EPSILON){
            System.out.print("Su saldo es de $");
            System.out.println(currentBalance);
        } else
        System.out.println("");

        scan.close();
    }

    public static void main(String[] args){
        String host = (args.length < 1) ? "localhost" : args[0];
        String stringPort = (args.length < 2) ? "3001" : args[1];
        Scanner scan = new Scanner(System.in);

        try{
            int port = Integer.parseInt(stringPort);
            Registry registry = LocateRegistry.getRegistry(host, port);
            Banco stub = (Banco) registry.lookup("Banco");
            System.out.println(stub.TestConnection());

            int option = 0;
            System.out.println("======== Banco NSL ========");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Ingresar dinero");
            System.out.println("3 - Retirar dinero");
            System.out.println("Cualquier tecla - Salir");
            try {
                option = scan.nextInt();
            } catch (Exception e) {
                option = 0;
            } finally {
                switch (option) {
                    case 1:
                        readBalanceView(stub);
                        break;
                    case 2:
                        newDepositeView(stub);
                        break;
                    case 3:
                        newWithdrawalView(stub);
                        break;
                    default:
                        System.out.println("Gracias por usar nuestros servicios");
                }
            }
            scan.close();

        } catch(NumberFormatException e){
            System.err.println("Client exception: Invalid port");
        }catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

}