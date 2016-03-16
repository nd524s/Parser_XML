package com.epam.task5.entity;

import javax.xml.bind.annotation.*;

/**
 * Created by Никита on 29.12.2015.
 */

public class PromotionalCard extends Card {
    private String companyName;
    private String advertisingPhone;

    public PromotionalCard() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAdvertisingPhone() {
        return advertisingPhone;
    }

    public void setAdvertisingPhone(String advertisingPhone) {
        this.advertisingPhone = advertisingPhone;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "PromotionalCard{" + s +
                "companyName='" + companyName + '\'' +
                ", advertisingPhone='" + advertisingPhone + '\'' +
                '}';
    }
}
