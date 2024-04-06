package mexer2.prog2.edu.slu;

public class Square extends Shape{
    protected double side;

    public Square(String name, double side) throws NumberFormatException{
        super(name);
        this.side = side;
    }

    @Override
    public double area() {
        double area = side * side;
        String areaString = String.format("%.5f", area);
        return Double.parseDouble(areaString);
    }

    @Override
    public double perimeter() {
        double perimeter = 4 * side;
        String perimeterString = String.format("%.5f", perimeter);
        return Double.parseDouble(perimeterString);
    }
}
