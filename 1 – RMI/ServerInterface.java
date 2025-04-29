import java.rmi.*;

public interface ServerInterface extends Remote {

    // DECLARE ALL THE REMOTE METHODS IN THIS INTERFACE

    // 1. Method to add two numbers
    double add(double num1, double num2) throws RemoteException;
    
    // 2. Method to subtract two numbers
    double subtract(double num1, double num2) throws RemoteException;

    // 3. Method to multiply two numbers
    double multiply(double num1, double num2) throws RemoteException;
    
    // 4. Method to divide two numbers
    double divide(double num1, double num2) throws RemoteException;

    // 5. Method to find the 2's power of a number
    double powerOf2(double num) throws RemoteException;

    // 6. Method to convert Celsius to Fahrenheit
    double celsiusToFahrenheit(double celsius) throws RemoteException;

    // 7. Method to convert Miles to Kilometers
    double milesToKilometers(double miles) throws RemoteException;

    // 8. Method to append and print the name of the user after "Hello "
    String hello(String name) throws RemoteException;

    // 9. Method to compare two strings lexicographically
    int compareStrings(String str1, String str2) throws RemoteException;

    // 10. Method to count the number of vowels in a string
    int countVowels(String str) throws RemoteException;

    // 11. Method to find the factorial of a number
    long factorial(long num) throws RemoteException;
}