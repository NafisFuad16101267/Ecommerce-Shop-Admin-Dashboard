import java.util.Scanner;
public class JavaStringReverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        /* Enter your code here. Print output to STDOUT. */
        int mid = A.length()/2;
        boolean palindrome = true;
        for(int i = 0; i<mid ; i++) {
        	if(A.charAt(i) != A.charAt(A.length()-i-1)) {
        		palindrome = false;
        		break;
        	}
        }
        if(palindrome == true) System.out.println("Yes");
        else System.out.print("No");
	}

}
