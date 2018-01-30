/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.SuperVillain;
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
public class SuperVillainDaoDbImpl implements SuperVillainDao {

    private static final String SQL_INSERT_VILLAIN = "insert into supervillain (`Name`, Description) VALUES (?, ?)";
    private static final String SQL_DELETE_VILLIAN = "DELETE FROM supervillain WHERE villain_id = ?";
    private static final String SQL_SELECT_VILLIAN = "SELECT sv.villain_id, sv.`Name`, sv.Description FROM supervillain sv WHERE villain_id =?";
    private static final String SQL_UPDATE_VILLIAN = "UPDATE supervillain SET `Name` = ?, Description = ? WHERE villain_id =?";
    private static final String SQL_SELECT_ALL_VILLIANS = "SELECT sv.villain_id, sv.Name, sv.Description FROM supervillain sv";
    private static final String SQL_SELECT_VILLAINS_BY_ORG_ID = "SELECT sv.villain_id, sv.`Name`, sv.Description "
            + "FROM supervillain sv "
            + "INNER JOIN orgvillain ov ON ov.villain_id = sv.villain_id "
            + "WHERE org_id = ?";
    private static final String SQL_SELECT_VILLAINS_BY_POWER_ID
            = "SELECT sv.villain_id, sv.`Name`, sv.Description "
            + "FROM supervillain sv "
            + "INNER JOIN villainpower vp ON vp.villain_id = sv.villain_id "
            + "WHERE vp.power_id = ?";
    private static final String SQL_INSERT_VILLAIN_POWER_BRIDGE
            = "INSERT INTO villainpower (villain_id, power_id) VALUES (?, ?)";
    private static final String SQL_INSERT_VILLAIN_ORG_BRIDGE
            = "INSERT INTO orgvillain (org_id, villain_id) VALUES (?, ?)";
    private static final String SQL_DELETE_VILLAIN_POWER_BRIDGE = "DELETE FROM villainpower WHERE villain_id = ?";
    private static final String SQL_DELETE_VILLAIN_ORG_BRIDGE = "DELETE FROM orgvillain WHERE villain_id = ?";
    private static final String SQL_DELETE_VILLAIN_SIGHTINGS_BRIDGE = "DELETE FROM sightingsvillains WHERE villain_id = ?";
    private static final String SQL_SELECT_VILLAINS_BY_SIGHTINGID = "SELECT sv.villain_id, sv.Name, sv.Description FROM supervillain sv "
            + "INNER JOIN sightingsvillains sightv ON sightv.villain_id = sv.villain_id WHERE sightv.sighting_id = ?";
    
    

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperVillain addVillain(SuperVillain villain) {
        jdbcTemplate.update(SQL_INSERT_VILLAIN, villain.getName(), villain.getDescription());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        villain.setId(newId);
        insertPowerIntoVillain(villain);
        insertOrgIntoVillain(villain);
        return villain;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeVillain(int villainId) {
        jdbcTemplate.update(SQL_DELETE_VILLAIN_POWER_BRIDGE, villainId);
        jdbcTemplate.update(SQL_DELETE_VILLAIN_ORG_BRIDGE, villainId);
        jdbcTemplate.update(SQL_DELETE_VILLAIN_SIGHTINGS_BRIDGE, villainId);
        jdbcTemplate.update(SQL_DELETE_VILLIAN, villainId);
    }

    @Override
    public SuperVillain updateVillain(SuperVillain villain) {
        jdbcTemplate.update(SQL_UPDATE_VILLIAN, villain.getName(), villain.getDescription(), villain.getId());

        jdbcTemplate.update(SQL_DELETE_VILLAIN_POWER_BRIDGE, villain.getId());
        jdbcTemplate.update(SQL_DELETE_VILLAIN_ORG_BRIDGE, villain.getId());

        insertPowerIntoVillain(villain);
        insertOrgIntoVillain(villain);
        return villain;
    }

    @Override
    public SuperVillain retrieveVillainById(int villainId) {
        try {
            SuperVillain villain = jdbcTemplate.queryForObject(SQL_SELECT_VILLIAN, new VillainMapper(), villainId);
            return villain;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperVillain> retrieveAllVillains() {
        List<SuperVillain> villainLists = jdbcTemplate.query(SQL_SELECT_ALL_VILLIANS, new VillainMapper());     
        return villainLists;
    }

    @Override
    public List<SuperVillain> retrieveVillainsByOrgId(int orgId) {
        List<SuperVillain> villainLists = jdbcTemplate.query(SQL_SELECT_VILLAINS_BY_ORG_ID, new VillainMapper(), orgId);
        return villainLists;
    }

    @Override
    public List<SuperVillain> retrieveVillainsByPowerId(int powerId) {
        List<SuperVillain> villainLists = jdbcTemplate.query(SQL_SELECT_VILLAINS_BY_POWER_ID, new VillainMapper(), powerId);
        return villainLists;
    }

    @Override
    public List<SuperVillain> retrieveVillainsBySightingId(int sightId) {
        List<SuperVillain> villainsList = jdbcTemplate.query(SQL_SELECT_VILLAINS_BY_SIGHTINGID, new VillainMapper(), sightId);
        return villainsList;
    }

    private void insertPowerIntoVillain(SuperVillain villain) {
        int villainId = villain.getId();
        List<Power> powers = villain.getPowers();

        for (Power currentPower : powers) {
            jdbcTemplate.update(SQL_INSERT_VILLAIN_POWER_BRIDGE, villainId, currentPower.getPowerId());
        }

    }

    private void insertOrgIntoVillain(SuperVillain villain) {
        int villainId = villain.getId();
        List<Org> orgs = villain.getOrgs();

        for (Org currentOrg : orgs) {
            jdbcTemplate.update(SQL_INSERT_VILLAIN_ORG_BRIDGE, currentOrg.getorgId(), villainId);
        }
    }  

    private static final class VillainMapper implements RowMapper<SuperVillain> {

        public SuperVillain mapRow(ResultSet rs, int rowNum) throws SQLException {
            SuperVillain villain = new SuperVillain();
            villain.setId(rs.getInt("villain_id"));
            villain.setName(rs.getString("Name"));
            villain.setDescription(rs.getString("Description"));
            return villain;
        }
    }
}
