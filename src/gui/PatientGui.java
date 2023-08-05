/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import booking.WestminsterSkinConsultationManager;
import manage.SkinConsultationManager;
import typeofperson.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PatientGui implements ActionListener {
    JFrame frame2;
    JFrame frame;
    JButton backBtn;
    WestminsterSkinConsultationManager skin;
    public PatientGui(WestminsterSkinConsultationManager skin,JFrame frame) {
        this.skin = skin;
        this.frame = frame;

        frame2 = new JFrame();

        Table table3 = new Table(frame2,skin);
        table3.createPatientTable();

        setBackBtn();

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
        frame2.setBounds(280,200,1000,350);
        frame2.setResizable(false); //can not resizable
        frame2.getContentPane().setBackground(new Color(241, 242, 246));

    }
    private void setBackBtn(){
        backBtn = new JButton("Back");
        backBtn.setBounds(850,250,80,30);
        backBtn.setBackground(new Color(112, 161, 255));
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Roboto",Font.PLAIN,13));

        frame2.add(backBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
            frame2.dispose();
            frame.setVisible(true);
        }
    }
}
