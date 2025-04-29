import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String args[]) {
        try {
            // Create and export the remote object
            ServerImplementation serverImpl = new ServerImplementation();

            // Create the registry and bind the remote object to it
            LocateRegistry.createRegistry(1099);

            // Print that the server is ready to accept requests
            System.out.println("Server is ready to accept requests...");

            // Bind the remote object to the registry with a name
            Naming.rebind("Server", serverImpl);
        } catch (Exception e) {
            System.out.println("An exception occured: " + e.getMessage());
        }
    }
}