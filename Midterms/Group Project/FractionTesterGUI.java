package edu.slu.prog2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * A graphical user interface for a Mixed Fraction Calculator.
 * This class provides a GUI with functionalities to reduce fractions and perform arithmetic operations on fractions.
 */
public class FractionTesterGUI extends JFrame {
    /*
     The structure the JFrame consists of 5 JPanels (horizontally down).

     1st Panel: titlePanel
     The title with the program description

     2nd Panel: functionButtonPanel
     The 2 buttons below the program description, reduce and calculator

     3rd Panel: calculatorPanel
     This consists of 2 panels, the panel for reduce and the panel for arithmetic operation.
     If the reduce button is clicked, then the reduce panel will show. If the calculator button is clicked, then
     the calculator panel will show. This is done with the use of CardLayout.

     4th Panel: resultPanel
     This consists of two panels, the panel where the result will show and the error message panel

     5th Panel: buttonsPanel
     This is where the 3 buttons are placed, clear, calculate, and exit.
     */

    // Panels for different sections of the GUI in order (top to bottom)
    private JPanel titlePanel; // contains the title with short description
    private JPanel functionButtonPanel; // the buttons reduce and calculator
    private JPanel calculatorPanel; // the panel that contains the textfields
    private JPanel resultPanel; // contains the textfield where the result will be shown and a JLabel below it to show error message if there is any
    private JPanel buttonsPanel; // the three buttons below. calculate, clear, exit.

    // Text fields for input and display
    private JTextField whole, numerator, denominator; //TextFields for the Reducing Fraction Part
    private JTextField whole1, numerator1, denominator1; // Textfields for the first Fraction in performing arithmetic operations
    private JTextField whole2, numerator2, denominator2; // Textfields for the second Fraction in performing arithmetic operations
    private JTextField resultTF; // Textfield for the result

    // Labels for error display
    private JLabel errorMessage;

    // Buttons for the 2nd panel
    private JButton reduceButton;
    private JButton calculatorButton;
    // Buttons for the 5th Panel
    private JButton clearButton;
    private JButton calculateButton;
    private JButton exitButton;

    // Dropdown for selecting arithmetic operations
    JComboBox<String> operation;

    // to determine which panel is showing in the CardLayout, is it the reducePanel or calculatorPanel
    static boolean isReducePanelShowing = false;

    // Panels for managing card layout
    JPanel cardPanel;
    CardLayout cardLayout;

    // Font used for text fields and labels
    private static final Font font = new Font("Arial", Font.PLAIN, 20);


