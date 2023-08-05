/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking;

import exception.EmptyException;
import exception.FullException;
import exception.NotFoundException;
import input.In;
import manage.SkinConsultationManager;
import typeofperson.Doctor;
import typeofperson.Patient;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    ArrayList<Patient> patientArrayList = new ArrayList<>();
    ArrayList<Consultation> consultationArrayList = new ArrayList<>();
    private int countConsul = 1;
    private String consultId = "1C";


    private final String[] mainMenu =   {
            "1. Add a new Doctor",
            "2. Delete a Doctor",
            "3. Print the list of the Doctors",
            "4. Save in file",
            "5. Open GUI",
            "6. Delete a Consult",
            "0. Exit."
    };

    /**
     *This method is used to display menu
     * @return int this return the option that user choose
     **/
    public int showMainMenu(){
        System.out.println("\n<<<<<<< Menu >>>>>>>\n");
        for (String s : mainMenu) {
            System.out.println(s);
        }
        return In.readInt("Option");
    }

    /**
     * This method is used to add new doctors to arraylist And checking if the arraylist is full.
     * And if there are ten doctors, show a warning
     **/
    public void addDoctor(){
        try{
            checkAdd();
            createObj();
        }catch (Exception e){
            System.err.println(e + "\n");
        }
    }

    /**
     * This method is used to create doctor objects
     * And the object is added to the array list
    * */
    private void createObj(){
        int medicalNumber = In.readInt("Medical Licence Number");
        for(Doctor doctor: doctorArrayList){
            if(medicalNumber == doctor.getMedicalLicenceNumber()){
                System.out.println("Already added..");
                return;
            }
        }
        Doctor doctorObj = new Doctor(In.readStr("Name"),In.readStr("Surname"),In.readDate(),
                In.readStr("Mobile Number"),medicalNumber,In.readStr("Specialisation"));
        doctorArrayList.add(doctorObj);
        System.out.println("Successfully Added...");
    }

    /**
    * This method is used to check if the array list is full
    * */
    private void checkAdd() throws FullException{
        if(doctorArrayList.size() == 10){
            throw new FullException("The doctor queue is full...");
        }
    }

    /**
     * This method is used to delete doctor.
     * And if there is no a doctor,show a warning.
     * */
    public void deleteDoctor(){
        try{
            checkDelete();
            delete();
        }catch (Exception e){
            System.err.println(e + "\n");
        }
    }


    /**
     * This method is used to Get the medical license number from the user, check it and delete it
     * */
    private void delete(){
        int medicalNumber = In.readInt("the number of the doctor you want to delete");
        try{
            foundDoctor(medicalNumber);
            for(Doctor doctor: doctorArrayList){
                if(doctor.getMedicalLicenceNumber() == medicalNumber){
                    System.out.println("Deleted Successfully...\n");
                    displayDetails(doctor);
                    doctorArrayList.remove(doctor);

                    System.out.println("\nThe Number of doctor right now : " + doctorArrayList.size());
                    return;
                }
            }

        }catch (Exception e){
            System.err.println(e + "\n");
        }
    }

    /**
     * This method is used to check if the array list is empty.
     * */
    private void checkDelete() throws EmptyException{
        if(doctorArrayList.size() == 0){
            throw new EmptyException("The doctor queue is Empty...");
        }
    }

    /**
     * This method is used to Show a warning if there is no doctor for the medical licence number entered by the user.
     * */
    private void foundDoctor(int medicalNumber) throws NotFoundException{
        int size = doctorArrayList.size();
        int count = 0;
        for(Doctor doctor: doctorArrayList){
            if(doctor.getMedicalLicenceNumber() != medicalNumber){
                count++;
            }
        }
        if(count == size){
            throw new NotFoundException("There is no Doctor with this medical licence number : "+ medicalNumber);
        }
    }
    
    /**
     * This method is used to delete consultation.
     * And if there is no a consultation,show a warning.
     * */
    public void deleteConsultation(){
       try{
            checkDeleteForConsult();
            deleteForConsult();
        }catch (Exception e){
            System.err.println(e + "\n");
        }
    }
    
    /**
     * This method is used to Get the consult Id from the user, check it and delete it
     * */
    private void deleteForConsult(){
        String consultId = In.readStr("the id of the Consult you want to delete");
        try{
            foundConsultation(consultId);
            for(Consultation consultation: consultationArrayList){
                if(consultation.getConsultationID().equals(consultId)){
                    System.out.println("Deleted Successfully...\n");
                    displayConsultDetails(consultation);
                    consultationArrayList.remove(consultation);
                    
                    System.out.println("\nThe number of Consultation right now : " + consultationArrayList.size());
                    return;
                }
            }
        }catch(Exception e){
            System.err.println(e + "\n");
        }
    }
    
    /**
     * This method is used to check if the array list is empty.
     * */
    private void checkDeleteForConsult() throws EmptyException{
        if(consultationArrayList.size() == 0){
            throw new EmptyException("The consultation queue is empty...");
        }
    }
    
    /**
    *This method is used to show a warning if there is no consult for the consult Id entered by the user
    **/
    private void foundConsultation(String consultId) throws NotFoundException{
        int size = consultationArrayList.size();
        int count = 0;
        for(Consultation consultation: consultationArrayList){
            if(!(consultation.getConsultationID().equals(consultId))){
                count++;
            }
        }
        if(count == size){
            throw new NotFoundException("There is no consultation with this consult id : "+ consultId);
        }
    }

    /**
     * This method is used to sort as alphabetically ordered
     * */
    public void sortSurname(){
        Collections.sort(doctorArrayList);
        for(Doctor doctor: doctorArrayList){
            displayDetails(doctor);
        }
    }

    /**
     * This method is used to store data as object to file
     * */
    public void saveData() throws IOException{
        File file1 = new File("doctor_details.txt");

        file1.delete(); // boolean value

        FileOutputStream fos = new FileOutputStream("doctor_details.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for(Doctor doctor: doctorArrayList){
            oos.writeObject(doctor);
        }
        oos.close();
        System.out.println("data save to file...");

    }

    public void saveAllData() throws IOException{
        saveDataOfConsult();
        saveDataOfPatient();
        saveData();
    }

    private void saveDataOfPatient() throws IOException{
        File file = new File("patient_details.txt");
        file.delete();

        FileOutputStream fos = new FileOutputStream("patient_details.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for(Patient patient: patientArrayList){
            oos.writeObject(patient);
        }
        oos.close();
    }

    private void saveDataOfConsult() throws IOException{
        File file = new File("consult_details.txt");
        file.delete();


        FileOutputStream fos = new FileOutputStream("consult_details.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(consultId);
            oos.writeInt(countConsul);

        for(Consultation consultation: consultationArrayList){
            oos.writeObject(consultation);
        }
        oos.close();
    }


    /**
     * This method is used to load data.
     * */
    public void readFile() throws IOException{
            FileInputStream fis = new FileInputStream("doctor_details.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while(true){
                try{
                    Doctor doctor = (Doctor) ois.readObject();
                    doctorArrayList.add(doctor);
                }catch (IOException | ClassNotFoundException e){
                    break;
                }
            }
    }


    public void readAllData() throws IOException, ClassNotFoundException {
        readConsultFile();
        readPatientFile();
        readFile();
    }

    private void readConsultFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("consult_details.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        consultId = (String) ois.readObject();
        countConsul = ois.readInt();

        while(true){
            try{
                Consultation consultation = (Consultation) ois.readObject();
                consultationArrayList.add(consultation);
            }catch (IOException | ClassNotFoundException e){
                break;
            }
        }
    }

    private void readPatientFile() throws IOException{
        FileInputStream fis = new FileInputStream("patient_details.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        while(true){
            try{
                Patient patient = (Patient) ois.readObject();
                patientArrayList.add(patient);
            }catch (IOException | ClassNotFoundException e){
                break;
            }
        }
    }

    /**
     * This method use to display all details of a doctor
     * @param doctor This is the only one parameter to displayDetails Method
     * */
    public void displayDetails(Doctor doctor){
        System.out.println("\nMedical ID : " + doctor.getMedicalLicenceNumber());
        System.out.println("Name : " + doctor.getFullName());
        System.out.println("Date of Birth : " + doctor.getDateOfBirth());
        System.out.println("Mobile Number : " + doctor.getMobileNumber());
        System.out.println("Specialisation : " + doctor.getSpecialisation() + "\n");
    }
    
    private void displayConsultDetails(Consultation consultation){
        System.out.println("\nConsult ID : " + consultation.getConsultationID());
        System.out.println("Patient ID : " + consultation.getPatientId());
        System.out.println("Doctor ID : " + consultation.getDoctorID());
        System.out.println("Consult Date : " + consultation.getConsulDate());
        System.out.println("From Time : " + consultation.getFromTime());
        System.out.println("To Time : " + consultation.getToTime());
        System.out.println("Cost (Euro) : " + consultation.getCost());
    }

    public void displayDetails1(){
        for(Doctor doctor: doctorArrayList){
            System.out.println("\nMedical ID : " + doctor.getMedicalLicenceNumber());
            System.out.println("Name : " + doctor.getFullName());
            System.out.println("Date of Birth : " + doctor.getDateOfBirth());
            System.out.println("Mobile Number : " + doctor.getMobileNumber());
            System.out.println("Specialisation : " + doctor.getSpecialisation() + "\n");
        }
    }

    /**
     * This method is used to
     * */
    public int getArraySize(){
        return doctorArrayList.size();
    }
    /**
     * This method is used to
     * */
    public ArrayList<Doctor> getArray(){
        return doctorArrayList;
    }
    public ArrayList<Patient> getPatientArrayList(){
        return  patientArrayList;
    }

    public ArrayList<Consultation> getConsultationArrayList(){
        return consultationArrayList;
    }
    /**
     * This method is used to
     * */
    public void addToPatientArray(String name,String surname,LocalDate date,String mobileNumber,String uniqueId){
        Patient patient = new Patient(name,surname,date,mobileNumber,uniqueId,consultId);
        //setPatientUniqueId();
        patientArrayList.add(patient);
    }

    /**
     *This method is used to
     * */
    public void addToConsultationArray(LocalDate consulDate, LocalTime toTime, LocalTime fromTime,int doctorId,int cost,String notes,String uniqueId){
        Consultation consultation = new Consultation(consulDate,toTime,fromTime,consultId,doctorId,cost,notes,uniqueId);
        setConsultId();
        consultationArrayList.add(consultation);
    }

    /**
     * This method is used to create id for consultation
     * */
    public void setConsultId(){
        countConsul++;
        consultId = countConsul + "C";
    }
    public String getConsultId(){
        return consultId;
    }


    public String returnConsultId(){
        return consultId;
    }


    public String getConsultationId(){
        int countConsulInt = countConsul -1;
        return countConsulInt + "C";
    }

}
