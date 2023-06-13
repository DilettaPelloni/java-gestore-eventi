package org.lessons.java.eventManager;

//IMPORT
import java.io.Closeable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EventManager {

    //ATTRIBUTI -----------------------------------------------------------------------------------------
    Event event; //per ora è prevista la gestione di un singolo evento

    //GETTER -----------------------------------------------------------------------------------------
    public Event getEvent() {
        return event;
    }

    //METODI -----------------------------------------------------------------------------------------

    public void createNewEvent() {
        System.out.println("\n-------------- BENVENUTO NEL MENU PER LA CREAZIONE DI UN NUOVO EVENTO --------------");
        //raccolgo il titolo ---------------------------------------------------------------
        String title;
        do {
            System.out.println("Come si chiamerà l'evento?");
            title = Main.SCAN.nextLine();
            //se il titolo non è valido stampo un messaggio
            if(!Event.isTitleValid(title)) {
                System.out.println("Il titolo è un campo obbligatorio.");
            }
        //ripeto finché non ho un titolo valido
        } while (!Event.isTitleValid(title));

        //raccolgo la data ---------------------------------------------------------------
        boolean isValid = false;
        LocalDate date = null;
        do {
            System.out.println("Quando si terrà l'evento?");
            //prendo giorno mese e anno
            System.out.println("Giorno");
            int day = UtilityChecks.requestIntInRange(1, 31);
            System.out.println("Mese");
            int month = UtilityChecks.requestIntInRange(1, 12);
            System.out.println("Anno");
            int year = UtilityChecks.requestIntInRange(0, 9999);
            //Questi metodi lanciano un eccezione se si invertono min e max, scelgo di non gestirla

            //creo la data
            try {
                date = LocalDate.of(year, month, day);
                //se la data non è valida stampo un messaggio
                if(!Event.isDateValid(date)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("La data " + date.format(formatter) + " non è valida. Deve essere successiva alla data odierna.");
                } else {
                    isValid = true;
                }
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            }

        //ripeto finché non ho una data valida
        } while (!isValid);

        //raccolgo il numero massimo di posti ---------------------------------------------------------------
        System.out.println("Quanti posti disponibili ci sono?");
        int seatCapacity = UtilityChecks.requestIntInRange(1, 9999);

        //creo il nuovo evento
        try {
            this.event = new Event(title,date,seatCapacity);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        //stampo l'evento
        System.out.println("Nuovo evento creato con successo:");
        System.out.println(event);

    }

    public void bookingMenu() {
        System.out.println("\n-------------- BENVENUTO NEL MENU PER LA PRENOTAZIONE --------------");

        //chiedo all'utente se vuole procedere
        boolean letsBook = UtilityChecks.requestSNAnswer("prenotazione");

        //se l'utente vuole procedere
        if(letsBook) {
            //raccolgo il numero delle prenotazioni
            System.out.println("Quanti posti vuoi prenotare?");
            int bookingNum = UtilityChecks.requestIntInRange(1, 9999);
            //qua forse potrei usare come max del metodo il numero di posti massimo
            //e dare un messaggio consono in caso di errore

            //provo a prenotare un numero di volte pari a quello desiderato dall'utente
            try {
                for (int i = 0; i < bookingNum; i++) {
                    event.bookSeat();
                    //se va tutto bene (bookSeat non ha tirato eccezioni)
                    System.out.println("Prenotazione del " + (i+1) +"° posto avvenuta con successo");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            //Mostro lo stato dei posti
            event.printSeatState();
        }
    }

    public void cancelMenu() {
        System.out.println("\n-------------- BENVENUTO NEL MENU PER LA DISDETTA --------------");

        //chiedo all'utente se vuole procedere
        boolean letsCancel = UtilityChecks.requestSNAnswer("disdetta");

        //se l'utente vuole procedere
        if(letsCancel) {
            //raccolgo il numero delle disdette
            System.out.println("Quanti posti vuoi disdire?");
            int cancelNum = UtilityChecks.requestIntInRange(1, 9999);

            //provo a disdire un numero di volte pari a quello desiderato dall'utente
            try {
                for (int i = 0; i < cancelNum; i++) {
                    event.cancelSeat();
                    //se va tutto bene (cancelSeat non ha tirato eccezioni)
                    System.out.println("Disdetta del " + (i+1) +"° posto avvenuta con successo");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            //Mostro lo stato dei posti
            event.printSeatState();
        }
    }
}
