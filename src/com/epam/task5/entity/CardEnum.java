package com.epam.task5.entity;

/**
 * Created by Никита on 30.12.2015.
 */
public enum CardEnum {
    CARDS("cards"),
    GREETING_CARD("greeting-card"),
    PROMOTIONAL_CARD("promotional-card"),
    ADVERTISING_PHONE("advertising-phone"),
    MUSIC_CARD("music-card"),
    AUTHOR("author"),
    YEAR("year"),
    THEMA("thema"),
    SENDING("sending"),
    COUNTRY("country"),
    VALUABLE("valuable"),
    NAME("name"),
    BIRTH_DATE("birth-date"),
    BIRTH_PLACE("birth-place"),
    COMPANY_NAME("company-name"),
    SONG("song");

    private String value;
    CardEnum(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }


}
