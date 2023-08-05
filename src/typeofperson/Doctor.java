/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package typeofperson;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Doctor extends Person implements Serializable, Comparable<Doctor> {

    private int medicalLicenceNumber;
    private String specialisation;
    private String consultationId;

   ArrayList<String> doctorConsult = new ArrayList<>();

    public Doctor(String name, String surname, LocalDate date,String mobileNumber,int medicalLicenceNumber,String specialisation){
        super(name,surname,date,mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
        setFullName();
    }

    public int compareTo(Doctor doctor){
        int compareInt = super.getSurname().compareTo(doctor.getSurname());
        if(compareInt < 0){
            return -1;
        }
        if(compareInt > 0){
            return 1;
        }
        return 0;
    }

    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }


    public int getMedicalLicenceNumber(){
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(int medicalLicenceNumber){
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation(){
        return specialisation;
    }

    public void setSpecialisation(String specialisation){
        this.specialisation = specialisation;
    }

    public void addConsults(String consultId){
        doctorConsult.add(consultId);
    }
    public ArrayList<String> getDoctorConsult(){
        return doctorConsult;
    }

}

