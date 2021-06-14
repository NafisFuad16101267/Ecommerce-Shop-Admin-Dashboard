import java.util.Scanner;
import java.util.HashMap;

public class JavaAnagrams {


  public static boolean isAnagram(String a, String b) {
      // Complete the function
	  String a1 = a.toLowerCase();
	  String b1 = b.toLowerCase();
	  HashMap<Character,Integer> map = new HashMap<Character,Integer>();
	  for(int i = 0; i<a1.length(); i++) {
		  if(!map.containsKey(a1.charAt(i))) {
			  map.put(a1.charAt(i), 1);
		  } else {
			  int value = map.get(a1.charAt(i));
			  map.put(a1.charAt(i), ++value);
		  }
	  }
	  HashMap<Character,Integer> map2 = new HashMap<Character,Integer>();
	  for(int j = 0; j<b1.length(); j++) {
		  if(!map2.containsKey(b1.charAt(j))) {
			  map2.put(b1.charAt(j), 1);
		  } else {
			  int value = map2.get(b1.charAt(j));
			  map2.put(b1.charAt(j), ++value);
		  }
	  }
	  for(int k = 0 ; k<a.length() ; k++) {
		  if(map2.containsKey(a1.charAt(k))) {
			  if(map2.get(a1.charAt(k)) != map.get(a1.charAt(k))) {
				  return false;
			  }
		  }else {
			  return false;
		  }
	  }
	  return true;
    }

  public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }

}
