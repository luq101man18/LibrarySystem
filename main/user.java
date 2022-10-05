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
   // make a function that keep track of multibale borrowed books to multibale users in java
   // hashMap <string id, Stirng username> 

   
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
   // int IDs;

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
/* 
    public void login(String name, String password){

        try{
            File file = new File(userFile);
            BufferedReader buffR = new BufferedReader(new FileReader(file));
            String currentLine;

            while((currentLine = buffR.readLine()) != null){
                currentLine.split(" : ");
                String[] arr = currentLine.split(" : ");
                userName = arr[0];
                userPassword = arr[1];
                address = arr[2];
                phoneNumber = Integer.parseInt(arr[3]);
                
                if (name.equals(userName)){

                    if (password.equals(userPassword)){

                        
                        setUserInfo(userName, userPassword, address, phoneNumber);
                        System.out.println("\nUSER LOGGED-IN SUCCESSFULLY\n");
                        flag = 1;
                        break;
                    }
                }
                
            }
            /*if (flag == 1){
                System.out.println("\nUSER LOGGED-IN SUCCESSFULLY\n");
                flag = 0;
            
            if(flag == 0){
                System.out.println("\nUSER DOESN'T EXIST\n");
                getUsernameAndPassword();
            }
            
            buffR.close();

        }catch(Exception e){}
    }
    public void logout(){

        // convert book to string
        String bookIDs  = String.valueOf(library.borrowedListBooks.keySet());
        // add the list
        //A map cannot contain duplicate keys; each key can map to at most one value.
        //List<String> values = new ArrayList<String>();
        values.add(getUsername());
        values.add(bookIDs);
        // borrowedBooksByUser.put(getUsername(),values);  //  get name printting null
        System.out.println(values);
        // clear all the borrowed books for the current user
        library.borrowedListBooks.clear();
        getUsernameAndPassword();
        //library.libraryMainOptions();
    }
    public void getUsernameAndPassword(){

        String name2 = "";
        String password2 = "";
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("\nEnter your username and password\nEnter username: \n");
            if(input.hasNextLine()){
                name2 = input.nextLine();
            }
            
            System.out.println("\nEnter password: \n");
            if(input.hasNextLine()){
                password2 = input.nextLine();
            }
            
            login(name2, password2);

        }catch(NoSuchElementException elementException){
            System.err.println( "Invalid input. Please try again." );
            input.next();
        }

       
    }

*/
    
    
}
