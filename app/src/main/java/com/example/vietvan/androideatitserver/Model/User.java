package com.example.vietvan.androideatitserver.Model;

/**
 * Created by VietVan on 14/06/2018.
 */

public class User {
    private String Name, Phone, IsStaff, Password;

    public User() {

    }

    public User(String name, String phone, String isStaff, String password) {
        Name = name;
        Phone = phone;
        IsStaff = isStaff;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", IsStaff='" + IsStaff + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
