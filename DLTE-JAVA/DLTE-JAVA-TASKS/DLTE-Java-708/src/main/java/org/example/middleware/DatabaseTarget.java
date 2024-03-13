package org.example.middleware;

import oracle.jdbc.OracleDriver;
import org.example.remotes.StorageTarget;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseTarget implements StorageTarget {

    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    private Connection connection;
    public DatabaseTarget(){
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
    }


    @Override
    public UserDatabaseRepository getUserRepository() {
        return new UserDatabaseRepository(connection);
    }
}
