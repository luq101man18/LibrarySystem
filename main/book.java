package main;

public class book {
    String bookName;
    String id;
    String author;
    String catagory;
    int availability;


    book(){
        this.bookName = null;
        this.author = null;
        this.catagory = null;
        this.id = null;
        this.availability = 0;
    }

    book(String bookName, String author, String catagory, String id, int availability){
        this.bookName = bookName;
        this.author = author;
        this.catagory = catagory;
        this.id = id;
        this.availability = availability;
    }

    public void setBookInfo(String bookName1, String author1, String catagor1, String id1, int availability){
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
    public void setBookid(String id){
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
    public String getId(){
        return id;
    }
    public int availability(){
        return availability;
    }

    public String bookInfo(){
        String bookFormat = String.format("%s : %s : %s : %s : %d", bookName, author, catagory, id, availability);
        return bookFormat;
    }

    @Override
    public String toString(){
        return bookInfo();
    }

    
}
