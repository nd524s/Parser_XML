package com.epam.task5.entity;

import javax.xml.bind.annotation.*;

/**
 * Created by Никита on 29.12.2015.
 */

public class MusicCard extends Card {
    private Author author = new Author();
    private String song;

    public MusicCard() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "MusicCard{" + s +
                "author=" + author.toString() +
                ", song='" + song + '\'' +
                '}';
    }
}
