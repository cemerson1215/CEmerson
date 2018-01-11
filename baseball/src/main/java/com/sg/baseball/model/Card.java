/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.model;

import java.util.Objects;

/**
 *
 * @author n0149245
 */
public class Card {
   int cardId;
   int cardNumber;
   String playerName;
   int year;
   Manufactuer manufactuer;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Manufactuer getManufactuer() {
        return manufactuer;
    }

    public void setManufactuer(Manufactuer manufactuer) {
        this.manufactuer = manufactuer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.cardId;
        hash = 97 * hash + this.cardNumber;
        hash = 97 * hash + Objects.hashCode(this.playerName);
        hash = 97 * hash + this.year;
        hash = 97 * hash + Objects.hashCode(this.manufactuer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.cardId != other.cardId) {
            return false;
        }
        if (this.cardNumber != other.cardNumber) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.playerName, other.playerName)) {
            return false;
        }
        if (!Objects.equals(this.manufactuer, other.manufactuer)) {
            return false;
        }
        return true;
    }
   
    
}
