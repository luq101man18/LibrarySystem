package main;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;





public class dataBaseFunctions extends dataBaseConnection {


    public ResultSet login(String name, String password, String query){
        try {
            
            preparedStatement = CreateConnection().prepareStatement(
                query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            return resultSet;

        }catch(Exception e){
            e.getMessage();
        }

        return null;
    };

    public Connection CreateConnection(){
        try {
            Class.forName(jdbc_driver);
            connection = DriverManager.getConnection(DataBase_url, DataBase_username, DataBase_passsword);
            return connection;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
    public void printData(ResultSet resultSet, ResultSetMetaData resultSetMetaData, int numberOfColumns){
        try {
            for (int i = 1; i <= numberOfColumns; i++) 
                System.out.printf("%-30s\t\t\t", resultSetMetaData.getColumnLabel(i));
            System.out.println('\n');    

            while(resultSet.next()){
                for (int i = 1; i <= numberOfColumns; i++) 
                    System.out.printf("%-30s\t\t\t", resultSet.getObject(i));
                System.out.println();   
                
            }
            System.out.println(); 
        } catch (Exception e) {
            e.getMessage();
        }
    }
   
    public void closeConnection(){
        try {
                
            resultSet.close();
            statement.close();
            connection.close(); 
        
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void getDataToPrint(String query){
        try {
            resultSet = createPrepareStatement(query);
            resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            printData(resultSet, resultSetMetaData, numberOfColumns);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public void getDataToPrint(String query, int userID, int Libraryid){
        try {
            resultSet = createPrepareStatement(query, Libraryid, userID);
            resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            printDataForSecondVersionGetData(resultSet, resultSetMetaData, numberOfColumns);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public void printDataForSecondVersionGetData(ResultSet resultSet, ResultSetMetaData resultSetMetaData, int numberOfColumns){
        try {
            for (int i = 1; i <= numberOfColumns; i++) 
                System.out.printf("%-30s\t\t\t", resultSetMetaData.getColumnLabel(i));
            System.out.println('\n');    

            while(resultSet.next()){
                int columnIndexOfReturned = 3;
                for (int i = 1; i <= numberOfColumns; i++){ 
                    String status = String.valueOf( resultSet.getObject(columnIndexOfReturned));
                    if("Returned".equals(status)){}
                    else
                        System.out.printf("%-30s\t\t\t", resultSet.getObject(i));
                }System.out.println();   
                
            }
            System.out.println(); 
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public Statement creatingStatement(){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return statement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet createPrepareStatement(String query){
            try {
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                return resultSet;
            }catch(Exception e){
                e.getMessage();
            }
            return null;
    }
    public ResultSet createPrepareStatement(String query, String value1){
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value1);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch(Exception e){
            e.getMessage();
        }
        return null;
    }
    public ResultSet createPrepareStatement(String query, String value1, String value2){
        try {
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, value1);
            preparedStatement.setString(2, value2);
            resultSet = preparedStatement.executeQuery();
            return resultSet;   
        }catch(Exception e){
            e.getMessage();
        }
        return null;
    }
    public ResultSet createPrepareStatement(String query, int value1){
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, value1);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch(Exception e){
            e.getMessage();
        }
        return null;
    }
    public ResultSet createPrepareStatement(String query, int value1, int vlaue2){
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, vlaue2);
            preparedStatement.setInt(2, value1);
            
            resultSet = preparedStatement.executeQuery();
    
            return resultSet;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean createPrepareStatementtoUpdate(String querytoExecute, int value1, int vlaue2){
        try {
            preparedStatement = connection.prepareStatement(querytoExecute);
            preparedStatement.setInt(1, value1);
            preparedStatement.setInt(2, vlaue2);
            
            int d = preparedStatement.executeUpdate();
            if (d > 0) {
                System.out.println("success");
                return true;
            } else {
                System.out.println("stuck somewhere");
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void createPrepareStatementtoUpdate(String querytoExecute, int value1, int vlaue2, int value3){
        try {
            preparedStatement = connection.prepareStatement(querytoExecute);
            preparedStatement.setInt(1, value1);
            preparedStatement.setInt(2, vlaue2);
            preparedStatement.setInt(3, value3);

            int d = preparedStatement.executeUpdate();
            if (d > 0) {
                System.out.println("success");
            } else {
                System.out.println("stuck somewhere");
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    public void createPrepareStatementtoUpdate(String querytoExecute, String value1, String vlaue2, String value3, int value4){
        try {
            preparedStatement = connection.prepareStatement(querytoExecute);
            preparedStatement.setString(1, value1);
            preparedStatement.setString(2, vlaue2);
            preparedStatement.setString(3, value3);
            preparedStatement.setInt(4, value4);
            
            int d = preparedStatement.executeUpdate();
            if (d > 0) {
                System.out.println("success");
            } else {
                System.out.println("stuck somewhere");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createPrepareStatementtoUpdate(String querytoExecute, int value1, int vlaue2, int value3, int value4){
        try {
            preparedStatement = connection.prepareStatement(querytoExecute);
            preparedStatement.setInt(1, value1);
            preparedStatement.setInt(2, vlaue2);
            preparedStatement.setInt(3, value3);
            preparedStatement.setInt(4, value4);
            int d = preparedStatement.executeUpdate();
            if (d > 0) {
                System.out.println("success");
            } else {
                System.out.println("stuck somewhere");
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    public void createPrepareStatementtoUpdate(String querytoExecute, int value1, String vlaue2, String value3, String value4){
        try {
            preparedStatement = connection.prepareStatement(querytoExecute);
            preparedStatement.setInt(1, value1);
            preparedStatement.setString(2, vlaue2);
            preparedStatement.setString(3, value3);
            preparedStatement.setString(4, value4);
            int d = preparedStatement.executeUpdate();
            if (d > 0) {
                System.out.println("success");
            } else {
                System.out.println("stuck somewhere");
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    public boolean createPrepareStatement(String query, int value1, int value2, int value3, String dateTime, String returned){
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, value1);
            preparedStatement.setInt(2, value2);
            preparedStatement.setInt(3, value3);
            preparedStatement.setString(4, dateTime);
            preparedStatement.setString(5, returned);
            
            int d = preparedStatement.executeUpdate();
            if (d > 0) {
                System.out.println("success");
                return true;
            } else {
                System.out.println("stuck somewhere");
                return false;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public int invalidInput(int value){
        Scanner input= new Scanner(System.in);

        System.err.print( "\nInvalid input. Please try again.\n" );
            if(input.hasNextLine()){
                value = input.nextInt();
            }
        return value;
    }

    public boolean CheckIfUserAlreadyExists(String value){
        try {
            String query = "select username from user where username = ?";
            resultSet = createPrepareStatement(query, value);

            if(resultSet == null){
                System.out.println("\nUsername already taken! try another one\n");
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNumberOfRecordsOnTheQuery(ResultSet resultSet){
        try {
            if(resultSet.next()){
                int data = resultSet.getInt(1);
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
