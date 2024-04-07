/**
 Name of programmer: Bag-eo, Jim Hendrix T.
 Class Code and Schedule: 9322A/B CS 122/122L MTH/TF
 Date: March 16, 2024
 */



package prog2.samcis;
/**
 *A template for an object representing a whole number with its decimal, binary, octal and hexadecimal form.
 **/
public class EquivalentNumbers implements NumberConverter {
    private double decimalNumber; // decimal number form
    private String hexadecimalString; // hexadecimal number form
    private String binary; // binary number form
    private String octal; // octal number form



    /**
     *Constructs an object representing the number 0
     **/
    public EquivalentNumbers(){
        decimalNumber = 0f;
        hexadecimalString = "0";
        binary = "0";
        octal = "0";
    }


    /**
     *Constructs an object representing a q whole number a
     **/
    public EquivalentNumbers(int a, String b, String c, String d){
        decimalNumber = a;
        hexadecimalString = b;
        binary = c;
        octal = d;
    }


    /**
     *Returns the decimal number form of an object of EquivalentNumbers
     **/
    public double getDecimalNumber(){
        return decimalNumber;
    }


    /**
     *Returns the hexadecimal number form of an object of EquivalentNumbers
     **/
    public String getHexadecimalString(){
        return hexadecimalString;
    }


    /**
     *Returns the binary number form of an object of EquivalentNumbers
     **/
    public String getBinaryString(){
        return binary;
    }



    /**
     *Returns the octal number form of an object of EquivalentNumbers
     **/
    public String getOctalString(){
        return octal;
    }


    /**
     *Sets the decimal number; then the hexadecimal number, binary number and octal number
     *are set based on the decimal number.
     */
    public void setDecimalNumber(double d){
        decimalNumber = d;
        hexadecimalString = decimalToBase(d, (byte)16);
        binary = decimalToBase(d, (byte) 2);
        octal = decimalToBase(d,(byte) 8);
    }


    /**
     *Sets the binary number, then the decimal number, octal number and hexadecimal number
     *are set based on the binary number.
     */
    public void setBinaryNumber(String b) throws Exception {
        if (!isValidBinaryString(b)){
            throw new Exception();
        }
        binary = b;
        decimalNumber = Double.parseDouble(binaryToBase(b, (byte) 10));
        octal = binaryToBase(b, (byte) 8);
        hexadecimalString = binaryToBase(b, (byte) 16);
    }


    public void setOctalNumber(String b) throws Exception {
        String regex = "[0-7.-]+";
        if (!b.matches(regex)){
            throw new Exception();
        }
        octal = b;
        binary = octalOrHexToBinary(b, (byte) 3);
        decimalNumber = Double.parseDouble(numberToDecimal(b, (byte) 8));
        hexadecimalString = binaryToOctalOrHexadecimal(binary, 4);
    }

    public void setHexadecimalString(String b) throws Exception {
        String regex = "[-0-9a-fA-F.]+";
        if (!b.matches(regex)){
            throw new Exception();
        }
        this.hexadecimalString = b;
        binary = octalOrHexToBinary(b, (byte) 4);
        octal = binaryToOctalOrHexadecimal(binary, 3);
        decimalNumber = Double.parseDouble(numberToDecimal(b, (byte) 16));
    }



    @Override
    public String decimalToBase(double number, byte base) {
        StringBuilder result = new StringBuilder();
        boolean negative = number < 0;
        long integerPart = (long) Math.abs(number);
        double fractionalPart = Math.abs(number) - Math.abs((long) number);

        convertIntegerPart(integerPart, base, result);
        convertFractionalPart(fractionalPart, base, result);

        if (negative) {
            result.insert(0, '-');
        }

        return result.toString();
    }



    private static void convertIntegerPart(long integerPart, int base, StringBuilder result) {
        if (integerPart == 0) {
            result.append('0');
        } else {
            while (integerPart > 0) {
                long remainder = integerPart % base;
                result.insert(0, toChar(remainder));
                integerPart /= base;
            }
        }
    }


