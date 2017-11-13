package com.example.clareblackburne.yogaapp;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by clareblackburne on 12/11/2017.
 */

public class Quotations {

    ArrayList<String> quotations;

    public Quotations(){
        quotations = new ArrayList<String>();
        quotations.add("The very heart of yoga practice is abhyasa - steady effort in the direction you want to go - Sally Kempton.");
        quotations.add("The soul is here for its own joy - Rumi.");
        quotations.add("Yoga is the stilling of the fluctuations of the mind - Yoga Sutra 12.");
        quotations.add("Yoga is the perfect opportunity to be curious about who you are - Jason Crandell.");
        quotations.add("The attitude of gratitude is the highest yoga - Yogi Bhakan.");
        quotations.add("Yoga teaches us to cure what need not be endured and to endure what cannot be cured - BKS Iyengar.");
        quotations.add("The quality of our breath expresses our inner feelings - TKV Desikachar.");
    }

    public ArrayList<String> getQuotations() {
        return quotations;
    }

    public String randomQuotation(){
        Collections.shuffle(quotations);
        return quotations.get(0);
    }
}
