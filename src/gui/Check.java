/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import typeofperson.Doctor;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Check {
    public boolean checkTxtFieldNull(JFrame frame, ArrayList<JTextField> txtF){
        boolean flag = false;
        for(JTextField textFields: txtF){
            String txt = textFields.getText();
            if(txt.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter input");
                frame.repaint();
                flag = true;
                return flag;
            }
        }
        return flag;
    }

}
