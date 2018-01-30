/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;

import com.sg.supervillain.model.SuperVillain;
import java.util.List;
//final
/**
 *
 * @author n0149245
 */
public interface SuperVillainDao {
    
    public SuperVillain addVillain (SuperVillain villain);
    
    public void removeVillain (int villainId);
    
    public SuperVillain updateVillain (SuperVillain villain);
    
    public SuperVillain retrieveVillainById (int villainId);
    
    public List<SuperVillain> retrieveAllVillains();
    
    public List<SuperVillain> retrieveVillainsByOrgId(int orgId);
    
    public List<SuperVillain> retrieveVillainsByPowerId(int powerId);
    
    public List<SuperVillain> retrieveVillainsBySightingId(int sightId);
    
}
