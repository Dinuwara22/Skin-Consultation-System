/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manage;

import booking.Consultation;
import typeofperson.Doctor;
import typeofperson.Patient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface SkinConsultationManager {

    int showMainMenu();
    void addDoctor();
    void deleteDoctor();
    void deleteConsultation();
    void sortSurname();
    void saveData() throws IOException;
    void readFile() throws IOException;
    int getArraySize();
    void saveAllData() throws IOException;
    void readAllData() throws IOException, ClassNotFoundException;
    ArrayList<Patient> getPatientArrayList();
    ArrayList<Consultation> getConsultationArrayList();
    ArrayList<Doctor> getArray();
    void addToPatientArray(String name,String surname,LocalDate date,String mobileNumber,String uniqueId);
    void addToConsultationArray(LocalDate consulDate, LocalTime toTime, LocalTime fromTime,int doctorId,int cost,String notes,String uniqueId);
    String getConsultId();
    String returnConsultId();
    void setConsultId();
    String getConsultationId();
}
