package org.example;
import java.sql.*;
import java.util.*;
public class UserLogin extends BankManager{
    AccDetails acc;
    String sqlToCheckUser="select * from bank_user where user_name=?";
    Scanner sc=new Scanner(System.in);
    public void firstScreen() throws SQLException {
        Database db=new Database();
        con=DriverManager.getConnection(url,uname,pass);
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the username: ");
        String uname=sc.nextLine();
        System.out.println("Enter the password: ");
        String pass=sc.nextLine();
        try(PreparedStatement ps=con.prepareStatement(sqlToCheckUser)){
            ps.setString(1,uname);
            try(ResultSet rs=ps.executeQuery()){
                String m="";
                if(rs.next()) {
                    m = rs.getString(4);
                }else{
                    System.out.println("Error!...Please check the user name");
                    System.out.println();
                    Main.MainPage();
                }
                if(m.equals(pass)){
                    System.out.println("Sucessfully Logged in!");
                    acc=new AccDetails(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5));
                    secondScreen();
                }else{
                    System.out.println("Error!...Password Incorrect");
                    System.out.println();
                    Main.MainPage();
                }
            }
        }
        con.close();

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
