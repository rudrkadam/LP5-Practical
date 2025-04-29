import mpi.*;

public class ArraySum {
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int root = 0;

        int[] sendBuffer = new int[size];
        int[] recvBuffer = new int[size];
        int[] gatherBuffer = new int[size];

        if (rank == root) {
            sendBuffer = new int[size];
            System.out.println("\nRoot Initializing data: ");
            for (int i = 0; i < size; i++) {
                sendBuffer[i] = i + 1;
                System.out.println("Element " + i + " = " + sendBuffer[i]);
            }
        }

        MPI.COMM_WORLD.Scatter(
                sendBuffer, 0, 1, MPI.INT,
                recvBuffer, 0, 1, MPI.INT,
                root);

        int localSum = recvBuffer[0];

        System.out.println();
        System.out.println("Process " + rank + " received " + recvBuffer[0] +
                " and the computed intermediate sum is " + localSum);

        MPI.COMM_WORLD.Gather(
                new int[] { localSum }, 0, 1, MPI.INT,
                gatherBuffer, 0, 1, MPI.INT,
                root);
        int totalSum = 0;
        if (rank == root) {
            for (int val : gatherBuffer) {
                totalSum += val;
            }
            System.out.println("\nFinal Total Sum at Root: " + totalSum);
        }

        MPI.Finalize();

    }
}
