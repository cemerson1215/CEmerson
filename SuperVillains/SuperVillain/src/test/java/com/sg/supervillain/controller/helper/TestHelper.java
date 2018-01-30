/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.controller.helper;
//final
import com.sg.supervillain.dao.LocationDao;
import com.sg.supervillain.dao.OrgDao;
import com.sg.supervillain.dao.PowersDao;
import com.sg.supervillain.dao.SightingsDao;
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sg.supervillain.dao.SuperVillainDao;

/**
 *
 * @author n0149245
 */
public class TestHelper {
    @Inject
    PowersDao powerDao;
    
    @Inject
    SuperVillainDao superVillainDao;
    
    @Inject
    OrgDao orgDao;
    
    @Inject
    LocationDao locDao;
    
    @Inject
    SightingsDao sightDao;

    public PowersDao getPowerDao() {
        return powerDao;
    }

    public void setPowerDao(PowersDao powerDao) {
        this.powerDao = powerDao;
    }

    public SuperVillainDao getSuperVillainDao() {
        return superVillainDao;
    }

    public void setSuperVillainDao(SuperVillainDao superVillainDao) {
        this.superVillainDao = superVillainDao;
    }

    public OrgDao getOrgDao() {
        return orgDao;
    }

    public void setOrgDao(OrgDao orgDao) {
        this.orgDao = orgDao;
    }

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
    

    
    public TestHelper() {
         ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "test-applicationContext.xml");
        powerDao = ctx.getBean("powerDao", PowersDao.class); 
        superVillainDao = ctx.getBean("superVillainDao", SuperVillainDao.class);
        orgDao = ctx.getBean("orgDao", OrgDao.class);
        locDao = ctx.getBean("locDao", LocationDao.class);
        sightDao = ctx.getBean("sightDao", SightingsDao.class);
    }        

    public Power createPower(int num) {
        Power power = new Power();
        power.setPowerId(num);
        power.setDescription("fly " + num);
        powerDao.createPower(power);
        return power;
    }

    public Org createOrg(int num) {
        Org org = new Org();
        org.setorgId(num);
        org.setName("Suicide Squad");
        org.setDescription("Some Org");
        org.setAddress("Deep Sea");
        org.setEmail("org@villains.com");
        org.setPhone("555-555-555");
        orgDao.addOrg(org);
        return org;
    }

    public Location createLoc(int num) {
        Location loc = new Location();
        loc.setLocId(num);
        loc.setName("Green Mountain");
        loc.setDescription("It is green");
        loc.setAddress("Home");
        loc.setLatitude(new Float("4.366661"));
        loc.setLongitude(new Float("7.272"));
        locDao.addLocation(loc);
        return loc;
    }

    public SuperVillain createVillain(int num) {
        List<Power> powerList = new ArrayList<Power>();
        List<Org> orgList = new ArrayList<Org>();

        SuperVillain villain = new SuperVillain();
        villain.setId(num);
        villain.setName("Joker");
        villain.setDescription("HaHAHAhahahAA");
        villain.setOrgs(orgList);
        villain.setPowers(powerList);
        superVillainDao.addVillain(villain);
        return villain;
    }
    
    public SuperVillain updateVillain(SuperVillain villain){
        superVillainDao.updateVillain(villain);
        return villain;
    }
    
    public Sighting createSighting (int num) {
        List<SuperVillain> villainsList = new ArrayList<SuperVillain>();
        Location location = createLoc(1);
        String date = "2017-10-31 10:32:05";
        LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        Sighting sighting = new Sighting();
        sighting.setSightingId(num);
        sighting.setLocation(location);
        sighting.setTimeOfSight(d);
        sighting.setVillainsList(villainsList);
        sightDao.addSighting(sighting);
        return sighting;
    }
    
    public Sighting updateSighting(Sighting sighting) {
        sightDao.updateSighting(sighting);
        return sighting;
    }
    
    public void removeSightings() {
        List<Sighting> sights = sightDao.retrieveAllSightings();
        for (Sighting currentSighting : sights) {
            sightDao.removeSighting(currentSighting.getSightingId());
        }
    }
}
