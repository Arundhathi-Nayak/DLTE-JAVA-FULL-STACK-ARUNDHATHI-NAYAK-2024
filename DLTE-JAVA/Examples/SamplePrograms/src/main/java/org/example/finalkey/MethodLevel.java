package org.example.finalkey;

public class MethodLevel {


        final void method()
        {
            System.out.println("This is a final method.");
        }

    class Second extends MethodLevel
    {
        //void method()
        {
            // Compile-error! We can not override
            System.out.println("Illegal!");
        }
    }
}
