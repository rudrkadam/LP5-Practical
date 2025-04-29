import java.io.*;
import java.net.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.print("Enter number of clocks: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        LocalDateTime[] times = new LocalDateTime[n];

        writer.println(n);

        for (int i = 0; i < n; i++) {
            System.out.print("Enter time for clock " + (i + 1) + " (yyyy-MM-dd HH:mm:ss): ");
            String timeStr = scanner.nextLine();
            times[i] = LocalDateTime.parse(timeStr, formatter);
            writer.println(times[i].format(formatter));
        }

        for (int i = 0; i < n; i++) {
            String response = reader.readLine();
            long adjustmentSeconds = Long.parseLong(response);
            LocalDateTime newTime = times[i].plusSeconds(adjustmentSeconds);
            System.out.println("\nClock " + (i + 1) + " adjustment: " + adjustmentSeconds + " seconds");
            System.out.println("Old time: " + times[i].format(formatter));
            System.out.println("New time: " + newTime.format(formatter));
        }
        System.out.println("\nAll adjustments sent. Closing connection...");

        socket.close();
        scanner.close();
    }
}