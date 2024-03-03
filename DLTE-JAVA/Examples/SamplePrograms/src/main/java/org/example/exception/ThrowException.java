package org.example.exception;

public class ThrowException {

        public static void validate(int age) {
            if(age<18) {
                //throw Arithmetic exception if not eligible to vote
                throw new ArithmeticException("Person is not eligible to vote");
            }
            else {
                System.out.println("Person is eligible to vote!!");
            }
        }
        //main method
        public static void main(String args[]){
            //calling the function
            try{
                validate(13);
            }catch (ArithmeticException exception){
                System.out.println(exception.getMessage());
            }


        }
    }

