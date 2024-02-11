import com.training.Calculator.Calculator;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.assertj.core.api.Assertions;
/*
* 1. Either make the method setup static or annotate the class with
*       @TestInstance(TestInstance.Lifecycle.PER_CLASS)
* 2. Common errors:
*   a) org.junit.platform.commons.JUnitException:
*   @BeforeAll method 'void CalculatorTest.setup()' must be static unless the test class is
*   annotated with @TestInstance(Lifecycle.PER_CLASS).
        sol: fix the below exception by using @TestInstance
*   b)
 * */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

     private static Calculator testCalculator = null;


     @BeforeAll
     static
     void initialSetUp(){

         testCalculator = new Calculator();
    }

    @Test
    @Description("testing for the sum operation")
    @Order(2)
    void testPerformOperation(){
        double num1 = 20d;
        double num2 = 40d;
        int choice = 1;
        double result = testCalculator.performOperation(num1, num2, choice);
        System.out.println(result);
        org.junit.jupiter.api.Assertions.assertEquals(60d, result);
    }

    @ParameterizedTest
    @CsvSource({"2,3,5", "5, 7, 12", "10, 5, 15"})
    void testWithParams(int num1, int num2, int expectedSum){
        int choice = 1;
        double result = testCalculator.performOperation(num1, num2, choice);
        System.out.println(result);
        org.junit.jupiter.api.Assertions.assertEquals(expectedSum, result);

    }

    @Test
    void compareTwoObjects(){
        Person expected = new Person("John", 25, new Address("123 Street"));
        Person actual = new Person("John", 25, new Address("456 Street"));

        // Compare objects, excluding the 'address' field
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("address")
                .isEqualTo(expected);
    }

    @Test
    void testForDivison(){
        int choice = 4;
        //double result = testCalculator.performOperation(1, 0, choice);
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            testCalculator.performOperation(4, 0, choice);
        });

        System.out.println(exception);
        org.junit.jupiter.api.Assertions.assertEquals(exception.getMessage(), "Division by zero not allowed");
    }

    @Test
    void testForperformOperationException(){
        int choice = 5;
        //double result = testCalculator.performOperation(1, 0, choice);
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            testCalculator.performOperation(5, 0, choice);
        });

        org.junit.jupiter.api.Assertions.assertEquals(exception.getMessage(), "operation not found");
    }

    @AfterAll
    static void doCleanup(){
         //clean all your resources
    }

    // Define the Person and Address classes
    static class Person {
        private String name;
        private int age;
        private Address address;

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        // Getters and setters
    }

    static class Address {
        private String street;

        public Address(String street) {
            this.street = street;
        }
        // Getters and setters
    }
}
