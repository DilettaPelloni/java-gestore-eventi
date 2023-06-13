package org.lessons.java.eventManager;

//IMPORT (vengono usati nei test)
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public final static Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args){

        //creo un nuovo EventManager
        EventManager em = new EventManager();

        //creo un nuovo evento
        em.createNewEvent();

        //entro nel menu per la gestione delle prenotazioni
        em.bookingMenu();

        //entro nel menu per la gestione delle disdette
        em.cancelMenu();

        //chiudo lo scanner
        SCAN.close();

//        //TEST METODI CLASSE CONCERT --------------------------
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
//        //risultato: Data e ora: 02/02/2024 - 23:15, Titolo: mioConcerto, Prezzo: 50,95€

        //TEST METODI CLASSE PROGRAMMEVENTI --------------------------
//        ProgrammEventi newProgram = new ProgrammEventi("mioProgramma");
//        newProgram.addEvent(new Event("primo evento",LocalDate.of(2024,02,02),100));
//        newProgram.addEvent(new Event("secondo evento",LocalDate.of(2024,02,02),150));
//        newProgram.addEvent(new Event("terzo evento",LocalDate.of(2024,05,20),50));
//        newProgram.addEvent(new Event("quarto evento",LocalDate.of(2024,05,20),2500));
//
//        System.out.println(newProgram);
//        //risultato
//        //Titolo del programma: mioProgramma
//        //Data: 02/02/2024, Titolo: primo evento
//        //Data: 02/02/2024, Titolo: secondo evento
//        //Data: 20/05/2024, Titolo: terzo evento
//        //Data: 20/05/2024, Titolo: quarto evento
//
//        LocalDate myDate = LocalDate.of(2024,02,02);
//        System.out.println(newProgram.getEventsByDate(myDate));
//        //risultato
//        //[Data: 02/02/2024, Titolo: primo evento
//        //, Data: 02/02/2024, Titolo: secondo evento
//        //]
//
//        System.out.println(newProgram.howManyEvents());
//        //risultato
//        //4
//
//        newProgram.emptyEventsList();
//        System.out.println(newProgram);
//        //risultato
//        //Titolo del programma: mioProgramma

    }
}
