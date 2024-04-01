package edu.slu.prog2;


public class Fraction {
    private int numerator;
    private int denominator;

    // Default constructor
    public Fraction(){
        numerator = 0;
        denominator = 1;
    }

    public Fraction(Fraction fraction) {
        this.numerator = fraction.getNumerator();
        this.denominator = fraction.getDenominator();
    }


    // Constructor with parameters
    public Fraction(int numerator, int denominator){
        if (denominator == 0){
            throw new ArithmeticException();
        }
        // Check if the denominator is negative and the numerator is positive, place the negative sign on the numerator for better readability on output
        else if (denominator < 0){
            this.numerator = -numerator;
            this.denominator = Math.abs(denominator);
            return;
        }

        // Instantiate the attributes to the values passed
        this.numerator = numerator;
        this.denominator = denominator;
    }


    /**
     * Sets the numerator of the fraction to the specified value.
     *
     * @param numerator The numerator value to set.
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }


    /**
     * Setter the denominator of the fraction to the specified value.
     *
     * @param denominator The denominator value to set
     */
    public boolean setDenominator(int denominator) {
        if (denominator == 0) return false;
        // If the numerator and denominator are negative, then the fraction is positive
        if(denominator < 0){
            setNumerator(-numerator);
            this.denominator = -denominator;
        } else {
            this.denominator = denominator;
        }
        return true;
    } // end of method -> setDenominator


    /**
     * Retrieves the numerator of the fraction.
     *
     * @return The numerator value of the fraction.
     */
    public int getNumerator() {
        return numerator;
    }


    /**
     * Retrieves the denominator of the fraction
     *
     * @return The denominator value of the fraction
     */
    public int getDenominator() {
        return denominator;
    }



    /**
     * Converts the fraction to its decimal form.
     * Returns the decimal value with three decimal places for better output view, though less accurate.
     *
     * @return The decimal form of the fraction with three decimal places.
     */
    public double toDouble() {
        double decimal = (double) getNumerator() / getDenominator();
        String decimalString = String.format("%.4f", decimal);
        return Double.parseDouble(decimalString);
    } // end of method -> toDouble


    /**
     * Returns the string representation of the fraction.
     * If the denominator is 0, returns "undefined" (for division).
     * If the numerator is 0 or the denominator is 1, returns only the numerator.
     * Otherwise, returns the fraction in the format "numerator/denominator".
     *
     * @return The string representation of the fraction.
     */
    @Override
    public String toString() {
        if (numerator == 0 || denominator == 1) {
            return String.valueOf(numerator);
        } else if (denominator == 0) {
            return "undefined"; // for division
        }
        String VINCULUM = "/";
        return getNumerator() + VINCULUM + getDenominator();
    } // end of method -> toString




    /**
     * Reduces the fraction to its simplest form by dividing both the numerator and denominator
     * by their greatest common divisor (GCD).
     */
    public void reduce() {
        int gcd = computeGCD();
        if (gcd != 0) {
            setNumerator(this.numerator / gcd);
            setDenominator(this.denominator / gcd);
        } else {
            // Handle the case where gcd is zero (optional)
            System.out.println("Cannot reduce fraction: GCD is zero");
        }
    }

    /**
     * Computes the greatest common divisor (GCD) of the numerator and denominator
     * using Euclid's algorithm.
     *
     * @return the GCD of the numerator and denominator
     */
    private int computeGCD() {
        int numerator = getNumerator();
        int denominator = getDenominator();

        if (numerator < denominator){
            int temp = numerator;
            numerator = denominator;
            denominator = temp;
        }

        // Euclid's algorithm to compute GCD
        while (denominator != 0) {
            int temp = denominator;
            denominator = numerator % denominator;
            numerator = temp;
        }
        return numerator;  // Return the GCD
    }




