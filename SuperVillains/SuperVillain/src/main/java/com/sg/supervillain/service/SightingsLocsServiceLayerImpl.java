/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.service;
//final
import com.sg.supervillain.dao.LocationDao;
import com.sg.supervillain.dao.SightingsDao;
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.sg.supervillain.dao.SuperVillainDao;
import com.sg.supervillain.dao.SuperVillainPersistenceException;

/**
 *
 * @author n0149245
 */
public class SightingsLocsServiceLayerImpl implements SightingsLocsServiceLayer {

    @Inject
    LocationDao locDao;

    @Inject
    SightingsDao sightDao;

    @Inject
    SuperVillainDao superVillainDao;

    @Inject
    VillainPowerOrgServiceLayer vpoService;

    public LocationDao getLocDao() {
        return locDao;
    }

    public void setLocDao(LocationDao locDao) {
        this.locDao = locDao;
    }

    public SightingsDao getSightDao() {
        return sightDao;
    }

    public void setSightDao(SightingsDao sightDao) {
        this.sightDao = sightDao;
    }

    public SuperVillainDao getSuperVillainDao() {
        return superVillainDao;
    }

    public void setSuperVillainDao(SuperVillainDao superVillainDao) {
        this.superVillainDao = superVillainDao;
    }

    public VillainPowerOrgServiceLayer getVpoService() {
        return vpoService;
    }

    public void setVpoService(VillainPowerOrgServiceLayer vpoService) {
        this.vpoService = vpoService;
    }

    @Override
    public Location addLocation(Location location) {
        return locDao.addLocation(location);
    }

    @Override
    public void removeLocation(int locId) throws SuperVillainPersistenceException {
        locDao.removeLocation(locId);
    }

    @Override
    public Location updateLocation(Location location) {
        return locDao.updateLocation(location);
    }

    @Override
    public Location retrieveLocationById(int locId) {
        return locDao.retrieveLocationById(locId);
    }

    @Override
    public List<Location> retrieveAllLocations() {
        return locDao.retrieveAllLocations();
    }

    @Override
    public List<Location> retrieveLocsByVillainId(int villainId) {
        return locDao.retrieveLocsByVillainId(villainId);
    }

    @Override
    public List<Location> retrieveLocsByPowerId(int powerId) {
        return locDao.retrieveLocsByPowerId(powerId);
    }

    @Override
    public Location retrieveLocBySightId(int sightid) {
        return locDao.retrieveLocBySightId(sightid);
    }

    @Override
    public List<Location> retrieveLocsBySightingsIds(List<Integer> sightingsList) {
        List<Location> locList = new ArrayList<>();
        for (Integer currentSightId : sightingsList) {
            locList.add(locDao.retrieveLocBySightId(currentSightId));
        }
        return locList;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        //get the loc object if not complete 
        return sightDao.addSighting(sighting);
    }

    @Override
    public void removeSighting(int sightId) {
        sightDao.removeSighting(sightId);
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        return sightDao.updateSighting(sighting);
    }

    @Override
    public Sighting retrieveSightingById(int sightId) {
        Sighting sight = sightDao.retrieveSightingById(sightId);
        sight.setLocation(locDao.retrieveLocBySightId(sightId));
        sight.setVillainsList(vpoService.retrieveVillainsBySightingId(sightId));
        return sight;
    }

    @Override
    public List<Sighting> retrieveAllSightings() {
        List<Sighting> sightList = sightDao.retrieveAllSightings();
        return associateLocToSightings(sightList);
    }

    @Override
    public List<Sighting> retrieveSightingsByVillainId(int villainId) {
        List<Sighting> sightList = sightDao.retrieveSightingsByVillainId(villainId);
        return associateLocToSightings(sightList);
    }

    @Override
    public List<Sighting> retrieveSightingsByOrgId(int orgId) {
        List<Sighting> sightList = sightDao.retrieveSightingsByOrgId(orgId);
        return associateLocToSightings(sightList);
    }

    @Override
    public List<Sighting> retrieveSightingsByLocationId(int locId) {
        List<Sighting> sightList = sightDao.retrieveSightingsByLocationId(locId);
        return associateLocToSightings(sightList);
    }

    @Override
    public List<Sighting> retrieveSightingsByDate(LocalDate date) {
        List<Sighting> sightList = sightDao.retrieveSightingsByDate(date);
        return associateLocToSightings(sightList);
    }
    
    @Override
    public List<Location> retrieveLocationsByDate(LocalDate date) {
        List<Sighting> sightList = retrieveSightingsByDate(date);
        List<Location> locList = new ArrayList<>();
        for (Sighting currentSight : sightList) {
            locList.add(retrieveLocBySightId(currentSight.getSightingId()));
        }
        return locList;
    }
    
    @Override
    public List<SuperVillain> retrieveVillainsByDate(LocalDate date) {
        List<Sighting> sightList = retrieveSightingsByDate(date);
        List<SuperVillain> mstList = new ArrayList<>();
        for (Sighting currentSight : sightList) {
            List<SuperVillain> miniList = vpoService.retrieveVillainsBySightingId(currentSight.getSightingId());
            for (SuperVillain currentVil : miniList) {
                mstList.add(currentVil);
            }
        }
        return mstList;
    }

    @Override
    public List<SuperVillain> retrieveVillainsByLocId(int locId) {
        List<SuperVillain> masterVilList = new ArrayList<>();

        List<Sighting> sightList = retrieveSightingsByLocationId(locId);
        for (Sighting currentSight : sightList) {
            int id = currentSight.getSightingId();
            List<SuperVillain> miniList = vpoService.retrieveVillainsBySightingId(id);
            for (SuperVillain currentvil : miniList) {
                masterVilList.add(currentvil);
            }
        }
        return masterVilList;
    }

    private List<Sighting> associateLocToSightings(List<Sighting> sightList) {

        for (Sighting currentSight : sightList) {
            currentSight.setLocation(locDao.retrieveLocBySightId(currentSight.getSightingId()));
            currentSight.setVillainsList(vpoService.retrieveVillainsBySightingId(currentSight.getSightingId()));
        }
        return sightList;
    }

}
