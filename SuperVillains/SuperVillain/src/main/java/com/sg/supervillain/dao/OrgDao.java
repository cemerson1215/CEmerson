/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;

import com.sg.supervillain.model.Org;
import java.util.List;
//final
/**
 *
 * @author n0149245
 */
public interface OrgDao {
    public Org addOrg(Org org);
    
    public void removeOrg(int orgId);
    
    public Org updateOrg(Org org);
    
    public Org retrieveOrgById (int orgId);
    
    public List<Org> retrieveAllOrgs ();
    
    public List<Org> retrieveOrgsByPowerId (int powerId);
    
    public List<Org> retrieveOrgsByVillainId (int villainId);
    
}
