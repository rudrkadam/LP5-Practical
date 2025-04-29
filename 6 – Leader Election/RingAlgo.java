import java.util.*;

public class RingAlgo {

    int max_processes;
    int coordinator;
    boolean processes[];
    ArrayList<Integer> pid;

    // Constructor to create processes
    public RingAlgo(int max) {
        coordinator = max;
        max_processes = max;
        pid = new ArrayList<Integer>();
        processes = new boolean[max];

        System.out.println("\nCreating processes..");
        for (int i = 0; i < max; i++) {
            processes[i] = true;
            System.out.println("P" + (i + 1) + " created.");
        }

        System.out.println("P" + (coordinator) + " is the coordinator");
    }

    // Display the processes and their status
    void displayProcesses() {
        for (int i = 0; i < max_processes; i++) {
            if (processes[i]) System.out.println("P" + (i + 1) + " is recovered.");
            else System.out.println("P" + (i + 1) + " is crashed.");
        }

        System.out.println("P" + (coordinator) + " is the coordinator");
    }

    // Recover a process
    void recoverProcess(int process_id) {
        if (!processes[process_id - 1]) {
            processes[process_id - 1] = true;
            System.out.println("Process P" + (process_id) + " is recovered.");
        } else System.out.println("Process P" + (process_id) + " is already recovered.");
    }

    // Crash a process
    void crashProcess(int process_id) {
        if (!processes[process_id - 1]) {
            System.out.println("Process P" + (process_id) + " is already crashed.");
        } else {
            processes[process_id - 1] = false;
            System.out.println("Process P" + (process_id) + " is crashed.");
        }
    }

    // Display the array list
    void displayArrayList(ArrayList<Integer> pid) {
        System.out.print("[ ");
        for (Integer x : pid) System.out.print(x + " ");

        System.out.print(" ]\n");
    }

    // Run the election algorithm
    void initElection(int process_id) {
        if (processes[process_id - 1]) {
            pid.add(process_id);

            int temp = process_id;

            System.out.print("Process P" + process_id + " sending the following list: ");
            displayArrayList(pid);

            while (temp != process_id - 1) {
                if (processes[temp]) {
                    pid.add(temp + 1);
                    System.out.print("Process P" + (temp + 1) + " sending the following list: ");
                    displayArrayList(pid);
                }
                temp = (temp + 1) % max_processes;
            }

            coordinator = Collections.max(pid);
            System.out.println("Process P" + process_id + " has declared P" + coordinator + " as the coordinator");
            pid.clear();
        }
    }

    public static void main(String args[]) {
        RingAlgo ring = null;

        int max_processes = 0, process_id = 0;
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nRing Algorithm");
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
                    System.out.print("\nEnter the total number of processes: ");
                    max_processes = sc.nextInt();
                    ring = new RingAlgo(max_processes);
                    break;
                case 2:
                    if (ring == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    ring.displayProcesses();
                    break;
                case 3:
                    if (ring == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    System.out.print("\nEnter the process to recover: ");
                    process_id = sc.nextInt();
                    ring.recoverProcess(process_id);
                    break;
                case 4:
                    if (ring == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    System.out.print("\nEnter the process to crash: ");
                    process_id = sc.nextInt();
                    ring.crashProcess(process_id);
                    break;
                case 5:
                    if (ring == null) {
                        System.out.println("No processes created yet.");
                        break;
                    }
                    System.out.print("\nEnter the process which will initiate election: ");
                    process_id = sc.nextInt();
                    ring.initElection(process_id);
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
