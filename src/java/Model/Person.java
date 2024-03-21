/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author JAST
 */
public class Person {
    private int idperson;
 private String num_document; 
 private String fullname;
 private String address;
 private String telephone;
 private String user;
 private String password;
 private String status;
 private String idTypePerson;

    public Person() {
    }

    public Person(int idperson, String num_document, String fullname, String address, String telephone, String user, String password, String status, String idTypePerson) {
        this.idperson = idperson;
        this.num_document = num_document;
        this.fullname = fullname;
        this.address = address;
        this.telephone = telephone;
        this.user = user;
        this.password = password;
        this.status = status;
        this.idTypePerson = idTypePerson;
    }

    public int getIdperson() {
        return idperson;
    }

    public void setIdperson(int idperson) {
        this.idperson = idperson;
    }

    public String getNum_document() {
        return num_document;
    }

    public void setNum_document(String num_document) {
        this.num_document = num_document;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdTypePerson() {
        return idTypePerson;
    }

    public void setIdTypePerson(String idTypePerson) {
        this.idTypePerson = idTypePerson;
    }
    

    
}
