
create sequence CUSTOMERID_SEQ start with 100 increment by 1;
 
create table MYBANK_APP_CUSTOMER(

CUSTOMER_ID INT,

CUSTOMER_NAME VARCHAR2(255) NOT NULL,

CUSTOMER_ADRESS VARCHAR2(255) NOT NULL,

CUSTOMER_STATUS VARCHAR2(255) NOT NULL,

CUSTOMER_CONTACT NUMBER(10) NOT NULL,

USERNAME VARCHAR2(255) NOT NULL,

PASSWORD VARCHAR2(255) NOT NULL

);
 
alter table MYBANK_APP_CUSTOMER add constraint CUSTOMERID_SEQ primary key(CUSTOMER_ID);
 
create sequence KYC_SEQ start with 100 increment by 1;

CREATE TABLE MYBANK_APP_KYC(

KYC_NUMBER INT,

CUSTOMER_ID INT NOT NULL,

KYC_PAN VARCHAR2(255) NOT NULL,

KYC_AADHAAR NUMBER(16) NOT NULL,

KYC_STATUS VARCHAR2(255) NOT NULL,

FOREIGN KEY(CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) on delete cascade

);

alter table MYBANK_APP_KYC add constraint KYC_SEQ primary key(KYC_NUMBER);
 
create sequence ACC_SEQ start with 100 increment by 1;

CREATE TABLE MYBANK_APP_ACCOUNT(

ACCOUNT_ID INT,

CUSTOMER_ID INT,

ACCOUNT_TYPE VARCHAR(50) NOT NULL,

ACCOUNT_NUMBER VARCHAR(225) NOT NULL UNIQUE,

ACCOUNT_STATUS NUMBER(2) NOT NULL,

FOREIGN KEY (CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) on delete cascade

);
 
alter table MYBANK_APP_ACCOUNT add constraint ACC_SEQ  primary key(ACCOUNT_ID);
 
create sequence DEPOSIT_SEQ start with 100 increment by 1;

CREATE TABLE MYBANK_APP_DEPOSITAVAILABLE(

DEPOSIT_ID INT,

DEPOSIT_NAME VARCHAR2(255) NOT NULL,

DEPOSIT_ROI DECIMAL(15,2) NOT NULL,

DEPOSIT_TYPE VARCHAR(255) NOT NULL,

DEPOSIT_DESCRIPTION CLOB NOT NULL

);

alter table MYBANK_APP_DEPOSITAVAILABLE add constraint DEPOSIT_SEQ primary key(DEPOSIT_ID);
 
CREATE SEQUENCE DEPOSITAVAIL_SEQ START WITH 100 INCREMENT BY 1;

CREATE TABLE MYBANK_APP_DEPOSITAVAILED(

DEPOSIT_AVAIL_ID INT,

CUSTOMER_ID INT NOT NULL,

DEPOSIT_ID INT NOT NULL,

DEPOSITED_AMOUNT DECIMAL(15,2) NOT NULL,

DEPOSIT_DURATION INT NOT NULL,

DEPOSIT_MATURITY DATE NOT NULL,

FOREIGN KEY (CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) on delete cascade,

FOREIGN KEY (DEPOSIT_ID) REFERENCES MYBANK_APP_DEPOSITAVAILABLE(DEPOSIT_ID) on delete cascade

);
 
alter table MYBANK_APP_DEPOSITAVAILED add constraint DEPOSITAVAIL_SEQ primary key(DEPOSIT_AVAIL_ID);
 
CREATE SEQUENCE INSURANCEID_SEQ START WITH 100 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_INSURANCEAVAILABLE(

INSURANCE_ID INT ,

INSURANCE_TYPE VARCHAR2(50) NOT NULL,

INSURANCE_NAME VARCHAR2(255)NOT NULL,

INSURANCE_KEY_BENEFITS CLOB NOT NULL,

INSURANCE_LIFETIME INT NOT NULL);
 
alter table MYBANK_APP_INSURANCEAVAILABLE add constraint INSURANCEID_SEQ  primary key(INSURANCE_ID);

CREATE SEQUENCE INSURANCE_AVAIL_ID_SEQ START WITH 100 INCREMENT BY 1;
 
