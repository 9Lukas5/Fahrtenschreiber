/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hft_stuttgart.wilu1bif;

// imports
import java.util.Scanner;

/**
 * 
 * HFT-Stuttgart, Semester 1+, Hausaufgabe 4
 * Fahrtenschreiber programmieren
 * @author Lukas Wiest
 */
public class Fahrtenschreiber
{
    // class vars
    static Scanner in = new Scanner (System.in);
    
    static int gesamtKilometer;
    static float gesamtVerbauch;
    static float gesamtKosten;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
    }
    
    public static void zeigeGesamtKilometer()
    {
        System.out.print("Aktueller Kilometerstand laut Fahrtenbuch: ");
        System.out.println(gesamtKilometer + "km");
    }
}
