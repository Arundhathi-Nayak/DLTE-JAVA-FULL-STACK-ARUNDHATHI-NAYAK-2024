package app.mobilebanking.middleware;
/**
 * Here we implements all the operations that declared in interface UserRepository
 * Here we added methods reading from file and writing into file
 */

import app.mobilebanking.entity.Account;
import app.mobilebanking.exceptions.WithdrawException;
import org.example.remotes.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserFileRepository implements UserRepository {
    private String filePath;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    private Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Scanner scanner = new Scanner(System.in);
    private List<Account> accountList=new ArrayList<>();
    // Creating a file to store logs
    public UserFileRepository(String url){
        filePath=url;
        try{
            FileHandler fileHandler=new FileHandler("accounts-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }
        catch (IOException ioException){}
    }
   // writing into the file
    private void writeIntoFile(){
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(accountList);

            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException ioException){}
    }
   // reading from the file
    private void readFromFile(){
        try{
            FileInputStream fileInputStream=new FileInputStream(filePath);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);

            accountList=(List<Account>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException | ClassNotFoundException ioException){}
    }
    // adding new Transaction to file
    public void addTransactions(){
        readFromFile();
        accountList.add(new Account(123458613,46545,"varun@gmail.com","varun",46531.0,"varun12","varun123"));
        accountList.add(new Account(535456345,49665,"arundhathi@gmail.com","arundhathi",1531534.0,"arundhathi51","=arundathi123"));
        accountList.add(new Account(683231531,41555,"ekshan@gmail.com","eksha",3521.0,"eksha25","eksha365"));
        accountList.add(new Account(856341556,52025,"shreyas@gmail.com","shreyas",859652.0,"shreyas12","shreyas123"));
        writeIntoFile();
    }
    // listing all Transaction available in files
//    @Override
//    public List<Account> findALL() {
//        return  null;
//    }
    // username and password verification implementation
    @Override
    public boolean verifyPassword(String username, String password) {
        readFromFile();
        Account account = accountList.stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
        try {
            if (account == null) {
                System.out.println(resourceBundle.getString("username.not.found"));
                logger.log(Level.WARNING, resourceBundle.getString("username.not.found"));
//                return false;
                throw new WithdrawException();
            } else if (!account.getPassword().equals(password)) {
                logger.log(Level.WARNING, resourceBundle.getString("password.not.matched"));
                System.out.println(resourceBundle.getString("password.not.matched"));
                throw new WithdrawException();
            } else
                return true;
        }catch(WithdrawException withdrawException){
            for(int attempts=2;attempts<=3;){
                System.out.println(resourceBundle.getString("accounts.login.fail")+" Only "+(3-attempts+1)+" attempts left");
                logger.log(Level.WARNING,resourceBundle.getString("accounts.login.fail"));
                System.out.println(withdrawException);
                System.out.println("Enter Your Username");
                String user = scanner.next();
                System.out.println("Enter Password");
                String pin = scanner.next();
                account =accountList.stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
                if(account!=null && account.getUsername().equals(user) && account.getPassword().equals(pin)){
                    System.out.println(resourceBundle.getString("accounts.login.success"));
                    logger.log(Level.INFO,resourceBundle.getString("accounts.login.success"));
                    return true;
                }else{
                    //   System.out.println(resourceBundle.getString("accounts.login.fail")+" Only "+(3-attempts)+" attempts left");;
                    attempts++;
                }if(attempts>3) {
                    System.out.println(resourceBundle.getString("accounts.no.more.attempts"));
                    logger.log(Level.WARNING,resourceBundle.getString("accounts.no.more.attempts"));
                }
            }
        }
        return false;
    }
    // Method for withdrawal of money
    @Override
    public void withdraw(String username,String password,double withdrawAmount) {
        readFromFile();
        if(verifyPassword(username,password)){
            Account account = accountList.stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
            if(account.getBalance()-withdrawAmount<0){
                logger.log(Level.WARNING,resourceBundle.getString("no.money"));
            }
            else {
                account.setBalance(account.getBalance()-withdrawAmount);
                writeIntoFile();
                System.out.println(resourceBundle.getString("remaining.balance")+balance(username));
                return;
            }

        }
        else{
            logger.log(Level.WARNING,resourceBundle.getString("password.incorrect"));
            System.out.println(resourceBundle.getString("password.incorrect"));
        }
    }
    //Filtering to get only balance from transaction object
    @Override
    public double balance(String username) {
        readFromFile();
        Account account = accountList.stream().filter(each ->each.getUsername().equals(username)).findFirst().orElse(null);
        return account.getBalance();
    }
}