CREATE TABLE MYBANK_APP_INSURANCEAVAILED(

INSURANCE_AVAIL_ID INT,

CUSTOMER_ID INT ,

INSURANCE_ID INT ,

INSURANCE_COVERAGE DECIMAL(15,2) NOT NULL,

INSURANCE_PREMIUM DECIMAL(15,2) NOT NULL,

FOREIGN KEY(CUSTOMER_ID) REFERENCES MYBANK_APP_CUSTOMER(CUSTOMER_ID) on delete cascade,

FOREIGN KEY(INSURANCE_ID) REFERENCES MYBANK_APP_INSURANCEAVAILABLE(INSURANCE_ID) on delete cascade

);
 
alter table MYBANK_APP_INSURANCEAVAILED add constraint INSURANCE_AVAIL_ID_SEQ  primary key(INSURANCE_AVAIL_ID);
 
create sequence DEBIT_SEQ start with 100 increment by 1;

CREATE TABLE MYBANK_APP_DebitCard(

DEBITCARD_NUMBER NUMBER(20),

ACCOUNT_ID INT,

DEBITCARD_CVV INT NOT NULL,

DEBITCARD_EXPIRY DATE NOT NULL,

DEBITCARD_STATUS NUMBER(4) NOT NULL,

DEBITCARD_DOMESTIC_LIMIT NUMBER(20) NOT NULL,

DEBITCARD_INTERNATIONAL_LIMIT NUMBER(20) NOT NULL,

FOREIGN KEY(ACCOUNT_ID) REFERENCES  MYBANK_APP_ACCOUNT(ACCOUNT_ID) ON DELETE CASCADE

);
 
alter table MYBANK_APP_DebitCard add constraint DEBIT_SEQ  primary key(DEBITCARD_NUMBER);
 
create sequence LOAN_SEQ start with 100 increment by 1;
 
create table MYBANK_APP_LOANAVAILABLE(

loan_number int,

loan_type varchar2(255) not null,

loan_name varchar(255) not null,

loan_description clob not null,

loan_roi decimal(15,2) not  null

);
 
alter table MYBANK_APP_LOANAVAILABLE add constraint LOAN_SEQ  primary key(loan_number);
 
create sequence LOANAVAIL_SEQ start with 100 increment by 1;
 
create table MYBANK_APP_LOANAVAILED(

loan_avail_number int,

customer_number int,

loan_number int,

loan_amount decimal(15,2) not null,

loan_emi decimal(15,2) not null,

loan_tenure int not null,

FOREIGN KEY (customer_number) REFERENCES MYBANK_APP_Customer(customer_Id) on delete cascade,

FOREIGN KEY (loan_number) REFERENCES MYBANK_APP_LOANAVAILABLE(loan_number) on delete cascade

);
 
alter table MYBANK_APP_LOANAVAILED add constraint LOANAVAIL_SEQ  primary key(loan_avail_number);
 
create sequence transactionid_seq start with 100 increment by 1;
 
CREATE TABLE MYBANK_APP_Transaction (

    transaction_id INT,

    account_id INT,

    transaction_type VARCHAR(50) not null,

    transaction_from VARCHAR(255) not null,

    transaction_to VARCHAR(255) not null,

    transaction_date DATE not null,

    transaction_amount DECIMAL(15,2) not null,

    transaction_status VARCHAR(50) not null,

    FOREIGN KEY (account_id) REFERENCES MYBANK_APP_Account(account_id) on delete cascade

);
 
alter table MYBANK_APP_Transaction add constraint transactionid_seq  primary key(transaction_id);
 
create sequence payee_seq start with 100 increment by 1;
 
CREATE TABLE MYBANK_APP_Payee (

    payee_id INT ,

    customer_id INT,

    account_id INT,

    payee_name VARCHAR(255) not null,

    FOREIGN KEY (customer_id) REFERENCES MYBANK_APP_Customer(customer_id) on delete cascade,

    FOREIGN KEY (account_id) REFERENCES MYBANK_APP_Account(account_id) on delete cascade

);
 
alter table MYBANK_APP_Payee add constraint payee_seq  primary key(payee_id);