package JavaExceptionHandling;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
    	try {
    		Scanner sc = new Scanner(System.in);
    		int a = sc.nextInt();
    		int b = sc.nextInt();
    		int result = a/b;
    		System.out.println(result);
    	}catch(InputMismatchException e) {
    		System.out.println(e.getClass().getName());
    	}catch(Exception ex) {
    		System.out.println(ex);
    	}
    }
}