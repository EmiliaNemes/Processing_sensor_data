package org;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args ) throws IOException {
        Tasks tasks = new Tasks();
        Scanner in = new Scanner(System.in);
        System.out.println( "Write the number of the task you want to perform: (or 'exit' in case you want to terminate the program)" );
        String text = in.nextLine();
        while(!text.equals("exit")){
            switch(text){
                case "1": tasks.performTask1();
                    break;
                case "2": tasks.performTask2();
                    break;
                case "3": tasks.performTask3();
                    break;
                case "4": tasks.performTask4();
                    break;
                case "5": tasks.performTask5();
                    break;
                case "6": tasks.performTask6();
                    break;
                default: System.out.println("No such task");
            }
            System.out.println();
            System.out.println( "Write the number of the task you want to perform: (or 'exit' in case you want to terminate the program)" );
            text = in.nextLine();
        }
    }
}