    private static void convertFractionalPart(double fractionalPart, int base, StringBuilder result) {
        if (fractionalPart == 0){
            return;
        }
        result.append(".");
        for (int i = 0; i < 10; i++) {
            fractionalPart *= base;
            int digit = (int) fractionalPart;
            result.append(toChar(digit));
            fractionalPart -= digit;
        }
    }


    /**
     * Converts a numeric value to its character representation.
     *
     * @param value the numeric value to convert
     * @return the character representation of the value
     */
    private static char toChar(long value) {
        return (char) (value < 10 ? '0' + value : 'A' + value - 10);
    }


    /**
     *Returns the decimal number corresponding to a given binary number
     **/
    @Override
    public String binaryToBase(String binary, byte base) throws Exception{
        if (base == 10){
            return numberToDecimal(binary, (byte) 2);
        } else if (base == 8){
            return binaryToOctalOrHexadecimal(binary, 3);
        } else {
            return binaryToOctalOrHexadecimal(binary, 4);
        }
    }



    private String numberToDecimal(String number, byte base) {
        double result = 0;
        if (base == 16){
            result = hexadecimalToDecimal(number);
            return String.valueOf(result);
        }
        boolean isNegative = Double.parseDouble(number) < 0;
        boolean isWholeNumber = !number.contains(".");
        int numberFractionalPart = 0;
        if (!isWholeNumber){
            // Find the index of the dot
            int dotIndex = number.indexOf('.');

            // Extract the fractional part of the number number
            numberFractionalPart = (int) Double.parseDouble(number.substring(dotIndex + 1));
        }

        String numberWhole = String.valueOf((int) Double.parseDouble(number));
        int exponent = 0;
        int limit = 0;
        if (isNegative) {
            limit = 1;
        }
        for (int i = numberWhole.length() - 1; i >= limit; i--){

            result += Integer.parseInt(String.valueOf(numberWhole.charAt(i))) * (int)Math.pow(base, exponent++);
        }

        if (!isWholeNumber){
            exponent = 0;
            for (int i = 0; i < String.valueOf(numberFractionalPart).length(); i++){
                result += Integer.parseInt(String.valueOf((String.valueOf(numberFractionalPart).charAt(i)))) * Math.pow(base, --exponent);
            }
        }

        if (isNegative){
            result = 0 - result;
        }

        return String.valueOf(result);
    }

    private String binaryToOctalOrHexadecimal(String binary, int group) {
        StringBuilder resultString = new StringBuilder();
        int result = 0;
        boolean isNegative = Double.parseDouble(binary) < 0;
        boolean isWhole = !binary.contains(".");
        String wholePart = binary;
        String numberFractionalPart = "";
        if (!isWhole) {
            String[] x = binary.split("\\.");
            wholePart = x[0];
            numberFractionalPart = x[1];
        }

        int exponent = 0;
        int j = 0;
        if (isNegative){
            j++;
        }
        for (int i = wholePart.length() - 1; i >= j; i--){
            result += Integer.parseInt(String.valueOf(wholePart.charAt(i))) * (int)Math.pow(2, exponent++);
            if (exponent == group || i == j){
                exponent = 0;
                char c = hexDigit((byte) result);
                resultString.insert(0, c);
                result = 0;
            }
        }

        if (!isWhole){
            resultString.append(".");

            // fill the gaps in the fractional part
            String binaryFractionalPartString = String.valueOf(numberFractionalPart);
            if (group == 3) {
                if (binaryFractionalPartString.length() % 3 == 1) {
                    binaryFractionalPartString += "00";
                } else if (String.valueOf(numberFractionalPart).length() % 3 == 2) {
                    binaryFractionalPartString += "0";
                }
            } else {
                if (binaryFractionalPartString.length() % 4 == 1){
                    binaryFractionalPartString += "000";
                } else if (binaryFractionalPartString.length() % 4 == 2){
                    binaryFractionalPartString += "00";
                } else if (binaryFractionalPartString.length() % 4 == 3){
                    binaryFractionalPartString += "0";
                }
            }

            String temp = "";
            exponent = 0;
            for (int i = binaryFractionalPartString.length() - 1; i >= 0; i--){
                result += (int) (Integer.parseInt(String.valueOf(binaryFractionalPartString.charAt(i))) * Math.pow(2, exponent++));
                if (exponent == group){
                    exponent = 0;
                    char c = hexDigit((byte) result);
                    temp = c + temp;
                    result = 0;
                }
            }
            resultString.append(temp);
        }

        if (isNegative){
            resultString.insert(0, "-");
        }

        return resultString.toString();
    }



