package main;

import java.util.Scanner;

public class book {
    String bookName;
    int id;
    String author;
    String catagory;
    int availability;

    Scanner input  = new Scanner(System.in);


    book(){
        this.bookName = null;
        this.author = null;
        this.catagory = null;
        this.id = 0;
        this.availability = 0;
    }

    book(String bookName, String author, String catagory, int id){
        this.bookName = bookName;
        this.author = author;
        this.catagory = catagory;
        this.id = id;
    }

    public void setBookInfo(String bookName1, String author1, String catagor1, int id1, int availability){
        this.bookName = bookName1;
        this.author = author1;
        this.catagory = catagor1;
        this.id = id1;
        this.availability = availability;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public void setBookName(String bookName){
        this.bookName = bookName;
    }
    public void setBookCatagory(String catagory){
        this.catagory = catagory;
    }
    public void setBookid(int id){
        this.id = id;
    }
    public void setBookAvailability(int availability){
        this.availability = availability;
    }
    public String getBookName(){
        return bookName;
    }

    public String getAuthor(){
        return author;
    }

    public String getCatagory(){
        return catagory;
    }
    public int getId(){
        return id;
    }
    public int availability(){
        return availability;
    }

    public String bookInfo(){
        String bookFormat = String.format("%s : %s : %s : %d", bookName, author, catagory, id);
        return bookFormat;
    }

    @Override
    public String toString(){
        return bookInfo();
    }

    public void setBookId(){

        try {
            System.out.println("Enter the book id:\n");
            if(input.hasNextLine()){
                id = Integer.parseInt(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setBookTitle(){
       try {
            System.out.println("Enter the book title:\n");
            if(input.hasNextLine()){
                bookName = input.next();
                input.nextLine();
            }
       } catch (Exception e) {
        e.printStackTrace();
       }
    }
    public void setBookAuthor(){
        try {
            System.out.println("Enter the book author:\n");
            if(input.hasNextLine()){
                author = input.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setBookCatagory(){
        try {
            System.out.println("Enter the book catagory:\n");
            if(input.hasNextLine()){
                catagory = input.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
