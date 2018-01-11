/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.dao;

import com.sg.baseball.model.Manufactuer;
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
public class ManufactuerDaoDbImpl implements ManufactuerDao {

    private static final String SQL_INSERT_MANUF = "INSERT INTO Manufacturers (name) VALUES (?)";
    private static final String SQL_DELETE_MANUF = "DELETE FROM Manufacturers WHERE manufactId = ?";
    private static final String SQL_SELECT_MANUF = "SELECT * FROM Manufacturers WHERE manufactId = ?";
    private static final String SQL_UPDATE_MANUF = "UPDATE Manufacturers SET name = ? WHERE manufactId = ?";
    private static final String SQL_SELECT_ALL_MANUFS = "SELECT * FROM Manufacturers";
    private static final String SQL_SELECT_MANUF_BY_CARDID = "select * from manufacturers m"
            + " INNER JOIN CARDS c ON c.manufactuer_id = m.manufactId"
            + " WHERE cardId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Manufactuer addManufactuer(Manufactuer manufactuer) {
        jdbcTemplate.update(SQL_INSERT_MANUF, manufactuer.getName());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        manufactuer.setManufactId(newId);
        return manufactuer;
    }

    @Override
    public void removeManufactuer(int manufactId) {
        jdbcTemplate.update(SQL_DELETE_MANUF, manufactId);
    }

    @Override
    public Manufactuer updateManufactuer(Manufactuer manufactuer) {
        jdbcTemplate.update(SQL_UPDATE_MANUF, manufactuer.getName(), manufactuer.getManufactId());
        return manufactuer;
    }

    @Override
    public Manufactuer retrieveManufById(int manufactId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MANUF, new ManufactMapper(), manufactId);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Manufactuer> retrieveAllManufactuers() {
        List<Manufactuer> manufactList = jdbcTemplate.query(SQL_SELECT_ALL_MANUFS, new ManufactMapper());
        return manufactList;
    }

    @Override
    public Manufactuer retrieveManufByCardId(int cardId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MANUF_BY_CARDID, new ManufactMapper(), cardId);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class ManufactMapper implements RowMapper<Manufactuer> {

        public Manufactuer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Manufactuer manufact = new Manufactuer();
            manufact.setManufactId(rs.getInt("manufactId"));
            manufact.setName(rs.getString("name"));
            return manufact;
        }
    }

}
