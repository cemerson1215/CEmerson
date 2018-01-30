/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.controller.helper;

import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.SuperVillain;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0149245
 */
public class TestHelperService {
    
    public static Location createLocation() {
        Location onlyLoc = new Location();
        onlyLoc.setLocId(1);
        onlyLoc.setName("Green Mountain");
        onlyLoc.setDescription("It is green");
        onlyLoc.setAddress("Home");
        onlyLoc.setLatitude(new Float("4.366661"));
        onlyLoc.setLongitude(new Float("7.272"));
        return onlyLoc;
    }
    
    public static Power createPower() {
        Power power = new Power();
        power.setPowerId(1);
        power.setDescription("Professional Level Hammer Slam");
        return power;
    }
    
    public static Org createOrg() {
        Org org = new Org();
        org.setorgId(1);
        org.setName("Suicide Squad");
        org.setDescription("We're BAD guys, it's what we do.");
        org.setAddress("Swamp Prison");
        org.setEmail("rapsheet101@cellblock6.com");
        org.setPhone("555-555-555");
        return org;
    }
    
    public static SuperVillain createVillain() {
        
        List<Power> powerList = new ArrayList<Power>();
        powerList.add(createPower());
        List<Org> orgList = new ArrayList<Org>();
        orgList.add(createOrg());

        SuperVillain villain = new SuperVillain();
        villain.setId(1);
        villain.setName("Joker");
        villain.setDescription("HaHAHAhahahAA");
        villain.setOrgs(orgList);
        villain.setPowers(powerList);
        
        return villain;
    }
}
