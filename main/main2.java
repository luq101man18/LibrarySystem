package main;
import java.util.Scanner;
import java.util.NoSuchElementException;
public class main2 {
    public static void main(String args[]){
        
        library LibraryMain = new library();
        user person = new user();  
        Scanner input = new Scanner(System.in);
        LibraryMain.readFileToListBooks();
        person.getUsernameAndPassword();

        while(true){
            System.out.println("\n\n1) add a book \n2) list all the books \n3) borrow book \n4) return book \n5) logout \n6) EXIT\n\n");

            int option= 0;
            try{
                if(input.hasNextLine()){
                    option = input.nextInt();
                }
                
    
                System.out.println("\n");
                
                if (option == 1){
    
                    LibraryMain.addBook(); //  make it just eligble to the librarain
    
                    
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
                    person.logout();
                    
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
