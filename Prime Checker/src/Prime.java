import java.util.ArrayList;
import java.util.List;

class Prime {
    public void checkPrime(int ...number) {
        for(int i = 0 ; i<number.length ; i++) {
        	boolean flag = isPrime(number[i]);
        	if(flag) System.out.print(number[i]+" ");
        	if(i == (number.length-1)) System.out.println();
        }
    }
    public static boolean isPrime(int n) {
    	if (n == 1)
    		return false;
    	for(int i = 2; i<n; i++) {
    		if(n%i == 0)
    			return false;
    	}
    	return true;
    }
}
