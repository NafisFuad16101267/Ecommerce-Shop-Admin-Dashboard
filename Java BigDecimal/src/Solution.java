import java.math.BigDecimal;
import java.util.*;
class Solution{
    public static void main(String []args){
        //Input
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        String []s=new String[n+2];
        for(int i=0;i<n;i++){
            s[i]=sc.next();
        }
        sc.close();

        Comparator<String> customComparator = new Comparator<String>() {
            @Override
                public int compare(String number1, String number2) {
                    BigDecimal bigDecimal1 = new BigDecimal(number1);
                    BigDecimal bigDecimal2 = new BigDecimal(number2);
                    return bigDecimal2.compareTo(bigDecimal1);
                }
        };
        
        Arrays.sort(s,0,n,customComparator);
        
        for(int i=0;i<n;i++)
        {
            System.out.println(s[i]);
        }
    }
}
