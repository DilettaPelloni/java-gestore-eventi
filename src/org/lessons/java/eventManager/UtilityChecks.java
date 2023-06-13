package org.lessons.java.eventManager;

import java.util.Scanner;

public class UtilityChecks {

    //classe di utility per provare a razionalizzare i controlli

    //COSTRUTTORE -----------------------------------------------------------------------------------------
    //per evitare che venga istanziata creo un costruttore private
    private UtilityChecks() {}

    //METODI -----------------------------------------------------------------------------------------
    public static int requestIntInRange(int min, int max) throws RuntimeException {

        //verifico che min e max abbiano senso
        if(min > max) {
            throw new RuntimeException("Il minimo non può essere maggiore del massimo");
        }

        //preparo result
        int result = min - 1;

        //comincio il ciclo
        do {
            System.out.println("Inserisci un numero: ");
            //se mi viene dato qualcosa di diverso da un numero lancio un'eccezione
            try {
                result = Integer.parseInt(Main.SCAN.nextLine());
                //se il numero non è in range lancio un'eccezione
                if(result < min || result > max) {
                    System.out.println("Il numero deve essere compreso fra " + min + " e " + max);
                }
            } catch (NumberFormatException e){
                System.out.println("Devi inserire un numero!");
            }
        } while (result < min || result > max);

        //restituisco il risultato
        return result;
    }

    public static boolean requestSNAnswer(String type) {
        String choice;
        do {
            System.out.println("Vuoi effettuare una " + type + "? s/n");
            choice = Main.SCAN.nextLine();
            if(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")) {
                System.out.println("L'input " + choice + " non è valido. Inserire 's' o 'n'.");
            }
        } while (!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n"));
        return choice.equalsIgnoreCase("s");
    }
}
