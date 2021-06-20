import java.io.*;
import java.util.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class Solution {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{ 
    	Scanner sc = new  Scanner(System.in);
    	String input = sc.nextLine();
    	
    	//get massage instance form MD5
    	MessageDigest md = MessageDigest.getInstance("MD5");
    	
    	//update message from input text in Bytes
    	md.update(input.getBytes(StandardCharsets.UTF_8));
    	
    	// Get the hashbytes
        byte[] hashBytes = md.digest();
        
        //Convert hash bytes to hex format
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        
        // Print the hashed text
        System.out.println(sb.toString());
    }
}