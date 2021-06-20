import java.io.*;
import java.util.*;

/*
 * 
 * problem link = https://www.hackerrank.com/challenges/java-lambda-expressions/problem
 * This Java 8 challenge tests your knowledge of Lambda expressions!

Write the following methods that return a lambda expression performing a specified action:

    PerformOperation isOdd(): The lambda expression must return 

if a number is odd or
if it is even.
PerformOperation isPrime(): The lambda expression must return
if a number is prime or
if it is composite.
PerformOperation isPalindrome(): The lambda expression must return
if a number is a palindrome or

    if it is not.

Input Format

Input is handled for you by the locked stub code in your editor.

Output Format

The locked stub code in your editor will print

lines of output.

Sample Input

The first line contains an integer,

(the number of test cases).

The
subsequent lines each describe a test case in the form of space-separated integers:
The first integer specifies the condition to check for ( for Odd/Even, for Prime, or

for Palindrome). The second integer denotes the number to be checked.

5
1 4
2 5
3 898
1 3
2 12

Sample Output

EVEN
PRIME
PALINDROME
ODD
COMPOSITE

 * */

interface PerformOperation {
	boolean check(int a);
}
class MyMath {
	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	// Write your code here
	public PerformOperation isOdd() {
		return (int num)-> (num%2==0)?false:true;
	}
	public PerformOperation isPrime() {
		return (int num)->{
			if(num == 1)
				return false;
			if(num == 2)
				return true;
			int size = (int)Math.sqrt((double)num);
			
			for(int i = 3 ; i<=size ;i++) {
				if(num%i==0) {
					return false;
				}
			}
			
			return true;
		};
	}
	public PerformOperation isPalindrome() {
		return (int num)->{
			List<Integer> list = new ArrayList<>();
			int res = num;
			while(res!=0) {
				list.add(res%10);
				res = res / 10;
			}
			int size = list.size();
			for(int i = 0; i<size/2 ; i++) {
				if(list.get(i) != list.get(size-1-i)) {
					return false;
				}
			}
			return true;
		};
	}
}

public class Solution {

	public static void main(String[] args) throws IOException {
		MyMath ob = new MyMath();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		while (T--> 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}
			System.out.println(ans);
		}
	}
}