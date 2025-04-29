import java.util.*;

public class BullyAlgo {

    int coordinator;
    int max_processes;
    boolean processes[];

    // Constructor to create processes
    public BullyAlgo(int max) {
        max_processes = max;
        processes = new boolean[max_processes];
        coordinator = max;

        System.out.println("\nCreating processes..");
        for (int i = 0; i < max; i++) {
            processes[i] = true;
            System.out.println("P" + (i + 1) + " created");
        }

        System.out.println("Process P" + coordinator + " is the coordinator");
    }

    // Display the processes and their status
    void displayProcesses() {
        for (int i = 0; i < max_processes; i++) {
            if (processes[i]) {
                System.out.println("P" + (i + 1) + " is recovered");
            } else System.out.println("P" + (i + 1) + " is crashed");
        }

        System.out.println("Process P" + coordinator + " is the coordinator");
    }

    // Recover a process
    void recoverProcess(int process_id) {
        if (!processes[process_id - 1]) {
            processes[process_id - 1] = true;
            System.out.println("Process " + process_id + " is now recovered.");
        } else
            System.out.println("Process " + process_id + " is already recovered.");
    }

    // Crash a process
    void crashProcess(int process_id) {
        if (!processes[process_id - 1]) {
            System.out.println("Process " + process_id + " is already crashed.");
        } else {
            processes[process_id - 1] = false;
            System.out.println("Process " + process_id + " is crashed.");
        }
    }

    // Run the election algorithm
    void runElection(int process_id) {
        coordinator = process_id;
        boolean keepGoing = true;

        for (int i = process_id; i < max_processes && keepGoing; i++) {
            System.out.println("Election message sent from process " + process_id + " to process " + (i + 1));
            if (processes[i]) {
                keepGoing = false;
                runElection(i + 1);
            }
        }
    }

    public static void main(String args[]) {
        BullyAlgo bully = null;
        int max_processes = 0, process_id = 0;
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nBully Algorithm");
            System.out.println("1 - Create processes");
            System.out.println("2 - Display processes");
            System.out.println("3 - Recover a process");
            System.out.println("4 - Crash a process");
            System.out.println("5 - Run election algorithm");
            System.out.println("6 - Exit Program");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the number of processes: ");
                    max_processes = sc.nextInt();
                    bully = new BullyAlgo(max_processes);
                    break;
                case 2:
                    if (bully == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    bully.displayProcesses();
                    break;
                case 3:
                    if (bully == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    System.out.print("\nEnter the process number to recover: ");
                    process_id = sc.nextInt();
                    bully.recoverProcess(process_id);
                    break;
                case 4:
                    if (bully == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    System.out.print("\nEnter the process number to crash: ");
                    process_id = sc.nextInt();
                    bully.crashProcess(process_id);
                    break;
                case 5:
                    if (bully == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    System.out.print("\nEnter the process number which will perform election: ");
                    process_id = sc.nextInt();
                    bully.runElection(process_id);
                    bully.displayProcesses();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        }
    }
}
