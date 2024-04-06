package mexer2.prog2.edu.slu;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Tester extends JFrame {
    private JPanel titlePanel;
    private JPanel optionsPanel;
    private JPanel dashboardPanel;
    private JPanel buttonPanel;

    private JComboBox<String> areaOrPerimeter;
    private JComboBox<String> polygonMenu;

    private JLabel formula;
    private JLabel squareAreaOrPerimeterLabel, rectangleAreaOrPerimeterLabel, triangleAreaOrPerimeterLabel, circleAreaOrPerimeterLabel;
    private JLabel squareUnitsLabel, rectangleUnitsLabel, triangleUnitsLabel, circleUnitsLabel;

    private JTextField side, length, width, radius, side2, side3, side4;
    private JTextField squareAnswerJTextfield, rectangleAnswerJTextfield, circleAnswerJTextfield, triangleAnswerJTextfield;

    private JButton calculate;
    private JButton clear;
    private JButton exit;

    private CardLayout figureCardLayout, answerCardLayout;
    private JPanel figureCardPanel, answerCardPanel;


    private static final Font font = new Font("Comic Sans MS", Font.BOLD, 30);
    private static final Font font1 = new Font("Verdana", Font.BOLD, 20);

    public Tester(){
        setTitle("Bag-eoMalasanMartinPajaraSambotYu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setTitlePanel();
        setOptionsPanel();
        setDashboardPanel();
        setButtonPanel();


        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(titlePanel);
        add(optionsPanel);
        add(dashboardPanel);
        add(buttonPanel);

        setSize(new Dimension(800, 600));
        setVisible(true);

        setEnabled(true);
    }

    private void setTitlePanel() {
        JLabel titleLabel = new JLabel("Perimeter and Area Calculator");
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        JLabel descriptionLabel = new JLabel("The app efficiently calculates the perimeter and area " +
                "of squares, triangles, rectangles, and circles.");

        descriptionLabel.setVerticalAlignment(JLabel.TOP);
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 15));

        titlePanel = new JPanel(new GridLayout(2,1));
        titlePanel.add(titleLabel);
        titlePanel.add(descriptionLabel);
    }

    private void setOptionsPanel() {
        String[] options = {"Area", "Perimeter"};
        String[] options2 = {"Square", "Rectangle", "Triangle", "Circle"};
        areaOrPerimeter = new JComboBox<>(options);
        polygonMenu = new JComboBox<>(options2);

        ActionHandler polygonDropHandler = new ActionHandler();

        areaOrPerimeter.addActionListener(polygonDropHandler);
        polygonMenu.addActionListener(polygonDropHandler);


        areaOrPerimeter.setPreferredSize(new Dimension(150, 40));
        areaOrPerimeter.setFont(font1);

        polygonMenu.setPreferredSize(new Dimension(150, 40));
        polygonMenu.setFont(font1);



        optionsPanel = new JPanel();
        optionsPanel.add(areaOrPerimeter);
        optionsPanel.add(polygonMenu);
    }

    private void setDashboardPanel() {
        JPanel panel1 = new JPanel(new BorderLayout());


        figureCardPanel = new JPanel();
        figureCardLayout = new CardLayout();
        figureCardPanel.setLayout(figureCardLayout);

        JPanel squarePanel = new JPanel();
        JPanel rectanglePanel = new JPanel();
        JPanel circlePanel = new JPanel();
        JPanel trianglePanel = new JPanel();

        ImageIcon square = new ImageIcon("pic-square.png");
        JLabel squareLabel = new JLabel(square); // Create a JLabel to hold the ImageIcon
        ImageIcon rectangle = new ImageIcon("pic-rectangle.png");
        JLabel rectangleLabel = new JLabel(rectangle); // Create a JLabel to hold the ImageIcon
        ImageIcon circle = new ImageIcon("pic-circle.png");
        JLabel circleLabel = new JLabel(circle); // Create a JLabel to hold the ImageIcon
        ImageIcon triangle = new ImageIcon("pic-triangle.png");
        JLabel triangleLabel = new JLabel(triangle); // Create a JLabel to hold the ImageIcon

        squarePanel.add(squareLabel);
        rectanglePanel.add(rectangleLabel);
        circlePanel.add(circleLabel);
        trianglePanel.add(triangleLabel);

        figureCardPanel.add(squarePanel, "square");
        figureCardPanel.add(rectanglePanel, "rectangle");
        figureCardPanel.add(circlePanel, "circle");
        figureCardPanel.add(trianglePanel, "triangle");

        formula = new JLabel("Area : a²");
        formula.setHorizontalAlignment(SwingConstants.CENTER);
        formula.setFont(font);

        panel1.add(figureCardPanel, BorderLayout.PAGE_START);

        formula = new JLabel("Area : a²");
        formula.setHorizontalAlignment(SwingConstants.CENTER);
        formula.setFont(font);

// Add formula to panel1 at the bottom
        panel1.add(formula, BorderLayout.PAGE_END);

        JPanel panel2 = new JPanel();

        answerCardPanel = new JPanel();
        answerCardLayout = new CardLayout();
        answerCardPanel.setLayout(answerCardLayout);

        JPanel squareAnswer = new JPanel(new GridLayout(0,1));
        setSquare(squareAnswer);
        JPanel rectangleAnswer = new JPanel(new GridLayout(0,1));
        setRectangle(rectangleAnswer);
        JPanel circleAnswer = new JPanel(new GridLayout(0,1));
        setCircleAnswer(circleAnswer);
        JPanel triangleAnswer = new JPanel(new GridLayout(0,1));
        setTriangleAnswer(triangleAnswer);

        answerCardPanel.add(squareAnswer, "squareAnswer");
        answerCardPanel.add(rectangleAnswer, "rectangleAnswer");
        answerCardPanel.add(circleAnswer, "circleAnswer");
        answerCardPanel.add(triangleAnswer, "triangleAnswer");

        panel2.add(answerCardPanel);

        dashboardPanel = new JPanel(new GridBagLayout());
        dashboardPanel.add(panel1);
        dashboardPanel.add(panel2);
    }

    private void setSquare(JPanel squareAnswer) {
        JPanel panel = new JPanel(new BorderLayout());
        side = new JTextField();
        setDigitsAndDotFilter(side);
        side.setHorizontalAlignment(JTextField.CENTER);
        side.setPreferredSize(new Dimension(200, 50)); // Set the preferred size for the side text field
        JLabel leftLabel = new JLabel("a   ");
        JLabel rightLabel = new JLabel(" units");
        panel.add(leftLabel, BorderLayout.WEST);
        panel.add(side, BorderLayout.CENTER);
        panel.add(rightLabel, BorderLayout.EAST);

        JPanel panel1 = new JPanel(new BorderLayout());
        squareAnswerJTextfield = new JTextField();
        squareAnswerJTextfield.setPreferredSize(side.getPreferredSize()); // Set the preferred size for the squareAnswerJTextfield text field to match the side text field
        squareAnswerJTextfield.setHorizontalAlignment(JTextField.CENTER);
        squareAnswerJTextfield.setEnabled(false);
        squareAnswerJTextfield.setDisabledTextColor(Color.BLACK);
        squareAreaOrPerimeterLabel = new JLabel("Area : ");
        squareUnitsLabel = new JLabel(" units²");
        panel1.add(squareAreaOrPerimeterLabel, BorderLayout.WEST);
        panel1.add(squareAnswerJTextfield, BorderLayout.CENTER);
        panel1.add(squareUnitsLabel, BorderLayout.EAST);

        squareAnswer.add(new JLabel());
        squareAnswer.add(panel);
        squareAnswer.add(new JLabel());
        squareAnswer.add(panel1);
    }


    private void setRectangle(JPanel rectangleAnswer) {
        JPanel panel = new JPanel(new BorderLayout());
        length = new JTextField();
        setDigitsAndDotFilter(length);
        length.setHorizontalAlignment(JTextField.CENTER);
        length.setPreferredSize(new Dimension(200, 50)); // Set the preferred size for the length text field
        JLabel leftLabel = new JLabel("a   ");
        JLabel rightLabel = new JLabel(" units");
        panel.add(leftLabel, BorderLayout.WEST);
        panel.add(length, BorderLayout.CENTER);
        panel.add(rightLabel, BorderLayout.EAST);
        
        JPanel panel1 = new JPanel(new BorderLayout());
        width = new JTextField();
        setDigitsAndDotFilter(width);
        width.setHorizontalAlignment(JTextField.CENTER);
        width.setPreferredSize(length.getPreferredSize()); // Set the preferred size for the length text field
        JLabel leftLabel1 = new JLabel("b   ");
        JLabel rightLabel1 = new JLabel(" units");
        panel1.add(leftLabel1, BorderLayout.WEST);
        panel1.add(width, BorderLayout.CENTER);
        panel1.add(rightLabel1, BorderLayout.EAST);

        JPanel panel2 = new JPanel(new BorderLayout());
        rectangleAnswerJTextfield = new JTextField();
        rectangleAnswerJTextfield.setPreferredSize(length.getPreferredSize()); // Set the preferred size for the rectangleAnswerJTextfield text field to match the length text field
        rectangleAnswerJTextfield.setHorizontalAlignment(JTextField.CENTER);
        rectangleAnswerJTextfield.setEnabled(false);
        rectangleAnswerJTextfield.setDisabledTextColor(Color.BLACK);
        rectangleAreaOrPerimeterLabel = new JLabel("Area : ");
        rectangleUnitsLabel = new JLabel(" units²");
        panel2.add(rectangleAreaOrPerimeterLabel, BorderLayout.WEST);
        panel2.add(rectangleAnswerJTextfield, BorderLayout.CENTER);
        panel2.add(rectangleUnitsLabel, BorderLayout.EAST);

        rectangleAnswer.add(panel);
        rectangleAnswer.add(panel1);
        rectangleAnswer.add(new JLabel());
        rectangleAnswer.add(panel2);
        
    }

    private void setCircleAnswer(JPanel circleAnswer) {
        JPanel panel = new JPanel(new BorderLayout());
        radius = new JTextField();
        setDigitsAndDotFilter(radius);
        radius.setHorizontalAlignment(JTextField.CENTER);
        radius.setPreferredSize(new Dimension(200, 50)); // Set the preferred size for the radius text field
        JLabel leftLabel = new JLabel("r   ");
        JLabel rightLabel = new JLabel(" units");
        panel.add(leftLabel, BorderLayout.WEST);
        panel.add(radius, BorderLayout.CENTER);
        panel.add(rightLabel, BorderLayout.EAST);

        JPanel panel1 = new JPanel(new BorderLayout());
        circleAnswerJTextfield = new JTextField();
        circleAnswerJTextfield.setPreferredSize(radius.getPreferredSize()); // Set the preferred size for the circleAnswerJTextfield text field to match the radius text field
        circleAnswerJTextfield.setHorizontalAlignment(JTextField.CENTER);
        circleAnswerJTextfield.setEnabled(false);
        circleAnswerJTextfield.setDisabledTextColor(Color.black);
        circleAreaOrPerimeterLabel = new JLabel("Area : ");
        circleUnitsLabel = new JLabel(" units²");
        panel1.add(circleAreaOrPerimeterLabel, BorderLayout.WEST);
        panel1.add(circleAnswerJTextfield, BorderLayout.CENTER);
        panel1.add(circleUnitsLabel, BorderLayout.EAST);

        circleAnswer.add(new JLabel());
        circleAnswer.add(panel);
        circleAnswer.add(new JLabel());
        circleAnswer.add(panel1);
    }

    private void setTriangleAnswer(JPanel triangleAnswer) {
        JPanel panel = new JPanel(new BorderLayout());
        side2 = new JTextField();
        setDigitsAndDotFilter(side2);
        side2.setHorizontalAlignment(JTextField.CENTER);
        side2.setPreferredSize(new Dimension(200, 50)); // Set the preferred size for the side2 text field
        JLabel leftLabel = new JLabel("a   ");
        JLabel rightLabel = new JLabel(" units");
        panel.add(leftLabel, BorderLayout.WEST);
        panel.add(side2, BorderLayout.CENTER);
        panel.add(rightLabel, BorderLayout.EAST);

        JPanel panel1 = new JPanel(new BorderLayout());
        side3 = new JTextField();
        setDigitsAndDotFilter(side3);
        side3.setHorizontalAlignment(JTextField.CENTER);
        side3.setPreferredSize(side2.getPreferredSize()); // Set the preferred size for the side2 text field
        JLabel leftLabel1 = new JLabel("b   ");
        JLabel rightLabel1 = new JLabel(" units");
        panel1.add(leftLabel1, BorderLayout.WEST);
        panel1.add(side3, BorderLayout.CENTER);
        panel1.add(rightLabel1, BorderLayout.EAST);

        JPanel panel2 = new JPanel(new BorderLayout());
        side4 = new JTextField();
        setDigitsAndDotFilter(side4);
        side4.setHorizontalAlignment(JTextField.CENTER);
        side4.setPreferredSize(side2.getPreferredSize()); // Set the preferred size for the side2 text field
        JLabel leftLabel2 = new JLabel("c   ");
        JLabel rightLabel2 = new JLabel(" units");
        panel2.add(leftLabel2, BorderLayout.WEST);
        panel2.add(side4, BorderLayout.CENTER);
        panel2.add(rightLabel2, BorderLayout.EAST);

        JPanel panel3 = new JPanel(new BorderLayout());
        triangleAnswerJTextfield = new JTextField();
        triangleAnswerJTextfield.setPreferredSize(side2.getPreferredSize()); // Set the preferred size for the triangleAnswerJTextfield text field to match the side text field
        triangleAnswerJTextfield.setHorizontalAlignment(JTextField.CENTER);
        triangleAnswerJTextfield.setEnabled(false);
        triangleAnswerJTextfield.setDisabledTextColor(Color.black);
        triangleAreaOrPerimeterLabel = new JLabel("Area : ");
        triangleUnitsLabel = new JLabel(" units²");
        panel3.add(triangleAreaOrPerimeterLabel, BorderLayout.WEST);
        panel3.add(triangleAnswerJTextfield, BorderLayout.CENTER);
        panel3.add(triangleUnitsLabel, BorderLayout.EAST);

        triangleAnswer.add(panel);
        triangleAnswer.add(panel1);
        triangleAnswer.add(panel2);
        triangleAnswer.add(new JLabel());
        triangleAnswer.add(panel3);
    }

    private void setButtonPanel() {
        calculate = new JButton("Calculate");
        clear = new JButton("Clear");
        exit = new JButton("Exit");
        
        ActionHandler actionHandler = new ActionHandler();
        calculate.addActionListener(actionHandler);
        clear.addActionListener(actionHandler);
        exit.addActionListener(actionHandler);

        buttonPanel = new JPanel();
        buttonPanel.add(calculate);
        buttonPanel.add(clear);
        buttonPanel.add(exit);
    }


    // Method to attach document filter to accept only digits and a dot
    private void setDigitsAndDotFilter(JTextField textField) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("^-?\\d*\\.?\\d*$")) { // Only allow digits and a dot
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }


    public static void main(String[] args) {
        new Tester();
    }


    class ActionHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object buttonChoice = e.getSource();
            String selectedPolygon = (String) polygonMenu.getSelectedItem();
            boolean isArea = (areaOrPerimeter.getSelectedItem()).equals("Area");


            switch (Objects.requireNonNull(selectedPolygon)){
                case "Triangle" -> {
                    figureCardLayout.show(figureCardPanel, "triangle");
                    answerCardLayout.show(answerCardPanel, "triangleAnswer");
                    if (isArea){
                        formula.setText("");
                        triangleAnswerJTextfield.setText("");
                    } else {
                        formula.setText("Perimeter : a + b + c");
                        triangleAreaOrPerimeterLabel.setText("Perimeter : ");
                        triangleUnitsLabel.setText("units");
                        triangleAnswerJTextfield.setText("");
                    }
                }
                case "Circle" -> {
                    figureCardLayout.show(figureCardPanel, "circle");
                    answerCardLayout.show(answerCardPanel, "circleAnswer");
                    if (isArea){
                        formula.setText("Area : πr²");
                        circleAreaOrPerimeterLabel.setText("Area : ");
                        circleUnitsLabel.setText("units²");
                        circleAnswerJTextfield.setText("");
                    } else {
                        formula.setText("Perimeter : 2πr");
                        circleAreaOrPerimeterLabel.setText("Perimeter : ");
                        circleUnitsLabel.setText("units");
                        circleAnswerJTextfield.setText("");
                    }
                }
                case "Square" -> {
                    figureCardLayout.show(figureCardPanel, "square");
                    answerCardLayout.show(answerCardPanel, "squareAnswer");
                    if (isArea){
                        formula.setText("Area : a²");
                        circleAreaOrPerimeterLabel.setText("Area : ");
                        circleUnitsLabel.setText("units²");
                        squareAnswerJTextfield.setText("");
                    } else {
                        formula.setText("Perimeter : 4a");
                        circleAreaOrPerimeterLabel.setText("Perimeter : ");
                        circleUnitsLabel.setText("units");
                        squareAnswerJTextfield.setText("");
                    }
                }
                case "Rectangle" -> {
                    figureCardLayout.show(figureCardPanel, "rectangle");
                    answerCardLayout.show(answerCardPanel, "rectangleAnswer");
                    if (isArea) {
                        formula.setText("Area : a * b");
                        rectangleAreaOrPerimeterLabel.setText("Area : ");
                        rectangleUnitsLabel.setText("units²");
                        rectangleAnswerJTextfield.setText("");
                    } else {
                        formula.setText("Perimeter : 2a + 2b");
                        rectangleAreaOrPerimeterLabel.setText("Perimeter : ");
                        rectangleUnitsLabel.setText("units");
                        rectangleAnswerJTextfield.setText("");
                    }
                }
            }


            if (buttonChoice != clear || buttonChoice != exit || buttonChoice != calculate){
                if (buttonChoice == clear){
                    side.setText("");
                    length.setText("");
                    width.setText("");
                    radius.setText("");
                    side2.setText("");
                    side3.setText("");
                    side4.setText("");
                    squareAnswerJTextfield.setText("");
                    triangleAnswerJTextfield.setText("");
                    rectangleAnswerJTextfield.setText("");
                    circleAnswerJTextfield.setText("");
                }
                else if (buttonChoice == exit){
                    System.exit(0);
                }
                else if (buttonChoice == calculate){
                    Shape shape;
                    switch (selectedPolygon) {
                        case "Triangle" -> {
                            double a = Double.parseDouble(side2.getText());
                            double b = Double.parseDouble(side3.getText());
                            double c = Double.parseDouble(side4.getText());
                            shape = new Triangle("triangle", a, b, c);

                            if (isArea) {
                                triangleAnswerJTextfield.setText(String.valueOf(shape.area()));
                            } else {
                                triangleAnswerJTextfield.setText(String.valueOf(shape.perimeter()));
                            }
                        }
                        case "Square" -> {
                            double a = Double.parseDouble(side.getText());
                            shape = new Square("square", a);
                            if (isArea) {
                                squareAnswerJTextfield.setText(String.valueOf(shape.area()));
                            } else {
                                squareAnswerJTextfield.setText(String.valueOf(shape.perimeter()));
                            }
                        }
                        case "Circle" -> {
                            double r = Double.parseDouble(radius.getText());
                            shape = new Circle("circle", r);
                            if (isArea) {
                                circleAnswerJTextfield.setText(String.valueOf(shape.area()));
                            } else {
                                circleAnswerJTextfield.setText(String.valueOf(shape.perimeter()));
                            }
                        }
                        default -> {
                            double a = Double.parseDouble(length.getText());
                            double b = Double.parseDouble(width.getText());
                            shape = new Rectangle("rectangle", a, b);
                            if (isArea) {
                                rectangleAnswerJTextfield.setText(String.valueOf(shape.area()));
                            } else {
                                rectangleAnswerJTextfield.setText(String.valueOf(shape.perimeter()));
                            }
                        }
                    }


                }
            }

        }
    }

    
    

}