    /**
     *Returns the hexadecimal digit corresponding to a given number in the range 0 to 15
     **/
    public char hexDigit(byte digit){
        return switch (digit) {
            case 1 -> '1';
            case 2 -> '2';
            case 3 -> '3';
            case 4 -> '4';
            case 5 -> '5';
            case 6 -> '6';
            case 7 -> '7';
            case 8 -> '8';
            case 9 -> '9';
            case 10 -> 'A';
            case 11 -> 'B';
            case 12 -> 'C';
            case 13 -> 'D';
            case 14 -> 'E';
            case 15 -> 'F';
            default -> '0';
        };
    }


    @Override
    public String octalToBase(String number, byte base) {
        if (base == 2){
            return octalOrHexToBinary(number, (byte) 3);
        } else {
            return octalToHexadecimal(number);
        }
    }

    private String octalOrHexToBinary(String number, byte base) {
        StringBuilder resultString = new StringBuilder();
        boolean isNegative = number.charAt(0) == '-';
        boolean isWhole = !number.contains(".");
        String wholePart = number;
        String numberFractionalPart = "";
        if (!isWhole) {
            String[] x = number.split("\\.");
            wholePart = x[0];
            numberFractionalPart = x[1];
        }

        int i = 0;
        if (isNegative){
            i++;
        }
        for (int j = wholePart.length() - 1; j >= i; j--){
            String equivalent = toBinary(String.valueOf(wholePart.charAt(j)), base);
            resultString.insert(0, equivalent);
        }

        if (!isWhole){
            resultString.append(".");
            for (int j = 0; j < numberFractionalPart.length(); j++){
                String equivalent = toBinary(String.valueOf(numberFractionalPart.charAt(j)), base);
                resultString.append(equivalent);
            }
        }

        if (isNegative){
            resultString.insert(0, "-");
        }
        return resultString.toString();

    }

    private String toBinary(String result, byte base) {
        result = result.toUpperCase();
        if (base == 3) {
            return switch (result) {
                case "1" -> "001";
                case "2" -> "010";
                case "3" -> "011";
                case "4" -> "100";
                case "5" -> "101";
                case "6" -> "110";
                case "7" -> "111";
                default -> "000";
            };
        } else {
            return switch (result) {
                case "1" -> "0001";
                case "2" -> "0010";
                case "3" -> "0011";
                case "4" -> "0100";
                case "5" -> "0101";
                case "6" -> "0110";
                case "7" -> "0111";
                case "8" -> "1000";
                case "9" -> "1001";
                case "A" -> "1010";
                case "B" -> "1011";
                case "C" -> "1100";
                case "D" -> "1101";
                case "E" -> "1110";
                case "F" -> "1111";
                default -> "0000";
            };
        }
    }

    private String octalToHexadecimal(String number) {
        String binaryForm = getBinaryString();
        return binaryToOctalOrHexadecimal(binaryForm, 4);
    }


    @Override
    public String hexadecimalToBase(String hexadecimalString, byte base) {
        String binaryForm = octalOrHexToBinary(hexadecimalString, (byte) 4);
        return binaryToOctalOrHexadecimal(binaryForm, 8);
    }





