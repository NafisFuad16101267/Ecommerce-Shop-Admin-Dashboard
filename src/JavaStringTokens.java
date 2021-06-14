import java.util.Scanner;
import java.util.regex.*;  
public class JavaStringTokens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        String output = "";
        for(int i = 0; i < s.length() ; i++) {
        	char c = s.charAt(i);
        	boolean temp = Pattern.matches("[789]{1}[0-9]{9}", "9953038949");
        	System.out.print(temp);
        }
        
        
        scan.close();
	}

}
