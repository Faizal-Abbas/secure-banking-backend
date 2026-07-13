package org.example;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.*;
public class UserLogin extends BankManager{
    AccDetails acc=null;
    String sqlToCheckUser="from AccDetails a where a.uname=:t1";
    Scanner sc=new Scanner(System.in);

    public void firtScreen() throws Exception{
        try(Session session= sf.openSession()){
            System.out.println("Enter the username: ");
            String uname=sc.nextLine();
            System.out.println("Enter the password: ");
            String pass=sc.nextLine();
            Query<AccDetails> query= session.createQuery(sqlToCheckUser,AccDetails.class);
            query.setParameter("t1",uname);
            acc=query.getSingleResultOrNull();
            if(acc==null) {
                System.out.println("Error!...Please check the user name");
                System.out.println();
                Main.MainPage();
                return;
            }
            if(acc.getPass().equals(pass)){
                System.out.println("logged in successfully");
                System.out.println();
                secondScreen();
            }
            else {
                System.out.println("Error!...Please check the user name");
                System.out.println();
                Main.MainPage();
            }
        }
    }

    private void secondScreen() throws SQLException {
        while(true) {
            System.out.println("Enter the option to perform:-");
            System.out.println("1 - Info");
            System.out.println("2 - Transaction");
            System.out.println("3 - Check Balance");
            System.out.println("4 - Logout");
            System.out.println();
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    printInfo();
                    break;
                case 2:
                    System.out.println("Enter the acc id to transfer and amount :-");
                    int id=sc.nextInt();
                    sc.nextLine();
                    int amount=sc.nextInt();
                    try{
                        super.Transaction(acc,id,amount);
                    }
                    catch(Exception e){
                        System.out.println("Transaction Failed, Please find the details below");
                        System.out.println(e);
                    }
                    break;
                case 3:
                    getBalance();
                    break;
                case 4:
                    Main.MainPage();
                    return;

            }
        }
    }
    private void printInfo(){
        System.out.print("Account Details:-");
        System.out.println("Name: "+acc.getName());
        System.out.println("User Name: "+acc.getUname());
        System.out.println("Bank id: "+acc.getId());
        System.out.println();
    }

    private void getBalance(){
        System.out.println("Your Account balance: "+acc.getBalance());
        System.out.println();
    }
}
