import java.util.Scanner;
import java.util.regex.*;

/*
 * problem link: https://www.hackerrank.com/challenges/pattern-syntax-checker/problem
 * */
public class Solution
{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
        if(!in.hasNext())
            System.out.println("Invalid");
		int testCases = Integer.parseInt(in.nextLine());
		while(testCases>0){
			if(in.hasNext()){
                String pattern = in.nextLine();
          	    //Write your code
                try{
                    Pattern.compile(pattern);
                    System.out.println("Valid"); 
                }catch(PatternSyntaxException e){
                    System.out.println("Invalid");
                }
            }
            testCases--;
		}
	}
}



