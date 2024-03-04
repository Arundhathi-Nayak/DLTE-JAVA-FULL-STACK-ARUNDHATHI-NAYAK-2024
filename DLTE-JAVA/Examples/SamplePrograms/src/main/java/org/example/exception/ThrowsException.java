package org.example.exception;

import java.io.IOException;


public class ThrowsException{
        public static void main(String args[]) throws IOException {
            ThrowsException myMethod=new ThrowsException();
            myMethod.myMethod();
//            try{
//                Test exception=new Test();
//                exception.method();
//            }catch(Exception e){System.out.println("exception handled");}
//

        }
    void myMethod() throws IOException {
        Test method=new Test();
        method.method();
    }
    }
class Test {

    void method() throws IOException {
        throw new IOException("device error");
    }

}

