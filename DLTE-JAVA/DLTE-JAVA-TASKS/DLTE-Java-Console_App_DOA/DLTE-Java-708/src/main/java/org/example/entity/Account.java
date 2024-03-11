package org.example.entity;

//import java.io.Serializable;

public class Account {
        private long accountNumber;
        private long customerId;
        private String email;
        private String name;
        private double balance;
        private String username;
        private String password;

        public Account(){

        }

        public Account(long accountNumber, long customerId, String email, String name, double balance, String username, String password) {
            this.accountNumber = accountNumber;
            this.customerId = customerId;
            this.email = email;
            this.name = name;
            this.balance = balance;
            this.username = username;
            this.password = password;
        }

        public long getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(long customerId) {
            this.customerId = customerId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "accountNumber=" + accountNumber +
                    ", customerId=" + customerId +
                    ", email='" + email + '\'' +
                    ", name='" + name + '\'' +
                    ", balance=" + balance +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
}
