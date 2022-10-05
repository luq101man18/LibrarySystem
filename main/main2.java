package main;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
// move login and logout out 
// create user objects
// file for each user to store books then read it when it's created
// edit borrow function
// 

public class main2 {
    static library LibraryMain = new library();
    static user person = new user();  
    user User;
    String userFile = "user.txt";
    String[] usersBookFile  = {"luqman.txt", "suliman.txt"};
    int flag = 0;

    // ------------------------------------------- login ----------------------------
    
    void getUsernameAndPassword(){

        String username = "";
        String userpassword = "";
        Scanner getUserInfo = new Scanner(System.in);

        try{
            System.out.println("\nEnter your username and password\nEnter username: \n");
            if(getUserInfo.hasNextLine()){
                username = getUserInfo.nextLine();
            }
            
            System.out.println("\nEnter password: \n");
            if(getUserInfo.hasNextLine()){
                userpassword = getUserInfo.nextLine();
            }
            
            login(username, userpassword);

        }catch(NoSuchElementException elementException){
            System.err.println( "Invalid getUserInfo. Please try again." );
            getUserInfo.next();
        }

       
    }
    String findUserBookFile(String username){
        String userBookFile = "";
        try{
            //String userBookFile = "";

            // search for the user book file
            
            for(int counter = 0; counter < usersBookFile.length; counter++){
                if (usersBookFile[counter].toString().equals(username)){
                    userBookFile = username;
                    break;
                }
            }
        }catch(Exception e){

        }
        return userBookFile;
    }
    void checkPreviousBorrrowedBooksByUser(String username){
        
        try{
            // open the user book file and restore the data

            File userBooksFile = new File(findUserBookFile(username));
            BufferedReader buff = new BufferedReader(new FileReader(userBooksFile));
            String currentLine= "";

            // check for other method with less time complexity, now this is n^2

            while((currentLine = buff.readLine())!= null){
                String id = currentLine;
                for(int counter3 = 0; counter3<LibraryMain.listBooks.length; counter3++){
                    String idAsString = String.valueOf(LibraryMain.listBooks[counter3].id);
    
                    if (idAsString.equals(id)){
                        LibraryMain.borrowedListBooks.put(idAsString, LibraryMain.listBooks[counter3]);
                    }
                }
            }
            buff.close();
            System.out.println("data restored for current user");
        }catch(Exception e){

        }
    }
    void login(String name, String password){

        try{
            File fileUser = new File(userFile);
            BufferedReader buffR = new BufferedReader(new FileReader(fileUser));
            String currentLine;

            while((currentLine = buffR.readLine()) != null){
                currentLine.split(" : ");
                String[] arr = currentLine.split(" : ");
                String userName = arr[0];
                String userPassword = arr[1];
                String address = arr[2];
                int phoneNumber = Integer.parseInt(arr[3]);
                
                if (name.equals(userName)){

                    if (password.equals(userPassword)){

                        System.out.println("\nUSER LOGGED-IN SUCCESSFULLY\n");
                        
                        User = new user(userName, userPassword, address, phoneNumber);
                
                        checkPreviousBorrrowedBooksByUser(User.userName+".txt");
                        flag = 1;
                        
                        break;
                    }
                }
                
            }
            if(flag == 0){
                System.out.println("\nUSER DOESN'T EXIST\n");
                getUsernameAndPassword();
            }
            
            buffR.close();

        }catch(Exception e){}
    }

    //-------------------------------------------- logout ----------------------------------------

    public void logout(){

        try{
            File fileuserBookToWrite = new File(findUserBookFile(User.userName+".txt"));
            FileWriter fr = new FileWriter(fileuserBookToWrite);
            BufferedWriter BuffR = new BufferedWriter(fr);
            PrintWriter PrintW = new PrintWriter(BuffR);
            
            String[] IDs = LibraryMain.setOfKeysFromBorrwoedListBooks.toArray(new String[LibraryMain.setOfKeysFromBorrwoedListBooks.size()]);
            for(int counter = 0; counter < IDs.length; counter++){
                PrintW.println(IDs[counter]);
            }
            PrintW.close();
            LibraryMain.borrowedListBooks.clear();
            getUsernameAndPassword();

        }catch(Exception e){

        }
    }
   
    // ----------------------------------- main -------------------------------------

    public static void main(String args[]){
        

        main2 mainLi = new main2(); 
        Scanner input = new Scanner(System.in);
        LibraryMain.readFileToListBooks();
        mainLi.getUsernameAndPassword();


        while(true){
            System.out.println("\n\n1) add a book \n2) list all the books \n3) borrow book \n4) return book \n5) logout \n6) EXIT\n\n");

            int option= 0;
            try{
                if(input.hasNextLine()){
                    option = input.nextInt();
                }
                
    
                System.out.println("\n");
                
                if (option == 1){
    
                    LibraryMain.addBook(); //  make it just eligble to the librarai
                    
                }
                else if (option == 2){
                    
                    LibraryMain.listAllTheBooks();
            

                }
    
                else if(option == 3){
    
                    LibraryMain.borrowBook();
    
                }
                else if(option ==4){
    
                    LibraryMain.returnBook(); 

                }
                else if(option == 5){
                    mainLi.logout();
                    
                }else if(option == 6){
    
                    System.exit(0);
                }
                else{
    
                    System.out.printf("the option 1-4 if you want you entered: %d, if you want to termiante the program press 0 otherwise press 1: ", option);
                    int executer = 1;
                    if(input.hasNextLine()){
                        executer = input.nextInt();
                    }
    
                    if (executer == 0){
    
                        System.exit(1);
                        
                    }
                    else{
    
                       
    
                    }
                } 
    
            }
            catch(NoSuchElementException elementException){
                System.err.print( "Invalid input. Please try again." );
                if(input.hasNextLine()){
                    option = input.nextInt();
                }
            }
    
        }


    
    }
        

}
