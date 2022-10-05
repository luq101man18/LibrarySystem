package main;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;
import java.util.Set;
import java.util.NoSuchElementException;
public class library {
    
   // user person = new user(); // for the logout function
    book[]  listBooks;


    static String file = "book.txt";
    String bookName = "";
    String author = "";
    String catagory = "";
    String id = "";
    int availability;
    Map <String, book>  borrowedListBooks = new HashMap<>();
    Set <String> setOfKeysFromBorrwoedListBooks = borrowedListBooks.keySet();
    book newBook =  new book(bookName, author, catagory, id, availability);
    static File fileBook = new File(file);
    int flag;
    int  numberLines;
    int Bflag= 0;

    // was added to get the path of the file to count the lines
    Path filePath = Paths.get(file);
    
    public  void readFileToListBooks(){

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
            String bookName1;
            String author1;
            String catagory1;
            String id1;
            int availability1 = 0;
            int counter1 = 0;
            String arr[];

            while((currentLine = buffR.readLine()) != null) {
                
                arr = currentLine.split(" : ");
                bookName1 = arr[0];
                author1 = arr[1];
                catagory1 = arr[2];
                id1 = arr[3];
                availability1 = Integer.parseInt(arr[4]);
            

                newBook.setBookInfo(bookName1, author1, catagory1, id1, availability1);
             
                listBooks[counter1] = new book(newBook.bookName,newBook.author, newBook.catagory, newBook.id, newBook.availability);
                counter1++;
            }

            System.out.println();

            buffR.close();
        }catch(Exception e){
            System.out.println("file is empty");
        }
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
            //input.close();
            printW.close();

            System.out.println("\n-----------------------\n");
            System.out.println("\nBOOK WAS ADDED\n");
            System.out.println("\n-----------------------\n");
            System.out.println("\nreturn to main\n");
          //  libraryMainOptions();
        }catch(Exception e){}
    };
    public void listAllTheBooks(){
        try{
        for(int counter2 = 0; counter2 < listBooks.length; counter2++){
            
            String book = String.valueOf(listBooks[counter2]);
            System.out.println(book);
        }
        }catch(Exception e){}
        
    }
    public void borrowBook(){
        String bookName = "";
        bookName = gettingBookNameToBorrow(bookName);

        for(int counter3 = 0; counter3<listBooks.length; counter3++){

            String bookNameAsString = String.valueOf(listBooks[counter3].bookName);
            if (bookNameAsString.equals(bookName)){
                if(listBooks[counter3].availability > 0){
                    // check if the book is borrowed
                    checkBorrowedBook(bookName, listBooks[counter3].id);
                    // add book to the borrowed List 
                    borrowedListBooks.put(listBooks[counter3].id, listBooks[counter3]);
                    // keep track of the borrowed book
                    listBooks[counter3].availability--;
                    System.out.println("\nBook is borrowed\n");
                    Bflag = 1;
                }else{
                    System.out.printf("\nAll the %s books are borrowed now, sorry for the inconvenience\n", listBooks[counter3].bookName);
                }
            }   
        }
        if (Bflag == 0){
            System.out.println("\nbook isn't available\n");
            borrowBook();
        }

        Bflag = 0;
    }

    public void returnBook(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nReturn book\n");
        System.out.println("\nwrite the name of the book down\n");

        String nameOfBook = input.next();
        int Rflag = 0;
        for(int counter4 = 0; counter4 < listBooks.length; counter4++){
            String bookL = String.valueOf(listBooks[counter4].bookName);
            if (bookL.equals(nameOfBook)){
                borrowedListBooks.remove(bookL);
             //   user.borrowedBooksByUser.remove(person.getUsername());
                listBooks[counter4].availability++;
                System.out.println("\nBook is returned \n");
                Rflag = 1;

            }
        }
        if(Rflag == 0){
            System.out.println("\nBook wasn't found\n");
        }

    }
    public void checkBorrowedBook(String bookName, String id){
     
        
        if(borrowedListBooks.containsKey(id))
        {
            System.out.println("\n----- YOU ALREADY BORROWED THE BOOK! -----\n");
            borrowBook();
        }
         /*if(id ==  user.borrowedBooksByUser.get(user.getUsername()))
        {
            System.out.println("\n----- YOU ALREADY BORROWED THE BOOK! Different user-----\n");
            borrowBook();
           // libraryMainOptions();

        }*/
    
    }
    
    // To print something different when you call System.out.println(myObject), you must override the toString() method in your own class.

    public String gettingBookNameToBorrow(String bookName){
        Scanner input = new Scanner(System.in);
        System.out.println(("enter the name of the book: \n"));
        bookName = input.next();
        return bookName;
    }
}