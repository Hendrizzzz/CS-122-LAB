package mexer2.prog2.edu.slu;

public class Rectangle extends Square{
    private final double side2;
    public Rectangle(String name, double side1, double side2) {
        super(name, side1);
        this.side2 = side2;
    }

    @Override
    public double area() {
        double area = side2 * side;
        String areaString = String.format("%.5f", area);
        return Double.parseDouble(areaString);
    }

    @Override
    public double perimeter() {
        double perimeter = 2 * side2 + 2 * side;
        String perimeterString = String.format("%.5f", perimeter);
        return Double.parseDouble(perimeterString);
    }
}
