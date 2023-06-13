package org.lessons.java.eventManager;

//IMPORT
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgrammEventi {

    //ATTRIBUTI -----------------------------------------------------------------------------------------
    private String title;
    private List<Event> events;

    //COSTRUTTORE -----------------------------------------------------------------------------------------
    public ProgrammEventi(String title) throws RuntimeException {
        //titolo
        if(isTitleValid(title)) {
            this.title = title;
        } else {
            throw new RuntimeException("Il titolo è un campo obbligatorio.");
        }
        //lista eventi
        this.events = new ArrayList<>();
    }

    //GETTER -----------------------------------------------------------------------------------------
    public String getTitle() {
        return title;
    }
    public List<Event> getEvents() {
        return events;
    }

    //METODI -----------------------------------------------------------------------------------------

    //pubblici ---------
    public void addEvent(Event event) { events.add(event); }

    public List<Event> getEventsByDate(LocalDate date) {
        //creo una lista che conterrà il risultato della ricerca
        List<Event> resultList = new ArrayList<>();
        //ciclo la lista di eventi
        for (Event ev : events) {
            //se la data dell'evento corrisponde alla data ricevuta come parametro
            if(ev.getDate().isEqual(date)){
                //aggiungo l'evento alla mia lista
                resultList.add(ev);
            }
        }
        //restituisco la lista
        return resultList;
    }

    public int howManyEvents(){ return events.size(); }

    public void emptyEventsList() { events.removeAll(events); }

    //override
    @Override
    public String toString() {
        //comincio a costruire la stringa
        String result = "Titolo del programma: " + title + "\n";
        //ciclo gli eventi e li aggiungo alla stinga
        for (Event ev : events) {
            result += ev.toString();
        }
        return  result;
    }

    //validatori ---------
    private boolean isTitleValid(String title) {
        return title != null && !title.isBlank();
    }
}
