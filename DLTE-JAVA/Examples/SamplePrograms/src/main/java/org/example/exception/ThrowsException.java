package org.example.exception;

import java.io.IOException;


public class ThrowsException{
        public static void main(String args[]){
            try{
                Test exception=new Test();
                exception.method();
            }catch(Exception e){System.out.println("exception handled");}


        }
    }
class Test {

    void method() throws IOException {
        throw new IOException("device error");
    }
}

