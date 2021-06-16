import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number1 = sc.next();
        String number2 = sc.next();
        BigInteger bigInteger1 = new BigInteger(number1);
        BigInteger bigInteger2 = new BigInteger(number2);
        BigInteger result1 = bigInteger1.add(bigInteger2);
        BigInteger result2 = bigInteger1.multiply(bigInteger2);
        System.out.println(result1);
        System.out.println(result2);
    }
}
