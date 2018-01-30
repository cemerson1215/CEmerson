/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.service;
//final
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.SuperVillain;
import java.util.List;

/**
 *
 * @author n0149245
 */
public interface VillainPowerOrgServiceLayer {
    public Power createPower (Power power);
    
    public void removePower (int powerId);
    
    public Power updatePower (Power power);
    
    public Power retrievePowerById (int powerId);
    
    public List<Power> retrieveAllPowers();
    
    public List<Power> retrievePowersByVillainId(int villainId);
    
    public List<Power> retrievePowersByOrgId(int orgId);
    
     public Org addOrg(Org org);
    
    public void removeOrg(int orgId);
    
    public Org updateOrg(Org org);
    
    public Org retrieveOrgById (int orgId);
    
    public List<Org> retrieveAllOrgs ();
    
    public List<Org> retrieveOrgsByPowerId (int powerId);
    
    public List<Org> retrieveOrgsByVillainId (int heroId);
    
    public SuperVillain addVillain (SuperVillain villain);
    
    public void removeVillain (int villainId);
    
    public SuperVillain updateVillain (SuperVillain villain);
    
    public SuperVillain retrieveVillainById (int villainId);
    
    public List<SuperVillain> retrieveAllVillains();
    
    public List<SuperVillain> retrieveVillainsByOrgId(int orgId);
    
    public List<SuperVillain> retrieveVillainsByPowerId(int powerId);
    
    public List<SuperVillain> retrieveVillainsBySightingId(int sightId);
    
    
}
