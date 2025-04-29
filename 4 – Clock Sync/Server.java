import java.io.*;
import java.net.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client...");
        Socket client = serverSocket.accept();
        System.out.println("\nClient connected.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int n = Integer.parseInt(reader.readLine());
        List<LocalDateTime> times = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String timeStr = reader.readLine();
            LocalDateTime time = LocalDateTime.parse(timeStr, formatter);
            times.add(time);
            System.out.println("Received time for clock " + (i + 1) + ": " + timeStr);
        }

        // Calculate average time by converting to epoch seconds
        long sum = 0;
        for (LocalDateTime time : times) {
            sum += time.toEpochSecond(ZoneOffset.UTC);
        }
        
        long avgSeconds = sum / n;
        LocalDateTime avgTime = LocalDateTime.ofEpochSecond(avgSeconds, 0, ZoneOffset.UTC);
        System.out.println("\nAverage time: " + avgTime.format(formatter));

        // Send adjustments in seconds to each client
        System.out.println("\nSending adjustments to client...");
        for (LocalDateTime time : times) {
            long clientTimeSeconds = time.toEpochSecond(ZoneOffset.UTC);
            long adjustment = avgSeconds - clientTimeSeconds;
            writer.println(adjustment);
            System.out.println("Sent adjustment: " + adjustment + " seconds");
        }

        client.close();
        serverSocket.close();
    }
}