package org.lessons.java.eventManager;

//IMPORT
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {

    //ATTRIBUTI -----------------------------------------------------------------------------------------
    private String title;
    private LocalDate date;
    private int bookedSeats;
    //costanti --------
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final int seatCapacity;


    //COSTRUTTORE -----------------------------------------------------------------------------------------
    public Event(String title, LocalDate date, int seatCapacity) throws RuntimeException {
        //titolo
        if(isTitleValid(title)) {
            this.title = title;
        } else {
            throw new RuntimeException("Il titolo è un campo obbligatorio.");
        }
        //data
        if(isDateValid(date)) {
            this.date = date;
        } else {
            throw new RuntimeException("La data " + date.format(dateFormatter) + " non è valida. Deve essere successiva alla data odierna.");
        }
        //numero di posti totali
        if(isSeatCapacityValid(seatCapacity)) {
            this.seatCapacity = seatCapacity;
        } else {
            throw new RuntimeException("Il numero di posti " + seatCapacity + " non è valido. Deve essere maggiore di 0.");
        }
        //posti prenotati
        this.bookedSeats = 0;
    }


    //GETTER -----------------------------------------------------------------------------------------
    public String getTitle() {
        return title;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getFormattedDate(){
        return date.format(dateFormatter);
    }
    public int getSeatsCapacity() {
        return seatCapacity;
    }
    public int getBookedSeats() {
        return bookedSeats;
    }
    public int getAvailableSeats() {
        return seatCapacity - bookedSeats;
    }


    //SETTER -----------------------------------------------------------------------------------------
    public void setTitle(String title) throws RuntimeException {
        if(isTitleValid(title)) {
            this.title = title;
        } else {
            throw new RuntimeException("Il titolo è un campo obbligatorio.");
        }
    }
    public void setDate(LocalDate date) throws RuntimeException {
        if(isDateValid(date)) {
            this.date = date;
        } else {
            throw new RuntimeException("La data " + date.format(dateFormatter) + " non è valida. Deve essere successiva alla data odierna.");
        }
    }


    //METODI -----------------------------------------------------------------------------------------

    //pubblici ---------
    public void bookSeat() throws RuntimeException {
        // ------------- VERIFICO LA DATA -------------
        //se la data di oggi è successiva alla data dell'evento non è possibile prenotare
        if(isEventPast()) {
            throw new RuntimeException("L'evento è trascorso. Non è possibile prenotare");
        }

        // ---------- VERIFICO IL NUMERO DI POSTI DISPONIBILI ----------
        //se i posti disponibili sono pari o minori di 0 non è possibile prenotare
        if(getAvailableSeats() <= 0) {
            throw new RuntimeException("Non ci sono sufficienti posti disponibili. Non è possibile procedere con la prenotazione");
        }

        // ---------- SE CI SONO TUTTI I PRESUPPOSTI ----------
        //aumento di 1 i posti prenotati
        this.bookedSeats ++;
    }
    public void cancelSeat() {
        // ------------- VERIFICO LA DATA -------------
        //se la data di oggi è successiva alla data dell'evento non è possibile disdire
        if(isEventPast()) {
            throw new RuntimeException("L'evento è trascorso. Non è possibile disdire");
        }

        // ---------- VERIFICO IL NUMERO DI POSTI PRENOTATI ----------
        //se i posti prenotati sono pari o minori di 0 non è possibile disdire
        if(this.bookedSeats <= 0) {
            throw new RuntimeException("Non ci sono posti prenotati. Non è possibile disdire");
        }

        // ---------- SE CI SONO TUTTI I PRESUPPOSTI ----------
        //diminuisco di 1 i posti prenotati
        this.bookedSeats --;
    }

    //override ---------
    @Override
    public String toString() {
        return "Data: " + date.format(dateFormatter) + ", Titolo: " + title + "\n";
    }

    //validatori ---------
    private boolean isEventPast() {
        //prendo la data di oggi
        LocalDate today = LocalDate.now();
        //la confronto con la data dell'evento
        return today.isAfter(this.date);
    }
    //ho reso i seguenti validatori statici così posso usarli per validare al di fuori della classe
    public static boolean isDateValid(LocalDate date) {
        //prendo la data di oggi
        LocalDate today = LocalDate.now();
        //la confronto con la data ricevuta
        return date.isAfter(today);
    }
    public static boolean isSeatCapacityValid(int seatCapacity) {
        //il numero di posti totali deve essere maggiore di 0
        return seatCapacity > 0;
    }
    public static boolean isTitleValid(String title) {
        return title != null && !title.isBlank();
    }

}
