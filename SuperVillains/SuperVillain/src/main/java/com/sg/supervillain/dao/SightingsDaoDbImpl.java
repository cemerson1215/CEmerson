/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingsDaoDbImpl implements SightingsDao {

    private static final String SQL_INSERT_SIGHTING = "INSERT INTO sightings (loc_id, `DateTime`) VALUES (?, ?)";
    private static final String SQL_DELETE_SIGHTING = "DELETE FROM sightings WHERE sighting_id = ?";
    private static final String SQL_SELECT_SIGHTING = "SELECT s.sighting_id, s.loc_id, s.`DateTime` FROM sightings s WHERE sighting_id =?";
    private static final String SQL_UPDATE_SIGHTING = "UPDATE sightings SET loc_id = ?, `DateTime` = ? WHERE sighting_id =?";
    private static final String SQL_SELECT_ALL_SIGHTINGS = "SELECT s.sighting_id, s.loc_id, s.`DateTime` FROM sightings s";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "SELECT s.sighting_id, s.loc_id, s.`DateTime` FROM sightings s WHERE date(`DateTime`) = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_LOC_ID
            = "SELECT s.sighting_id, s.loc_id, s.`DateTime` "
            + "FROM sightings s "
            + "WHERE loc_id = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_VILLAIN_ID
            = "SELECT s.sighting_id, s.loc_id, s.`DateTime` "
            + "FROM sightings s "
            + "INNER JOIN sightingsvillains sv ON sv.sighting_id = s.sighting_id "
            + "WHERE villain_id = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_ORG_ID
            = "SELECT s.sighting_id, s.loc_id, s.`DateTime` "
            + "FROM sightings s "
            + "INNER JOIN sightingsvillains sv ON sv.sighting_id = s.sighting_id "
            + "INNER JOIN orgvillain ov ON ov.villain_id = sv.villain_id "
            + "WHERE ov.org_id = ?";
    private static final String SQL_INSERT_SIGHTING_VILLAIN_BRIDGE = "INSERT INTO sightingsvillains (sighting_id, villain_id) VALUES (?, ?)";
    private static final String SQL_DELETE_SIGHTING_VILLAIN_BRIDGE = "DELETE FROM sightingsvillains WHERE sighting_id = ?";
    private static final String SQL_UPDATE_SIGHTING_BY_LOC_ID = "UPDATE sightings SET loc_id = ? WHERE loc_id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING, sighting.getLocation().getLocId(), sighting.getTimeOfSight().toString());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        insertSightingVillainBridge(sighting);
        return sighting;
    }

    @Override
    public void removeSighting(int sightId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_VILLAIN_BRIDGE, sightId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightId);
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, sighting.getLocation().getLocId(), sighting.getTimeOfSight().toString(), sighting.getSightingId());
        
        jdbcTemplate.update(SQL_DELETE_SIGHTING_VILLAIN_BRIDGE, sighting.getSightingId());
        
        insertSightingVillainBridge(sighting);
        
        return sighting;
    }
    
    @Override
    public Sighting updateSightingByLocId (Sighting sighting, int newLocid) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING_BY_LOC_ID, newLocid, sighting.getLocation().getLocId());
        Location location = new Location();
        location.setLocId(newLocid);
        sighting.setLocation(location);
        return sighting;        
    }

    @Override
    public Sighting retrieveSightingById(int sightId) {
        try { 
           Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), sightId);
           return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> retrieveAllSightings() {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, 
                new SightingMapper());
        return sightingList;
    }

    @Override
    public List<Sighting> retrieveSightingsByVillainId(int villainId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_VILLAIN_ID,
                new SightingMapper(), villainId);
        return sightingList;
    }

    @Override
    public List<Sighting> retrieveSightingsByOrgId(int orgId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_ORG_ID,
                new SightingMapper(), orgId);
        return sightingList;
    }

    @Override
    public List<Sighting> retrieveSightingsByLocationId(int locId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOC_ID,
                new SightingMapper(), locId);
        return sightingList;
    }

    @Override
    public List<Sighting> retrieveSightingsByDate(LocalDate date) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                new SightingMapper(), date.toString());
        return sightingList;
    }

    private void insertSightingVillainBridge(Sighting sighting) {
        int id = sighting.getSightingId();
        List<SuperVillain> villainList = sighting.getVillainsList();

        for (SuperVillain currentVillain : villainList) {
            jdbcTemplate.update(SQL_INSERT_SIGHTING_VILLAIN_BRIDGE, id, currentVillain.getId());
        }
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sighting_id"));
            sighting.setTimeOfSight(rs.getTimestamp("DateTime").toLocalDateTime());
            return sighting;            
        }
    }
}
