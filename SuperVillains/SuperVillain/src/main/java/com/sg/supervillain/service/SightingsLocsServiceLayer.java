/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.service;
//final
import com.sg.supervillain.dao.SuperVillainPersistenceException;
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0149245
 */
public interface SightingsLocsServiceLayer {

    public Location addLocation(Location location);

    public void removeLocation(int locId) throws SuperVillainPersistenceException;

    public Location updateLocation(Location location);

    public Location retrieveLocationById(int locId);

    public List<Location> retrieveAllLocations();

    public List<Location> retrieveLocsByVillainId(int villainId);

    public List<Location> retrieveLocsByPowerId(int powerId);

    public Location retrieveLocBySightId(int sightid);

    public List<Location> retrieveLocsBySightingsIds(List<Integer> sightingsList);

    public Sighting addSighting(Sighting sighting);

    public void removeSighting(int sightId);

    public Sighting updateSighting(Sighting sighting);

    public Sighting retrieveSightingById(int sightId);

    public List<Sighting> retrieveAllSightings();

    public List<Sighting> retrieveSightingsByVillainId(int villainId);

    public List<Sighting> retrieveSightingsByOrgId(int orgId);

    public List<Sighting> retrieveSightingsByLocationId(int locId);

    public List<Sighting> retrieveSightingsByDate(LocalDate date);
    
    public List<Location> retrieveLocationsByDate(LocalDate date);
    
    public List<SuperVillain> retrieveVillainsByDate(LocalDate date);
    
    public List<SuperVillain> retrieveVillainsByLocId (int locId);
}
