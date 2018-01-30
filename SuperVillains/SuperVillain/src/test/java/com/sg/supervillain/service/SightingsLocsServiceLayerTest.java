/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.service;
//final

import com.sg.supervillain.controller.helper.TestHelperService;
import com.sg.supervillain.dao.SuperVillainPersistenceException;
import com.sg.supervillain.model.Location;
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0149245
 */
public class SightingsLocsServiceLayerTest {

    Sighting sight = new Sighting();
    Location onlyLoc = new Location();
    SuperVillain villain = new SuperVillain();

    SightingsLocsServiceLayer slServiceTest;
    VillainPowerOrgServiceLayer vpoServiceTest;

    public SightingsLocsServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        slServiceTest = ctx.getBean("slServiceTest", SightingsLocsServiceLayer.class);
        vpoServiceTest = ctx.getBean("vpoServiceTest", VillainPowerOrgServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SuperVillainPersistenceException {
        List<Org> orgList = vpoServiceTest.retrieveAllOrgs();
        for (Org currentOrg : orgList) {
            vpoServiceTest.removeOrg(currentOrg.getorgId());
        }

        List<Power> powrList = vpoServiceTest.retrieveAllPowers();
        for (Power currentPwr : powrList) {
            vpoServiceTest.removePower(currentPwr.getPowerId());
        }

        List<SuperVillain> vilList = vpoServiceTest.retrieveAllVillains();
        for (SuperVillain currentVil : vilList) {
            vpoServiceTest.removeVillain(currentVil.getId());
        }

        List<Sighting> sightList = slServiceTest.retrieveAllSightings();
        for (Sighting currentSight : sightList) {
            slServiceTest.removeSighting(currentSight.getSightingId());
        }

        List<Location> locList = slServiceTest.retrieveAllLocations();
        for (Location currentLoc : locList) {
            slServiceTest.removeLocation(currentLoc.getLocId());
        }

        sight = new Sighting();
        String date = "2017-10-31 05:12:32";
        LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        onlyLoc = TestHelperService.createLocation();
        List<SuperVillain> villainsList = new ArrayList<>();
        villain = TestHelperService.createVillain();
        villainsList.add(villain);

        sight.setSightingId(1);
        sight.setLocation(onlyLoc);
        sight.setTimeOfSight(d);
        sight.setVillainsList(villainsList);

        List<Power> pwrListFmVil = villain.getPowers();
        for (Power currentPwr : pwrListFmVil) {
            vpoServiceTest.createPower(currentPwr);
        }

        List<Org> orgListFmVil = villain.getOrgs();
        for (Org currentOrg : orgListFmVil) {
            vpoServiceTest.addOrg(currentOrg);
        }

        vpoServiceTest.addVillain(villain);
        slServiceTest.addLocation(onlyLoc);
        slServiceTest.addSighting(sight);

    }

    @After
    public void tearDown()  {

    }

    @Test
    public void testAddLocation() {
        Location frmServ = slServiceTest.addLocation(onlyLoc);
        assertEquals(frmServ, onlyLoc);
    }

    @Test
    public void testRemoveLocation() throws SuperVillainPersistenceException {
        Location delLoc = slServiceTest.addLocation(onlyLoc);
        slServiceTest.removeLocation(delLoc.getLocId());
    }

    @Test
    public void testUpdateLocation() {
        Location loc = slServiceTest.retrieveLocationById(onlyLoc.getLocId());
        loc.setDescription("Test Description");
        Location frmSv = slServiceTest.updateLocation(loc);
        assertEquals(frmSv, loc);
    }

    @Test
    public void testRetrieveLocationById() {
        assertNotNull(slServiceTest.retrieveLocationById(onlyLoc.getLocId()));
    }

    @Test
    public void testRetrieveAllLocations() {
        assertNotNull(slServiceTest.retrieveAllLocations());
    }

    @Test
    public void testRetrieveLocsByVillainId() {
        assertNotNull(slServiceTest.retrieveLocsByVillainId(villain.getId()));
    }

    @Test
    public void testRetrieveLocsByPowerId() {
        Power power = villain.getPowers().get(0);
        assertNotNull(slServiceTest.retrieveLocsByPowerId(power.getPowerId()));
    }

    @Test
    public void testRetrieveLocBySightId() {
        assertNotNull(slServiceTest.retrieveLocBySightId(sight.getSightingId()));
    }

    @Test
    public void testRetrieveLocsBySightingsIds() {
        List<Integer> sightIds = new ArrayList<>();
        sightIds.add(sight.getSightingId());
        assertNotNull(slServiceTest.retrieveLocsBySightingsIds(sightIds));
    }

    @Test
    public void testAddCompleteSighting() {
        Sighting frmServ = slServiceTest.addSighting(sight);
        assertEquals(sight, frmServ);
    }

    @Test
    public void testRemoveSighting() {        
        Sighting deleteSight = slServiceTest.addSighting(sight);
        slServiceTest.removeSighting(deleteSight.getSightingId());
    }

    @Test
    public void testUpdateSighting() {
        String date = "2017-10-01 03:12:32";
        LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        sight.setTimeOfSight(d);
        Sighting sightFmSev = slServiceTest.updateSighting(sight);
        assertEquals(sight, sightFmSev);
    }

    @Test
    public void testRetrieveSightingById() {
        assertNotNull(slServiceTest.retrieveSightingById(sight.getSightingId()));
        assertNotNull(slServiceTest.retrieveSightingById(sight.getSightingId()).getLocation());
        assertNotNull(slServiceTest.retrieveSightingById(sight.getSightingId()).getVillainsList());
    }

    @Test
    public void testRetrieveAllSightings() {
        assertNotNull(slServiceTest.retrieveAllSightings());
        List<Sighting> sightList = slServiceTest.retrieveAllSightings();
        for (Sighting currentSight : sightList) {
            assertNotNull(currentSight.getVillainsList());
            assertNotNull(currentSight.getLocation());
        }
    }

    @Test
    public void testRetrieveSightingsByVillainId() {
        List<Sighting> sightList = slServiceTest.retrieveSightingsByVillainId(villain.getId());
        for (Sighting currentSight : sightList) {
            assertNotNull(currentSight.getLocation());
            assertNotNull(currentSight.getVillainsList());
        }
    }

    @Test
    public void testRetrieveSightingsByOrgId() {
        List<Sighting> sightList = slServiceTest.retrieveSightingsByOrgId(sight.getSightingId());
        assertNotNull(sightList);
        for (Sighting currentSight : sightList) {
            assertNotNull(currentSight.getLocation());
            assertNotNull(currentSight.getVillainsList());
        }
    }

    @Test
    public void testRetrieveSightingsByLocationId() {
        List<Sighting> sightList = slServiceTest.retrieveSightingsByLocationId(sight.getSightingId());
        assertNotNull(sightList);
        for (Sighting currentSight : sightList) {
            assertNotNull(currentSight.getLocation());
            assertNotNull(currentSight.getVillainsList());
        }
    }

    @Test
    public void testRetrieveSightingsByDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate d = LocalDate.parse("2017-10-21", format);
        List<Sighting> sightList = slServiceTest.retrieveSightingsByDate(d);
        assertNotNull(sightList);
        for (Sighting currentSight : sightList) {
            assertNotNull(currentSight.getLocation());
            assertNotNull(currentSight.getVillainsList());
        }
    }

    @Test
    public void testRetrieveVillainsByDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate d = LocalDate.parse("2017-10-31", format);
        List<SuperVillain> vilList = slServiceTest.retrieveVillainsByDate(d);
        assertNotNull(vilList);
        for (SuperVillain currentVil : vilList) {
            assertNotNull(currentVil.getOrgs());
            assertNotNull(currentVil.getPowers());
        }
    }

    @Test
    public void testRetrieveLocationsByDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate d = LocalDate.parse("2017-10-31", format);
        assertNotNull(slServiceTest.retrieveLocationsByDate(d));

    }

    @Test
    public void testRetrieveVillainsByLocId() {
        List<SuperVillain> vilList = slServiceTest.retrieveVillainsByLocId(onlyLoc.getLocId());
        assertNotNull(vilList);
        for (SuperVillain currentvil : vilList) {
            assertNotNull(currentvil.getOrgs());
            assertNotNull(currentvil.getPowers());
        }
    }

}
