package edu.slu.prog2;

public class MixedFraction extends Fraction {
    private int whole;


    /**
     * Constructs a MixedFraction object with default values (whole = 0, fraction part = 0/1).
     */
    public MixedFraction(){
        super();
        whole = 0;
    }


    /**
     * Constructs a MixedFraction object with the given whole number and fraction.
     *
     * @param whole    The whole number part of the mixed fraction.
     * @param fraction The fraction part of the mixed fraction.
     */
    public MixedFraction(int whole, Fraction fraction){
        super(fraction);
        if (fraction.getNumerator() < 0 && whole != 0) {
            this.whole = -whole;
            setNumerator(-(fraction.getNumerator()));
            return;
        }
        this.whole = whole;
    }


    /**
     * Constructs a MixedFraction object with the given whole number, numerator, and denominator.
     *
     * @param whole       The whole number part of the mixed fraction.
     * @param numerator   The numerator of the fraction part.
     * @param denominator The denominator of the fraction part.
     */
    public MixedFraction(int whole, int numerator, int denominator) throws ArithmeticException{
        super(numerator, denominator);
        if (getNumerator() < 0 && whole != 0) {
            this.whole = -whole;
            setNumerator(-getNumerator());
            return;
        }
        this.whole = whole;
    }


    /**
     * Constructs a MixedFraction object from a Fraction object.
     *
     * @param fraction The Fraction object representing the mixed fraction.
     */
    public MixedFraction(Fraction fraction){
        super(fraction);
        whole = 0;
    }

    public void setWholePart(int whole){
        if (getNumerator() < 0 && whole != 0){
            this.whole = -whole;
            setNumerator(-getNumerator());
            return;
        }
        this.whole = whole;
    }


    /**
     * Sets the numerator and denominator of the fraction part of the MixedFraction.
     *
     * @param fraction The Fraction object representing the fraction part.
     */
    public void setFractionPart(Fraction fraction){
        setNumerator(fraction.getNumerator());
        setDenominator(fraction.getDenominator());
    }


    /**
     * Retrieves the whole part of the MixedFraction.
     *
     * @return The whole part of the MixedFraction.
     */
    public int getWhole(){
        return whole;
    }



    /**
     * Retrieves the fractional part of the MixedFraction as a Fraction object.
     *
     * @return The fractional part of the MixedFraction as a Fraction object.
     */
    public Fraction getFractionPart(){
        return new Fraction(getNumerator(), getDenominator());
    }



    /**
     * Converts the MixedFraction to an equivalent Fraction (Improper Fraction).
     *
     * @return The MixedFraction converted to a Fraction.
     */
    public Fraction toFraction(){
        int numerator = whole * getDenominator() + getNumerator();
        return new Fraction(numerator, getDenominator());
    }




    /**
     * Adds the specified mixed fraction to this mixed fraction.
     *
     * @param other The mixed fraction to be added.
     * @return The result of adding the specified mixed fraction to this mixed fraction.
     */
    public MixedFraction add(MixedFraction other){
        return performOperation(other, "addition");
    }


    /**
     * Subtracts the specified mixed fraction from this mixed fraction.
     *
     * @param other The mixed fraction to be subtracted.
     * @return The result of subtracting the specified mixed fraction from this mixed fraction.
     */
    public MixedFraction subtract(MixedFraction other){
        return performOperation(other, "subtraction");
    }



    /**
     * Multiplies this mixed fraction by the specified mixed fraction.
     *
     * @param other The mixed fraction to multiply by.
     * @return The result of multiplying this mixed fraction by the specified mixed fraction.
     */
    public MixedFraction multiplyBy(MixedFraction other){
        return performOperation(other, "multiplication");
    }


     /**
     * Divides this mixed fraction by the specified mixed fraction.
     *
     * @param other The mixed fraction to divide by.
     * @return The result of dividing this mixed fraction by the specified mixed fraction.
     */
    public MixedFraction divideBy(MixedFraction other){
        return performOperation(other, "division");
    }


