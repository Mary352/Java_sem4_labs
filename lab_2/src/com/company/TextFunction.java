package com.company;

public class TextFunction {

    public  int test;
    public TextFunction() {
    }

    public String getTest() {
        return "Hello from first project";
    }

    public void setTest(int test) {
        this.test = test;

        for (int i = 0; i < 10; i++) {
            System.out.println("Counter " +i);
        }
    }
}
