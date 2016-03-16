package com.epam.task5.entity;

import javax.xml.bind.annotation.*;

/**
 * Created by Никита on 29.12.2015.
 */

public class Card {
    private String year;
    private String thema;
    private boolean sending;
    private String country;
    private String valuable;

    public Card() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public boolean isSending() {
        return sending;
    }

    public void setSending(boolean sending) {
        this.sending = sending;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getValuable() {
        return valuable;
    }

    public void setValuable(String valuable) {
        this.valuable = valuable;
    }

    @Override
    public String toString() {
        return  "year='" + year + '\'' +
                ", thema='" + thema + '\'' +
                ", sending=" + sending +
                ", country='" + country + '\'' +
                ", valuable='" + valuable + '\'';
    }
}
