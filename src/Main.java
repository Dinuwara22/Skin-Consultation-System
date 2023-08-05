/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Dinuwara
 */
import booking.WestminsterSkinConsultationManager;
import exception.FullException;
import gui.DashBoard;
import manage.SkinConsultationManager;

public class Main {
    public static void main(String[] args) throws Exception {

        WestminsterSkinConsultationManager skinObj = new WestminsterSkinConsultationManager();
        skinObj.readAllData();
        int option;
        do{
            option = skinObj.showMainMenu();
            switch (option) {
                case 1:
                    //Add doctor
                    skinObj.addDoctor();
                    break;
                case 2:
                    //Delete Doctor
                    skinObj.deleteDoctor();
                    break;
                case 3:
                    // To sort using surname
                    skinObj.sortSurname();
                    break;
                case 4:
                    //store data to text file
                    skinObj.saveData();
                    break;
                case 5:
                    //gui part
                    DashBoard f = new DashBoard(skinObj.getArraySize(),skinObj);
                    break;
                case 6:
                    //delete consultation
                    skinObj.deleteConsultation();
                    break;
                case 0:
                    //Exit
                    skinObj.saveAllData();
                    System.out.println("Exiting Programming....");
                    System.exit(1);
                    //return;
                default:
                    System.out.println("Please enter valid Option...!\n");
            }
        }while(option != 0);
    }
}
