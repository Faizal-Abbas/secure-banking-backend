package org.example;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(){
        MainPage();
    }
    static void MainPage(){

        UserLogin user=new UserLogin();
        System.out.println("Welcome to the AAA Mobile Banking");
        System.out.println("Please Enter the login:");
        System.out.println("1 - User Login");
        System.out.println("2 - Exit");
        System.out.println();
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        switch(choice){
            case 1:
                try {
                    user.firstScreen();
                }
                catch(Exception e){
                    System.out.println(e);
                }
                break;
            case 2:
                break;
        }
    }
}
