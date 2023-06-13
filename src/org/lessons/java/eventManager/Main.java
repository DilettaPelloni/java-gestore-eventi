package org.lessons.java.eventManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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

        //entro nel menu per la gestione delle disdette
        em.cancelMenu();

        //chiudo lo scanner
        em.closeScanner();

        //TEST METODI CLASSE CONCERT --------------------------
//        Concert concert = new Concert(
//                //titolo
//                "mioConcerto",
//                //data
//                LocalDate.of(2024,02,02),
//                //posti totali
//                100,
//                //ora
//                LocalTime.of(23,15),
//                //prezzo
//                new BigDecimal(50.95)
//        );
//        System.out.println(concert);



    }
}
