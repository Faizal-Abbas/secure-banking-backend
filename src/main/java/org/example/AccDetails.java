package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_user")
public class AccDetails {
    @Id
    private int id;
    private String name;
    @Column(name = "user_name")
    private String uname;
    @Column(name ="password")
    private String pass;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public AccDetails(){

    }

    public AccDetails(int id, String name, String uname, String pass, int balance){
        this.id=id;
        this.name=name;
        this.uname=uname;
        this.pass=pass;
        this.balance=balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
