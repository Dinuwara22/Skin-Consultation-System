/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import booking.Consultation;
import booking.WestminsterSkinConsultationManager;
import manage.SkinConsultationManager;
import typeofperson.Doctor;
import typeofperson.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Table {
    JTable table;
    JTableHeader tableHeader;
    DefaultTableModel model;
    JScrollPane pane;
    JFrame frame;
    int time1;
    int time2;
    WestminsterSkinConsultationManager skin;

    public Table(JFrame frame, WestminsterSkinConsultationManager skin){
        this.skin = skin;
        this.frame = frame;
    }

    public void createDocDetailTable(){
        setTableProperties(columnsDocTable());
        resizeWidthColum();
        addDocDetailData();
        setPanel();
    }

    public void createAvailableDocTable(LocalTime fromTime,LocalTime toTime,LocalDate bookingDate){
        setTableProperties(columnsAvailableDocColumns());
        resizeWidthAvailableDocTable();
        addAvailableDocDetailData(fromTime,toTime,bookingDate);
        setAvailableDocTablePane();
    }
    public void createPatientTable(){
        setTableProperties(columnsNameForPatient());
        addPatientDetailData();
        setPanelForPatientTable();
    }

    private void setTableProperties(String[] columnsName){
        table = new JTable();
        tableHeader = table.getTableHeader();

        model = new DefaultTableModel(){ //edit krnna ba
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.setColumnIdentifiers(columnsName);
        table.setModel(model);


        tableHeader.setFont(new Font("Tahoma",Font.BOLD,14));
        tableHeader.setBackground(new Color(164, 176, 190));
        table.setFont(new Font("Tahoma",Font.PLAIN,13));
        table.setBackground(new Color(223, 228, 234));
    }

    private String[] columnsDocTable(){
        return new String[]{"Name","Surname","Date of Birth","Mobile Number","Medical Licence number","specialisation"};
    }

    private void resizeWidthColum(){
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // resize column width
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(250);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
    }

    private void resizeWidthAvailableDocTable(){
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
    }
    private void addDocDetailData(){
        Object[] rowData = new Object[6];

        for(Doctor doctor: skin.getArray()){
            rowData[0] = doctor.getName();
            rowData[1] = doctor.getSurname();
            rowData[2] = doctor.getDateOfBirth();
            rowData[3] = doctor.getMobileNumber();
            rowData[4] = doctor.getMedicalLicenceNumber();
            rowData[5] = doctor.getSpecialisation();
            model.addRow(rowData);
        }
    }
    private void setPanel(){
        pane = new JScrollPane(table);
        pane.setBounds(20,500,1090,184);
        frame.add(pane);
    }

    private String[] columnsAvailableDocColumns(){
        return new String[]{"Name","Surname","Medical Licence Number"};
    }

    private void setAvailableDocTablePane(){
        pane = new JScrollPane(table);
        pane.setBounds(980,260,500,184);
        frame.add(pane);
    }

    private void addAvailableDocDetailData(LocalTime fromTime,LocalTime toTime,LocalDate bookingDate){
        Object[] rowData = new Object[3];

        ArrayList<Integer> docIDs = new ArrayList<>();

        for(Consultation consultation: skin.getConsultationArrayList()){
            if((consultation.getConsulDate().equals(bookingDate) && (consultation.getFromTime().isBefore(fromTime) && (consultation.getToTime().isAfter(fromTime)))) || (consultation.getConsulDate().equals(bookingDate) && (consultation.getFromTime().isBefore(toTime) && (consultation.getToTime().isAfter(toTime))))){

            }else{
                docIDs.add(consultation.getDoctorID());
            }
            setTime(consultation.getFromTime(),consultation.getToTime());
        }
        for(Doctor doctor: skin.getArray()){
            int number = doctor.getDoctorConsult().size();
            if(number == 0){
                docIDs.add(doctor.getMedicalLicenceNumber());
            }
        }

        for(Integer docId : docIDs){
            for(Doctor doctor: skin.getArray()){
                if(doctor.getMedicalLicenceNumber() == docId){
                    rowData[0] = doctor.getName();
                    rowData[1] = doctor.getSurname();
                    rowData[2] = doctor.getMedicalLicenceNumber();
                    model.addRow(rowData);
                }
            }
        }
    }
    private void setTime(LocalTime toTime1,LocalTime fromTime1){
        int fromTimeH = fromTime1.getHour();
        int fromTimeM = fromTime1.getMinute();
        time1 = Integer.parseInt(String.valueOf(fromTimeH)+String.valueOf(fromTimeM));

        int toTimeH = toTime1.getHour();
        int toTimeM = toTime1.getMinute();
        time2 = Integer.parseInt(String.valueOf(toTimeH)+String.valueOf(toTimeM));


    }

    public void hideTable(){
        pane.setVisible(false);
    }

    private String[] columnsNameForPatient(){
        return new String[]{"Name","Surname","Date of Birth","Mobile Number","Unique Id"};
    }

    private void addPatientDetailData(){
        Object[] rowData = new Object[5];

        for(Patient patient: skin.getPatientArrayList()){
            rowData[0] = patient.getName();
            rowData[1] = patient.getSurname();
            rowData[2] = patient.getDateOfBirth();
            rowData[3] = patient.getMobileNumber();
            rowData[4] = patient.getUniqueId();
            model.addRow(rowData);
        }
    }
    private void setPanelForPatientTable(){
        pane = new JScrollPane(table);
        pane.setBounds(40,10,900,200);
        frame.add(pane);
    }
}

