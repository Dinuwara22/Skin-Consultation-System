/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import booking.Consultation;
import booking.WestminsterSkinConsultationManager;
import manage.SkinConsultationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Currency;

public class ConsultationGui implements ActionListener {
    JFrame frame1;
    JFrame frame2;
    JPanel panel1;
    JPanel panel2;
    JTextField searchTxt;
    WestminsterSkinConsultationManager skin;
    JButton searchBtn;
    JButton backBtn;
    JTextField consultTxt;
    JTextField patientNameTxt;
    JTextField doctorFullNameTxt;
    JTextField costTxt;
    JTextField bookDateTxt;
    JTextField fromTimeTxt;
    JTextField toTimeTxt;
    JTextField notesTxt;
    public ConsultationGui(JFrame frame1,WestminsterSkinConsultationManager skin) {
        this.frame1 = frame1;
        this.skin = skin;

        frame2 = new JFrame();

        setPanel();
        setPanel2();
        setBackBtn();

        searchBtn.addActionListener(this);
        backBtn.addActionListener(this);

        createFrame();
    }
    private void createFrame(){

        ImageIcon image = new ImageIcon("src\\hospital_logo.png");

        // Frame properties
        frame2.setIconImage(image.getImage()); // set icon of window
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(null);
        //frame.setLocationRelativeTo(null); // center
        frame2.setVisible(true); // display
        frame2.setTitle("Skin Consultation Centre");
        frame2.setBounds(450,50,700,600);
        frame2.setResizable(false); //can not resizable
        frame2.getContentPane().setBackground(new Color(241, 242, 246));

    }

    private void setPanel(){
        JLabel consultIdLbl = new JLabel("Consultation ID :");
        consultIdLbl.setBounds(10,20,140,20);
        consultIdLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        consultTxt = new JTextField();
        consultTxt.setBounds(170,20,180,20);

        JLabel patientNameLbl = new JLabel("Patient Id :");
        patientNameLbl.setBounds(10,60,140,20);
        patientNameLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        patientNameTxt = new JTextField();
        patientNameTxt.setBounds(170,60,180,20);

        JLabel doctorFullNameLbl = new JLabel("Doctor ID :");
        doctorFullNameLbl.setBounds(10,100,140,20);
        doctorFullNameLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        doctorFullNameTxt = new JTextField();
        doctorFullNameTxt.setBounds(170,100,180,20);

        JLabel costLbl = new JLabel("Cost :");
        costLbl.setBounds(10,140,140,20);
        costLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        costTxt = new JTextField();
        costTxt.setBounds(170,140,140,20);

        JLabel bookDateLbl = new JLabel("Book Date :");
        bookDateLbl.setBounds(10,180,140,20);
        bookDateLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        bookDateTxt = new JTextField();
        bookDateTxt.setBounds(170,180,180,20);

        JLabel fromTimeLbl = new JLabel("From Time :");
        fromTimeLbl.setBounds(10,220,140,20);
        fromTimeLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        fromTimeTxt = new JTextField();
        fromTimeTxt.setBounds(170,220,180,20);

        JLabel toTimeLbl = new JLabel("To Time :");
        toTimeLbl.setBounds(10,260,180,20);
        toTimeLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        toTimeTxt = new JTextField();
        toTimeTxt.setBounds(170,260,180,20);

        JLabel notesLbl = new JLabel("Notes :");
        notesLbl.setBounds(10,300,180,20);
        notesLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        notesTxt = new JTextField();
        notesTxt.setBounds(170,300,180,20);

        panel1 = new JPanel();
        panel1.setBounds(100,100,490,350);
        panel1.setLayout(null);
        panel1.setBackground(new Color(223, 228, 234));
        panel1.setVisible(false);

        panel1.add(consultIdLbl);
        panel1.add(patientNameLbl);
        panel1.add(doctorFullNameLbl);
        panel1.add(costLbl);
        panel1.add(bookDateLbl);
        panel1.add(fromTimeLbl);
        panel1.add(toTimeLbl);
        panel1.add(notesLbl);
        panel1.add(consultTxt);
        panel1.add(patientNameTxt);
        panel1.add(doctorFullNameTxt);
        panel1.add(costTxt);
        panel1.add(bookDateTxt);
        panel1.add(fromTimeTxt);
        panel1.add(toTimeTxt);
        panel1.add(notesTxt);
        frame2.add(panel1);
    }

    private boolean setValue(){
        for(Consultation consultation: skin.getConsultationArrayList()){
            if(searchTxt.getText().equals(consultation.getConsultationID())){
                consultTxt.setText(consultation.getConsultationID());
                patientNameTxt.setText(consultation.getPatientId());
                doctorFullNameTxt.setText(String.valueOf(consultation.getDoctorID()));
                costTxt.setText(euro() + String.valueOf(consultation.getCost()));
                bookDateTxt.setText(String.valueOf(consultation.getConsulDate()));
                fromTimeTxt.setText(String.valueOf(consultation.getFromTime()));
                toTimeTxt.setText(String.valueOf(consultation.getToTime()));
                notesTxt.setText(consultation.getNotes());

                consultTxt.setEditable(false);
                patientNameTxt.setEditable(false);
                doctorFullNameTxt.setEditable(false);
                costTxt.setEditable(false);
                bookDateTxt.setEditable(false);
                fromTimeTxt.setEditable(false);
                toTimeTxt.setEditable(false);
                notesTxt.setEditable(false);

                return false;
            }
        }
        return true;
    }

    private String euro(){
        Currency euro = Currency.getInstance("EUR");
        return euro.getSymbol();
    }

    public void afterSearchBtn(){
        if(setValue()){
            JOptionPane.showMessageDialog(null,"Can not find with this id");
            searchTxt.setText("");
            panel1.setVisible(false);
        }else{
            panel1.setVisible(true);
        }
    }

    private void setPanel2(){
        searchBtn = new JButton("Search");
        searchBtn.setBounds(350,10,80,20);
        searchBtn.setBackground(new Color(112, 161, 255));
        searchBtn.setFocusable(false);
        searchBtn.setFont(new Font("Roboto",Font.PLAIN,13));

        JLabel searchLbl = new JLabel("Enter Consultation Id :");
        searchLbl.setBounds(10,10,140,20);
        searchLbl.setFont(new Font("Roboto",Font.PLAIN,13));

        searchTxt = new JTextField();
        searchTxt.setBounds(150,10,180,20);

        panel2 = new JPanel();
        panel2.setBounds(100,30,490,40);
        panel2.setLayout(null);
        panel2.setBackground(new Color(223, 228, 234));

        panel2.add(searchBtn);
        panel2.add(searchTxt);
        panel2.add(searchLbl);

        frame2.add(panel2);
    }
    private void setBackBtn(){
        backBtn = new JButton("Back");
        backBtn.setBounds(510,470,80,30);
        backBtn.setBackground(new Color(112, 161, 255));
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Roboto",Font.PLAIN,13));

        frame2.add(backBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchBtn){
            afterSearchBtn();
        } else if (e.getSource() == backBtn) {
            frame1.setVisible(true);
            frame2.dispose();
        }
    }
}

