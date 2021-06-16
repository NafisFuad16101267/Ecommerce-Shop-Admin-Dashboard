import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Add{
    public void add(int ...a){
        int sum = 0;
        for(int i = 0 ; i<a.length ; i++){
            if(i<a.length-1){
                System.out.print(a[i]+"+");
            }else{
                System.out.print(a[i]);
            }
            sum = sum + a[i];
        }
        System.out.print("="+sum);
        System.out.println();
    }
}