/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.model.Power;
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
public class PowersDaoDbImpl implements PowersDao {

    private static final String SQL_INSERT_POWER = "INSERT INTO powers (description) VALUES (?)";
    private static final String SQL_DELETE_POWER = "DELETE FROM powers WHERE power_id =?";
    private static final String SQL_SELECT_POWER = "SELECT * FROM powers WHERE power_id =?";
    private static final String SQL_UPDATE_POWER = "UPDATE powers SET description = ? WHERE power_id =?";
    private static final String SQL_SELECT_ALL_POWERS = "SELECT * FROM powers";
    private static final String SQL_SELECT_POWERS_BY_VILLIAN_ID
            = "SELECT p.power_id, p.description "
            + "FROM powers p "
            + "INNER JOIN villainpower vp ON vp.power_id = p.power_id "
            + "INNER JOIN supervillain sv ON sv.villain_id = vp.villain_id "
            + "WHERE sv.villain_id = ?";

    private static final String SQL_SELECT_POWERS_BY_ORG_ID
            = "SELECT p.power_id, p.description "
            + "FROM powers p "
            + "INNER JOIN villainpower vp ON vp.power_id = p.power_id "
            + "INNER JOIN supervillain sv ON sv.villain_id = vp.villain_id "
            + "INNER JOIN orgvillain ov ON sv.villain_id = ov.villain_id "
            + "INNER JOIN orgs o ON o.org_id = ov.org_id "
            + "WHERE o.org_id = ?";

    private static final String SQL_REMOVE_POWER_FRM_POWERVILLIAN_BRIDGE
            = "DELETE FROM villainpower WHERE power_id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Power createPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER, power.getDescription());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        power.setPowerId(newId);
        return power;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removePower(int powerId) {
        jdbcTemplate.update(SQL_REMOVE_POWER_FRM_POWERVILLIAN_BRIDGE, powerId);
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }

    @Override
    public Power updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER, power.getDescription(), power.getPowerId());
        return power;
    }

    @Override
    public Power retrievePowerById(int powerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER, new PowerMapper(), powerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> retrieveAllPowers() {
        List<Power> listpowers = jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
        return listpowers;
    }

    @Override
    public List<Power> retrievePowersByVillainId(int villainId) {
        List<Power> powerList
                = jdbcTemplate.query(SQL_SELECT_POWERS_BY_VILLIAN_ID, new PowerMapper(),
                        villainId);
        return powerList;
    }

    @Override
    public List<Power> retrievePowersByOrgId(int orgId) {
        List<Power> powerList
                = jdbcTemplate.query(SQL_SELECT_POWERS_BY_ORG_ID, new PowerMapper(),
                        orgId);
        return powerList;
    }

    private static final class PowerMapper implements RowMapper<Power> {

        public Power mapRow(ResultSet rs, int rowNum) throws SQLException {
            Power power = new Power();
            power.setPowerId(rs.getInt("power_id"));
            power.setDescription(rs.getString("Description"));
            return power;

        }
    }

}
