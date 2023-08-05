/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package typeofperson;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String fullName;

    public Person(String name, String surname, LocalDate dateOfBirth, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    //getter for name
    public String getName(){
        return name;
    }
    //setter for name
    public void setName(String name){
        this.name = name;
    }
    //getter for surname
    public String getSurname(){
        return surname;
    }
    //setter for surname
    public void setSurname(String surname){
        this.surname = surname;
    }
    //getter for date of birth
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    //setter for date of birth
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    //getter for mobile number
    public String getMobileNumber(){
        return mobileNumber;
    }
    //setter for mobile number
    public void setMobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    //getter for full name
    public String getFullName(){
        return fullName;
    }
    public void setFullName(){
        fullName = name + " " + surname;
    }

}
