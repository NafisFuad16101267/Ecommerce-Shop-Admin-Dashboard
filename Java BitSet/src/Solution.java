import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        BitSet bitSetArray[] = new BitSet[2];
        bitSetArray[0] = new BitSet(n);
        bitSetArray[1] = new BitSet(n);
        sc.nextLine();
        for(int i = 0 ; i<m ; i++) {
            String s = sc.nextLine();
            String input[] = s.split(" ");
            bitSetArray[0].set(Integer.parseInt(input[1]));
            bitSetArray[1].set(Integer.parseInt(input[2]));
            bitSetArray[0].and(bitSetArray[1]);
        }
    }
}