/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.service;
//final
import com.sg.supervillain.dao.OrgDao;
import com.sg.supervillain.dao.PowersDao;
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.SuperVillain;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.sg.supervillain.dao.SuperVillainDao;

/**
 *
 * @author n0149245
 */
@Service
public class VillainPowerOrgServiceLayerImpl implements VillainPowerOrgServiceLayer {

    @Inject
    PowersDao powerDao;

    @Inject
    OrgDao orgDao;

    @Inject
    SuperVillainDao superVillainDao;

    public PowersDao getPowerDao() {
        return powerDao;
    }

    public void setPowerDao(PowersDao powerDao) {
        this.powerDao = powerDao;
    }

    public OrgDao getOrgDao() {
        return orgDao;
    }

    public void setOrgDao(OrgDao orgDao) {
        this.orgDao = orgDao;
    }

    public SuperVillainDao getSuperVillainDao() {
        return superVillainDao;
    }

    public void setSuperVillainDao(SuperVillainDao superVillainDao) {
        this.superVillainDao = superVillainDao;
    }

    @Override
    public Power createPower(Power power) {
        return powerDao.createPower(power);
    }

    @Override
    public void removePower(int powerId) {
        powerDao.removePower(powerId);
    }

    @Override
    public Power updatePower(Power power) {
        return powerDao.updatePower(power);
    }

    @Override
    public Power retrievePowerById(int powerId) {
        return powerDao.retrievePowerById(powerId);
    }

    @Override
    public List<Power> retrieveAllPowers() {
        return powerDao.retrieveAllPowers();
    }

    @Override
    public Org addOrg(Org org) {
        return orgDao.addOrg(org);
    }

    @Override
    public void removeOrg(int orgId) {
        orgDao.removeOrg(orgId);
    }

    @Override
    public Org updateOrg(Org org) {
        return orgDao.updateOrg(org);
    }

    @Override
    public Org retrieveOrgById(int orgId) {
        return orgDao.retrieveOrgById(orgId);
    }

    @Override
    public List<Org> retrieveAllOrgs() {
        return orgDao.retrieveAllOrgs();
    }

    @Override
    public SuperVillain addVillain(SuperVillain villain) {
        return superVillainDao.addVillain(villain);
    }

    @Override
    public void removeVillain(int villainId) {
        superVillainDao.removeVillain(villainId);
        
    }

    @Override
    public SuperVillain updateVillain(SuperVillain villain) {
        return superVillainDao.updateVillain(villain);
    }

    @Override
    public SuperVillain retrieveVillainById(int villainId) {
        SuperVillain villain = superVillainDao.retrieveVillainById(villainId);

        if (villain != null) {
            List<Power> pwrList = powerDao.retrievePowersByVillainId(villainId);
            villain.setPowers(pwrList);

            List<Org> orgList = orgDao.retrieveOrgsByVillainId(villainId);
            villain.setOrgs(orgList);
        }
        return villain;
    }

    @Override
    public List<SuperVillain> retrieveAllVillains() {
        List<SuperVillain> villainList = superVillainDao.retrieveAllVillains();

        return associateVillainWithPowrOrg(villainList);
    }

    @Override
    public List<Power> retrievePowersByVillainId(int villainId) {
        return powerDao.retrievePowersByVillainId(villainId);
    }

    @Override
    public List<Power> retrievePowersByOrgId(int orgId) {
        return powerDao.retrievePowersByOrgId(orgId);
    }

    @Override
    public List<Org> retrieveOrgsByPowerId(int powerId) {
        return orgDao.retrieveOrgsByPowerId(powerId);
    }

    @Override
    public List<Org> retrieveOrgsByVillainId(int villainId) {
        return orgDao.retrieveOrgsByVillainId(villainId);
    }

    @Override
    public List<SuperVillain> retrieveVillainsByOrgId(int orgId) {
        List<SuperVillain> vilList = superVillainDao.retrieveVillainsByOrgId(orgId);
        return associateVillainWithPowrOrg(vilList);
    }

    @Override
    public List<SuperVillain> retrieveVillainsByPowerId(int powerId) {
        List<SuperVillain> vilList = superVillainDao.retrieveVillainsByPowerId(powerId);
        return associateVillainWithPowrOrg(vilList);
    }

    @Override
    public List<SuperVillain> retrieveVillainsBySightingId(int sightId) {
        List<SuperVillain> vilList = superVillainDao.retrieveVillainsBySightingId(sightId);
        return associateVillainWithPowrOrg(vilList);
    }  

    private List<SuperVillain> associateVillainWithPowrOrg(List<SuperVillain> villainList) {
        for (SuperVillain currentVillain : villainList) {
            List<Power> pwrList = powerDao.retrievePowersByVillainId(currentVillain.getId());
            currentVillain.setPowers(pwrList);
            List<Org> orgList = orgDao.retrieveOrgsByVillainId(currentVillain.getId());
            currentVillain.setOrgs(orgList);
        }
        return villainList;
    }
}