    /**
     * Adds another fraction to this fraction.
     *
     * <p>The method computes the result of adding the specified {@code anotherFraction}
     * to this fraction and sets the numerator and denominator accordingly. It uses the formula:
     *
     * <pre>
     *   resultNumerator = (this.numerator * anotherFraction.getDenominator() +
     *                      anotherFraction.getNumerator() * this.denominator);
     *   resultDenominator = (this.denominator * anotherFraction.getDenominator());
     * </pre>
     *
     * <p>After setting the numerator and denominator, the method reduces the fraction to its
     * simplest form by dividing both the numerator and denominator by their greatest common divisor (GCD).
     *
     * @param anotherFraction the fraction to be added to this fraction
     */
    public Fraction add (Fraction anotherFraction) {
        // Set the denominator of the sumOfTwoFractions Fraction by multiplying the denominators
        int numerator = (this.numerator * anotherFraction.getDenominator() +
                anotherFraction.getNumerator() * this.denominator);
        int denominator = (this.denominator * anotherFraction.getDenominator());

        Fraction sum = new Fraction(numerator, denominator);
        sum.reduce();
        return sum;
    } // end of the method -> add







    /**
     * Subtracts another fraction from this fraction.
     *
     * <p>The method computes the result of subtracting the specified {@code anotherFraction}
     * from this fraction and sets the numerator and denominator accordingly. It uses the formula:
     *
     * <pre>
     *   resultNumerator = (this.numerator * anotherFraction.getDenominator() -
     *                      anotherFraction.getNumerator() * this.numerator);
     *   resultDenominator = (this.denominator * anotherFraction.getDenominator());
     * </pre>
     *
     * <p>After setting the numerator and denominator, the method reduces the fraction to its
     * simplest form by dividing both the numerator and denominator by their greatest common divisor (GCD).
     *
     * @param anotherFraction the fraction to be subtracted from this fraction
     */
    public Fraction subtract (Fraction anotherFraction) {
        // Set the denominator of the sumOfTwoFractions Fraction by multiplying the denominators
        int numerator = (this.numerator * anotherFraction.getDenominator() -
                anotherFraction.getNumerator() * this.denominator);

        // Set the denominator of the sumOfTwoFractions Fraction by multiplying the denominators
        int denominator = (this.denominator * anotherFraction.getDenominator());

        Fraction difference = new Fraction(numerator, denominator);
        difference.reduce();
        return difference;
    } // end of the method -> subtract





    /**
     * Multiplies this fraction by another fraction.
     *
     * <p>The method computes the result of multiplying this fraction by the specified {@code anotherFraction}
     * and sets the numerator and denominator accordingly. It uses the formula:
     *
     * <pre>
     *   resultNumerator = (this.numerator * anotherFraction.getNumerator());
     *   resultDenominator = (this.denominator * anotherFraction.getDenominator());
     * </pre>
     *
     * <p>After setting the numerator and denominator, the method reduces the fraction to its
     * simplest form by dividing both the numerator and denominator by their greatest common divisor (GCD).
     *
     * @param anotherFraction the fraction to be multiplied by this fraction
     */
    public Fraction multiplyBy (Fraction anotherFraction) {
        // Set the denominator of the sumOfTwoFractions Fraction by multiplying the denominators
        int numerator = (this.numerator * anotherFraction.getNumerator());

        // Set the denominator of the sumOfTwoFractions Fraction by multiplying the denominators
        int denominator = (this.denominator * anotherFraction.getDenominator());

        Fraction product = new Fraction(numerator, denominator);
        product.reduce();
        return product;
    } // end of the method -> multiplyBy



    /**
     * Divides this fraction by another fraction.
     *
     * <p>The method computes the result of dividing this fraction by the specified {@code anotherFraction}
     * and sets the numerator and denominator accordingly. It uses the formula:
     *
     * <pre>
     *   resultNumerator = (this.numerator * anotherFraction.getDenominator());
     *   resultDenominator = (this.denominator * anotherFraction.getNumerator());
     * </pre>
     *
     * <p>After setting the numerator and denominator, the method reduces the fraction to its
     * simplest form by dividing both the numerator and denominator by their greatest common divisor (GCD).
     *
     * @param anotherFraction the fraction to divide this fraction by
     * */
    public Fraction divideBy(Fraction anotherFraction){
        // Set the denominator of the sumOfTwoFractions Fraction by multiplying the denominators
        int numerator = (this.numerator * anotherFraction.getDenominator());

        // Set the denominator of the sumOfTwoFractions Fraction by multiplying the denominators
        int denominator = (this.denominator * anotherFraction.getNumerator());

        Fraction quotient = new Fraction(numerator, denominator);
        quotient.reduce();

        return quotient;
    } // end of method -> divideBy

} // end of the class