    /**
     * Constructs a FractionCalculatorGUI object.
     * Initializes and sets up the GUI components.
     */
    public FractionTesterGUI() {
        setTitle("Bag-eoMalasanMartinPajaraSambotYu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Construct the 5 panels
        setTitlePanel();
        setFunctionPanel();
        setCalculatorPanel();
        setResultPanel();
        setButtonsPanel();

        // Set what layout will the contentPane will use
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add the main 5 panels
        getContentPane().add(titlePanel);
        getContentPane().add(functionButtonPanel);
        getContentPane().add(calculatorPanel);
        getContentPane().add(resultPanel);
        getContentPane().add(buttonsPanel);

        setVisible(true);
    }



    /**
     * Sets up the title panel with the program description.
     */
    private void setTitlePanel() {
        titlePanel = new JPanel();

        // The title label and a little bit of modification
        JLabel titleName = new JLabel("Mixed Fraction Calculator");
        titleName.setVerticalAlignment(JLabel.TOP);
        titleName.setHorizontalAlignment(JLabel.CENTER);
        titleName.setFont(new Font("Arial", Font.BOLD, 30));

        // Description Label and a little bit of modification
        JLabel description = new JLabel("With this calculator, you can reduce and perform arithmetic operations on fractions, mixed fractions or both.");
        description.setVerticalAlignment(JLabel.TOP);
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setFont(new Font("Arial", Font.BOLD, 15));

        // Make the first panel in a grid layout (vertical), then add the two Labels
        titlePanel.setLayout(new GridLayout(2,1));
        titlePanel.add(titleName);
        titlePanel.add(description);
    }




    /**
     * Sets up the panel for function buttons (reduce and calculator).
     */
    private void setFunctionPanel() {
        functionButtonPanel = new JPanel();
        ButtonHandler buttonHandler = new ButtonHandler();

        // Create two buttons
        reduceButton = new JButton("Reduce");
        calculatorButton = new JButton("Calculator");

        // adds an actionListener to the two buttons
        calculatorButton.addActionListener(buttonHandler);
        reduceButton.addActionListener(buttonHandler);

        // Adds both buttons to the second panel
        functionButtonPanel.add(reduceButton);
        functionButtonPanel.add(calculatorButton);
    }




    /**
     * Sets up the calculator panel with input fields for fractions and arithmetic operations.
     * This panel contains a CardLayout that has two panels, 'reducePanel' and 'calculatorP'.
     */
    private void setCalculatorPanel() {
        // Create card panel and set CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // the first panel of the CardLayout
        JPanel reducePanel = new JPanel();
        setReduceContent(reducePanel);

        // the second panel of the CardLayout
        JPanel calculatorP = new JPanel();
        setCalculateContent(calculatorP);

        // Add panels to the CardLayout with unique names
        cardPanel.add(calculatorP, "panel2");
        cardPanel.add(reducePanel, "panel1");

        // instantiates the calculatorPanel
        calculatorPanel = new JPanel();

        // add it to one of the main panels -> third panel
        calculatorPanel.add(cardPanel);
    }


    /**
     * Sets up the panel for displaying arithmetic operations and input fields for fractions.
     * @param calculatorP The panel to set up for displaying arithmetic operations and input fields.
     */
    private void setCalculateContent(JPanel calculatorP) {
        // First Fraction
        JPanel fraction = new JPanel(new GridLayout(3,1));
        setUpFirstFraction(fraction);

        // the operations to be used + - x ÷
        JComboBox<String> operation = getStringJComboBox();

        // Second Fraction
        JPanel fraction1 = new JPanel(new GridLayout(3,1));
        setUpSecondFraction(fraction1);

        calculatorP.setLayout(new FlowLayout());

        // Add it to the one calculateP, one of the two panels in the CardLayout
        calculatorP.add(whole1); // first fraction
        calculatorP.add(fraction);
        calculatorP.add(new JLabel("   ")); // for spacing
        calculatorP.add(operation); // the vinculum
        calculatorP.add(new JLabel("   ")); // for spacing
        calculatorP.add(whole2); // second fraction
        calculatorP.add(fraction1);
    }


    /**
     * Sets up the input fields for the first fraction.
     * @param fraction The panel to set up for the first fraction input fields.
     */
    private void setUpFirstFraction(JPanel fraction) {
        whole1 = new JTextField();
        whole1.setHorizontalAlignment(0);
        whole1.setPreferredSize(new Dimension(70, 40));
        whole1.setFont(font);

        numerator1 = new JTextField();
        numerator1.setHorizontalAlignment(0);
        numerator1.setPreferredSize(new Dimension(70, 40));
        numerator1.setFont(font);

        JLabel lineLabel = new JLabel("━━━━━━━━━━━━");

        denominator1 = new JTextField();
        denominator1.setPreferredSize(new Dimension(70, 40));
        denominator1.setHorizontalAlignment(0);
        denominator1.setFont(font);

        fraction.add(numerator1);
        fraction.add(lineLabel);
        fraction.add(denominator1);
    }


    /**
     * Sets up the input fields for the second fraction.
     * @param fraction1 The panel to set up for the second fraction input fields.
     */
    private void setUpSecondFraction(JPanel fraction1) {
        whole2 = new JTextField();
        whole2.setHorizontalAlignment(0);
        whole2.setPreferredSize(new Dimension(70, 40));
        whole2.setFont(font);

        numerator2 = new JTextField();
        numerator2.setHorizontalAlignment(0);
        numerator2.setPreferredSize(new Dimension(70, 40));
        numerator2.setFont(font);

        JLabel lineLabel1 = new JLabel("━━━━━━━━━━━━");

        denominator2 = new JTextField();
        denominator2.setPreferredSize(new Dimension(70, 40));
        denominator2.setHorizontalAlignment(0);
        denominator2.setFont(font);

        fraction1.add(numerator2);
        fraction1.add(lineLabel1);
        fraction1.add(denominator2);
    }



    /**
     * Creates and configures a JComboBox for selecting arithmetic operations.
     * @return JComboBox<String> The configured JComboBox for selecting arithmetic operations.
     */
    private JComboBox<String> getStringJComboBox() {
        String[] items = {"+", "-", "x", "÷"};
        operation = new JComboBox<>(items);

        // Set custom renderer to center the text
        operation.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return this;
            }
        });
        operation.setPreferredSize(new Dimension(70, 40));
        operation.setFont(font);
        return operation;
    }



    /**
     * Sets up the panel for reducing fractions.
     * @param reducePanel The panel to set up for reducing fractions.
     */
    private void setReduceContent(JPanel reducePanel) {
        whole = new JTextField();
        whole.setHorizontalAlignment(0);
        whole.setPreferredSize(new Dimension(70, 40));
        whole.setFont(font);
        numerator = new JTextField();
        numerator.setHorizontalAlignment(0);
        numerator.setPreferredSize(new Dimension(70, 40));
        numerator.setFont(font);
        JLabel lineLabel = new JLabel("━━━━━━━━━━━━");
        denominator = new JTextField();
        denominator.setPreferredSize(new Dimension(70, 40));
        denominator.setHorizontalAlignment(0);
        denominator.setFont(font);
        JPanel fraction = new JPanel(new GridLayout(3,1));
        fraction.add(numerator);
        fraction.add(lineLabel);
        fraction.add(denominator);

        // Add it to the reducePanel, one the two panels in the CardLayout
        reducePanel.add(whole);
        reducePanel.add(fraction);
    }



    /**
     * Sets up the panel for displaying calculation results and error messages.
     */
    private void setResultPanel() {
        resultPanel = new JPanel(new GridLayout(2,1));
        JPanel resultContainer = new JPanel();
        errorMessage = new JLabel("", SwingConstants.CENTER);
        errorMessage.setForeground(Color.RED);
        resultTF = new JTextField("");
        resultTF.setHorizontalAlignment(JTextField.CENTER);
        resultTF.setPreferredSize(new Dimension(250, 40));
        resultTF.setFont(font);
        resultTF.setEnabled(false);
        resultTF.setDisabledTextColor(Color.BLACK);
        JLabel resultText = new JLabel("Result: ");
        resultContainer.add(resultText);
        resultContainer.add(resultTF);

        // Add it to one of the main 5 panels -> fourth panel
        resultPanel.add(resultContainer);
        resultPanel.add(errorMessage);
    }



    /**
     * Sets up the panel for buttons such as clear, calculate, and exit.
     */
    private void setButtonsPanel() {
        buttonsPanel = new JPanel();
        ButtonHandler buttonHandler = new ButtonHandler();

        clearButton = new JButton("clear");
        calculateButton = new JButton("calculate");
        exitButton = new JButton("exit");

        clearButton.addActionListener(buttonHandler);
        calculateButton.addActionListener(buttonHandler);
        exitButton.addActionListener(buttonHandler);

        // Add to one of the main 5 panels -> fifth panel
        buttonsPanel.add(clearButton);
        buttonsPanel.add(calculateButton);
        buttonsPanel.add(exitButton);
    }





    /**
     * The main method to launch the Fraction Calculator GUI.
     * @param args The command-line arguments (unused).
     */
    public static void main(String[] args) {
        new FractionTesterGUI();
    }






    /**
     * ActionListener implementation for handling button clicks in the GUI.
     */
    private class ButtonHandler implements ActionListener{


        /**
         * Handles button click events.
         * @param e The ActionEvent object representing the button click event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object choice = e.getSource();

            // If the exit button is clicked, exit the application
            if (choice == exitButton){
                System.exit(0);
            }

            // If the clear button is clicked, clear the input fields
            else if (choice == clearButton){
                clearTextFields();
            }

            // If the calculate button is clicked, perform either reduction or arithmetic operation
            else if (choice == calculateButton){
                errorMessage.setText("");
                if (isReducePanelShowing){
                    reduce();
                } else {
                    performArithmetic();
                }
            } else { // if the choice is either reduceButton or calculatorButton
                resultTF.setText("");
                errorMessage.setText("");
                // Show the appropriate panel based on the button clicked
                if (choice == reduceButton) {
                    cardLayout.show(cardPanel, "panel1");
                    isReducePanelShowing = true;
                } else if (choice == calculatorButton) {
                    cardLayout.show(cardPanel, "panel2");
                    isReducePanelShowing = false;
                }
            }
        }


        /**
         * Clears the input fields based on the currently displayed panel.
         */
        private void clearTextFields() {
            if (isReducePanelShowing){
                // Clear the fields for reducing fractions
                whole.setText("");
                numerator.setText("");
                denominator.setText("");
            } else {
                // Clear the fields for arithmetic operations
                whole1.setText("");
                numerator1.setText("");
                denominator1.setText("");
                whole2.setText("");
                numerator2.setText("");
                denominator2.setText("");
            }
            resultTF.setText("");
            errorMessage.setText("");
        }


        /**
         * Reduces the fraction entered by the user and displays the result.
         */
        private void reduce() {
            // Check if numerator or denominator input fields are empty
            if (numerator.getText().isEmpty() || denominator.getText().isEmpty())
            {
                errorMessage.setText("Some values are still missing");
                return;
            }

            // Parse the input values
            int wholeR = parseInteger(whole.getText());
            int numeratorR = parseInteger(numerator.getText());
            int denominatorR = parseInteger(denominator.getText());

            if (wholeR == Integer.MAX_VALUE || numeratorR == Integer.MAX_VALUE || denominatorR == Integer.MAX_VALUE){
                return;
            }

            // Create a MixedFraction object with the provided values
            MixedFraction fraction = createMixedFraction(wholeR, numeratorR, denominatorR);
            if (fraction == null){
                return; // Don't simplify if the denominator is 0
            }

            // Simplify the fraction and display the result
            fraction.simplify();
            resultTF.setText(fraction.toString());
        }



        /**
         * Parses a string input into an integer.
         *
         * @param input The string input to be parsed.
         * @return The parsed integer value, or 0 if the input is empty or not a valid integer.
         */
        private int parseInteger(String input) {
            if (input.isEmpty()) {
                return 0;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                errorMessage.setText("Some values are not valid. ");
                return Integer.MAX_VALUE;
            }
        }


        /**
         * Creates a MixedFraction object with the provided values.
         *
         * @param wholeR       The whole number part of the fraction.
         * @param numeratorR   The numerator part of the fraction.
         * @param denominatorR The denominator part of the fraction.
         * @return The created MixedFraction object, or null if the denominator is 0.
         */
        private MixedFraction createMixedFraction(int wholeR, int numeratorR, int denominatorR) {
            try {
                return new MixedFraction(wholeR, numeratorR, denominatorR);
            } catch (ArithmeticException e){
                // Handle denominator being 0
                errorMessage.setText("Denominator cannot be 0");
                resultTF.setText("");
                return null;
            }
        }




        /**
         * Performs arithmetic operations on fractions and displays the result.
         * Checks if all required input values for arithmetic operations are provided,
         * then performs the selected operation and shows the result. Handles cases
         * where the provided values are invalid or result in division by zero.
         */
        private void performArithmetic() {
            String operationChoice = (String) operation.getSelectedItem();

            // Check if any input fields are empty
            if (!inputFieldsIsValid()){
                return;
            }

            MixedFraction first;
            MixedFraction second;

            try {
                // Parse input values for the first fraction
                first = parseFraction(whole1, numerator1, denominator1);
                // Parse input values for the second fraction
                second = parseFraction(whole2, numerator2, denominator2);
            } catch (NumberFormatException e){
                errorMessage.setText("Some values are not valid."); // if there are non-integers
                return;
            }

            // Perform the arithmetic operation
            MixedFraction result = calculateResult(operationChoice, first, second);

            // Display the result
            if (!result.toString().contains("/")) {
                resultTF.setText(result.toString());
            } else {
                resultTF.setText(result + "  or  " + result.toDouble());
            }
        } // end of method -> performArithmetic




        /**
         * Parses the input values for a fraction and creates a MixedFraction object.
         * @param wholeField The JTextField for the whole part of the fraction.
         * @param numeratorField The JTextField for the numerator of the fraction.
         * @param denominatorField The JTextField for the denominator of the fraction.
         * @return The MixedFraction object representing the parsed fraction.
         * @throws NumberFormatException if any of the input values is not a valid integer.
         * @throws ArithmeticException if the denominator is 0.
         */
        private MixedFraction parseFraction(JTextField wholeField, JTextField numeratorField, JTextField denominatorField) {
            int whole = wholeField.getText().isEmpty() ? 0 : Integer.parseInt(wholeField.getText());
            int numerator = Integer.parseInt(numeratorField.getText());
            int denominator = Integer.parseInt(denominatorField.getText());
            return new MixedFraction(whole, numerator, denominator);
        }

        /**
         * Calculates the result of the arithmetic operation.
         * @param operationChoice The selected arithmetic operation.
         * @param first The first fraction.
         * @param second The second fraction.
         * @return The result of the arithmetic operation.
         * @throws ArithmeticException if the operation results in division by zero.
         */
        private MixedFraction calculateResult(String operationChoice, MixedFraction first, MixedFraction second) {
            return switch (operationChoice) {
                case "+" -> first.add(second);
                case "-" -> first.subtract(second);
                case "x" -> first.multiplyBy(second);
                case "÷" -> first.divideBy(second);
                default -> new MixedFraction(); // Default to returning zero, but this won't likely happen
            };
        }


        /**
         * Checks if all required input fields for performing arithmetic operations are filled.
         * If any of the input fields is empty, sets an error message and returns false.
         * Otherwise, returns true indicating that all input fields are filled.
         *
         * @return true if all required input fields are filled, false otherwise.
         */
        private boolean inputFieldsIsValid() {
            if (numerator1.getText().isEmpty() || denominator1.getText().isEmpty() ||
                numerator2.getText().isEmpty() || denominator2.getText().isEmpty())
            {
                errorMessage.setText("Some values are still missing");
                return false;
            }
            return true;
        }


    } // end of the inner class -> ButtonHandler

} // end of the outer class -> FractionCalculatorGUI
