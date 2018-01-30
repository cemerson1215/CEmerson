/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;

import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
public class OrgDaoDbImpl implements OrgDao {

    private static final String SQL_INSERT_ORG = "INSERT INTO orgs (Org_Name, Description, Address, Email, Phone) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ORG = "DELETE FROM orgs WHERE org_id =?";
    private static final String SQL_SELECT_ORG = "SELECT o.org_id, o.org_name, o.description, o.Address, o.Email, o.Phone FROM orgs o WHERE org_id =?";
    private static final String SQL_UPDATE_ORG = "UPDATE orgs SET Org_Name = ?, Description = ?, Address = ?, Email = ?, Phone = ? WHERE org_id =?";
    private static final String SQL_SELECT_ALL_ORGS = "SELECT o.org_id, o.org_name, o.description, o.Address, o.Email, o.Phone FROM orgs o";
    private static final String SQL_SELECT_ORGS_BY_VILLIAN_ID
            = "SELECT o.org_id, o.org_name, o.description, o.Address, o.Email, o.Phone "
            + "FROM orgs o "
            + "INNER JOIN orgvillain ov ON ov.org_id = o.org_id "
            + "WHERE villain_id = ?";
    private static final String SQL_SELECT_ORGS_BY_POWER_ID
            = "SELECT o.org_id, o.org_name, o.description, o.Address, o.Email, o.Phone "
            + "FROM orgs o "
            + "INNER JOIN orgvillain ov ON ov.org_id = o.org_id "
            + "INNER JOIN villainpower vp ON ov.villain_id = vp.villain_id "
            + "WHERE vp.power_id = ?";
    
    private static final String SQL_REMOVE_ORG_FRM_ORGVILLIAN_BRIDGE
            = "DELETE FROM orgvillain WHERE org_id = ?";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Org addOrg(Org org) {
       jdbcTemplate.update(SQL_INSERT_ORG, org.getName(), org.getDescription(), org.getAddress(), org.getEmail(), org.getPhone());
       int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
       org.setorgId(newId);
       return org;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeOrg(int orgId) {
       jdbcTemplate.update(SQL_REMOVE_ORG_FRM_ORGVILLIAN_BRIDGE, orgId);
       jdbcTemplate.update(SQL_DELETE_ORG, orgId);
    }

    @Override
    public Org updateOrg(Org org) {
        jdbcTemplate.update(SQL_UPDATE_ORG, org.getName(), org.getDescription(), org.getAddress(), org.getEmail(), org.getPhone(), org.getorgId());
        return org;
    }

    @Override
    public Org retrieveOrgById(int orgId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORG, new OrgMapper(), orgId);            
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Org> retrieveAllOrgs() {
        List<Org> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGS, new OrgMapper());
        return orgList;
    }

    @Override
    public List<Org> retrieveOrgsByPowerId(int powerId) {
        List<Org> orgList
                = jdbcTemplate.query(SQL_SELECT_ORGS_BY_POWER_ID, new OrgMapper(), powerId);
        return orgList;
    }

    @Override
    public List<Org> retrieveOrgsByVillainId(int villainId) {
        List<Org> orgList
                = jdbcTemplate.query(SQL_SELECT_ORGS_BY_VILLIAN_ID, new OrgMapper(), villainId);
        return orgList;
    }
    
    private static final class OrgMapper implements RowMapper<Org> {

        public Org mapRow(ResultSet rs, int rowNum) throws SQLException {
            Org org = new Org();
            org.setorgId(rs.getInt("org_id"));
            org.setName(rs.getString("Org_Name"));
            org.setDescription(rs.getString("Description"));
            org.setAddress(rs.getString("Address"));
            org.setEmail(rs.getString("Email"));
            org.setPhone(rs.getString("Phone"));
            return org;

        }
    }

}
