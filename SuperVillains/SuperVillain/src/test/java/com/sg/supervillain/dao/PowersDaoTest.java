/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.dao.PowersDao;
import com.sg.supervillain.controller.helper.TestHelper;
import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.SuperVillain;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0149245
 */
public class PowersDaoTest {

    PowersDao powerDao;
    TestHelper helper = new TestHelper();

    public PowersDaoTest() {
          ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        powerDao = ctx.getBean("powerDao", PowersDao.class);
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
//        powerDao = ctx.getBean("powerDao", PowersDao.class);

        List<Power> powers = powerDao.retrieveAllPowers();
        for (Power currentPower : powers) {
            powerDao.removePower(currentPower.getPowerId());
        }
    }

    @After
    public void tearDown() {
//        List<Power> powers = powerDao.retrieveAllPowers();
//        for (Power currentPower : powers) {
//            powerDao.removePower(currentPower.getPowerId());
//        }
    }

    @Test
    public void testCreateGetOnePower() {
        Power power = helper.createPower(1);

        Power fromDb = powerDao.retrievePowerById(power.getPowerId());
        assertEquals(power, fromDb);
    }

    @Test
    public void testRemovePower() {
        Power power = helper.createPower(1);

        Power fromDb = powerDao.retrievePowerById(power.getPowerId());
        assertEquals(power, fromDb);

        powerDao.removePower(power.getPowerId());
        assertNull(powerDao.retrievePowerById(power.getPowerId()));
    }

    @Test
    public void testUpdatePower() {
        Power power = helper.createPower(1);

        Power fromDb = powerDao.retrievePowerById(power.getPowerId());
        assertEquals(power, fromDb);

        power.setDescription("New Description");
        powerDao.updatePower(power);
        Power updateDb = powerDao.retrievePowerById(power.getPowerId());
        assertEquals("New Description", updateDb.getDescription());
    }

    @Test
    public void testRetrieveTwoPowers() {
        List<Power> powerList = new ArrayList<Power>();
        for (int i = 0; i < 2; i++) {
            Power power = helper.createPower(i);
            powerList.add(power);
        }
        List<Power> powersFromDao = powerDao.retrieveAllPowers();

        assertEquals(powerList, powersFromDao);
        assertEquals(2, powersFromDao.size());
    }

    @Test
    public void testRetrievePowersByVillainId() {
        Power power = helper.createPower(1);
        SuperVillain villain = helper.createVillain(1);
        List<Power> powersList = new ArrayList<Power>();
        powersList.add(power);
        villain.setPowers(powersList);
        helper.updateVillain(villain);
        int id = villain.getId();
        List<Power> powersByVilId = powerDao.retrievePowersByVillainId(id);
        assertTrue(powersByVilId.contains(power));
    }

    @Test
    public void testRetrievePowersByOrgId() {
        Power power = helper.createPower(1);

        SuperVillain villain = helper.createVillain(1);
        List<Power> powersList = new ArrayList<Power>();
        powersList.add(power);
        villain.setPowers(powersList);

        Org org = helper.createOrg(1);
        List<Org> orgList = new ArrayList<Org>();
        orgList.add(org);
        villain.setOrgs(orgList);
        helper.updateVillain(villain);

        List<Power> powersByOrgIdList = powerDao.retrievePowersByOrgId(org.getorgId());
        assertTrue(powersByOrgIdList.contains(power));

    }

}
