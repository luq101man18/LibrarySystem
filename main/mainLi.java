package main;
import java.util.Scanner;
public class mainLi{
    /* 
     public static void libraryMainOptions(){
       // library newlli= new library();
        user person = new user();
        System.out.println("\n\n1) add a book \n2) list all the books \n3) borrow book \n4) return book \n5) logout \n6) EXIT\n\n");
        Scanner input = new Scanner(System.in);

        int option  = input.nextInt();

        System.out.println("\n");
        
        if (option == 1){

            library.addBook(); //  make it just eligble to the librarain

            libraryMainOptions();
        }
        else if (option == 2){
            
            library.listAllTheBooks();
     
            libraryMainOptions();
        }

        else if(option == 3){

            library.borrowBook();

            libraryMainOptions();
        }
        else if(option ==4){

            library.returnBook(); 

            libraryMainOptions();

        }
        else if(option == 5){

            person.logout();

        }else if(option == 6){

            System.exit(0);
        }
        else{

            System.out.printf("the option 1-4 if you want you entered: %d, if you want to termiante the program press 0 otherwise press 1: ", option);
            int executer = input.nextInt();

            if (executer == 0){

                System.exit(1);
                
            }
            else{

                libraryMainOptions();

            }
        }
        input.close();
     } */
    
    public static void main(String[] args) {
        
        library newlli= new library();
        user person = new user();  

        newlli.readFileToListBooks();
        System.out.println("\nWelcome to the library\n");

        person.getUsernameAndPassword();

        System.out.println("\nPlease choose from the follwoing:\n");
                      
        newlli.libraryMainOptions();

        person.logout();
    }

}