import java.util.Scanner;
import java.util.StringTokenizer; 

public class JavaStringTokens {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        s = s.trim();
        if (s.isEmpty()) System.out.println(0);
        else {
        	String output[] = s.split("[!,?._'@ ]+");
            System.out.println(output.length);
            for(int i = 0; i<output.length; i++) {
            	System.out.println(output[i]);
            }
        }
        
        scan.close();
	}

}
