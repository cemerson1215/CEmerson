/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.dao;

import com.sg.baseball.model.Card;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author n0149245
 */
public class CardDaoDbImpl implements CardDao {

    private static final String SQL_INSERT_CARD = "INSERT INTO Cards (card_number, players_name, `year`, manufactuer_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE_CARD = "DELETE FROM Cards WHERE cardId = ?";
    private static final String SQL_SELECT_CARD = "SELECT * FROM cards WHERE cardId =?";
    private static final String SQL_UPDATE_CARD = "UPDATE cards SET card_number = ?, players_name=?, year=?, manufactuer_id = ? WHERE cardId=?";
    private static final String SQL_SELECT_ALL_CARDS = "SELECT * FROM cards";
    private static final String SQL_RETRIEVECARDS_BYMANFID = "SELECT * FROM cards WHERE manufactuer_id = ?";
    private static final String SQL_RETRIEVECARDS_BY_YEAR = "SELECT * FROM cards WHERE year = ?";
    private static final String SQL_RETRIEVECARDS_BY_PLAYER = "SELECT * FROM cards WHERE players_name = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Card addCard(Card card) {
        jdbcTemplate.update(SQL_INSERT_CARD, card.getCardNumber(), card.getPlayerName(), card.getYear(), card.getManufactuer().getManufactId());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        card.setCardId(newId);
        return card;
    }

    @Override
    public void removeCard(int cardId) {
        jdbcTemplate.update(SQL_DELETE_CARD, cardId);
    }

    @Override
    public Card updateCard(Card card) {
        jdbcTemplate.update(SQL_UPDATE_CARD, card.getCardNumber(), card.getPlayerName(), card.getYear(), card.getManufactuer().getManufactId(), card.getCardId());
        return card;
    }

    @Override
    public Card retrieveCardById(int cardId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CARD, new CardMapper(), cardId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Card> retrieveAllCArds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CARDS, new CardMapper());
    }

    @Override
    public List<Card> retrieveCardsByManufactId(int manufactId) {
        return jdbcTemplate.query(SQL_RETRIEVECARDS_BYMANFID, new CardMapper(), manufactId);
    }

    @Override
    public List<Card> retrieveCardsByYear(int year) {
        return jdbcTemplate.query(SQL_RETRIEVECARDS_BY_YEAR, new CardMapper(), year);
    }

    @Override
    public List<Card> retrieveCardsByPlayer(String playerName) {
        return jdbcTemplate.query(SQL_RETRIEVECARDS_BY_PLAYER, new CardMapper(), playerName);
    }

    private static final class CardMapper implements RowMapper<Card> {

        public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
            Card card = new Card();
            card.setCardId(rs.getInt("cardId"));
            card.setCardNumber(rs.getInt("card_number"));
            card.setPlayerName(rs.getString("players_name"));
            card.setYear(rs.getInt("year"));
            return card;
        }
    }

}
