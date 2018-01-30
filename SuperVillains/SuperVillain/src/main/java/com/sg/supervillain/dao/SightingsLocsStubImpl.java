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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0149245
 */
public class SightingsLocsStubImpl implements LocationDao, SightingsDao {

    Location onlyLoc = new Location();
    Location emptyLoc = new Location();
    List<Location> locList = new ArrayList<>();
    Sighting onlySight = new Sighting();
    Sighting incompleteSight = new Sighting();
    List<Sighting> sightList = new ArrayList<>();
    String date = "2017-10-31 05:12:32";
    LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Power onlyPower = new Power();
    List<Power> powerList = new ArrayList<>();

    Org onlyOrg = new Org();
    List<Org> orgList = new ArrayList<>();

    SuperVillain onlyVillain = new SuperVillain();
    List<SuperVillain> villainList = new ArrayList<>();

    public SightingsLocsStubImpl() {
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

        onlyVillain.setId(1);
        onlyVillain.setName("Joker");
        onlyVillain.setDescription("HaHAHAhahahAA");
        villainList.add(onlyVillain);

        onlyLoc.setLocId(1);
        onlyLoc.setName("Green Mountain");
        onlyLoc.setDescription("It is green");
        onlyLoc.setAddress("Home");
        onlyLoc.setLatitude(new Float("4.366661"));
        onlyLoc.setLongitude(new Float("7.272"));
        locList.add(onlyLoc);

        onlySight.setSightingId(1);
        onlySight.setLocation(onlyLoc);
        onlySight.setTimeOfSight(d);
        onlySight.setVillainsList(villainList);
        sightList.add(onlySight);

        incompleteSight.setSightingId(2);
        incompleteSight.setLocation(emptyLoc);
        incompleteSight.setTimeOfSight(d);
        incompleteSight.setVillainsList(villainList);
    }

    @Override
    public Location addLocation(Location location) {
        return onlyLoc;
    }

    @Override
    public void removeLocation(int locId) {
        //dao does not return anything when it removes
    }

    @Override
    public Location updateLocation(Location location) {
        return onlyLoc;
    }

    @Override
    public Location retrieveLocationById(int locId) {
        return onlyLoc;
    }

    @Override
    public List<Location> retrieveAllLocations() {
        return locList;
    }

    @Override
    public List<Location> retrieveLocsByVillainId(int villainId) {
        return locList;
    }

    @Override
    public List<Location> retrieveLocsByPowerId(int powerId) {
        return locList;

    }

    @Override
    public Location retrieveLocBySightId(int sightid) {
            return onlyLoc;       
    }


    @Override
    public Sighting addSighting(Sighting sighting) {
            return onlySight;        
    }

    @Override
    public void removeSighting(int sightId) {
        //dao does not return anything on remove
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        return sighting;
    }

    @Override
    public Sighting updateSightingByLocId(Sighting sighting, int newLocid) {
        
            Location loc = new Location();
            loc.setLocId(newLocid);
            loc.setName("Green Mountain");
            loc.setDescription("It is green");
            loc.setAddress("Home");
            loc.setLatitude(new Float("4.366661"));
            loc.setLongitude(new Float("7.272"));

            Sighting sight = new Sighting();
            sight.setSightingId(1);
            sight.setLocation(loc);
            sight.setTimeOfSight(d);
            sight.setVillainsList(villainList);

            return sight;
        
    }

    @Override
    public Sighting retrieveSightingById(int sightId) {
            return onlySight;        
    }

    @Override
    public List<Sighting> retrieveAllSightings() {
        return sightList;
    }

    @Override
    public List<Sighting> retrieveSightingsByVillainId(int villainId) {
            return sightList;        
    }

    @Override
    public List<Sighting> retrieveSightingsByOrgId(int orgId) {
            return sightList;
    }

    @Override
    public List<Sighting> retrieveSightingsByLocationId(int locId) {
            return sightList;
    }

    @Override
    public List<Sighting> retrieveSightingsByDate(LocalDate date) {        
            return sightList;        
    }

}
