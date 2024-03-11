package org.example.middleware;

import org.example.entity.Account;
import org.example.exceptions.WithdrawException;
import org.example.remotes.UserRepository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDatabaseRepository implements UserRepository {
    private Connection connection;
    private List<Account> accountList = new ArrayList<>();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDatabaseRepository(Connection connection) {
        try {
            this.connection = connection;
            FileHandler fileHandler = new FileHandler("accounts-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

//    @Override
//    public List<Account> findALL() {
//        try{
//            String query="select * from my_bank";
//            preparedStatement=connection.prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//            accountList=new ArrayList<>();
//            Account account = null;
//            while(resultSet.next()){
//                account=new Account();
//                account.setAccountNumber(resultSet.getLong(1));
//                account.setCustomerId(resultSet.getLong(2));
//                account.setEmail(resultSet.getString(3));
//                account.setName(resultSet.getString(4));
//                account.setBalance(resultSet.getDouble(5));
//                account.setUsername(resultSet.getString(6));
//                account.setPassword(resultSet.getString(7));
//                accountList.add(account);
//            }
//        }
//        catch (SQLException sqlException){
//            System.out.println(sqlException);
//        }
//        return accountList;
//    }

    @Override
    public boolean verifyPassword(String username, String password) {
        try {
            String query = "select username,password from my_bank where username=? and password=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (!(resultSet.getString(1).equals(username) && resultSet.getString(2).equals(password)))
                    throw new WithdrawException();
                else
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // temp
    }

    @Override
    public void withdraw(String username, String password, double withdrawAmount) {
        try {
            if (verifyPassword(username, password)) {
                String query = "select balance from my_bank where username=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Double currentBalance = resultSet.getDouble(1);
                    if (currentBalance - withdrawAmount < 0) {
                        //low balance
                    } else {
                        String query1 = "update my_bank set balance=? where username=?";
                        preparedStatement = connection.prepareStatement(query1);
                        preparedStatement.setDouble(1, currentBalance - withdrawAmount);
                        preparedStatement.setString(2, username);

                        preparedStatement.executeUpdate();
                    }

                }
            }
            System.out.println("Balance is="+balance(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double balance(String username) {
        try {
            String query = "select balance from my_bank where username=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public void addTransactions(Account account) {
        try {
            String query = new String();
            query = "insert into my_bank values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, account.getAccountNumber());
            preparedStatement.setLong(2, account.getCustomerId());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getName());
            preparedStatement.setDouble(5, account.getBalance());
            preparedStatement.setString(6, account.getUsername());
            preparedStatement.setString(7, account.getPassword());

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                logger.log(Level.INFO, resourceBundle.getString("record.push.ok"));
                System.out.println(resourceBundle.getString("record.push.ok"));
            } else {
                logger.log(Level.INFO, resourceBundle.getString("record.push.fail"));
                System.out.println(resourceBundle.getString("record.push.fail"));
            }
        } catch (SQLException sqlException) {
            System.out.println(resourceBundle.getString("card.not.ok"));
        }

    }
}
