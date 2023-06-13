package org.lessons.java.eventManager;

//IMPORT
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{

    //ATTRIBUTI -----------------------------------------------------------------------------------------
    LocalTime time;
    BigDecimal price;

    //COSTRUTTORE -----------------------------------------------------------------------------------------
    public Concert(String title, LocalDate date, int SEAT_CAPACITY, LocalTime time, BigDecimal price) throws RuntimeException {
        //richiamo il costruttore di Event
        super(title, date, SEAT_CAPACITY);
        //ora
        this.time = time;
        //prezzo
        if (isPriceValid(price)) {
            this.price = price;
        } else {
            throw new RuntimeException("Il prezzo non può essere negativo");
        }

    }

    //GETTER -----------------------------------------------------------------------------------------
    public LocalTime getTime() {
        return time;
    }
    public BigDecimal getPrice() {
        return price;
    }

    //SETTER -----------------------------------------------------------------------------------------
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public void setPrice(BigDecimal price) {
        if (isPriceValid(price)) {
            this.price = price;
        } else {
            throw new RuntimeException("Il prezzo non può essere negativo");
        }
    }

    //METODI -----------------------------------------------------------------------------------------
    //validatori ---------
    private boolean isPriceValid(BigDecimal price) {
        //non accetto prezzi negativi (o è ok perché accetto concerti gratuiti)
        return price.compareTo(new BigDecimal(0)) >= 0;
    }

}
