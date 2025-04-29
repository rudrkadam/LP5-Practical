import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        try {
            // Get reference to the remote object
            String serverURL = "rmi://localhost/Server";
            ServerInterface serverIntf = (ServerInterface) Naming.lookup(serverURL);

            Scanner sc = new Scanner(System.in);

            // IMPLEMENT THE PROBLEM STATEMENT HERE ↴
            

            // INVOKE THE REMOTE METHOD(S) to perform the necessary operations
            // using the `serverIntf` reference ↴
            
            sc.close();
        } catch (Exception e) {
            System.out.println("An exception occured: " + e.getMessage());
        }
    }
}
