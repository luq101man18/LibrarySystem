package main;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.io.File;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;

public class library {
    book[] listBooks;
    String file = "book.txt";
    String bookName;
    String author;
    String catagory;
    String id;
    int availability;
    book newBook =  new book(bookName, author, catagory, id, availability);
    File fileBook = new File(file);
    int flag;
    int numberLines;
    Map<String, book> borrowedListBooks = new HashMap<>();
    // was added to get the path of the file to count the lines
    Path filePath = Paths.get(file);


    public void readFileToListBooks(){

        try{
            // why long ??
            long numOfLines = Files.lines(filePath).count();
            //casting
            int lines = (int)numOfLines-1;
            numberLines = lines;
            //assigning 
            listBooks = new book[lines];

            BufferedReader buffR = new BufferedReader(new FileReader(fileBook));
            String currentLine;
            String bookName = "";
            String author;
            String catagory;
            String id;
            int availability;
            int counter = 0;
            String arr[];

            while((currentLine = buffR.readLine()) != null) {
                
                arr = currentLine.split(" : ");
                bookName = arr[0];
                author = arr[1];
                catagory = arr[2];
                id = arr[3];
                availability = Integer.parseInt(arr[4]);
            

                newBook.setBookInfo(bookName, author, catagory, id, availability);
             
                listBooks[counter] = new book(newBook.bookName,newBook.author, newBook.catagory, newBook.id, newBook.availability);
                counter++;
            }

            System.out.println(listBooks.length);

        }catch(Exception e){}
    }
    public void libraryMain(){
        System.out.println("\nWelcome to the library\n");
        System.out.println("\nPlease choose from the follwoing:\n");
        System.out.println("1) add a book \n2) list all the books \n3) borrow book \n4) return book \n5) EXIT\n");
        libraryMainOptions();
    }
    public void addBook(){
        System.out.println("\n-----------------------\n");
        System.out.println("\nYou can add a book here\n");
        System.out.println("Please enter the book info as detailed- EXAMPLE( name : author : catagory : id : availability): \n");
        
        try{
            FileWriter bookFile = new FileWriter(fileBook, true);
            BufferedWriter BuffWrite = new BufferedWriter(bookFile);
            PrintWriter printW = new PrintWriter(BuffWrite);
            Scanner input  = new Scanner(System.in);
            String book = input.nextLine();

            printW.println(book);
            printW.flush();
            input.close();
            printW.close();

            System.out.println("\n-----------------------\n");
            System.out.println("\nBOOK WAS ADDED\n");
            System.out.println("\n-----------------------\n");
            System.out.println("\nreturn to main\n");
            libraryMain();
        }catch(Exception e){}

    };
    public void libraryMainOptions(){
        Scanner input = new Scanner(System.in);
       
        
        int option  = input.nextInt();
        System.out.println("\n");
        
        if (option == 1){

            addBook(); //  make it just eligble to the librarain

            libraryMain();
        }
        else if (option == 2){
            
            listAllTheBooks();
     
            libraryMain();
        }

        else if(option == 3){

            borrowBook();

            libraryMain();
        }
        else if(option ==4){

            returnBook(); 

            libraryMain();

        }
        else if(option == 5){
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
    }
    public void listAllTheBooks(){
        
        for(int counter2 = 0; counter2 < listBooks.length; counter2++){
            
            String book = String.valueOf(listBooks[counter2]);
            System.out.println(book);
        }
        
    }
    public void borrowBook(){
        Scanner input = new Scanner(System.in);
        int Bflag= 0;
        System.out.println(("enter the name of the book: \n"));
        String bookName = input.next();

        for(int counter3  = 0; counter3<listBooks.length; counter3++){
            String bookL = String.valueOf(listBooks[counter3].bookName);
            if (bookL.equals(bookName)){
                if(listBooks[counter3].availability > 0){
                    borrowedListBooks.put(bookName, listBooks[counter3]);
                    listBooks[counter3].availability--;
                    System.out.println("\nBook is borrowed\n");
                    Bflag = 1;
                }else{
                    System.out.printf("\nAll the %s books are borrowed now, sorry for the inconvenience\n", listBooks[counter3].bookName);
                    libraryMain();
                }
            }   
        }
        if (Bflag == 0){
            System.out.println("\nbook isn't available\n");
        }
    }
    public void lookForBook(){
        try{
        
        Scanner source  = new Scanner( new File(file));

        int counter2 = 0;
        while (source.hasNextLine()){

            String line = source.nextLine();

            //System.out.println(line);
            String[] arr = line.split(" : ");
            String bookName = arr[0];
            String author = arr[1];
            String catagory = arr[2];
            String id = arr[3];
            listBooks[counter2] = new book(bookName, author, catagory, id, availability);
            counter2++;
        }   
        
    }catch(Exception e){}



    }
    public void returnBook(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nReturn book\n");
        System.out.println("\nwrite the name of the book down\n");

        String nameOfBook = input.next();
        int Rflag = 0;
        for(int counter4 = 0; counter4 < numberLines; counter4++){
            String bookL = String.valueOf(listBooks[counter4].bookName);
            if (bookL.equals(nameOfBook)){
                borrowedListBooks.remove(bookL);
                listBooks[counter4].availability++;
                System.out.println("\nBook is returned \n");
                Rflag = 1;

            }
        }
        if(Rflag == 0){
            System.out.println("\nBook wasn't found\n");
        }

    }

    
    // To print something different when you call System.out.println(myObject), you must override the toString() method in your own class. Here's a simple example:
}