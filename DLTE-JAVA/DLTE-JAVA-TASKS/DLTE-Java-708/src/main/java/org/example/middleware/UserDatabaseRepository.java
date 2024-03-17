package org.example.middleware;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exceptions.WithdrawException;
import org.example.remotes.UserRepository;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.ResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
//import java.util.logging.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDatabaseRepository implements UserRepository {
    private Connection connection;
    private List<Account> accountList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

   // private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    static long transactionID = 10000000L;

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

    public List<Transaction> findALL() {
        ArrayList<Transaction> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions";
            preparedStatement=connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                transactionArrayList.add(new Transaction(resultSet.getDate(2),resultSet.getLong(1),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDouble(5)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }

    public List<Transaction> findAllUser(String user) {
        ArrayList<Transaction> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions where transaction_username=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                transactionArrayList.add(new Transaction(resultSet.getDate(2),resultSet.getLong(1),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDouble(5)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }

    public List<Transaction> findAllByDate(Date date,String user) {
        ArrayList<Transaction> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions where transaction_username=? and transaction_date=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setDate(2,date);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                transactionArrayList.add(new Transaction(resultSet.getDate(2),resultSet.getLong(1),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDouble(5)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }

    @Override
    public Account findUserByUsername(String username) {

            Account account=null;
            try {
                String query="Select account_number,customer_id,email,name,balance from my_bank where username=?";
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,username);
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    System.out.print("Hello "+username+"! Your account details are:\nAccount Number:"+resultSet.getLong(1));
                    System.out.print("\nCustomer ID:"+resultSet.getLong(2)+"\nEmail ID:"+resultSet.getString(3));
                    System.out.print("\nName:"+resultSet.getString(4)+"\nBank Balance:"+resultSet.getDouble(5));
                    account = new Account();
                    account.setAccountNumber(resultSet.getLong(1));
                    account.setCustomerId(resultSet.getLong(2));
                    account.setEmail(resultSet.getString(3));
                    account.setName(resultSet.getString(4));
                    account.setBalance(resultSet.getDouble(5));
                }else{
                    throw new WithdrawException();
                }
            }catch (WithdrawException e){
                System.out.println(resourceBundle.getString("username.not.found")+" "+username);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return account;

    }

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
            }else{
                throw new WithdrawException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (WithdrawException withdrawException) {
            for (int attempts = 2; attempts <= 3; ) { System.out.println(resourceBundle.getString("accounts.login.fail") + " Only " + (3 - attempts + 1) + " attempts left");
                logger.log(Level.WARNING, resourceBundle.getString("accounts.login.fail"));
                System.out.println(withdrawException);
               System.out.println(resourceBundle.getString("enter.name"));
                String user = scanner.next();
               System.out.println(resourceBundle.getString("enter.password"));
                String pin = scanner.next();
                String query = "select username,password from my_bank where username=? and password=?";
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, user);
                    preparedStatement.setString(2, pin);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                      //  System.out.println(resourceBundle.getString("accounts.login.success"));
                      // logger.log(Level.INFO, resourceBundle.getString("accounts.login.success"));
                        return true;
                    } else {
                        System.out.println(resourceBundle.getString("accounts.login.fail")+" Only "+(3-attempts)+" attempts left");;
                        attempts++;
                    }
                    if (attempts > 3) {
                        System.out.println(resourceBundle.getString("accounts.no.more.attempts"));
                        logger.log(Level.WARNING, resourceBundle.getString("accounts.no.more.attempts"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public double withdraw(String username, String password, double withdrawAmount) {
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
                        throw new WithdrawException();
                    } else {
                        String query1 = "update my_bank set balance=? where username=?";
                        preparedStatement = connection.prepareStatement(query1);
                        preparedStatement.setDouble(1, currentBalance - withdrawAmount);
                        preparedStatement.setString(2, username);
                        preparedStatement.executeUpdate();
                        String query2 = "insert into transactions values(?,?,?,?,?)";
                        String query3 = "SELECT MAX(transaction_id) FROM transactions";
                        preparedStatement = connection.prepareStatement(query3);
                        resultSet=preparedStatement.executeQuery();
                        if (resultSet.next())
                            transactionID = resultSet.getLong(1);
                        preparedStatement = connection.prepareStatement(query2);
                        preparedStatement.setLong(1, (transactionID+1));
                        preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                        preparedStatement.setString(3, username);
                        preparedStatement.setDouble(4, withdrawAmount);
                        preparedStatement.setDouble(5,(currentBalance - withdrawAmount));
                        preparedStatement.executeUpdate();
                    }

                }
            }
            System.out.println(resourceBundle.getString("withdraw.success"));
            System.out.println("Balance is " + balance(username));

            logger.log(Level.INFO,resourceBundle.getString("withdraw.success"));
        }catch(WithdrawException e){
            System.out.println(resourceBundle.getString("no.money"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return balance(username);
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
            String query;
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
