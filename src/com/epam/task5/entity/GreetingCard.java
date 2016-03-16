package com.epam.task5.entity;


import javax.xml.bind.annotation.*;

/**
 * Created by Никита on 29.12.2015.
 */

public class GreetingCard extends Card {
    private Author author = new Author();

    public GreetingCard() {
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "GreetingCard{" + s +
                "author=" + author.toString() +
                '}';
    }
}
