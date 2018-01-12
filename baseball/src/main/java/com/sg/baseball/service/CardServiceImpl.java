
package com.sg.baseball.service;

import com.sg.baseball.dao.CardDao;
import com.sg.baseball.model.Card;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author n0149245
 */
public class CardServiceImpl implements CardService {

    @Inject
    CardDao cardDao;

    @Inject
    ManufactService manufactService;

    public CardDao getCardDao() {
        return cardDao;
    }

    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public ManufactService getManufactService() {
        return manufactService;
    }

    public void setManufactService(ManufactService manufactService) {
        this.manufactService = manufactService;
    }

    @Override
    public Card addCard(Card card) {
        return cardDao.addCard(card);
    }

    @Override
    public void removeCard(int cardId) {
        cardDao.removeCard(cardId);
    }

    @Override
    public Card updateCard(Card card) {
        return cardDao.updateCard(card);
    }

    @Override
    public Card retrieveCardById(int cardId) {
        return associateManufToCard(cardDao.retrieveCardById(cardId));
    }

    @Override
    public List<Card> retrieveAllCards() {
        List<Card> cardList = cardDao.retrieveAllCards();
        for (Card currentCard : cardList) {
            associateManufToCard(currentCard);
        }
        return cardList;
    }

    @Override
    public List<Card> retrieveCardsByManufactId(int manufactId) {
        List<Card> cardList = cardDao.retrieveCardsByManufactId(manufactId);
        for (Card currentCard : cardList) {
            associateManufToCard(currentCard);
        }
        return cardList;
    }

    @Override
    public List<Card> retrieveCardsByYear(int year) {
        List<Card> cardList = cardDao.retrieveCardsByYear(year);
        for (Card currentCard : cardList) {
            associateManufToCard(currentCard);
        }
        return cardList;
    }

    @Override
    public List<Card> retrieveCardsByPlayer(String playerName) {
       List<Card> cardList = cardDao.retrieveCardsByPlayer(playerName);
        for (Card currentCard : cardList) {
            associateManufToCard(currentCard);
        }
        return cardList; 
    }

    private Card associateManufToCard(Card card) {
        card.setManufactuer(manufactService.retrieveManufByCardId(card.getCardId()));
        return card;
    }

}
