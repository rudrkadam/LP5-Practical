import java.rmi.*;
import java.rmi.server.*;

// Class that implements the Remote Interface
public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {

    // ALL THE REMOTE METHODS ARE IMPLEMENTED IN THIS CLASS

    // Constructor
    public ServerImplementation() throws RemoteException {
    }

    public double add(double d1, double d2) throws RemoteException {
        return d1 + d2;
    }

    public double subtract(double d1, double d2) throws RemoteException {
        return d1 - d2;
    }

    public double multiply(double d1, double d2) throws RemoteException {
        return d1 * d2;
    }

    public double divide(double d1, double d2) throws RemoteException {
        if (d2 == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return d1 / d2;
    }

    public double powerOf2(double d1) throws RemoteException {
        return Math.pow(2, d1);
    }

    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        return (celsius * 9 / 5) + 32;
    }

    public double milesToKilometers(double miles) throws RemoteException {
        return miles * 1.60934;
    }

    public String hello(String name) throws RemoteException {
        return "Hello " + name + "!";
    }

    public int compareStrings(String str1, String str2) throws RemoteException {
        return str1.compareTo(str2);
        // 1. Returns 0 if str1 is equal to str2
        // 2. Returns a negative no. if str1 is lexicographically less than str2
        // 3. Returns a positive no. if str1 is lexicographically greater than str2
    }

    public int countVowels(String str) throws RemoteException {
        int count = 0;
        for (char c : str.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public long factorial(long num) throws RemoteException {
        if (num < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        long result = 1;
        for (long i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}