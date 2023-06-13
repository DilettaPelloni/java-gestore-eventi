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

            int day = UtilityChecks.requestDateElement("Giorno", 1, 31);
            int month = UtilityChecks.requestDateElement("Mese", 1, 12);
            int year = UtilityChecks.requestDateElement("Anno", 0, 9999);

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
        int seatCapacity = 0;
        do {
            System.out.println("Quanti posti disponibili ci sono?");
            try {
                seatCapacity = Integer.parseInt(Main.SCAN.nextLine());
                //se il valore non è valido
                if(!Event.isSeatCapacityValid(seatCapacity)) {
                    System.out.println("Il numero di posti " + seatCapacity + " non è valido. Deve essere maggiore di 0.");
                }
            } catch (NumberFormatException e){
                System.out.println("Inserisci un numero");
            }
        //ripeto finché non ho un numero valido
        } while (!Event.isSeatCapacityValid(seatCapacity));

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
        String choice;
        do {
            System.out.println("Vuoi effettuare una prenotazione? s/n");
            choice = Main.SCAN.nextLine();
            if(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")) {
                System.out.println("L'input " + choice + " non è valido. Inserire 's' o 'n'.");
            }
        } while (!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n"));

        boolean letsBook = choice.equalsIgnoreCase("s");

        //se l'utente vuole procedere
        if(letsBook) {
            //raccolgo il numero delle prenotazioni
            int bookingNum = 0;
            do {
                System.out.println("Quanti posti vuoi prenotare?");
                try {
                    bookingNum = Integer.parseInt(Main.SCAN.nextLine());
                    if(bookingNum <= 0) {
                        System.out.println("Il numero deve essere maggiore di 0");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Inserisci un numero");
                }
            } while(bookingNum <= 0);

            //provo a prenotare un numero di volte pari a quello desiderato dall'utente
            try {
                for (int i = 0; i < bookingNum; i++) {
                    event.bookSeat();
                    //se va tutto bene (bookSeat non ha tirato eccezioni)
                    System.out.println("Prenotazione di n° " + (i+1) +" posti avvenuta con successo");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            //Mostro lo stato dei posti
            System.out.println("\nStato dell'evento: ");
            System.out.println("Numero di posti prenotati: " + event.getBookedSeats());
            System.out.println("Numero di posti disponibili: " + event.getAvailableSeats());
        }
    }

    public void cancelMenu() {
        System.out.println("\n-------------- BENVENUTO NEL MENU PER LA DISDETTA --------------");
        //chiedo all'utente se vuole procedere
        String choice;
        do {
            System.out.println("Vuoi effettuare una disdetta? s/n");
            choice = Main.SCAN.nextLine();
            if(!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n")) {
                System.out.println("L'input " + choice + " non è valido. Inserire 's' o 'n'.");
            }
        } while (!choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("n"));

        boolean letsCancel = choice.equalsIgnoreCase("s");

        //se l'utente vuole procedere
        if(letsCancel) {
            //raccolgo il numero delle disdette
            int cancelNum = 0;
            do {
                System.out.println("Quanti posti vuoi disdire?");
                try {
                    cancelNum = Integer.parseInt(Main.SCAN.nextLine());
                    if(cancelNum <= 0) {
                        System.out.println("Il numero deve essere maggiore di 0");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Inserisci un numero");
                }
            } while(cancelNum <= 0);

            //provo a disdire un numero di volte pari a quello desiderato dall'utente
            try {
                for (int i = 0; i < cancelNum; i++) {
                    event.cancelSeat();
                    //se va tutto bene (cancelSeat non ha tirato eccezioni)
                    System.out.println("Disdetta di n° " + (i+1) +" posti avvenuta con successo");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            //Mostro lo stato dei posti
            System.out.println("\nStato dell'evento: ");
            System.out.println("Numero di posti prenotati: " + event.getBookedSeats());
            System.out.println("Numero di posti disponibili: " + event.getAvailableSeats());
        }
    }

}
