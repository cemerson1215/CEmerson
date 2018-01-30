/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.dao.SightingsDao;
import com.sg.supervillain.controller.helper.TestHelper;
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0149245
 */
public class SightingsDaoTest {
    SightingsDao sightDao;
    TestHelper helper = new TestHelper();
    
    
    public SightingsDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        sightDao = ctx.getBean("sightDao", SightingsDao.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(
//                "test-applicationContext.xml");
//        sightDao = ctx.getBean("sightDao", SightingsDao.class);
        
        List<Sighting> sights = sightDao.retrieveAllSightings();
        for (Sighting currentSighting : sights) {
            sightDao.removeSighting(currentSighting.getSightingId());
        }
    }
    
    @After
    public void tearDown() {
//        List<Sighting> sights = sightDao.retrieveAllSightings();
//        for (Sighting currentSighting : sights) {
//            sightDao.removeSighting(currentSighting.getSightingId());
//        }
    }

    @Test
    public void testAddGetOneSighting() {
        Sighting sight = helper.createSighting(1);
        sight.setLocation(null);
        sight.setVillainsList(null);
        Sighting fromDao = sightDao.retrieveSightingById(sight.getSightingId());
        assertEquals(sight, fromDao);
    }

    @Test
    public void testRemoveSighting() {
        Sighting sight = helper.createSighting(1);
        Sighting fromDao = sightDao.retrieveSightingById(sight.getSightingId());
        assertEquals(sight.getSightingId(), fromDao.getSightingId());
        
        sightDao.removeSighting(sight.getSightingId());
        assertNull(sightDao.retrieveSightingById(sight.getSightingId()));
    }

    @Test
    public void testUpdateSighting() {
        Sighting sight = helper.createSighting(1);
        Sighting fromDao = sightDao.retrieveSightingById(sight.getSightingId());
        assertEquals(sight.getSightingId(), fromDao.getSightingId());
        
        String date = "2017-10-05 10:32:05";
        LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        sight.setTimeOfSight(d);
        sightDao.updateSighting(sight);
        Sighting updatefromDao = sightDao.retrieveSightingById(sight.getSightingId());
        assertEquals(d, updatefromDao.getTimeOfSight());
    }

    @Test
    public void testRetrieveTwoSightings() {
        List<Sighting> sightList = new ArrayList<>();
        for (int i = 1; i < 3; i++){
            Sighting sight = helper.createSighting(i);
            sightList.add(sight);
        }
        List<Sighting> sightListfrmDao = sightDao.retrieveAllSightings();
        assertEquals(2, sightListfrmDao.size());
    }

    @Test
    public void testRetrieveSightingsByVillainId() {
        SuperVillain villain = helper.createVillain(1);
        Sighting sight = helper.createSighting(1);
        List<SuperVillain> villainsList = new ArrayList<>();
        villainsList.add(villain);
        sight.setVillainsList(villainsList);
        helper.updateSighting(sight);
        
        int id = sight.getSightingId();
        Sighting expectedSight = helper.createSighting(1);
        expectedSight.setSightingId(id);
        
        //helper method creates location with 1 - so what comes back will have a loc of locid1        
        expectedSight.setLocation(null);
        expectedSight.setVillainsList(null);
        
        List<Sighting> sightList = sightDao.retrieveSightingsByVillainId(villain.getId());
        assertTrue(sightList.contains(expectedSight));
    }

    @Test
    public void testRetrieveSightingsByOrgId() {
        SuperVillain villain = helper.createVillain(1);
        Org org = helper.createOrg(1);
        List<Org> orgList = new ArrayList<>();
        orgList.add(org);
        villain.setOrgs(orgList);
        helper.updateVillain(villain);
        
        Sighting sight = helper.createSighting(1);
        List<SuperVillain> villainsList = new ArrayList<>();
        villainsList.add(villain);
        sight.setVillainsList(villainsList);
        helper.updateSighting(sight);
        
        int id = sight.getSightingId();
        Sighting expectedSight = helper.createSighting(1);
        expectedSight.setSightingId(id);
        expectedSight.setLocation(null);
        expectedSight.setVillainsList(null);
        
        List<Sighting> sightList = sightDao.retrieveSightingsByOrgId(org.getorgId());
        assertTrue(sightList.contains(expectedSight));
        
    }

    @Test
    public void testRetrieveSightingsByLocationId() {
        Sighting sight = helper.createSighting(1);
        //Sighting sight2 = helper.createSighting(1);
       // Location loc = sight.getLocation();
       // sight2.setLocation(loc);
        //helper.updateSighting(sight2);
        List<Sighting> sightList = sightDao.retrieveSightingsByLocationId(sight.getLocation().getLocId());
        assertEquals(1, sightList.size());
        
    }

    @Test
    public void testRetrieveSightingsByDate() {
        Sighting sight = helper.createSighting(1);
        String date = "2017-10-31 10:32:05";
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        List<Sighting> sightList2 = sightDao.retrieveSightingsByDate(LocalDate.parse("2017-10-31", format));        
        
        int id = sight.getSightingId();
        Sighting expectedSight = helper.createSighting(1);
        expectedSight.setSightingId(id);
        
        expectedSight.setLocation(null);
        expectedSight.setVillainsList(null);
        
        assertTrue(sightList2.contains(expectedSight));
    }

    
}
