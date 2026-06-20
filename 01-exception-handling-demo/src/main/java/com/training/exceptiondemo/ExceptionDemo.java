package com.training.exceptiondemo;

import java.util.Scanner;

public class ExceptionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first number: ");
            int first = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter second number: ");
            int second = Integer.parseInt(scanner.nextLine());

            int result = first / second;
            System.out.println("Result: " + result);

        } catch (NumberFormatException ex) {
            System.out.println("Error: Please enter valid numbers.");
        } catch (ArithmeticException ex) {
            System.out.println("Error: Cannot divide by zero.");
        } finally {
            System.out.println("Program finished.");
            scanner.close();
        }
    }
}
