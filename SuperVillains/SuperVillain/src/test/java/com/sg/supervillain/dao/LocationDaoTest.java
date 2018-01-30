/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.dao.LocationDao;
import com.sg.supervillain.controller.helper.TestHelper;
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
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
public class LocationDaoTest {

    LocationDao locDao;
    TestHelper helper = new TestHelper();

    public LocationDaoTest() {
         ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        locDao = ctx.getBean("locDao", LocationDao.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SuperVillainPersistenceException {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(
//                "test-applicationContext.xml");
//        locDao = ctx.getBean("locDao", LocationDao.class);

        helper.removeSightings();
        List<Location> locs = locDao.retrieveAllLocations();
        for (Location currentLocation : locs) {

            locDao.removeLocation(currentLocation.getLocId());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetOneLocation() {
        Location loc = helper.createLoc(1);

        Location fromDb = locDao.retrieveLocationById(loc.getLocId());
        assertEquals(loc, fromDb);
    }

    @Test
    public void testRemoveLocation() throws SuperVillainPersistenceException {
        Location loc = helper.createLoc(1);

        Location fromDb = locDao.retrieveLocationById(loc.getLocId());
        assertEquals(loc, fromDb);

        locDao.removeLocation(loc.getLocId());
        assertNull(locDao.retrieveLocationById(loc.getLocId()));
    }

    @Test
    public void testUpdateLocation() {
        Location loc = helper.createLoc(1);

        Location fromDb = locDao.retrieveLocationById(loc.getLocId());
        assertEquals(loc, fromDb);

        loc.setDescription("New Descrip");
        locDao.updateLocation(loc);
        Location updateDb = locDao.retrieveLocationById(loc.getLocId());
        assertEquals("New Descrip", updateDb.getDescription());
    }

    @Test
    public void testRetrieveTwoLocations() {
        List<Location> locList = new ArrayList<Location>();
        for (int i = 0; i < 2; i++) {
            Location location = helper.createLoc(i);
            locList.add(location);
        }
        List<Location> locListFrmDao = locDao.retrieveAllLocations();
        assertEquals(locList, locListFrmDao);
        assertEquals(2, locListFrmDao.size());

    }

    @Test
    public void testRetrieveLocsByVillainId() {
        Sighting sighting = helper.createSighting(1);
        Location loc = sighting.getLocation();
        SuperVillain villain = helper.createVillain(1);
        List<SuperVillain> villainList = new ArrayList<>();
        villainList.add(villain);
        sighting.setVillainsList(villainList);
        helper.updateSighting(sighting);

        List<Location> locList = locDao.retrieveLocsByVillainId(villain.getId());
        assertTrue(locList.contains(loc));
    }

    @Test
    public void testRetrieveLocsByPowerId() {
        Sighting sighting = helper.createSighting(1);
        Location loc = sighting.getLocation();
        SuperVillain villain = helper.createVillain(1);
        List<SuperVillain> villainList = new ArrayList<>();
        villainList.add(villain);
        sighting.setVillainsList(villainList);
        Power power = helper.createPower(1);
        List<Power> powersList = new ArrayList<>();
        powersList.add(power);
        villain.setPowers(powersList);
        helper.updateSighting(sighting);
        
        helper.updateVillain(villain);

        List<Location> locList = locDao.retrieveLocsByVillainId(villain.getId());
        assertTrue(locList.contains(loc));

    }

    @Test
    public void testRetrieveLocBySightId() {
        Sighting sighting = helper.createSighting(1);
        Location loc = sighting.getLocation();

        Location fromDao = locDao.retrieveLocBySightId(sighting.getSightingId());
        assertEquals(fromDao, loc);
    }

}
