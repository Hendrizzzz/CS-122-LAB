/**
 Name of programmer: Bag-eo, Jim Hendrix T.
 Class Code and Schedule: 9322A/B CS 122/122L MTH/TF
 Date: March 16, 2024
 */




//5. Include the NumberConverterBoard class in the package called prog2.samcis
package prog2.samcis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class NumberConverterBoard extends JFrame{
    private JPanel numberPanel;
    private JPanel buttonPanel;
    private JButton convertButton, clearButton, exitButton;
    private JTextField binaryTF, decimalTF, octalTF, hexadecimalTF;
    private EquivalentNumbers number;
    private JLabel problemDisplayer;
    private JPanel reminderPanel;
    private ButtonsHandler buttonsHandler;
    private JPanel headPanel;
    public static void main(String[] args){
        new NumberConverterBoard();
    }

    public NumberConverterBoard(){
        setTitle("Number Converter");
        setSize(1000,600);
        headPanel = new JPanel();
        JLabel textArea = new JLabel();
        textArea.setText("This application helps you convert a decimal number to binary, octal and hexadecimal. Please enter the decimal number in the field provided. Then, press the convert key.");
        headPanel.add(textArea);
        numberPanel = new JPanel(new GridLayout(0,1));
        setNumberPanel(numberPanel);
        buttonPanel = new JPanel();
        setButtonPanel(buttonPanel);
        reminderPanel = new JPanel();
        problemDisplayer = new JLabel("");
        problemDisplayer.setFont(new Font("Arial", Font.ITALIC, 20));
        reminderPanel.add(problemDisplayer);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(headPanel);
        getContentPane().add(numberPanel);
        getContentPane().add(reminderPanel);
        getContentPane().add(buttonPanel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void setButtonPanel(JPanel panel){
        convertButton = new JButton("convert");
        clearButton = new JButton("clear");
        exitButton = new JButton("exit");
        buttonsHandler = new ButtonsHandler();
        convertButton.addActionListener(buttonsHandler);
        clearButton.addActionListener(buttonsHandler);
        exitButton.addActionListener(buttonsHandler);
        panel.add(convertButton);
        panel.add(clearButton);
        panel.add(exitButton);
    }

    public void setNumberPanel(JPanel panel){
        JLabel decimalLabel = new JLabel("<html><div style='text-align: left; width: 200px;'>Decimal Number:</div></html>", SwingConstants.CENTER); // make it to the right
        decimalLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel binaryLabel = new JLabel("<html><div style='text-align: left; width: 200px;'>Binary Number:</div></html>", SwingConstants.CENTER);
        binaryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel octalLabel = new JLabel("<html><div style='text-align: left; width: 200px;'>Octal Number:</div></html>", SwingConstants.CENTER);
        octalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel hexadecimalLabel = new JLabel("<html><div style='text-align: left; width: 200px;'>Hexadecimal Number:</div></html>", SwingConstants.CENTER);
        hexadecimalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        decimalTF = new JTextField();
        decimalTF.setPreferredSize(new Dimension(10, 40));
        decimalTF.setFont(new Font("Arial",Font.BOLD,20));
        decimalTF.setForeground(Color.BLACK);

        binaryTF = new JTextField();
        binaryTF.setPreferredSize(decimalTF.getPreferredSize());
        binaryTF.setFont(new Font("Arial",Font.BOLD,20));
        binaryTF.setForeground(Color.BLACK);

        octalTF = new JTextField();
        octalTF.setPreferredSize(decimalTF.getPreferredSize());
        octalTF.setFont(new Font("Arial",Font.BOLD,20));
        octalTF.setForeground(Color.black);

        hexadecimalTF = new JTextField();
        hexadecimalTF.setPreferredSize(decimalTF.getPreferredSize());
        hexadecimalTF.setFont(new Font("Arial",Font.BOLD,20));
        hexadecimalTF.setForeground(Color.BLACK);

        panel.setLayout(new GridLayout(0,2));
        panel.add(decimalLabel);
        panel.add(decimalTF);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(binaryLabel);
        panel.add(binaryTF);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(octalLabel);
        panel.add(octalTF);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(hexadecimalLabel);
        panel.add(hexadecimalTF);
    }

    private class ButtonsHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == exitButton)
                System.exit(0);
            if (e.getSource()== clearButton){
                decimalTF.setText("");
                binaryTF.setText("");
                octalTF.setText("");
                hexadecimalTF.setText("");
                problemDisplayer.setText("");
            }
            if (e.getSource()== convertButton)
                try{
                    if (!decimalTF.getText().isEmpty() && binaryTF.getText().isEmpty() && octalTF.getText().isEmpty() && hexadecimalTF.getText().isEmpty()){
                        convertDecimal();
                    } else if (decimalTF.getText().isEmpty() && !binaryTF.getText().isEmpty() && octalTF.getText().isEmpty() && hexadecimalTF.getText().isEmpty()){
                        convertBinary();
                    } else if (decimalTF.getText().isEmpty() && binaryTF.getText().isEmpty() && !octalTF.getText().isEmpty() && hexadecimalTF.getText().isEmpty()){
                        convertOctal();
                    } else if (decimalTF.getText().isEmpty() && binaryTF.getText().isEmpty() && octalTF.getText().isEmpty() && !hexadecimalTF.getText().isEmpty()){
                        convertHexadecimal();
                    }else {
                        problemDisplayer.setText("Please enter a value on exactly one number system to convert.");
                        return;
                    }

                    problemDisplayer.setText("");

                } catch (NumberFormatException x ){
                    problemDisplayer.setText("One of the numbers does not follow the format of a number.");
                } catch (Exception e2){
                    problemDisplayer.setText("The input does not follow the format of the Number System");
                }
        }

        private void convertDecimal() {
            number = new EquivalentNumbers();
            number.setDecimalNumber(Float.parseFloat(decimalTF.getText()));
            binaryTF.setText(number.getBinaryString());
            octalTF.setText(number.getOctalString());
            hexadecimalTF.setText(number.getHexadecimalString());
        }

        private void convertBinary() throws Exception {
            number = new EquivalentNumbers();
            number.setBinaryNumber(binaryTF.getText());
            decimalTF.setText(String.valueOf(number.getDecimalNumber()));
            octalTF.setText(number.getOctalString());
            hexadecimalTF.setText(number.getHexadecimalString());
        }

        private void convertOctal() throws Exception {
            number = new EquivalentNumbers();
            number.setOctalNumber(octalTF.getText());
            binaryTF.setText(number.getBinaryString());
            decimalTF.setText(String.valueOf(number.getDecimalNumber()));
            hexadecimalTF.setText(number.getHexadecimalString());
        }

        private void convertHexadecimal() throws Exception {
            number = new EquivalentNumbers();
            number.setHexadecimalString(hexadecimalTF.getText());
            binaryTF.setText(number.getBinaryString());
            octalTF.setText(number.getOctalString());
            decimalTF.setText(String.valueOf(number.getDecimalNumber()));
        }
    }
}
