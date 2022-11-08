package main;

import java.sql.ResultSet;
import java.util.Scanner;
public class user extends encryption {

    String userFile = "user.txt";
    String  userName; 
    String userPassword;
    String address;
    int id;
    int phoneNumber;
    Scanner input = new Scanner(System.in);
    static int logoutFlag;
    int flag;
 

    user(){
        userName = null;
        this.userPassword = null;
        this.address = null;
        this.phoneNumber = 0;
    }

    user(int id, String username, String userPassword, String address, int phoneNumber){
        this.id = id;
        userName = username;
        this.userPassword = userPassword;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setUserInfo(int id1, String username1, String userPassword1, String address1, int phoneNumber1){
        userName = username1;
        this.userPassword = userPassword1;
        this.address = address1;
        this.phoneNumber = phoneNumber1;
        this.id = id1;
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
    public int getID(){
        return id;
    }

    public String userInfo(){
     //   String userFormat = String.format("%d : %s : %s : %s : %s : %d", id, userName, userPassword, address, phoneNumber);
        String user = id + " : " + userName + " : " + userPassword + " : " + address + " : " + phoneNumber;
        return user;

    }
    public void setUsernameForRegistration(){
        try {
            System.out.println("Enter username: \n");
            if(input.hasNextLine()){
                userName = input.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPasswordForRegistration(){
        try {
            System.out.println("\nEnter password: \n");
            if(input.hasNextLine()){
                String tempPassword = input.nextLine();
                userPassword = Hashing(tempPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAddressForRegistration(){
        try{
            System.out.println("\nEnter address: \n");
            if(input.hasNextLine()){
                address = input.nextLine();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPhoneNumberForRegistration(){
        try{    
            System.out.println("\nEnter phone number: \n");
            if(input.hasNextLine()){
                phoneNumber = Integer.parseInt(input.nextLine()) ;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setUserInfo(ResultSet resultSet){
        try {
                
                userName = resultSet.getString("username");
                id = resultSet.getInt("id_user");
                userPassword = resultSet.getString("password");
                address = resultSet.getString("address");
                phoneNumber = resultSet.getInt("phoneNumber");

            
            System.out.println("Hello " + getUsername());
        } catch (Exception e) {
            e.getMessage();
        }
    }
   

}   