    /**
     * Performs the specified arithmetic operation on this mixed fraction and the specified mixed fraction.
     *
     * @param other The mixed fraction to perform the operation with.
     * @param operation The operation to perform (e.g., "addition", "subtraction", "multiplication", "division").
     * @return The result of the specified operation between this mixed fraction and the specified mixed fraction.
     */
    private MixedFraction performOperation(MixedFraction other, String operation){
        Fraction thisFraction = getMixedFraction().toFraction();
        Fraction otherFraction = other.getMixedFraction().toFraction();
        Fraction result = new Fraction();
        switch(operation){
            case "addition" -> result = thisFraction.add(otherFraction);
            case "subtraction" -> result = thisFraction.subtract(otherFraction);
            case "multiplication" -> result = thisFraction.multiplyBy(otherFraction);
            case "division" -> result = thisFraction.divideBy(otherFraction);
        }
        MixedFraction resultMixedNumber = toMixedFraction(result);
        if (resultMixedNumber.getWhole() < 0 && resultMixedNumber.getNumerator() < 0){
            resultMixedNumber.setNumerator(-resultMixedNumber.getNumerator());
        }
        return resultMixedNumber;
    }



    /**
     * Creates and returns a new MixedFraction object equivalent to the current MixedFraction.
     *
     * @return A new MixedFraction object equivalent to the current MixedFraction.
     */
    private MixedFraction getMixedFraction(){
        return new MixedFraction(whole, getFractionPart());
    }


    /**
     * Simplifies this mixed fraction, converting any improper fractions to mixed numbers if necessary.
     * If the numerator's absolute value is less than the denominator, no simplification is performed.
     */
    public void simplify(){
        reduce();
        if (Math.abs(getNumerator()) < getDenominator()){
            return;
        }
        if (whole < 0){
            setWholePart(-(-whole + getNumerator()/getDenominator()));
        } else {
            setWholePart(whole + getNumerator()/getDenominator());
        }
        setNumerator(getNumerator() % getDenominator());
    }



    /**
     * Converts the specified improper fraction to a mixed fraction.
     *
     * @param improperFraction The improper fraction to convert.
     * @return The equivalent mixed fraction.
     */
    public static MixedFraction toMixedFraction(Fraction improperFraction){
        if (improperFraction.getDenominator() == 0) {
            return new MixedFraction(improperFraction);
        }
        int wholeNumber = improperFraction.getNumerator()/improperFraction.getDenominator();
        if (wholeNumber == 0) {
            return new MixedFraction(improperFraction);
        }
        int numerator = Math.abs(improperFraction.getNumerator()) % improperFraction.getDenominator();
        MixedFraction result = new MixedFraction(wholeNumber, numerator, improperFraction.getDenominator());
        if (result.getWhole() < 0 && result.getNumerator() < 0){
            result.setNumerator(-result.getNumerator());
        }
        return result;
    }



    @Override
    public String toString(){
        if (whole == 0 && getNumerator() == 0){
            return "" + 0;
        }
        else if (whole == 0){
            return getNumerator() + "/" + getDenominator();
        } else if (getDenominator() == 1) {
            return String.valueOf(getWhole());
        } else if (getDenominator() == 0) {
            return "undefined"; // for division
        } else if (whole < 0 && getNumerator() < 0){
            return whole + " " + -(getNumerator()) + "/" + getDenominator();
        }

        return whole + " " + getNumerator() + "/" + getDenominator();
    }



    /**
     * Converts this mixed fraction to a decimal number.
     *
     * @return The decimal representation of this mixed fraction.
     */
    @Override
    public double toDouble(){
        double decimal = whole + (double) getNumerator() / getDenominator();
        String decimalString = String.format("%.4f", decimal);
        return Double.parseDouble(decimalString);
    }


} // end of MixedFraction
