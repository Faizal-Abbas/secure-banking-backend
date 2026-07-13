package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import java.util.*;
import java.sql.*;
public class BankManager{
    protected static SessionFactory sf;
    static{
        try{
            Configuration cfg=new Configuration();
            cfg.addAnnotatedClass(AccDetails.class).configure();
            sf=cfg.buildSessionFactory();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void Transaction(AccDetails acc,int id,int amount) throws Exception{
        try(Session session=sf.openSession()){
            AccDetails receiver=null;
            receiver=session.find(AccDetails.class,id);
            if(receiver==null){
                throw new Exception("Invalid id");
            }
            if(acc.getBalance() < amount) {
                throw new Exception("Insufficient Balance");
            }
            int fromBalance = acc.getBalance();
            int ToBalance = receiver.getBalance();
            int ToId = receiver.getId();
            fromBalance = fromBalance - amount;
            ToBalance = ToBalance + amount;
            if(ToId==acc.getId()){
                throw new Exception("Can't transfer within the same amount");
            }
            acc.setBalance(fromBalance);
            receiver.setBalance(ToBalance);
            org.hibernate.Transaction tx=null;
            try {
                tx = session.beginTransaction();
                session.merge(acc);
                session.merge(receiver);
                tx.commit();
                System.out.println("Transaction Completed successfully");
            }
            catch(Exception e){
                if(tx!= null) {
                    tx.rollback();
                }
                System.out.println("Transaction failed");
            }
        }
    }
}