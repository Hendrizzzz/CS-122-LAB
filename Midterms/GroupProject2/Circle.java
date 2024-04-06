package mexer2.prog2.edu.slu;

public class Circle extends Shape {
    private final double radius;
    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double area() {
        double area = Math.PI * Math.pow(radius,2);
        String areaString = String.format("%.5f", area);
        return Double.parseDouble(areaString);
    }

    @Override
    public double perimeter() {
        double perimeter = Math.PI * (2 * radius);
        String perimeterString = String.format("%.5f", perimeter);
        return Double.parseDouble(perimeterString);
    }
}
