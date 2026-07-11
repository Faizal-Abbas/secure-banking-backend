package org.example;
import java.util.*;
import java.sql.*;
public class BankManager extends Database{
    final String sql="select * from bank_user where id=?";
    final String from="update bank_user set balance=? where id=?";
    public void Transaction(AccDetails acc, int id,int amount) throws Exception{
        try(PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);
            try(ResultSet rs=ps.executeQuery()) {
                if (!rs.next()) {
                    throw new Exception("Invalid id");
                }
                if (acc.getBalance() < amount) {
                    throw new Exception("Insufficient Balance");
                }
                int fromBalance = acc.getBalance();
                int ToBalance = rs.getInt(5);
                int ToId = rs.getInt(1);
                fromBalance = fromBalance - amount;
                ToBalance = ToBalance + amount;
                if(ToId==acc.getId()){
                    throw new Exception("Can't transfer within the same amount");
                }
                acc.setBalance(fromBalance);
                try {
                    con.setAutoCommit(false);
                    try (PreparedStatement ps2 = con.prepareStatement(from)) {
                        ps2.setInt(1, ToBalance);
                        ps2.setInt(2, ToId);
                        ps2.executeUpdate();
                    }
                    try (PreparedStatement ps3 = con.prepareStatement(from)) {
                        ps3.setInt(1, fromBalance);
                        ps3.setInt(2, acc.getId());
                        ps3.executeUpdate();
                    }
                    con.commit();
                    con.setAutoCommit(true);
                    System.out.println("Transaction successfull!!");
                    System.out.println();
                }catch(Exception e){
                    con.rollback();
                    System.out.println(e);
                }
            }
        }

    }
}
