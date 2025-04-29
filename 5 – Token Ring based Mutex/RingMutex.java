import java.io.*;
import java.net.*;

public class RingMutex {
    private static boolean hasToken;
    private static String nextSiteIP;
    private static int listenPort;
    private static int sendPort;

    // Constructor to initialize the RingMutex with token status and next site information
    public RingMutex(boolean hasToken, String nextSiteIP, int listenPort, int sendPort) {
        RingMutex.hasToken = hasToken;
        RingMutex.nextSiteIP = nextSiteIP;
        RingMutex.listenPort = listenPort;
        RingMutex.sendPort = sendPort;
    }

    // Start the RingMutex process
    public void start() {
        // Start a thread to listen for incoming token
        new Thread(() -> listenForToken()).start();

        // If this site has token initially, send it manually
        if (hasToken) {
            try {
                Thread.sleep(2000); // Small delay to allow other site to start
                enterCriticalSection();
                passToken();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Listen for incoming token messages
    private void listenForToken() {
        try (ServerSocket serverSocket = new ServerSocket(listenPort)) {
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = reader.readLine();
                if ("TOKEN".equals(message)) {
                    hasToken = true;
                    enterCriticalSection();
                    passToken();
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Pass the token to the next site
    private void passToken() {
        try (Socket socket = new Socket(nextSiteIP, sendPort)) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("TOKEN\n");
            writer.flush();
            hasToken = false;
            System.out.println("Token passed to " + nextSiteIP + ":" + sendPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Simulate entering the critical section
    private void enterCriticalSection() {
        System.out.println("\n==== Entering Critical Section ====");
        try {
            Thread.sleep(3000); // Simulate doing some work in CS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==== Exiting Critical Section ====\n");
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java Site <hasToken:true/false> <nextSiteIP> <listenPort> <sendPort>");
            return;
        }
        boolean hasToken = Boolean.parseBoolean(args[0]);
        String nextSiteIP = args[1];
        int listenPort = Integer.parseInt(args[2]);
        int sendPort = Integer.parseInt(args[3]);

        RingMutex site = new RingMutex(hasToken, nextSiteIP, listenPort, sendPort);
        site.start();
    }
}
