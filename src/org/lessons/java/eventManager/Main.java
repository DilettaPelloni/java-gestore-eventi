package org.lessons.java.eventManager;

public class Main {
    public static void main(String[] args) {

        //creo un nuovo EventManager
        EventManager em = new EventManager();
        //apro lo scanner
        em.openScanner();
        //creo un nuovo evento
        em.createNewEvent();
        //entro nel menu per la gestione delle prenotazioni
        em.bookingMenu();
        //chiudo lo scanner
        em.closeScanner();

    }
}
