/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author n0149245
 */
public class VillainPowerOrgStubImpl implements PowersDao, OrgDao, SuperVillainDao {

    Power onlyPower = new Power();
    List<Power> powerList = new ArrayList<>();
    List<Power> emptyPowerList = new ArrayList<>();
    Org onlyOrg = new Org();
    List<Org> orgList = new ArrayList<>();
    List<Org> emptyOrgList = new ArrayList<>();

    List<SuperVillain> villainList = new ArrayList<>();
    SuperVillain emptyVillain = new SuperVillain();

    Sighting onlySight = new Sighting();
    Location onlyLoc = new Location();

    public VillainPowerOrgStubImpl() {
        onlyPower.setPowerId(1);
        onlyPower.setDescription("Professional Level Hammer Slam");
        powerList.add(onlyPower);

        onlyOrg.setorgId(1);
        onlyOrg.setName("Suicide Squad");
        onlyOrg.setDescription("We're BAD guys, it's what we do.");
        onlyOrg.setAddress("Swamp Prison");
        onlyOrg.setEmail("rapsheet101@cellblock6.com");
        onlyOrg.setPhone("555-555-555");
        orgList.add(onlyOrg);

        emptyVillain.setId(1);
        emptyVillain.setName("Joker");
        emptyVillain.setDescription("HaHAHAhahahAA");
        emptyVillain.setPowers(emptyPowerList);
        emptyVillain.setOrgs(emptyOrgList);
        villainList.add(emptyVillain);
    }

    @Override
    public Power createPower(Power power) {
        return onlyPower;

    }

    @Override
    public void removePower(int powerId) {
        //nothing is returned from dao;
    }

    @Override
    public Power updatePower(Power power) {
        return power;

    }

    @Override
    public Power retrievePowerById(int powerId) {
        return onlyPower;

    }

    @Override
    public List<Power> retrieveAllPowers() {
        return powerList;
    }

    @Override
    public List<Power> retrievePowersByVillainId(int villainId) {

        return powerList;

    }

    @Override
    public List<Power> retrievePowersByOrgId(int orgId) {

        return powerList;

    }

    @Override
    public Org addOrg(Org org) {
            return onlyOrg;        
    }

    @Override
    public void removeOrg(int orgId) {
        // nothing is returned from dao;
    }

    @Override
    public Org updateOrg(Org org) {
        return onlyOrg;
       
    }

    @Override
    public Org retrieveOrgById(int orgId) {        
            return onlyOrg;        
    }

    @Override
    public List<Org> retrieveAllOrgs() {
        return orgList;
    }

    @Override
    public List<Org> retrieveOrgsByPowerId(int powerId) {

        return orgList;

    }

    @Override
    public List<Org> retrieveOrgsByVillainId(int villainId) {

        return orgList;

    }

    @Override
    public SuperVillain addVillain(SuperVillain villain) {        
            return emptyVillain;        
    }

    @Override
    public void removeVillain(int villainId) {
        //dao does not return anything
    }

    @Override
    public SuperVillain updateVillain(SuperVillain villain) {
        return emptyVillain; 
    }

    @Override
    public SuperVillain retrieveVillainById(int villainId) {    
            return emptyVillain;        
    }

    @Override
    public List<SuperVillain> retrieveAllVillains() {
        return villainList;
    }

    @Override
    public List<SuperVillain> retrieveVillainsByOrgId(int orgId) {
        return villainList;
    }

    @Override
    public List<SuperVillain> retrieveVillainsByPowerId(int powerId) {
        return villainList;
    }

    @Override
    public List<SuperVillain> retrieveVillainsBySightingId(int sightId) {
        return villainList;
    }

}
