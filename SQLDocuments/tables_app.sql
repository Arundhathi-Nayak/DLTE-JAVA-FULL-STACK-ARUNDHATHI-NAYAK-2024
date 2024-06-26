--------------------------------------------------------
--  File created - Tuesday-March-12-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TRANSACTIONS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."TRANSACTIONS" 
   (	"TRANSACTION_ID" NUMBER(*,0), 
	"TRANSACTION_DATE" DATE, 
	"TRANSACTION_USERNAME" VARCHAR2(20 BYTE), 
	"TRANSACTION_AMOUNT" NUMBER, 
	"TRANSACTION_BALANCE" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MY_BANK
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."MY_BANK" 
   (	"ACCOUNT_NUMBER" NUMBER, 
	"CUSTOMER_ID" NUMBER, 
	"EMAIL" VARCHAR2(30 BYTE), 
	"NAME" VARCHAR2(30 BYTE), 
	"BALANCE" NUMBER(20,2), 
	"USERNAME" VARCHAR2(30 BYTE), 
	"PASSWORD" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into SYSTEM.TRANSACTIONS
SET DEFINE OFF;
Insert into SYSTEM.TRANSACTIONS (TRANSACTION_ID,TRANSACTION_DATE,TRANSACTION_USERNAME,TRANSACTION_AMOUNT,TRANSACTION_BALANCE) values (10000002,to_date('12-03-24','DD-MM-RR'),'shreyas12',500,848652);
Insert into SYSTEM.TRANSACTIONS (TRANSACTION_ID,TRANSACTION_DATE,TRANSACTION_USERNAME,TRANSACTION_AMOUNT,TRANSACTION_BALANCE) values (10000003,to_date('12-03-24','DD-MM-RR'),'shreyas12',500,846652);
Insert into SYSTEM.TRANSACTIONS (TRANSACTION_ID,TRANSACTION_DATE,TRANSACTION_USERNAME,TRANSACTION_AMOUNT,TRANSACTION_BALANCE) values (10000004,to_date('12-03-24','DD-MM-RR'),'shreyas12',1000,845652);
Insert into SYSTEM.TRANSACTIONS (TRANSACTION_ID,TRANSACTION_DATE,TRANSACTION_USERNAME,TRANSACTION_AMOUNT,TRANSACTION_BALANCE) values (10000005,to_date('12-03-24','DD-MM-RR'),'shreyas12',1000,844652);
REM INSERTING into SYSTEM.MY_BANK
SET DEFINE OFF;
Insert into SYSTEM.MY_BANK (ACCOUNT_NUMBER,CUSTOMER_ID,EMAIL,NAME,BALANCE,USERNAME,PASSWORD) values (123458613,46545,'varun@gmail.com','varun',46531,'varun12','varun123');
Insert into SYSTEM.MY_BANK (ACCOUNT_NUMBER,CUSTOMER_ID,EMAIL,NAME,BALANCE,USERNAME,PASSWORD) values (535456345,49665,'arundhathi@gmail.com','arundhathi',1531534,'arundhathi51','arundathi123');
Insert into SYSTEM.MY_BANK (ACCOUNT_NUMBER,CUSTOMER_ID,EMAIL,NAME,BALANCE,USERNAME,PASSWORD) values (683231531,41555,'ekshan@gmail.com','eksha',3521,'eksha25','eksha365');
Insert into SYSTEM.MY_BANK (ACCOUNT_NUMBER,CUSTOMER_ID,EMAIL,NAME,BALANCE,USERNAME,PASSWORD) values (856341556,52025,'shreyas@gmail.com','shreyas',844652,'shreyas12','shreyas123');
--------------------------------------------------------
--  DDL for Index SYS_C007096
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007096" ON "SYSTEM"."TRANSACTIONS" ("TRANSACTION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007179
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007179" ON "SYSTEM"."MY_BANK" ("ACCOUNT_NUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table TRANSACTIONS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."TRANSACTIONS" MODIFY ("TRANSACTION_BALANCE" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."TRANSACTIONS" ADD PRIMARY KEY ("TRANSACTION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SYSTEM"."TRANSACTIONS" MODIFY ("TRANSACTION_AMOUNT" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."TRANSACTIONS" MODIFY ("TRANSACTION_USERNAME" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."TRANSACTIONS" MODIFY ("TRANSACTION_DATE" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."TRANSACTIONS" MODIFY ("TRANSACTION_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MY_BANK
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."MY_BANK" ADD PRIMARY KEY ("ACCOUNT_NUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
