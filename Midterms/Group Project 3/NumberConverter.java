/**
 Name of programmer: Bag-eo, Jim Hendrix T.
 Class Code and Schedule: 9322A/B CS 122/122L MTH/TF
 Date: March 16, 2024
 */



package prog2.samcis;

public interface NumberConverter {
    String decimalToBase(double number, byte base);
    String binaryToBase(String number, byte base) throws Exception;
    String hexadecimalToBase(String hexadecimalString, byte base);
    String octalToBase(String number, byte base);

}
