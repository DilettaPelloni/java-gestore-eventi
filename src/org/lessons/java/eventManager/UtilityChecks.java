package org.lessons.java.eventManager;

import java.util.Scanner;

public class UtilityChecks {

    //classe di utility per provare a razionalizzare i controlli

    //COSTRUTTORE -----------------------------------------------------------------------------------------
    //per evitare che venga istanziata creo un costruttore private
    private UtilityChecks() {}

    //METODI -----------------------------------------------------------------------------------------
    public static int requestDateElement(String type, int min, int max) {
        //preparo result
        int result = 0;
        //comincio il ciclo
        do {
            System.out.print(type + ": ");
            //se mi viene dato qualcosa di diverso da un numero lancio un'eccezione
            try {
                result = Integer.parseInt(Main.SCAN.nextLine());
                //se il numero non è in range lancio un'eccezione
                if(result < min || result > max) {
                    System.out.println("Il numero deve essere compreso fra " + min + " e " + max);
                }
            } catch (NumberFormatException e){
                System.out.println("Inserisci un numero");
            }
        } while (result < min || result > max);

        //restituisco il risultato
        return result;
    }

    public static String requestSNAnswer(String type) {
        String choice;
        do {
            System.out.println("Vuoi effettuare una " + type + "? s/n");
            choice = Main.SCAN.nextLine();
            if(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")) {
                System.out.println("L'input " + choice + " non è valido. Inserire 's' o 'n'.");
            }
        } while (!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n"));
        return choice;
    }
}
