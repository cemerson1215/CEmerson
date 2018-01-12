/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.service;

import com.sg.baseball.model.Card;
import java.util.List;

/**
 *
 * @author n0149245
 */
public interface CardService {

    public Card addCard(Card card);

    public void removeCard(int cardId);

    public Card updateCard(Card card);

    public Card retrieveCardById(int cardId);

    public List<Card> retrieveAllCards();

    public List<Card> retrieveCardsByManufactId(int manufactId);

    public List<Card> retrieveCardsByYear(int year);

    public List<Card> retrieveCardsByPlayer(String playerName);

}
