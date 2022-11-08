package main;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;





public class library extends libraryStore {
    book currentBook = new book();
    user person = new user();
    boolean returned;
    int userID;

    
    public void listAllTheBooks(){
    
        try {

            resultSet = createPrepareStatement("select name, Author, bookID from book"+
                                " inner join book_libaray"+
                                " on book.bookID = book_libaray.id_book"+
                                " where library_id = ?",id_store);
            resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            printData(resultSet, resultSetMetaData, numberOfColumns);
            
        } catch (Exception e) {
            e.getMessage();
        }
        
    }

    public void BorrwoBook(){
        boolean bookCondition = false;

        try {
            System.out.println("choose book from the list: \n");
            int option  = input.nextInt();
            setInfoOfBook(option);
            checkingBook( userID, id_store, currentBook.id, bookCondition);

        } catch (Exception e) {
        }
        
        
    }

    public void setInfoOfBook(int book_id){
        try {

            preparedStatement = connection.prepareStatement("Select * from book where bookID = ?");
            preparedStatement.setInt(1, book_id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                currentBook = new book(
                resultSet.getString("name"),
                resultSet.getString("Author"),
                resultSet.getString("catagory"),
                resultSet.getInt("bookID")
                );
            }
            System.out.println(currentBook.bookInfo());
        }catch (Exception e) {
            e.getMessage();
        }
       
    }

    public String getcurrentDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String castReturn(boolean returned){
        if(returned){
            String returnedWord = "Returned";
            return returnedWord;
        }else{
            String borrowWord = "Borrowed";
            return borrowWord;
        }
    }
    public void checkingBook(int userID, int libraryID, int bookID, boolean bookCondition){
        returned = bookCondition;
        boolean pass = false;
        if(checkBookStatusWithCurrentUser(returned)){
            if(checkIfBookIsAvailable(bookID)){ 
                if(checkIfTheBookExistsInTheCurrentLibrary(libraryID, bookID)){
                    pass = createPrepareStatement("insert into borrow (user_id, book_id, library_id, checkout, returned) values(?,?,?,?,?)"
                                    , userID, bookID, libraryID,
                                    getcurrentDateAndTime(), castReturn(returned));
                
                    if(pass) updateStockType(bookCondition);
                }
            }else{
            printBookUnavailableMessage();
            }
        }
    }

    public void updateStockType(boolean updateType){
        if (updateType) {
            updateStock(1);
        } else {
            updateStock(0);
        }
    }
    
    public void updateStock(int CheckoutOrReturn){
        String checkout = "stock -1";
        String returnBook = "stock +1";
        if(CheckoutOrReturn == 0){
           if (! createPrepareStatementtoUpdate("update book_libaray "
                                                +"set stock = "+checkout
                                                +" where id_book = ? and library_id = ?"
                                                , currentBook.id, id_store))
                                                System.out.println("\nStock wasn't updated\n");
        }else{
           if(! createPrepareStatementtoUpdate("update book_libaray "
                                                +"set stock = "+returnBook
                                                +" where id_book = ? and library_id = ?"
                                                , currentBook.id, id_store))
                                                System.out.println("\nStock wasn't updated\n");
        }
    }

    public void setReturnBookInfo(int userID, int libraryID, int bookID){
        boolean bookCondition = true;
        try {
            resultSet =  createPrepareStatement("select * from borrow where user_id = ? and book_id = ?", userID, bookID);
            if(resultSet.next()){
                do{
                    userID = resultSet.getInt("user_id");
                    bookID = resultSet.getInt("book_id");
                    libraryID = resultSet.getInt("library_id");
                    
                }while(resultSet.next());
                checkingBook(userID, libraryID, bookID, bookCondition);
            }else{
                System.out.println("Couldn't find book\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void returnBook(){
        boolean bookCondition = true;
        try {
            System.out.println("return id: \n");
            int option  = input.nextInt();
            setInfoOfBook(option);
            checkingBook(userID,id_store, currentBook.id, bookCondition);
        } catch (Exception e) {

        }
    }
    
    public void printBreakLine(){
        System.out.println("------");
    }
 
    public void getNewBookInfo(){
        
        currentBook.setBookId();
        printBreakLine();
        currentBook.setBookTitle();
        printBreakLine();
        currentBook.setBookAuthor();
        printBreakLine();
        currentBook.setBookCatagory();
        addBookQuery();
    }
    
    public void addBookQuery(){
        createPrepareStatementtoUpdate(
            "insert into book values (?,?,?,?)"
        , currentBook.id, currentBook.bookName,
         currentBook.author, currentBook.catagory);
    }

    public boolean checkIfBookIsAvailable(int bookID){
        int stock = 0;
        try {
            createPrepareStatement("select stock from book_libaray where id_book = ?", bookID);
            while(resultSet.next()){
                stock = resultSet.getInt("stock");
            }
            if (stock > 0 || returned) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean checkIfTheBookExistsInTheCurrentLibrary(int libraryID, int bookID){
        try {
            String query = "select count(*) from book"+
                            " inner join book_libaray"+
                            " on book.bookID = book_libaray.id_book"+
                            " where bookID = ? and library_id = ?";
    
            if(getNumberOfRecordsOnTheQuery(createPrepareStatement(query, libraryID, bookID)) == 1){
                return true;  
            }
            else{
                System.out.println("Book isn't available! \nYou could check other libraries");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void printBookUnavailableMessage(){
        System.out.println("Book in unavailable right now\n");
    }

    public void callListBorrowedBooks(){
        listAllTheBorrowedBooks(userID, id_store);
    }
    public void listAllTheBorrowedBooks(int user_id, int library_id){
        String query = "select name, book_id as id, max(returned) as Status from borrow"+
                        " inner join book on borrow.book_id = book.bookID"+
                        " where user_id = ? and library_id = ?"+
                        " group by book_id";
        
        getDataToPrint(query, user_id, library_id);
    }

    public boolean checkBookStatusWithCurrentUser(boolean status){
        if (!status)
            return checkIfBookAlreadyBorrowed(currentBook.id, userID);
        else   
            return checkIfBookAlreadyReturned(currentBook.id, userID);  
    }

    public boolean checkIfBookAlreadyBorrowed(int BookID, int userID){
        String bookStatus = "";
        String query = "select max(returned), max(checkout) from borrow where user_id = ? and book_id = ?";
        try {
            while(createPrepareStatement(query, BookID, userID).next()){
                
                bookStatus = resultSet.getString(1);
                if("Borrowed".equals(bookStatus)){
                    System.out.println("\nYou've already borrowed the book!\n");
                    return false;
                }else   
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkIfBookAlreadyReturned(int BookID, int userID){
        String bookStatus = "";
        String query = "select max(returned), max(checkout) from borrow where user_id = ? and book_id = ?";
        try {
            if(createPrepareStatement(query, BookID, userID).next()){
                bookStatus = resultSet.getString(1);
                if("Returned".equals(bookStatus)){
                    System.out.println("\nYou've already returned the book!\n");
                    return false;
                }else   
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    
}