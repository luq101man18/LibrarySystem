package main;
import java.util.Scanner;
import java.security.MessageDigest;

public class encryption {
    Scanner input = new Scanner(System.in);
   
    public String Hashing(String password){
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("e.getMessage()");
        }
        return null;
    }
   
    
}