    /**
     *Returns the decimal number corresponding to a given hexadecimal number
     **/
    public double hexadecimalToDecimal(String h){
        int result=0;
        boolean isWhole = !h.contains(".");
        boolean isNegative = (h.charAt(0) == '-');
        String wholePart = h;
        String numberFractionalPart = "";
        if (!isWhole) {
           String[] x = h.split("\\.");
           wholePart = x[0];
           numberFractionalPart = x[1];
        }
        int exponent = 0;
        int j = 0;
        if (isNegative){
            j = 1;
        }
        for (int i = wholePart.length() - 1; i >= j; i--){
            double pow = Math.pow(16, exponent++);
            switch (wholePart.charAt(i)){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    result += (int) (Integer.parseInt(""+wholePart.charAt(i))* pow);

                    break;
                case 'a':
                case 'A':
                    result += (int) (10* pow);
                    break;
                case 'b':
                case 'B':
                    result += (int) (11* pow);
                    break;
                case 'c':
                case 'C':
                    result += (int) (12* pow);
                    break;
                case 'd':
                case 'D':
                    result += (int) (13* pow);
                    break;
                case 'e':
                case 'E':
                    result += (int) (14* pow);
                    break;
                case 'f':
                case 'F':
                    result += (int) (15* pow);
                    break;
            }
        }

        double fractionResult = 0;
        if (!isWhole){
            exponent = 0;
            for (int i=0; i<numberFractionalPart.length(); i++){
                double pow = Math.pow(16, --exponent);
                switch (numberFractionalPart.charAt(i)){
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        fractionResult += Integer.parseInt(""+numberFractionalPart.charAt(i))* pow;

                        break;
                    case 'a':
                    case 'A':
                        fractionResult +=  (10* pow);
                        break;
                    case 'b':
                    case 'B':
                        fractionResult += (11* pow);
                        break;
                    case 'c':
                    case 'C':

                        fractionResult += (12* pow);
                        break;
                    case 'd':
                    case 'D':
                        fractionResult += (13* pow);
                        break;
                    case 'e':
                    case 'E':
                        fractionResult += (14* pow);
                        break;
                    case 'f':
                    case 'F':
                        fractionResult +=  (15* pow);
                        break;
                }
            }
        }
        if (isNegative){
            return 0 - (result + fractionResult);
        }
        return (result + fractionResult);
    }



//    /**
//     *Returns the decimal number corresponding to a given octal number
//     **/
//    public String octalToDecimal(String n){
//        int result=0;
//        boolean isWhole = n.contains("n");
//        boolean isNegative = n.charAt(0) == '-';
//        String wholePart = n;
//        String numberFractionalPart = "";
//        if (!isWhole) {
//            String[] x = n.split("\\.");
//            wholePart = x[0];
//            numberFractionalPart = x[1];
//        }
//
//        int i = 0;
//        if (isNegative){
//            i = 1;
//        }
//        for (;i<wholePart.length(); i++){
//            result += (int) (Integer.parseInt(""+wholePart.charAt(i))* Math.pow(8, wholePart.length()-1-i));
//        }
//        if (!isWhole){
//            for (i = 0; i < numberFractionalPart.length(); i++){
//                result += (int) (Integer.parseInt(""+numberFractionalPart.charAt(i))* Math.pow(8, -(numberFractionalPart.length()-2-i)));
//            }
//        }
//        if (isNegative){
//            result = -result;
//        }
//        return (String.valueOf(result));
//    }




    /**
     *Returns a string showing a whole number with its decimal, binary, octal and hexadecimal form.
     **/
    public String toString(){
        return ("Decimal Value = "+decimalNumber+"\nBinary Value = " + binary +"\nOctal Value = " +
                octal + "\nHexadecimal Value = " + hexadecimalString);
    }




    /**
     *Returns true if this number is equivalent to another number.
     **/
    public boolean equals(EquivalentNumbers another){
        return (this.decimalNumber == another.getDecimalNumber());
    }




    /**
     * Checks if a string represents a valid binary number.
     *
     * @param s the string to check
     * @return true if the string represents a valid binary number, false otherwise
     */
    private boolean isValidBinaryString(String s){
        boolean result = true;
        int dotCounter = 0;
        int index = 0;
        if (s.charAt(0) == '-'){
            index = 1;
        }
        for (; index< s.length(); index++ ){
            if (s.charAt(index) == '.' && index > 0){
                dotCounter++;
            }
            if (s.charAt(index) != '0' && s.charAt(index) != '1'){
                if (s.charAt(index) == '.' && dotCounter == 1) {
                    continue;
                }
                result = false;
                break;
            }
        }
        if (dotCounter > 1){
            return false;
        }
        return result;
    }


} // end of the class