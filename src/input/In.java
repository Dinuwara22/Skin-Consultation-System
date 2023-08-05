/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package input;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class In {

    static Scanner scn = new Scanner(System.in); // static denme okkma method wlta scanner eka use krnna ona nisa.

    /**
     * This method use to prints what needs to be done and returns a string value.
     * @param message this is A message to be shown to the user when inputting
     * @return The value(String) entered by the user
     * */
    public static String readStr(String message){
        System.out.println("Enter " + message + " : ");
        return scn.nextLine();
    }

    /**
     * This method use to prints what needs to be done and returns an integer value and fix given error.
     * @param message This is a message to be shown to the user when inputting
     * @return The value(Int) entered by the user
     * */
    public static int readInt(String message){
        while(true){
            try{
                Scanner int2 = new Scanner(System.in);
                System.out.println("Enter " + message + " : ");
                int i = int2.nextInt();
                int2.nextLine();
                return i;
            }catch (Exception e){
                System.out.println("\nPlease try again...");
            }
        }
    }

    public static LocalDate readDate(){
        while(true){
            try{
                return LocalDate.parse(readStr("Date of Birth (ex: '2022-12-05')"));
            }catch(Exception e){
                System.out.println("Please Try again...");
            }
        }
    }

}
