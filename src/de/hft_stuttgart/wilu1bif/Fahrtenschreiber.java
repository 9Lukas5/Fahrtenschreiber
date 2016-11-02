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
        System.out.println(gesamtKilometer + "km.");
    }
    
    public static void zeigeGesamtKosten()
    {
        System.out.print("Aktuelle Gesamtkosten laut Fahrtenbuch: ");
        System.out.println(gesamtKosten + "€.");
    }
    
    /**
     * Bei verbrauchAlsInt wird der aktuelle Gesamtverbrauch um vier stellen
     * nach links verschoben und dann in einen Integer gewandelt.
     * nach der Durchschnittsrechnung muss das Ergebnis wieder um zwei Stellen
     * nach rechts geschoben werden um ein bis zu zweistelliges Ergebnis zu bekommen.
     */
    
    public static void zeigedurchschnittsVerbrauch()
    {
        // local vars
        float schnitt;
        // Durchschnitt soll mit bis zu zwei Nachkommastellen angezeigt werden
        int verbrauchAlsInt = (int) (gesamtVerbauch * 10000);
        
        schnitt = (float) (verbrauchAlsInt / gesamtKilometer) / 100 ;
        
        System.out.print("Aktuell liegt der Durchschnittsverbrauch bei: ");
        System.out.println(schnitt + "l.");
    }
    
    public static void fahrtHinzufuegen(int gefahreneKilometer, float verbrauchterSprit)
    {
        // local vars
        int gesamtVerbrauchInt;
        int spritInt;
        int kilometerPreis = 20;
        int literpreis = 130;
        
        if (gefahreneKilometer == 0 && verbrauchterSprit == 0)
        {
        System.out.print("Bitte gefahrene Kilometer eingeben (ganze Kilometer): ");
        gefahreneKilometer = in.nextInt(); in.nextLine();
        
        System.out.print("Bitte verbrauchten Sprit eingeben (max 2 Nachkommastellen): ");
        verbrauchterSprit = in.nextFloat(); in.nextLine();
        }
        
        spritInt = (int) (verbrauchterSprit * 100);
        
        // Setze neu Gesamtkilometer
        gesamtKilometer += gefahreneKilometer;
        
        // Setze neuen Gesamtverbrauch
        gesamtVerbrauchInt = (int) (gesamtVerbauch * 100);
        gesamtVerbauch = (float) ((gesamtVerbrauchInt + spritInt) / 100);
        
        // entstandene Kosten
        gesamtKosten += ((gefahreneKilometer * kilometerPreis) / 100);
        gesamtKosten += ((spritInt * literpreis) / 10000);
    }
}
