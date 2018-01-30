/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;

import com.sg.supervillain.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//final
/**
 *
 * @author n0149245
 */
public class LocationDaoDbImpl implements LocationDao {

    private static final String SQL_INSERT_LOCATION = "INSERT INTO locations (Loc_Name, Description, Address, Latitude, longitude) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_LOCATION = "DELETE FROM locations WHERE loc_id =?";
    private static final String SQL_SELECT_LOCATION = "SELECT * FROM locations WHERE loc_id =?";
    private static final String SQL_UPDATE_LOCATION = "UPDATE locations SET Loc_Name = ?, Description = ?, Address = ?, Latitude = ?, Longitude = ? WHERE loc_id =?";
    private static final String SQL_SELECT_ALL_LOCATIONS = "SELECT * FROM locations";
    private static final String SQL_SELECT_LOCS_BY_VILLIAN_ID
            = "SELECT l.loc_id, l.Loc_Name, l.Description, l.address, l.latitude, l.longitude "
            + "FROM locations l "
            + "INNER JOIN sightings s ON l.loc_id = s.loc_id "
            + "INNER JOIN sightingsvillains sv ON sv.sighting_id = s.sighting_id "
            + "WHERE sv.villain_id = ?";
    private static final String SQL_SELECT_LOCS_BY_POWER_ID
            = "SELECT l.loc_id, l.Loc_Name, l.Description, l.address, l.latitude, l.longitude "
            + "FROM locations l "
            + "INNER JOIN sightings s ON l.loc_id = s.loc_id "
            + "INNER JOIN sightingsvillains sv ON sv.sighting_id = s.sighting_id "
            + "INNER JOIN villainpower vp ON sv.villain_id = vp.villain_id "
            + "WHERE vp.power_id = ?";
    private static final String SQL_RETRIEVE_LOC_BY_SIGHT_ID
            = "SELECT l.loc_id, l.Loc_Name, l.Description, l.address, l.latitude, l.longitude "
            + "FROM locations l "
            + "INNER JOIN sightings s ON s.loc_id = l.loc_id "
            + "WHERE sighting_id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION, location.getName(), location.getDescription(), location.getAddress(), location.getLatitude(),
                location.getLongitude());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocId(newId);
        return location;
    }

    @Override
    public void removeLocation(int locId) throws SuperVillainPersistenceException {
        try {
            jdbcTemplate.update(SQL_DELETE_LOCATION, locId);
        } catch (DataIntegrityViolationException e) {
            throw new SuperVillainPersistenceException("Could not delete Location. Associated Sightings still exist", e);
        }
    }

    @Override
    public Location updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION, location.getName(), location.getDescription(), location.getAddress(), location.getLatitude(),
                location.getLongitude(), location.getLocId());
        return location;
    }

    @Override
    public Location retrieveLocationById(int locId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> retrieveAllLocations() {
        List<Location> locationList = jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
        return locationList;
    }

    @Override
    public List<Location> retrieveLocsByVillainId(int villainId) {
        List<Location> locationList = jdbcTemplate.query(SQL_SELECT_LOCS_BY_VILLIAN_ID, new LocationMapper(), villainId);
        return locationList;
    }

    @Override
    public List<Location> retrieveLocsByPowerId(int powerId) {
        List<Location> locationList = jdbcTemplate.query(SQL_SELECT_LOCS_BY_POWER_ID, new LocationMapper(), powerId);
        return locationList;
    }

    @Override
    public Location retrieveLocBySightId(int sightid) {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_LOC_BY_SIGHT_ID, new LocationMapper(), sightid);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setLocId(rs.getInt("loc_id"));
            location.setName(rs.getString("Loc_Name"));
            location.setDescription(rs.getString("Description"));
            location.setAddress(rs.getString("Address"));
            location.setLatitude(rs.getFloat("Latitude"));
            location.setLongitude(rs.getFloat("longitude"));
            return location;

        }
    }
}
