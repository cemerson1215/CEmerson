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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0149245
 */
public class VillainPowerOrgServiceLayerTest {

    VillainPowerOrgServiceLayer vpoServiceTest;
    SightingsLocsServiceLayer slServiceTest;

    Sighting sight = new Sighting();
    Location onlyLoc = new Location();
    SuperVillain villain = new SuperVillain();
    Power power = new Power();
    Org org = new Org();

    public VillainPowerOrgServiceLayerTest() {
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
//
//        List<Org> orgList = vpoServiceTest.retrieveAllOrgs();
//        for (Org currentOrg : orgList) {
//            vpoServiceTest.removeOrg(currentOrg.getorgId());
//        }
//
//        List<Power> powrList = vpoServiceTest.retrieveAllPowers();
//        for (Power currentPwr : powrList) {
//            vpoServiceTest.removePower(currentPwr.getPowerId());
//        }
//
//        List<SuperVillain> vilList = vpoServiceTest.retrieveAllVillains();
//        for (SuperVillain currentVil : vilList) {
//            vpoServiceTest.removeVillain(currentVil.getId());
//        }
//
//        List<Sighting> sightList = slServiceTest.retrieveAllSightings();
//        for (Sighting currentSight : sightList) {
//            slServiceTest.removeSighting(currentSight.getSightingId());
//        }
//
//        List<Location> locList = slServiceTest.retrieveAllLocations();
//        for (Location currentLoc : locList) {
//            slServiceTest.removeLocation(currentLoc.getLocId());
//        }

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

        org = TestHelperService.createOrg();
        vpoServiceTest.addOrg(org);
        power = TestHelperService.createPower();
        vpoServiceTest.createPower(power);

    }

    @After
    public void tearDown() throws SuperVillainPersistenceException {
//        List<Org> orgList = vpoServiceTest.retrieveAllOrgs();
//        for (Org currentOrg : orgList) {
//            vpoServiceTest.removeOrg(currentOrg.getorgId());
//        }
//
//        List<Power> powrList = vpoServiceTest.retrieveAllPowers();
//        for (Power currentPwr : powrList) {
//            vpoServiceTest.removePower(currentPwr.getPowerId());
//        }
//
//        List<SuperVillain> vilList = vpoServiceTest.retrieveAllVillains();
//        for (SuperVillain currentVil : vilList) {
//            vpoServiceTest.removeVillain(currentVil.getId());
//        }
//
//        List<Sighting> sightList = slServiceTest.retrieveAllSightings();
//        for (Sighting currentSight : sightList) {
//            slServiceTest.removeSighting(currentSight.getSightingId());
//        }
//
//        List<Location> locList = slServiceTest.retrieveAllLocations();
//        for (Location currentLoc : locList) {
//            slServiceTest.removeLocation(currentLoc.getLocId());
//        }
    }

    @Test
    public void testCreatePower() {
        Power returnedPwr = vpoServiceTest.createPower(power);
        assertEquals(returnedPwr, power);
    }

    @Test
    public void testRemovePower() {
        Power deletePwr = vpoServiceTest.createPower(power);
        vpoServiceTest.removePower(deletePwr.getPowerId());
    }

    @Test
    public void testUpdatePower() {
        Power powerUpdate = vpoServiceTest.retrievePowerById(power.getPowerId());

        powerUpdate.setDescription("Test Description");
        Power frmService = vpoServiceTest.updatePower(powerUpdate);
        assertEquals(frmService, powerUpdate);
    }

    @Test
    public void testRetrievePowerById() {
        assertNotNull(vpoServiceTest.retrievePowerById(power.getPowerId()));
    }

    @Test
    public void testRetrieveAllPowers() {
        assertNotNull(vpoServiceTest.retrieveAllPowers());
    }

    @Test
    public void testAddOrg() {
        Org frmServ = vpoServiceTest.addOrg(org);
        assertEquals(frmServ, org);
    }

    @Test
    public void testRemoveOrg() {
        Org deleteOrg = vpoServiceTest.addOrg(org);
        vpoServiceTest.removeOrg(deleteOrg.getorgId());
        // there is no return - How do I verify, just no error?
    }

    @Test
    public void testUpdateOrg() {
        Org orgUpdate = vpoServiceTest.retrieveOrgById(org.getorgId());
        orgUpdate.setDescription("Test Description");
        vpoServiceTest.addOrg(orgUpdate);
        vpoServiceTest.updateOrg(orgUpdate);
        Org frmServ = vpoServiceTest.retrieveOrgById(orgUpdate.getorgId());
        assertEquals(frmServ, orgUpdate);
    }

    @Test
    public void testRetrieveOrgById() {
        assertNotNull(vpoServiceTest.retrieveOrgById(org.getorgId()));

    }

    @Test
    public void testRetrieveAllOrgs() {
        assertNotNull(vpoServiceTest.retrieveAllOrgs());
    }

    @Test
    public void testAddVillain() {
        SuperVillain frmServ = vpoServiceTest.addVillain(villain);
        assertNotNull(frmServ);
        assertNotNull(frmServ.getOrgs());
        assertNotNull(frmServ.getPowers());
//        assertEquals(villain, frmServ);
    }

    @Test
    public void testRemoveVillain() {

    }

    @Test
    public void testUpdateVillain() {
        SuperVillain villainUpdate = vpoServiceTest.retrieveVillainById(villain.getId());
        villainUpdate.setDescription("Harleen Frances Quinzel");
        SuperVillain frmServ = vpoServiceTest.updateVillain(villainUpdate);
        assertEquals(frmServ, villainUpdate);
    }

    @Test
    public void testRetrieveVillainById() {
        assertNotNull(vpoServiceTest.retrieveVillainById(villain.getId()));
    }

    @Test
    public void testRetrieveAllVillains() {
        List<SuperVillain> vilList = vpoServiceTest.retrieveAllVillains();
        assertNotNull(vilList);
        for (SuperVillain currentVil : vilList) {
            assertNotNull(currentVil.getPowers());
            assertNotNull(currentVil.getOrgs());
        }
    }

    @Test
    public void testRetrievePowersByVillainId() {

        assertNotNull(vpoServiceTest.retrievePowersByVillainId(villain.getId()));
    }

    @Test
    public void testRetrievePowersByOrgId() {
        assertNotNull(vpoServiceTest.retrievePowersByOrgId(power.getPowerId()));
    }

    @Test
    public void testRetrieveOrgsByPowerId() {
        assertNotNull(vpoServiceTest.retrieveOrgsByPowerId(power.getPowerId()));
    }

    @Test
    public void testRetrieveOrgsByVillainId() {
        List<Org> orgList = vpoServiceTest.retrieveOrgsByVillainId(villain.getId());
        assertNotNull(orgList);        

    }

    @Test
    public void testRetrieveVillainsByPowerId() {
        List<SuperVillain> vilList = vpoServiceTest.retrieveVillainsByPowerId(power.getPowerId());
        assertNotNull(vilList);
        for (SuperVillain currentvil : vilList) {
            assertNotNull(currentvil.getPowers());
            assertNotNull(currentvil.getOrgs());
        }
    }

    @Test
    public void testRetrieveVillainsBySightingId() {
        List<SuperVillain> vilList = vpoServiceTest.retrieveVillainsBySightingId(sight.getSightingId());
        assertNotNull(vilList);
        for (SuperVillain currentvil : vilList) {
            assertNotNull(currentvil.getPowers());
            assertNotNull(currentvil.getOrgs());
        }
    }

}
