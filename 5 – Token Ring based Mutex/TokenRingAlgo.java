import java.util.Scanner;

class TokenRingAlgo {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int n = sc.nextInt();

        int token = 0;

        // Print the nodes in the ring
        for (int i = 0; i < n; i++) System.out.print(" " + i);
        System.out.println(" " + 0);

        int choice = 0;

        do {
            System.out.print("\nEnter sender: ");
            int sender = sc.nextInt();

            System.out.print("Enter receiver: ");
            int receiver = sc.nextInt();

            // Validate sender and receiver
            if (sender < 0 || sender >= n) {
                System.out.println("Invalid sender node. Please enter a valid node.");
                continue;
            }
            if (receiver < 0 || receiver >= n) {
                System.out.println("Invalid receiver node. Please enter a valid node.");
                continue;
            }

            System.out.print("Enter Data (word): ");
            String data = sc.next();

            // Pass token from 0 to sender
            System.out.print("\nToken passing:");
            for (int i = token, j = token; (i % n) != sender; i++, j = (j + 1) % n) {
                System.out.print(" " + j + " ->");
            }
            System.out.println(" " + sender);

            // Sender is sending data to receiver
            System.out.println("\n...Sender " + sender + " sending data " + "\"" + data + "\"...\n");
            System.out.println("Data " + "\"" + data + "\"" + " sent from " + sender + " to " + (sender + 1) % n);
            
            // Data is forwarded from sender to receiver
            for (int i = (sender + 1) % n; i != receiver; i = (i + 1) % n) {
                System.out.println("Data " + "\"" + data + "\"" + " forwarded from " + i + " to " + (i + 1) % n);
            }

            // Receiver receives the data
            System.out.println("\nReceiver " + receiver + " received data " + "\"" + data + "\"!\n");
            token = sender;

            // Ask if the user wants to send data again or exit
            System.out.print("Enter 1 to send data again, or 0 to exit: ");
            choice = sc.nextInt();
        } while (choice == 1);

        sc.close();
    }

}
