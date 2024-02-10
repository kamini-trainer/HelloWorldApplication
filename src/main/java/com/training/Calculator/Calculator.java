package com.training.Calculator;

import org.openqa.selenium.NotFoundException;

import javax.lang.model.UnknownEntityException;
import java.util.Scanner;

public class Calculator {

    private Scanner scanner;

    public Calculator() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Simple Calculator");
        System.out.println("Enter two numbers:");

        System.out.print("Number 1: ");
        double num1 = scanner.nextDouble();

        System.out.print("Number 2: ");
        double num2 = scanner.nextDouble();

        System.out.println("\nSelect operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");

        System.out.print("Enter the operation (1-4): ");
        int choice = scanner.nextInt();

        double result = performOperation(num1, num2, choice);

        System.out.println("Result: " + result);
    }

    public double performOperation(double num1, double num2, int choice) {
        Operation operation = new Operation();

        switch (choice) {
            case 1:
                return operation.add(num1, num2);
            case 2:
                return operation.subtract(num1, num2);
            case 3:
                return operation.multiply(num1, num2);
            case 4:
                return operation.divide(num1, num2);
            default:
                throw new UnsupportedOperationException("operation not found");
        }
    }
}

