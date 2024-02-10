package com.training.Calculator;

public class Operation {
    double add(double num1, double num2) {
        return num1 + num2;
    }

    double subtract(double num1, double num2) {
        return num1 - num2;
    }

    double multiply(double num1, double num2) {
        return num1 * num2;
    }

    double divide(double num1, double num2) {
        if(num2==0){   // error code [DB_10234] ,  "cannot parse string values"
            throw new UnsupportedOperationException("Division by zero not allowed");
        }
        // error logs, info logs ....
        return num1 / num2;

    }
}
