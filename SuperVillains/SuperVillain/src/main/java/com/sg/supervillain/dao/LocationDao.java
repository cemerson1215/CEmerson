/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.model.Location;
import java.util.List;

/**
 *
 * @author n0149245
 */
public interface LocationDao {
    public Location addLocation (Location location);
    
    public void removeLocation (int locId) throws SuperVillainPersistenceException;
    
    public Location updateLocation (Location location);
    
    public Location retrieveLocationById (int locId);
    
    public List<Location> retrieveAllLocations();
    
    public List<Location> retrieveLocsByVillainId (int villianId);
    
    public List<Location> retrieveLocsByPowerId(int powerId);
    
    public Location retrieveLocBySightId (int sightid);
    
}
