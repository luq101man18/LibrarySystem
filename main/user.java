package main;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class user {

   
    library library = new library();
    String userFile = "user.txt";
    String  userName; 
    String userPassword;
    String address;
    int phoneNumber;
    String[] borrowedBooks;
    static Map <String, List<String>> borrowedBooksByUser= new HashMap<>();
    static List<String> values = new ArrayList<String>();
    static int logoutFlag;
    int flag;
 

    user(){
        userName = null;
        this.userPassword = null;
        this.address = null;
        this.phoneNumber = 0;
    }

    user(String username, String userPassword, String address, int phoneNumber){
        userName = username;
        this.userPassword = userPassword;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setUserInfo(String username1, String userPassword1, String address1, int phoneNumber1){
        userName = username1;
        this.userPassword = userPassword1;
        this.address = address1;
        this.phoneNumber = phoneNumber1;
    }

    public void setUsername(String username1){
        userName = username1;
    }
    public void setUserPassword(String passowrd){
        this.userPassword = passowrd;
    }
    public void setaddress(String address){
        this.address = address;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getUsername(){
        return userName;
    }

    public String getpassword(){
        return userPassword;
    }

    public String getaddress(){
        return address;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }

    public String userInfo(){
        String bookFormat = String.format("%s : %s : %s : %s : %d", userName, userPassword, address, phoneNumber);
        return bookFormat;
    }
    
    
}
