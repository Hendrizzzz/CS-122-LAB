package mexer2.prog2.edu.slu;

public class Triangle extends Shape{
    private final double side1;
    private final double side2;
    private final double side3;


    public Triangle(String name, double side1, double side2, double side3) {
        super(name);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double area() {
        double p = (side1 + side2 + side3) / 2; // half the perimeter
        double area = Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
        String areaString = String.format("%.5f", area);
        return Double.parseDouble(areaString);
    }

    @Override
    public double perimeter() {
        double perimeter = side1 + side2 + side3;
        String perimeterString = String.format("%.5f", perimeter);
        return Double.parseDouble(perimeterString);
    }
}
