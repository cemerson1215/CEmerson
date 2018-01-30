/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;

import com.sg.supervillain.model.Sighting;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//final
/**
 *
 * @author n0149245
 */
public interface SightingsDao {
    public Sighting addSighting  (Sighting sighting);
    
    public void removeSighting (int sightId);
    
    public Sighting updateSighting (Sighting sighting);
    
    public Sighting updateSightingByLocId (Sighting sighting, int newLocid);
    
    public Sighting retrieveSightingById (int sightId);
    
    public List<Sighting> retrieveAllSightings ();
    
    public List<Sighting> retrieveSightingsByVillainId (int villianId);
    
    public List<Sighting> retrieveSightingsByOrgId (int orgId);
    
    public List<Sighting> retrieveSightingsByLocationId (int locId);
    
    public List<Sighting> retrieveSightingsByDate (LocalDate date);
    
    
}
