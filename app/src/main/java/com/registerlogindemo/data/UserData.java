package com.registerlogindemo.data;



public class UserData {
    public int id;
    public String name;
    public String email;
    public String password;
    // Empty constructor
    public UserData(){

    }
    // constructor
    public UserData(int id, String name, String email,String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // constructor
    public UserData(String name, String email,String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public UserData(String name, String email){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
