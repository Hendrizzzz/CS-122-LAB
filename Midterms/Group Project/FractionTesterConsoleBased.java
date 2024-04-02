package edu.slu.prog2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FractionTesterConsoleBased {
    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    // Define ANSI escape codes for text formatting in the terminal
    // These codes are used to display text in different colors and styles
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String PURPLE = "\u001B[34m";
    private static final String BOLD = "\u001B[1m";
    private static final String YELLOW = "\u001B[35m";

    private final static String IOEXCEPTION = "An error occurred while reading the input. ";
    private final static String NUMBERFORMATEXCEPTION = RED + BOLD + "Invalid input. Please enter an integer. Try again. " + RESET + "\n-> ";
    private static final String INVALID_DENOMINATOR =  RED + BOLD +"Invalid input. Denominator cannot be zero. " + RESET;
    private static final String CHOICE_NOT_FOUND = RED + BOLD + "Choice not found. Try again. " + RESET + "\n-> ";

    public static void main(String[] args) {
        FractionTesterConsoleBased program;
        try{
            program = new FractionTesterConsoleBased();
            program.run();
        } catch(IOException e){
            System.out.println(IOEXCEPTION + e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    private void run() throws IOException{
        do {
            int operation = readOperation(); // read the user choice of operation
            switch (operation) {
                case 1, 2, 3, 4 -> { // For arithmetic operations (add, subtract, multiply, divide)
                    int typeOfFractions = readTypeOfFractions();  // Read the type of fractions involved in the operation
                    operate(operation, typeOfFractions);
                }
                case 5 -> { // // For reducing a fraction
                    int typeOfFraction = readTypeOfFraction();
                    reduce(typeOfFraction);
                }
                case 6 -> { // // Exit the program
                    return;
                }
            }
        } while (true); // // Loop indefinitely until the user chooses to exit

    }




    /**
     * Performs the specified operation on the given types of fractions.
     * @param operation The operation to perform.
     * @param typeOfFractions The types of fractions involved in the operation.
     * @throws IOException If an I/O error occurs.
     */
    private void operate(int operation, int typeOfFractions) throws IOException {
        switch (typeOfFractions){ // // Determine the type of fractions involved and perform the operation accordingly
            case 1 -> {
                Fraction first = setUpFraction(false, "one");
                Fraction second = setUpFraction(false, "two");
                performFractionOperation(first, second, operation);
            }
            case 2 -> {
                Fraction first = setUpFraction(false, "one");
                MixedFraction second = (MixedFraction) setUpFraction(true, "two");
                performFractionOperation(first, second, operation);
            }
            case 3 -> {
                MixedFraction first = (MixedFraction) setUpFraction(true, "one");
                Fraction second = setUpFraction(false, "two");
                performFractionOperation(first, second, operation);
            }
            case 4 -> {
                MixedFraction first = (MixedFraction) setUpFraction(true, "one");
                MixedFraction second = (MixedFraction) setUpFraction(true, "two");
                performFractionOperation(first, second, operation);
            }
        }
    }



    /**
     * Performs the specified operation on two fractions.
     * @param first The first fraction.
     * @param second The second fraction.
     * @param operation The operation to perform.
     */
    private <T extends Fraction, U extends Fraction> void performFractionOperation(T first, U second, int operation) {
        // Check if either of the fractions is a MixedFraction
        if (first instanceof MixedFraction || second instanceof MixedFraction){
            MixedFraction firstMixed = getMixedFractionFromFraction(first);
            MixedFraction secondMixed = getMixedFractionFromFraction(second);
            switch (operation){
                case 1 -> displayResult(firstMixed, secondMixed, firstMixed.add(secondMixed), "+");
                case 2 -> displayResult(firstMixed, secondMixed, firstMixed.subtract(secondMixed), "-");
                case 3 -> displayResult(firstMixed, secondMixed, firstMixed.multiplyBy(secondMixed), "x");
                case 4 -> displayResult(firstMixed, secondMixed, firstMixed.divideBy(secondMixed), "÷");
            }
        }
        else {
            // Perform arithmetic operation directly on Fraction objects
            switch (operation){
                case 1 -> displayResult(first, second, first.add(second), "+");
                case 2 -> displayResult(first, second, first.subtract(second), "-");
                case 3 -> displayResult(first, second, first.multiplyBy(second), "x");
                case 4 -> displayResult(first, second, first.divideBy(second), "÷");
            }
        }
    }

    /**
     * Helper method to convert a Fraction to a MixedFraction.
     * @param fraction The fraction to convert.
     * @return The converted MixedFraction.
     */
    private <T extends Fraction> MixedFraction getMixedFractionFromFraction(T fraction) {
        return (fraction instanceof MixedFraction) ? (MixedFraction) fraction : MixedFraction.toMixedFraction(fraction);
    }



    /**
     * Reduces the given fraction to its simplest form.
     *
     * @param typeOfFraction The type of fraction to reduce (1: Regular Fraction, 2: Mixed Fraction).
     * @throws IOException If an I/O error occurs during user input.
     */
    private void reduce(int typeOfFraction) throws IOException {
        // Declare variables to hold the fraction and its reduced form
        Fraction fraction;
        MixedFraction result;

        // Check if the fraction is a mixed fraction
        boolean isMixedFraction = (typeOfFraction == 2);

        // Set up the fraction based on its type (regular or mixed)
        fraction = setUpFraction(isMixedFraction, "");

        // Reduce the fraction to its simplest form
        result = isMixedFraction ? (MixedFraction) fraction : new MixedFraction(fraction);
        result.simplify();

        // Display the original fraction and its reduced form
        displayResult(MixedFraction.toMixedFraction(fraction), result);
    }





    /**
     * Sets up a fraction based on user input.
     * @param isMixedFraction Indicates whether the fraction is a mixed fraction.
     * @param fractionPosition The position of the fraction (e.g., "one", "two").
     * @return The initialized fraction.
     * @throws IOException If an I/O error occurs.
     */
    private Fraction setUpFraction(boolean isMixedFraction, String fractionPosition) throws IOException {
        Fraction fraction = new Fraction();
        System.out.println(YELLOW + BOLD + "Enter values for Fraction " + fractionPosition + "... " + RESET);
        int whole = isMixedFraction ? readInteger("Whole Number") : 0;
        fraction.setNumerator(readInteger("Numerator"));
        do {
            if (!fraction.setDenominator(readInteger("Denominator"))) {
                System.out.println(INVALID_DENOMINATOR);
                continue;
            }
            break;
        } while (true);
        if (isMixedFraction) {
            return new MixedFraction(whole, fraction);
        } else {
            return fraction;
        }
    }



    /**
     * Reads the type of fraction to reduce from the user input.
     * @return The type of fraction to reduce (1 or 2).
     * @throws IOException If an I/O error occurs.
     */
    private int readTypeOfFraction() throws IOException {
        System.out.print("""
                +--------------------------+
                | Reduce Fractions Menu:   |
                +--------------------------+
                | 1. Reduce Fraction       |
                | 2. Reduce Mixed Fraction |
                +--------------------------+
                """);
        System.out.print("Enter your choice (1 or 2): ");
        return readMenuOption(2);
    }


    /**
     * Reads the types of fractions involved in an operation from the user input.
     * @return The types of fractions (1 to 4).
     * @throws IOException If an I/O error occurs.
     */
    private int readTypeOfFractions() throws IOException {
        displayTypeOfFractions();
        return readMenuOption(4);
    }


    /**
     * Reads the operation to perform from the user input.
     * @return The operation to perform (one to 6).
     * @throws IOException If an I/O error occurs.
     */
    private int readOperation() throws IOException{
        displayOperationMenu();
        return readMenuOption(6);
    }


    /**
     * Displays the menu for selecting the types of fractions.
     */
    private void displayTypeOfFractions() {
        System.out.print("""
                +--------------------------------------+
                |   1. Fraction and Fraction           |
                |   2. Fraction and MixedFraction      |
                |   3. MixedFraction and Fraction      |
                |   4. MixedFraction and MixedFraction |
                +--------------------------------------+
                """);
        System.out.print("Choose the the type of Fractions: ");
    }



    /**
     * Reads a menu option from the user input.
     * @param max The maximum menu option value.
     * @return The selected menu option.
     * @throws IOException If an I/O error occurs.
     */
    private int readMenuOption(int max) throws IOException{
        do{
            int choice;
            try{
                choice = Integer.parseInt(READER.readLine());
            } catch (NumberFormatException e){
                System.out.print(NUMBERFORMATEXCEPTION);
                continue;
            }

            if (choice > max || choice < 1){
                System.out.print(CHOICE_NOT_FOUND);
            } else {
                return choice;
            }
        } while (true);
    }

    /**
     * Reads an integer value from the user input.
     * @param promptMessage The message to display as a prompt.
     * @return The integer value read from the input.
     * @throws IOException If an I/O error occurs.
     */
    private int readInteger(String promptMessage) throws IOException {
        while(true){
            System.out.printf(PURPLE + "%-12s%-2s" + RESET,promptMessage, ": ");
            try{
                return Integer.parseInt(READER.readLine());
            } catch (NumberFormatException e){
                System.out.print(NUMBERFORMATEXCEPTION);
            }
        }
    }

    /**
     * Displays the operation menu.
     */
    private void displayOperationMenu() {
        System.out.print("""
                +-----------------------------------+
                | Fraction Arithmetic Menu          |
                |                                   |
                |   1. Add                          |
                |   2. Subtract                     |
                |   3. Multiply                     |
                |   4. Divide                       |
                |   5. Reduce                       |
                |   6. Quit                         |
                +-----------------------------------+
                """);
        System.out.print("Choose the operation: ");
    }


    /**
     * Displays the result of a fraction arithmetic operation.
     * @param firstFraction The first fraction involved in the operation.
     * @param secondFraction The second fraction involved in the operation.
     * @param resultingFraction The resulting fraction.
     * @param operation The operation symbol.
     */
    private void displayResult(Fraction firstFraction, Fraction secondFraction, Fraction resultingFraction, String operation) {
        System.out.println(BOLD + "Result: ");
        System.out.println("╔═════════════════════════╗");
        System.out.printf("%-6s%9s%12s%n", "║", firstFraction, "║"); // first fraction
        System.out.println("║          " + operation + "              ║"); // operation
        System.out.printf("%-6s%9s%12s%n", "║", secondFraction, "║");
        System.out.println("║═════════════════════════║");

        // result
        System.out.printf(GREEN + "%2s%9s%4s%.4f%5s%n", "║ =", resultingFraction.toString(), " or ", resultingFraction.toDouble(), "║");
        System.out.println(RESET + BOLD + "╚═════════════════════════╝\n" + RESET);
    } // end of method -> displayResult


    /**
     * Displays the result of reducing a fraction.
     * @param fraction The original fraction.
     * @param result The reduced fraction.
     */
    private void displayResult(MixedFraction fraction, MixedFraction result) {
        System.out.println("The simplest form of " + fraction + " is " + result.toString());
    } // end of method -> displayResult

} // end of the class
