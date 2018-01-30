/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.model.Power;
import java.util.List;

/**
 *
 * @author n0149245
 */
public interface PowersDao {
    public Power createPower (Power power);
    
    public void removePower (int powerId);
    
    public Power updatePower (Power power);
    
    public Power retrievePowerById (int powerId);
    
    public List<Power> retrieveAllPowers();
    
    public List<Power> retrievePowersByVillainId(int villianId);
    
    public List<Power> retrievePowersByOrgId(int orgId);
    
}
