package main;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


class dataBaseConnection {
    public final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    protected final String DataBase_username = "root";
    protected final String DataBase_passsword = "luq101-007L";
    public final String DataBase_url = "jdbc:mysql://localhost/library";
    
    static Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    ResultSetMetaData resultSetMetaData  = null;
    PreparedStatement preparedStatement = null;

}
