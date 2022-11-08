package main;
import java.util.Scanner;

import java.util.NoSuchElementException;

public class main2 extends dataBaseFunctions{
    static library LibraryMain = new library();
    static user person = new user();  
    encryption encrypt = new encryption();
    String userFile = "user.txt";
    String[] usersBookFile  = {"luqman.txt", "suliman.txt"};
    int flag = 0;

    public void chooseLoginMethod(){
        connection = CreateConnection();
        Scanner option = new Scanner(System.in);
        int mehtod = 0;
        try {
            System.out.println(
                "\nDo want to create new account?\n"+
                "\npress 1 if yes, 2 if you already have an account\n"
            );
            if(option.hasNextLine()) mehtod = Integer.parseInt(option.nextLine());
    
            if(mehtod == 1) register();
            else if(mehtod == 2) getUsernameAndPassword();

        }catch(NoSuchElementException elementException){
            System.err.println( "Invalid option. Please try again." );
            option.next();
        }
    }
    
    boolean getUsernameAndPassword(){
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
                String tempPass = getUserInfo.nextLine();
                userpassword = encrypt.Hashing(tempPass);
            }
            Login(username, userpassword);
            return true;

        }catch(NoSuchElementException elementException){
            System.err.println( "Invalid getUserInfo. Please try again." );
            getUserInfo.next();
        }
        return false;
    }
    
    public void Login(String name, String password){
        try {
            resultSet =  createPrepareStatement("Select * from user where username = ? and password = ?", name, password);
            if(resultSet.next()){
                do{
                    person = new user(resultSet.getInt("id_user"), 
                    resultSet.getString("username"), 
                    resultSet.getString("password"), 
                    resultSet.getString("address"),
                    resultSet.getInt("phoneNumber"));
                } while (resultSet.next());
                LibraryMain.userID = person.getID();

                System.out.println("\nwelcome "+ person.getUsername());
            }
            else{
                System.out.println("\nUser isn't available!\nPlease try again\n");
                getUsernameAndPassword();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void Logout(){
        chooseLoginMethod();
        LibraryMain.chooseLibraryStore();
    }
    
    public void register(){
        try {
            System.out.println("\nEnter your information\n");
            person.setUsernameForRegistration();
            person.setPasswordForRegistration();
            person.setAddressForRegistration();
            person.setPhoneNumberForRegistration();

            if((CheckIfUserAlreadyExists(person.getUsername()))) chooseLoginMethod();
            else{
                String query = "insert into user (username, password, address, phoneNumber) values (?,?,?,?)";
                createPrepareStatementtoUpdate(query,person.userName, person.userPassword, person.address, person.phoneNumber);
                Login(person.getUsername(), person.getpassword());
            }
            
        } catch (Exception e) {
            e.getMessage();
        }

    }
    

    public static void main(String args[]){

        main2 mainLi = new main2(); 
        Scanner input = new Scanner(System.in);
        
        mainLi.chooseLoginMethod();
        LibraryMain.chooseLibraryStore();

        while(true){
            int option= 0;
            System.out.println("\n\n1) add a book \n2) list all the books \n3) borrow book \n4) return book \n"+
                                "5) list latest returned and borrowed books \n6) Logout \n"+
                                    "7) Change library \n8) EXIT\n\n");
            
            try{
                if(input.hasNextLine()){
                    option = input.nextInt();
                }
                
    
                System.out.println("\n");
                
                if (option == 1){
    
                    LibraryMain.getNewBookInfo();         
                    
                }
                else if (option == 2){
                    
                    LibraryMain.listAllTheBooks();
            
                }
    
                else if(option == 3){
    
                    LibraryMain.BorrwoBook();
    
                }
                else if(option ==4){
    
                    LibraryMain.returnBook();

                }
                else if(option == 5){
                    LibraryMain.callListBorrowedBooks();
                    
                }
                
                else if(option == 6){
                    mainLi.Logout();

                }
                else if(option == 7){
                    LibraryMain.chooseLibraryStore();

                }
                else if(option == 8){
                    input.close();
                    LibraryMain.closeConnection();
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
            }catch(Exception e){
                e.getMessage();
            }
    
        }


    
    }
        

}
