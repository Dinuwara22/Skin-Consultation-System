/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable {
    private LocalDate consulDate;
    private LocalTime toTime;
    private LocalTime fromTime;
    private int cost;
    private String notes;
    private String consultationID;
    private int doctorID;
    private String patientId;

    public Consultation(LocalDate consulDate, LocalTime toTime, LocalTime fromTime, String consultationID,int doctorID,int cost,String notes,String patientId) {
        this.consulDate = consulDate;
        this.toTime = toTime;
        this.fromTime = fromTime;
        this.cost = cost;
        this.doctorID = doctorID;
        this.notes = notes;
        this.consultationID = consultationID;
        this.patientId = patientId;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getConsultationID() {
        return consultationID;
    }

    public void setConsultationID(String consultationID) {
        this.consultationID = consultationID;
    }

    //getter for consultation date
    public LocalDate getConsulDate(){
        return consulDate;
    }

    //setter for consultation date
    public void setConsulDate(LocalDate consulDate){
        this.consulDate = consulDate;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    //getter for cost
    public int getCost(){
        return cost;
    }

    //setter for cost
    public void setCost(int cost){
        this.cost = cost;
    }

    //getter for notes
    public String getNotes(){
        return notes;
    }

    //setter for notes
    public void setNotes(String notes){
        this.notes = notes;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
