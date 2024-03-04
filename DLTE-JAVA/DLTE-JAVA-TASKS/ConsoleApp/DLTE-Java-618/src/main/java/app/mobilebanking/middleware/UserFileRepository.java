package app.mobilebanking.middleware;

import app.mobilebanking.entity.Account;
import app.mobilebanking.remotes.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserFileRepository implements UserRepository {
    private String filePath;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    private Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<Account> accountList=new ArrayList<>();
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

    public void addTransactions(){
        readFromFile();
        accountList.add(new Account(123458613,46545,"varun@gmail.com","varun",46531.0,"varun12","varun123"));
        accountList.add(new Account(535456345,49665,"arundathi@gmail.com","arundhathi",1531534.0,"arundathi51","=arundathi123"));
        accountList.add(new Account(683231531,41555,"ekshan@gmail.com","eeksha",3521.0,"eksha25","eksha365"));
        accountList.add(new Account(856341556,52025,"shreyas@gmail.com","shreyas",859652.0,"shreyas12","shreyas123"));
        writeIntoFile();
    }
    @Override
    public List<Account> findALL() {
        readFromFile();
        return accountList;
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        readFromFile();
        Account account = accountList.stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
        if(account == null){
            System.out.println("Username not found");
            return false;
        }
        else if(!account.getPassword().equals(password)){
            System.out.println("Password is Incorrect");
            return false;
        }
        else
            return true;
    }

    @Override
    public void withdraw(String username,String password,double withdrawAmount) {
        readFromFile();
        if(verifyPassword(username,password)){
            Account account = accountList.stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
            if(account.getBalance()-withdrawAmount<0){
                System.out.println("Insufficient Balance");
            }
            else {
                account.setBalance(account.getBalance()-withdrawAmount);
                writeIntoFile();
                System.out.println(balance(username));
                //logger transaction successful
                return;
            }

        }
        else{
            System.out.println("Password Incorrect Retry");
        }
    }

    @Override
    public double balance(String username) {
        readFromFile();
        Account account = accountList.stream().filter(each ->each.getUsername().equals(username)).findFirst().orElse(null);
        return account.getBalance();
    }
}
