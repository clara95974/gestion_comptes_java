package com.example.pa_java;

public class Employe{
    public int id;
    public String name;
    public String firstname;
    public String mail;
    public String country;
    public String date_inscrip;

    public String getDate_inscrip() {
        return date_inscrip;
    }

    public void setDate_inscrip(String date_inscrip) {
        this.date_inscrip = date_inscrip;
    }

    public Employe(int id, String name, String firstname, String mail, String country, String date_inscrip) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.mail = mail;
        this.country = country;
        this.date_inscrip = date_inscrip;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Nom Prenom : "+ this.name + " " + this.firstname + "\nMail : " + this.mail + "\n Pays : " + this.country;
    }

}
