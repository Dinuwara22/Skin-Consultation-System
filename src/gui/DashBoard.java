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
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Random;

public class DashBoard implements ActionListener{
    String mobileNumber;
    int cost;
    String notes;
    String uniqueId;
    String name;
    String surname;
    LocalTime toTime;
    LocalTime fromTime;
    LocalDate date;
    LocalDate bookingDate;
    int doctorId;
    int arraySize;
    JButton srtBtn;
    JFrame frame;
    Table table1;
    Table table4;
    JButton addBtn;
    JButton checkBtn;
    JTextField nameTxt;
    JTextField surnameTxt;
    JTextField dateOfBirthTxt;
    JTextField numberTxt;
    JTextField uniqueIdTxt;
    JButton submitBtn;
    JButton refreshBtn;
    JButton refresh2Btn;
    JButton saveBtn;
    Check check = new Check();
    WestminsterSkinConsultationManager skinObj;
    JLabel disIdLabel;
    JTextField tTimeTxt;
    JTextField fTimeTxt;
    JTextField dateTxt;
    JTextField docTxt;
    JTextField availableDateTxt;
    JTextField availableTimeTxt;
    JTextField notesTxt;
    JTextField availableToTimeTxt;
    ArrayList<JTextField> txtF = new ArrayList<>();
    ArrayList<JTextField> txtFForAvailableDoctors = new ArrayList<>();
    JButton patBtn;
    LocalDate availableDate;
    LocalTime availableTime;
    LocalTime availableToTime;
    JButton drBtn;


