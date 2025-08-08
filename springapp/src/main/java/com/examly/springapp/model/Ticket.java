package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="tickets")

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password_hash;
    private String name;
    private LocalDateTime createdAt;

    public Ticket(Long id, String email, String password_hash, String name, LocalDateTime createdAt){
        this.id=id;
        this.email=email;
        this.password_hash=password_hash;
        this.name=name;
        this.createdAt=createdAt;
    }

    public Long getid(){
        return id;
    }
    public void setid(Long id){
        this.id=id;
    }

    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email=email;
    }

    public String getpasswordhash(){
        return password_hash;
    }
    public void setpasswordhash(String password_hash){
        this.password_hash=password_hash;
    }

    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }

    public LocalDateTime getcreatedAt(){
        return createdAt;
    }
    public void setcreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

}

