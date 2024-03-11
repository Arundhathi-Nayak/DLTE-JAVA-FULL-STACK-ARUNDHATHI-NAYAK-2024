package app.mobilebanking;




import app.mobilebanking.middleware.FileStorageTarget;
//import app.mobilebanking.services.AccountService;
import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Start point of the application execution
 * Here First user need to login with his correct credentials if the credentials is not correct then user has 2 more chances to enter the credentials after that account get blocked.
 * Next user will redirect to the menu page where user will get the options available in the system.There is needs to click on required options and performs required action.
 */

public class App {

    private static StorageTarget storageTarget;
    private static AccountService services;
    private static Scanner scanner = new Scanner(System.in);
    // Adding the resource bundle and loggers
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application") ;
    private static  Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {
        int option=0;
        String username=null,password;
//        storageTarget=new FileStorageTarget();
        storageTarget= new DatabaseTarget();
        services=new AccountService(storageTarget);
        services.callAddTransactions(new Account(535456345, 49665, "arundhathi@gmail.com", "arundhathi", 1531534.0, "arundhathi51", "=arundathi123"));
        services.callAddTransactions(new Account(683231531, 41555, "ekshan@gmail.com", "eksha", 3521.0, "eksha25", "eksha365"));
        services.callAddTransactions(new Account(856341556, 52025, "shreyas@gmail.com", "shreyas", 859652.0, "shreyas12", "shreyas123"));

//        System.out.println( services.callFinaAll().toString());
        System.out.println(resourceBundle.getString("app.greet"));
        System.out.println(resourceBundle.getString("app.login.menu"));
        option = scanner.nextInt();
        if(option==1){
            boolean validate=false;
            System.out.println(resourceBundle.getString("user.name"));
            username = scanner.next();
            System.out.println(resourceBundle.getString("Enter.Password"));
            password = scanner.next();
            // Verifying the entered username, password if it is true it proceeds with further steps
            if(services.callVerifyPassword(username,password)){
                logger.log(Level.INFO,resourceBundle.getString("login.Successful")+":"+username);
                System.out.println(resourceBundle.getString("login.Successful"));
            while (true) {
                // Displaying the Dashboard
                System.out.println(resourceBundle.getString("app.dashboard.menu"));
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                    case 6:
                        System.out.println(resourceBundle.getString("app.page.under.development"));
                        break;
                    case 3:
                        double amount = 0;
                         do {
                             try {
                                 // Entering the withdraw amount
                                 System.out.println(resourceBundle.getString("Amount.withdraw"));
                                 amount = scanner.nextDouble();
                                 validate = true;
                             }
                             // checking for input format
                             catch (InputMismatchException inputMismatchException) {
                                 System.out.println(resourceBundle.getString("Enter.number"));
                                 scanner.nextLine();
                             }
                         }while (!validate);
                         // entering the password
                        System.out.println(resourceBundle.getString("Enter.Password"));
                        password = scanner.next();
                        services.callWithdraw(username, password, amount);
                        break;
                    default:
                        System.out.println(resourceBundle.getString("Thank.you"));
                        System.exit(0);
                }
            }
            }
        }
        else{
            System.out.println(resourceBundle.getString("app.page.under.development"));
            System.exit(0);
        }

    }
}