    public DashBoard(int size,WestminsterSkinConsultationManager skinObj){

        this.arraySize = size;
        this.skinObj = skinObj;

        frame = new JFrame();

        setPanel1();
        setPanel2();
        setPane3();

        table1 = new Table(frame,skinObj);
        table1.createDocDetailTable();

        sortButton();

        // Action Listener (button)
        srtBtn.addActionListener(this);
        addBtn.addActionListener(this);
        checkBtn.addActionListener(this);
        submitBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        patBtn.addActionListener(this);
        drBtn.addActionListener(this);
        refresh2Btn.addActionListener(this);

        createFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == srtBtn){
            sortTable();
        } else if (e.getSource() == addBtn) {
            addFile();
        } else if (e.getSource() == checkBtn) {
            afterCheckBtn();
        } else if(e.getSource() == submitBtn){
            setPatientDetails();
            setConsultationSize();
            setPatientSize();
        } else if (e.getSource() == refreshBtn) {
            afterRefreshBtn();
        } else if (e.getSource() == patBtn) {
            frame.dispose();
            PatientGui ptGui = new PatientGui(skinObj,frame);
        } else if (e.getSource() == drBtn) {
            frame.dispose();
            ConsultationGui cons = new ConsultationGui(frame,skinObj);
        } else if (e.getSource() == refresh2Btn) {
            table4.hideTable();
        }
    }

    private void afterRefreshBtn(){
        setEmptyTextField();
    }

    private void createFrame(){

        ImageIcon image = new ImageIcon("src\\hospital_logo.png");

        // Frame properties
        frame.setIconImage(image.getImage()); // set icon of window
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        //frame.setLocationRelativeTo(null); // center
        frame.setVisible(true); // display
        frame.setTitle("Skin Consultation Centre");
        frame.setBounds(10,10,1500,800);
        frame.setResizable(false); //can not resizable
        frame.getContentPane().setBackground(new Color(241, 242, 246));

    }
    private void setPanel1(){

        JLabel patientLabel = new JLabel("Patient Form");
        patientLabel.setBounds(20,20,500,40);
        patientLabel.setFont(new Font("Tahoma",Font.PLAIN,35));

        JLabel nameLabel = new JLabel("Name");
        addLabel(nameLabel,90,100,80,15);

        JLabel surnameLabel = new JLabel("Surname");
        addLabel(surnameLabel,480,100,80,15);

        JLabel dateOfBirthLabel = new JLabel("Date of Birth");
        addLabel(dateOfBirthLabel,45,140,100,15);

        JLabel exLabel = new JLabel("(Ex: 2023-01-11)");
        addLabel(exLabel,44,160,100,11);

        JLabel mobileLabel = new JLabel("Mobile Number");
        addLabel(mobileLabel,438,140,120,15);

        JLabel uniqueIdLabel = new JLabel("Consult Id");
        addLabel(uniqueIdLabel,60,280,80,15);

        JLabel doctorLabel = new JLabel("Doctor ID");
        addLabel(doctorLabel,60,320,80,15);

        JLabel patientUniqueId = new JLabel("Unique ID");
        addLabel(patientUniqueId,61,360,80,15);

        JLabel uniqueIdLbl = new JLabel("Unique Id");
        addLabel(uniqueIdLbl,80,360,80,15);

        JLabel date = new JLabel("Book Date");
        addLabel(date,54,240,80,15);

        disIdLabel = new JLabel(skinObj.getConsultId());
        disIdLabel.setBounds(150,280,80,20);
        disIdLabel.setFont(new Font("Roboto",Font.BOLD,15));

        JLabel fTime = new JLabel("From Time");
        addLabel(fTime,54,200,80,15);

        JLabel tTime = new JLabel("To Time");
        addLabel(tTime,487,200,80,15);

        JLabel notes = new JLabel("Notes");
        addLabel(notes,500,245,80,15);

        setTextFieldsForPanel1();
        setButtonForPanel1();

        JPanel panel1 = new JPanel();
        panel1.setBounds(20,50,920,400);
        panel1.setLayout(null);
        panel1.setBackground(new Color(223, 228, 234));

        panel1.add(nameLabel);
        panel1.add(surnameLabel);
        panel1.add(dateOfBirthLabel);
        panel1.add(mobileLabel);
        panel1.add(uniqueIdLabel);
        panel1.add(patientLabel);
        panel1.add(exLabel);
        panel1.add(patientUniqueId);
        panel1.add(disIdLabel);
        panel1.add(nameTxt);
        panel1.add(surnameTxt);
        panel1.add(dateOfBirthTxt);
        panel1.add(numberTxt);
        panel1.add(uniqueIdTxt);
        panel1.add(submitBtn);+
        panel1.add(refreshBtn);
        panel1.add(fTime);
        panel1.add(tTime);
        panel1.add(tTimeTxt);
        panel1.add(fTimeTxt);
        panel1.add(dateTxt);
        panel1.add(date);
        panel1.add(notes);
        panel1.add(addBtn);
        panel1.add(doctorLabel);
        panel1.add(docTxt);
        panel1.add(notesTxt);

        frame.add(panel1);

    }
    private void setTextFieldsForPanel1(){

        docTxt = new JTextField();
        docTxt.setBounds(150,320,200,20);

        dateTxt = new JTextField();
        dateTxt.setBounds(150,240,200,20);

        fTimeTxt = new JTextField();
        fTimeTxt.setBounds(150,200,200,20);

        tTimeTxt = new JTextField();
        tTimeTxt.setBounds(560,200,200,20);

        nameTxt = new JTextField();
        nameTxt.setBounds(150,100,200,20);

        surnameTxt = new JTextField();
        surnameTxt.setBounds(560,100,200,20);

        dateOfBirthTxt = new JTextField();
        dateOfBirthTxt.setBounds(150,140,200,20);

        numberTxt = new JTextField();
        numberTxt.setBounds(560,140,200,20);

        notesTxt = new JTextField();
        notesTxt.setBounds(560,245,200,20);

        uniqueIdTxt = new JTextField();
        uniqueIdTxt.setBounds(150,360,200,20);

        addArrayTextField();

    }
    private void setButtonForPanel1(){
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(680,340,80,30);
        submitBtn.setFocusable(false); // text eka wate iri na
        submitBtn.setBackground(new Color(112, 161, 255));
        submitBtn.setFont(new Font("Roboto",Font.PLAIN,13));

        refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(570,340,80,30);
        refreshBtn.setFocusable(false);
        refreshBtn.setBackground(new Color(112, 161, 255));
        refreshBtn.setFont(new Font("Roboto",Font.PLAIN,13));

        addBtn = new JButton("addFile");
        addBtn.setBounds(600,285,80,30);
        addBtn.setFocusable(false);
        addBtn.setBackground(new Color(255, 107, 129));
        addBtn.setFont(new Font("Roboto",Font.PLAIN,13));

    }

    private void addLabel(JLabel label,int x,int y,int width,int size){
        label.setBounds(x,y,width,20);
        label.setFont(new Font("Roboto",Font.PLAIN,size));
    }

    private void sortTable(){
        Collections.sort(skinObj.getArray());
        table1.hideTable();
        Table tb = new Table(frame,skinObj);
        tb.createDocDetailTable();
    }

    private void sortButton(){
        srtBtn = new JButton("Sort");
        srtBtn.setBounds(1000,710,80,30);
        srtBtn.setFocusable(false);
        srtBtn.setBackground(new Color(112, 161, 255));
        srtBtn.setFont(new Font("Roboto",Font.PLAIN,13));

        frame.add(srtBtn);
    }
    private void setPanel2(){
        drBtn = new JButton();
        drBtn.setText("Consults ");
        drBtn.setBounds(40,20,130,40);
        drBtn.setBackground(new Color(255, 71, 87));
        drBtn.setFocusable(false);
        drBtn.setFont(new Font("Tahoma",Font.PLAIN,13));

        patBtn = new JButton();
        patBtn.setText("Patient ("+ skinObj.getPatientArrayList().size() + ")");
        patBtn.setBounds(55 ,80,100,40);
        patBtn.setBackground(new Color(255, 71, 87));
        patBtn.setFocusable(false);
        patBtn.setFont(new Font("Tahoma",Font.PLAIN,13));

        JPanel panel2 = new JPanel();
        panel2.setBounds(1200,500,200,200);
        panel2.setLayout(null);
        panel2.setBackground(new Color(223, 228, 234));

        panel2.add(drBtn);
        panel2.add(patBtn);
        frame.add(panel2);

    }

    private void setPane3(){

        JLabel titLabel = new JLabel("Availability of the doctor");
        titLabel.setBounds(20,20,500,40);
        titLabel.setFont(new Font("Tahoma",Font.PLAIN,35));

        JLabel froTimeLabel = new JLabel("From time");
        froTimeLabel.setBounds(20,100,80,15);
        froTimeLabel.setFont(new Font("Roboto",Font.PLAIN,13));

        JLabel toTimeLbl = new JLabel("To time");
        toTimeLbl.setBounds(20,140,80,15);
        toTimeLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        JLabel availableDateLabel = new JLabel("Date");
        availableDateLabel.setBounds(260,100,80,20);
        availableDateLabel.setFont(new Font("Tahoma",Font.PLAIN,15));

        availableTimeTxt = new JTextField();
        availableTimeTxt.setBounds(110,100,100,20);

        availableToTimeTxt = new JTextField();
        availableToTimeTxt.setBounds(110,140,100,20);

        availableDateTxt = new JTextField();
        availableDateTxt.setBounds(330,100,100,20);

        checkBtn = new JButton("Check");
        checkBtn.setBounds(350,140,80,25);
        checkBtn.setBackground(new Color(112, 161, 255));
        checkBtn.setFocusable(false);
        checkBtn.setFont(new Font("Roboto",Font.PLAIN,13));

        refresh2Btn = new JButton("Refresh");
        refresh2Btn.setBounds(250,140,80,25);
        refresh2Btn.setBackground(new Color(112, 161, 255));
        refresh2Btn.setFocusable(false);
        refresh2Btn.setFont(new Font("Roboto",Font.PLAIN,13));


        JPanel panel3 = new JPanel();
        panel3.setBounds(980,50,490,180);
        panel3.setLayout(null);
        panel3.setBackground(new Color(223, 228, 234));


        panel3.add(titLabel);
        panel3.add(froTimeLabel);
        panel3.add(toTimeLbl);
        panel3.add(availableDateLabel);
        panel3.add(availableTimeTxt);
        panel3.add(availableToTimeTxt);
        panel3.add(availableDateTxt);
        panel3.add(checkBtn);
        panel3.add(refresh2Btn);
        frame.add(panel3);

    }
    private void setPatientDetails(){
        boolean flag1 = check.checkTxtFieldNull(frame,txtF);
        if(flag1){ // his ewa submit kroth error ekak pennva
            return;
        }

        getInput();
        displayUniqueId();
    }

    private void setConsultationSize(){
        drBtn.setText("Consults (" + skinObj.getConsultationArrayList().size() + ")");
    }
    private void setPatientSize(){
        patBtn.setText("Patient (" + skinObj.getPatientArrayList().size() + ")");
    }
    private void addArrayTextField(){ //array eka add kra gatta.txt field eka emptyda kiyala check kra ganna

        txtF.add(nameTxt);
        txtF.add(surnameTxt);
        txtF.add(dateOfBirthTxt);
        txtF.add(numberTxt);
        txtF.add(tTimeTxt);
        txtF.add(fTimeTxt);
        txtF.add(docTxt);
        txtF.add(dateTxt);
        txtF.add(uniqueIdTxt);

    }

    private void afterCheckBtn(){
        txtFForAvailableDoctors.add(availableDateTxt);
        txtFForAvailableDoctors.add(availableTimeTxt);
        txtFForAvailableDoctors.add(availableToTimeTxt);

        boolean flag = check.checkTxtFieldNull(frame,txtFForAvailableDoctors);
        if(flag){
            return;
        }

        getInputAvailableDoctor();
        table4 = new Table(frame,skinObj);
        table4.createAvailableDocTable(availableTime,availableToTime,availableDate); // wens krnna oni
        availableTimeTxt.setText("");
        availableToTimeTxt.setText("");
        availableDateTxt.setText("");
    }

    private void getInputAvailableDoctor(){

        try{
            availableDate = LocalDate.parse(availableDateTxt.getText());
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid date...");
            availableDateTxt.setText("");
            return;
        }

        try{
            availableToTime = LocalTime.parse(availableToTimeTxt.getText());
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid time...");
            availableTimeTxt.setText("");
        }
        try{
            availableTime = LocalTime.parse(availableTimeTxt.getText());
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid time...");
            availableTimeTxt.setText("");
        }
    }
    private void getInput(){

        name = nameTxt.getText().toLowerCase();
        surname = surnameTxt.getText().toLowerCase();
        notes = notesTxt.getText().toLowerCase();
        uniqueId = uniqueIdTxt.getText();
        mobileNumber = numberTxt.getText();


        try{
            doctorId = Integer.parseInt(docTxt.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid Number...");
            docTxt.setText("");
            return;
        }


        try{
            toTime = LocalTime.parse(tTimeTxt.getText());

            System.out.println(toTime.getHour() + toTime.getMinute());
        }catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid to time...");
            tTimeTxt.setText("");
            return;
        }

        try{
            fromTime = LocalTime.parse(fTimeTxt.getText());
        }catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid from Time...");
            fTimeTxt.setText("");
            return;
        }

        try{
            date = LocalDate.parse(dateOfBirthTxt.getText());
        }catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid date of Birth...");
            dateOfBirthTxt.setText("");
            return;
        }

        try{
            bookingDate = LocalDate.parse(dateTxt.getText());
        }catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(null,"Please Enter Valid booking date...");
            dateTxt.setText("");
            return;
        }

        if(skinObj.getConsultationArrayList().size() != 0){
            checkAvailableDoctor();
            setDoctorConsultId();
            setCost();
            skinObj.addToConsultationArray(bookingDate,toTime,fromTime,doctorId,cost,notes,uniqueId);
            addPatientToArray();
            setEmptyTextField();

        }else{
            setDoctorConsultId();
            setCost();
            skinObj.addToConsultationArray(bookingDate,toTime,fromTime,doctorId,cost,notes,uniqueId);
            setEmptyTextField();
            addPatientToArray();

        }

        JOptionPane.showMessageDialog(null,"Consultation Id : " + skinObj.getConsultationId()+"\n" +
                "Doctor Id : " + doctorId+"\n" +
                "Cost : " + euro() + cost+"\n" +
                "From Time : " + fromTime+"\n" +
                "To Time : " + toTime);


    }
    private String euro(){
        Currency euro = Currency.getInstance("EUR");
        return euro.getSymbol();
    }
    private void checkAvailableDoctor() {

        for(Consultation consultation: skinObj.getConsultationArrayList()){
            if(consultation.getDoctorID() == doctorId){
                boolean fromBoolean = (consultation.getConsulDate().equals(bookingDate) && (consultation.getFromTime().isBefore(fromTime) && (consultation.getToTime().isAfter(fromTime))));
                boolean toBoolean = (consultation.getConsulDate().equals(bookingDate) && (consultation.getFromTime().isBefore(toTime) && (consultation.getToTime().isAfter(toTime))));
                if( fromBoolean || toBoolean) {
                    skinObj.getArray();
                    addAnotherDoctor();
                    JOptionPane.showMessageDialog(null,"You are consultation is assign to new doctor\nDoctor Id :"+doctorId);
                    return;
                }
            }
        }
    }

    private void addAnotherDoctor(){
        ArrayList<Integer> docIDs = new ArrayList<>();

        for(Consultation consultation: skinObj.getConsultationArrayList()){
            boolean fromBool = (consultation.getConsulDate().equals(bookingDate) && (consultation.getFromTime().isBefore(fromTime) && (consultation.getToTime().isAfter(fromTime))));
            boolean toBool = (consultation.getConsulDate().equals(bookingDate) && (consultation.getFromTime().isBefore(toTime) && (consultation.getToTime().isAfter(toTime))));
            if(fromBool || toBool){

            }else{
                docIDs.add(consultation.getDoctorID());
            }
        }
        for(Doctor doctor: skinObj.getArray()){
            int number = doctor.getDoctorConsult().size();
            if(number == 0){
                docIDs.add(doctor.getMedicalLicenceNumber());
            }
        }
        int docIdsSize = docIDs.size();
        Random random = new Random();
        int x = random.nextInt(docIdsSize);
        doctorId = docIDs.get(x);
    }


    private void addPatientToArray(){
        skinObj.addToPatientArray(name,surname,date,mobileNumber,uniqueId);
    }
    private void displayUniqueId(){
        disIdLabel.setText(skinObj.getConsultId());
    }


    //doctor class eke array ekta add krnva
    private void setDoctorConsultId(){
        for(Doctor doctor: skinObj.getArray()){
            if(doctorId == doctor.getMedicalLicenceNumber()){
                doctor.addConsults(skinObj.returnConsultId()); // doctor class eke array ekta add krnva.
                return;
            }
        }
    }

    private void setCost(){
        int count = 0;
        int cost;
        for(Patient patient: skinObj.getPatientArrayList()){
            if(patient.getUniqueId().equals(uniqueId)){
                count++;
            }
        }
        if(count == 0){
            calPart(15);
        }else{
            calPart(25);
        }
    }
    private void calPart(int number){
        int fromTimeH = fromTime.getHour();
        int fromTimeM = fromTime.getMinute();
        int toTimeH = toTime.getHour();
        int toTimeM = toTime.getMinute();
        int minutes = toTimeM - fromTimeM;
        int hours = toTimeH - fromTimeH;
        if(minutes !=0){
            cost = (hours * number) + number;
        }else{
            cost = hours * number;
        }
    }



    private void addFile(){
        JFileChooser fileChooser = new JFileChooser();
        //int option = fileChooser.showOpenDialog(null);
        int option = fileChooser.showSaveDialog(null);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
           // System.out.println(file);
            try{
                int key = 1212;
                FileInputStream fis = new FileInputStream(file);
                byte data[] = new byte[0];
                try {
                    data = new byte[fis.available()];
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                fis.read(data);
                int i=0;
                for(byte b: data){
                    data[i] = (byte)(b ^ key);
                    i++;
                }
                FileOutputStream fos = new FileOutputStream("src\\details");
                fos.write(data);
                fos.close();
                fis.close();
                //System.out.println("End");
            }catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setEmptyTextField(){
        for(JTextField txtF : txtF){
            txtF.setText("");
        }
        notesTxt.setText("");
    }
}
