/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hft_stuttgart.wilu1bif;

// imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    
    static float gesamtKilometer;
    static float gesamtVerbauch;
    static float gesamtKosten;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        // falls kein Fahrtenbuch existiert ein neues beginnen und mit den drei
        // sinnvollen Fahrten der Aufgabe füllen.
        // falls ein Fahrtenbuch existiert, den Nutzer zur Eingabe einer eigenen
        // Fahrt auffordern.
        if (leseWerte() == 1)
        {
            fahrtHinzufuegen(718.6F, 62.48F);
            fahrtHinzufuegen(687.3F, 60.85F);
            fahrtHinzufuegen(668.4F, 55.6F);
        } else {fahrtHinzufuegen(0, 0);}
        
        zeigeGesamtKilometer();
        zeigeGesamtKosten();
        zeigedurchschnittsVerbrauch();
        
        // vor exit schreibe werte in values.conf
        schreibeWerte();
    }
    
    /**
     * <nothing>
     */
    public static void zeigeGesamtKilometer()
    {
        System.out.print("Aktueller Kilometerstand laut Fahrtenbuch: ");
        System.out.printf("%.2f", gesamtKilometer);
        System.out.println("km.");
    }
    
    /**
     * <nothing>
     */
    public static void zeigeGesamtKosten()
    {
        System.out.print("Aktuelle Gesamtkosten laut Fahrtenbuch: ");
        System.out.printf("%.2f", gesamtKosten);
        System.out.println("€.");
    }
    
    /**
     * <nothing>
     */
    public static void zeigedurchschnittsVerbrauch()
    {
        // local vars
        float schnitt;
        
        schnitt = (gesamtVerbauch / gesamtKilometer) * 100;
        
        System.out.print("Aktuell liegt der Durchschnittsverbrauch bei: ");
        System.out.printf("%.2f", schnitt);
        System.out.println("L.");
    }
    /**
     * 
     * @param gefahreneKilometer
     * @param verbrauchterSprit 
     */
    public static void fahrtHinzufuegen(float gefahreneKilometer, float verbrauchterSprit)
    {
        // local vars
        float kilometerPreis = 0.20F;
        float literpreis = 1.30F;
        
        if (gefahreneKilometer == 0 && verbrauchterSprit == 0)
        {
        System.out.print("Bitte gefahrene Kilometer eingeben: ");
        gefahreneKilometer = in.nextFloat(); in.nextLine();
        
        System.out.print("Bitte verbrauchten Sprit eingeben: ");
        verbrauchterSprit = in.nextFloat(); in.nextLine();
        
        schreibeFahrt(gefahreneKilometer, verbrauchterSprit);
        }
        
        // Setze neue Gesamtkilometer
        gesamtKilometer += gefahreneKilometer;
        
        // Setze neuen Gesamtverbrauch
        gesamtVerbauch += verbrauchterSprit;
        
        // Setze neue Gesamtkosten
        gesamtKosten += (gefahreneKilometer * kilometerPreis);
        gesamtKosten += (verbrauchterSprit * literpreis);
        
    }
    
    /**
     * <nothing>
     */
    public static int leseWerte()
    {
        // local vars
        BufferedReader br = null;
        String[] values = new String[3];
        String[] gesamtKilometerTeile;
        String[] gesamtKostenTeile;
        String[] gesamtVerbrauchTeile;
        
        try
        {
            br = new BufferedReader (new InputStreamReader(new FileInputStream("values.conf")));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null)
            {
                
                if (line.contains("gesamt") && i < 3)
                {
                    values[i] = line;
                    i++;
                }
            }
            br.close();
        } catch (Exception error)
        {
            System.out.println("Keine Config-Datei vorhanden. Starte mit neuem Fahrtenbuch");
            return 1;
        }
        
        gesamtKilometerTeile = values[0].split(": ");
        gesamtKilometer = Float.parseFloat(gesamtKilometerTeile[1]);
        
        gesamtKostenTeile = values[1].split(": ");
        gesamtKosten = Float.parseFloat(gesamtKostenTeile[1]);
        
        gesamtVerbrauchTeile = values[2].split(": ");
        gesamtVerbauch = Float.parseFloat(gesamtVerbrauchTeile[1]);
        return 0;
    }
    
    /**
     * <nothing>
     */
    public static void schreibeWerte()
    {
        // local vars
        File file;
        PrintWriter out;
        
        // init
        out = null;
        file = new File ("values.conf");
        
        // Prüfe ob Datei existiert
        try
        {
            if (file.exists() == false) file.createNewFile();
            
            //öffne Datei ohne überschreiben
            out = new PrintWriter (file, "utf-8");
            
        } catch (IOException error)
        {
            error.printStackTrace();
        }
        
        // Schreibe Werte in Datei
        out.println("gesamtKilometer: " + gesamtKilometer);
        out.println("gesamtKosten: " + gesamtKosten);
        out.println("gesamtVerbrauch: " + gesamtVerbauch);
        out.close();
    }
    
    public static void schreibeFahrt(float km, float sprit)
    {
        // local vars
        File file;
        PrintWriter out;
        
        // init
        out = null;
        file = new File ("Fahrten.txt");
        
        // Prüfe ob Datei existiert
        try
        {
            if (file.exists() == false) file.createNewFile();
            
            //öffne Datei ohne überschreiben
            out = new PrintWriter (new FileWriter (file, true));
            
        } catch (IOException error)
        {
            error.printStackTrace();
        }
        
        System.out.print("Bitte geben Sie Ihren Namen an: ");
        out.println("Name: " + in.nextLine());
        out.println("Distanz: " + km + "km");
        out.println("Verbrauch: " + sprit + "L.");
        out.println();
        out.println();
        out.close();
    }
}
