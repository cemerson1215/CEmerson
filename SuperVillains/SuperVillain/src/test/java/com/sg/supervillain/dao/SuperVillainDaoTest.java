/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.controller.helper.TestHelper;
import com.sg.supervillain.model.Org;
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
import com.sg.supervillain.dao.SuperVillainDao;

/**
 *
 * @author n0149245
 */
public class SuperVillainDaoTest {

    SuperVillainDao superVillainDao;
    TestHelper helper = new TestHelper();

    public SuperVillainDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        superVillainDao = ctx.getBean("superVillainDao", SuperVillainDao.class);

        List<SuperVillain> villains = superVillainDao.retrieveAllVillains();
        for (SuperVillain currentVillain : villains) {
            superVillainDao.removeVillain(currentVillain.getId());
        }
    }

    @After
    public void tearDown() {
//        List<SuperVillain> villains = superVillainDao.retrieveAllVillains();
//        for (SuperVillain currentVillain : villains) {
//            superVillainDao.removeVillain(currentVillain.getId());
//        }
    }

    @Test
    public void testAddGetOneVillain() {
        SuperVillain villain = helper.createVillain(1);

        SuperVillain fromDb = superVillainDao.retrieveVillainById(villain.getId());
        villain.setOrgs(null);
        villain.setPowers(null);
        assertEquals(villain, fromDb);
    }

    @Test
    public void testRemoveVillain() {
        SuperVillain villain = helper.createVillain(1);

        SuperVillain fromDb = superVillainDao.retrieveVillainById(villain.getId());
        villain.setOrgs(null);
        villain.setPowers(null);
        assertEquals(fromDb, villain);

        superVillainDao.removeVillain(villain.getId());
        assertNull(superVillainDao.retrieveVillainById(villain.getId()));
    }

    @Test
    public void testUpdateVillain() {
        SuperVillain villain = helper.createVillain(1);

        villain.setDescription("Puddin");
        superVillainDao.updateVillain(villain);
        SuperVillain updateDb = superVillainDao.retrieveVillainById(villain.getId());
        assertEquals("Puddin", updateDb.getDescription());
    }

    @Test
    public void testRetrieveAllVillains() {
        List<SuperVillain> villainList = new ArrayList<SuperVillain>();
        for (int i = 0; i < 2; i++) {
            SuperVillain villain = helper.createVillain(i);
            villainList.add(villain);         
        }

        List<SuperVillain> villainListFmDb = superVillainDao.retrieveAllVillains();
        for (SuperVillain villain : villainList) {
            villain.setOrgs(null);
            villain.setPowers(null);
        }

        assertEquals(villainList, villainListFmDb);
        assertEquals(2, villainListFmDb.size());
    }

    @Test
    public void testretrieveVillainsByOrgId() {
        SuperVillain villain = helper.createVillain(1);
        Org org = helper.createOrg(1);
        List<Org> orgList = new ArrayList<Org>();
        orgList.add(org);
        villain.setOrgs(orgList);
        helper.updateVillain(villain);
        //SL will do associating so right now the villain returned doesn't match since it does not contain org data
        SuperVillain expectedVillain = helper.createVillain(1);
        int id = villain.getId();
        expectedVillain.setId(id);
        expectedVillain.setOrgs(null);
        expectedVillain.setPowers(null);
        List<SuperVillain> villainList = superVillainDao.retrieveVillainsByOrgId(org.getorgId());
        assertTrue(villainList.contains(expectedVillain));
    }

    @Test
    public void testretrieveVillainsByPowerId() {
        Power power = helper.createPower(1);
        SuperVillain villain = helper.createVillain(1);
        List<Power> powersList = new ArrayList<Power>();
        powersList.add(power);
        villain.setPowers(powersList);
        helper.updateVillain(villain);
        SuperVillain expectedVillain = helper.createVillain(1);
        int id = villain.getId();
        expectedVillain.setId(id);
        expectedVillain.setOrgs(null);
        expectedVillain.setPowers(null);
        List<SuperVillain> villainList = superVillainDao.retrieveVillainsByPowerId(power.getPowerId());
        assertTrue(villainList.contains(expectedVillain));

    }

    @Test
    public void testRetrieveVillainsBySightingId() {
        SuperVillain villain = helper.createVillain(1);
        Sighting sight = helper.createSighting(1);
        List<SuperVillain> villainsList = new ArrayList<>();
        villainsList.add(villain);
        sight.setVillainsList(villainsList);
        helper.updateSighting(sight);
        villain.setOrgs(null);
        villain.setPowers(null);
        List<SuperVillain> villainListDao = superVillainDao.retrieveVillainsBySightingId(sight.getSightingId());
        assertTrue(villainListDao.contains(villain));

    }
}
