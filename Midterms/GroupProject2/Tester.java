package mexer2.prog2.edu.slu;

import javax.swing.*;
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

    private JLabel errorDisplayMessage;
    private JLabel formula;
    private JLabel squareAreaOrPerimeterLabel, unitsLabel;

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

        PolygonMenuDropDownHandler polygonDropHandler = new PolygonMenuDropDownHandler();

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

        JPanel squareAnswer = new JPanel(new GridLayout(3,1));
        setSquare(squareAnswer);
        JPanel rectangleAnswer = new JPanel(new GridLayout(5,1));
        setRectangle(rectangleAnswer);
        JPanel circleAnswer = new JPanel(new GridLayout());
        setCircleAnswer(circleAnswer);
        JPanel triangleAnswer = new JPanel(new GridLayout(4,1));
        setTiangleAnswer(triangleAnswer);

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
        JTextField side = new JTextField();
        side.setHorizontalAlignment(JTextField.CENTER);
        side.setPreferredSize(new Dimension(200, 50)); // Set the preferred size for the side text field
        JLabel leftLabel = new JLabel("a   ");
        JLabel rightLabel = new JLabel(" units");
        panel.add(leftLabel, BorderLayout.WEST);
        panel.add(side, BorderLayout.CENTER);
        panel.add(rightLabel, BorderLayout.EAST);

        JPanel panel1 = new JPanel(new BorderLayout());
        JTextField answer = new JTextField();
        answer.setPreferredSize(side.getPreferredSize()); // Set the preferred size for the answer text field to match the side text field
        answer.setHorizontalAlignment(JTextField.CENTER);
        answer.setEnabled(false);
        squareAreaOrPerimeterLabel = new JLabel("Area : ");
        unitsLabel = new JLabel(" units²");
        panel1.add(squareAreaOrPerimeterLabel, BorderLayout.WEST);
        panel1.add(answer, BorderLayout.CENTER);
        panel1.add(unitsLabel, BorderLayout.EAST);

        squareAnswer.add(panel);
        squareAnswer.add(new JLabel());
        squareAnswer.add(panel1);
    }


    private void setRectangle(JPanel rectangleAnswer) {

    }

    private void setCircleAnswer(JPanel circleAnswer) {
    }

    private void setTiangleAnswer(JPanel triangleAnswer) {
    }

    private void setButtonPanel() {
        calculate = new JButton("Calculate");
        clear = new JButton("Clear");
        exit = new JButton("Exit");

        buttonPanel = new JPanel();
        buttonPanel.add(calculate);
        buttonPanel.add(clear);
        buttonPanel.add(exit);
    }


    public static void main(String[] args) {
        new Tester();
    }


    class PolygonMenuDropDownHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedPolygon = (String) polygonMenu.getSelectedItem();
            boolean isArea = (areaOrPerimeter.getSelectedItem()).equals("Area");

            switch (Objects.requireNonNull(selectedPolygon)){
                case "Triangle" -> {
                    figureCardLayout.show(figureCardPanel, "triangle");
                    if (isArea){
                        formula.setText("");
                    } else {
                        formula.setText("Perimeter : a + b + c");
                    }
                }
                case "Circle" -> {
                    figureCardLayout.show(figureCardPanel, "circle");
                    if (isArea){
                        formula.setText("Area : πr²");
                    } else {
                        formula.setText("Perimeter : 2πr");
                    }
                }
                case "Square" -> {
                    figureCardLayout.show(figureCardPanel, "square");
                    if (isArea){
                        formula.setText("Area : a²");
                        squareAreaOrPerimeterLabel.setText("Area : ");
                        unitsLabel.setText("units²");
                    } else {
                        formula.setText("Perimeter : 4a");
                        squareAreaOrPerimeterLabel.setText("Perimeter : ");
                        unitsLabel.setText("units");
                    }
                }
                case "Rectangle" -> {
                    figureCardLayout.show(figureCardPanel, "rectangle");
                    if (isArea) {
                        formula.setText("Area : a * b");
                    } else {
                        formula.setText("Perimeter : 2a + 2b");
                    }
                }
            }

        }
    }

    
    

}


