package org.reviewdatabase.Details;

import exception.EmployeeNotFoundException;
import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionCreate {
    static Connection connection;
     static Logger logger= LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("Database");
    static ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    public static Connection createConnection( )  {
        try {
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (EmployeeNotFoundException | SQLException exception ) {
           logger.error(resourceBundle1.getString("server.error"));
        }
        return connection;
    }

}
