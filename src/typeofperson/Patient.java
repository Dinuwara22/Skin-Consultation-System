/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package typeofperson;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends Person implements Serializable {

    private String uniqueId;
    private String consultationId;

    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public Patient(String name, String surname, LocalDate dateOfBirth, String mobileNumber, String uniqueId,String consultId) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.uniqueId = uniqueId;
        this.consultationId = consultId;
    }

    //getter for unique id
    public String getUniqueId(){
        return uniqueId;
    }
    //setter for unique id
    public void setUniqueId(String uniqueId){
        this.uniqueId = uniqueId;
    }
}
