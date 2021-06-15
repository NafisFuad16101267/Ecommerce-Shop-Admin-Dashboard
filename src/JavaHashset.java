import java.util.HashSet;
import java.util.Scanner;
public class JavaHashset {
	public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
	        int t = s.nextInt();
	        String [] pair_left = new String[t];
	        String [] pair_right = new String[t];
	        
	        HashSet<String> hashset = new HashSet<String>();
	        
	        for (int i = 0; i < t; i++) {
	            pair_left[i] = s.next();
	            pair_right[i] = s.next();
	        }
	        
	        //Write your code here
	        for(int j = 0 ; j<t ; j++) {
	        	String input = pair_left[j] + " " +pair_right[j];
	            hashset.add(input);
	            System.out.println(hashset.size());
	        }
	}
}
