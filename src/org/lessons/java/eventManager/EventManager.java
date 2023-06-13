package org.lessons.java.eventManager;

//IMPORT
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EventManager {

    //ATTRIBUTI -----------------------------------------------------------------------------------------
    Event event; //per ora è prevista la gestione di un singolo evento
    Scanner scan;

    //METODI -----------------------------------------------------------------------------------------
    public void openScanner() {
        scan = new Scanner(System.in);
    }
    public void closeScanner() {
        if(scan != null) {
            scan.close();
        }
    }

    public void createNewEvent() {
        System.out.println("-------------- BENVENUTO NEL MENU PER LA CREAZIONE DI UN NUOVO EVENTO --------------");
        //raccolgo il titolo ---------------------------------------------------------------
        String title;
        do {
            System.out.println("Come si chiamerà l'evento?");
            title = scan.nextLine();
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

            //prendo il giorno
            int day = 0;
            do {
                System.out.print("Giorno: ");
                try {
                    day = Integer.parseInt(scan.nextLine());
                    //se il giorno non è compreso fra 1 e 31
                    if(day < 1 || day > 31 ) {
                        System.out.println("Il giorno deve essere un numero positivo compreso fra 1 e 31");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Inserisci un numero");
                }
            } while (day < 1 || day > 31);

            //prendo il mese
            int month = 0;
            do {
                System.out.print("Mese: ");
                try {
                    month = Integer.parseInt(scan.nextLine());
                    //se il giorno non è compreso fra 1 e 31
                    if(month < 1 || month > 12 ) {
                        System.out.println("Il mese deve essere un numero positivo compreso fra 1 e 12");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Inserisci un numero");
                }
            } while (month < 1 || month > 12);

            //prendo l'anno
            int year = 0;
            do {
                System.out.print("Anno: ");
                try {
                    year = Integer.parseInt(scan.nextLine());
                    //se l'anno è negativo
                    if(year < 0) {
                        System.out.println("L'anno deve essere un numero positivo");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Inserisci un numero");
                }
            } while (year < 0);

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
                seatCapacity = Integer.parseInt(scan.nextLine());
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
        System.out.println("-------------- BENVENUTO NEL MENU PER LA PRENOTAZIONE --------------");
        //chiedo all'utente se vuole procedere
        String choice;
        do {
            System.out.println("Vuoi effettuare una prenotazione? s/n");
            choice = scan.nextLine();
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
                    bookingNum = Integer.parseInt(scan.nextLine());
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
                }
                //se va tutto bene (bookSeat non ha tirato eccezioni)
                System.out.println("Prenotazione di n° " + bookingNum +" posti avvenuta con successo");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }


    }

}
