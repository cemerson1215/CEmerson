/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.dao;
//final
import com.sg.supervillain.dao.OrgDao;
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
public class OrgDaoTest {
    
    OrgDao orgDao;
    TestHelper helper = new TestHelper();
    
    public OrgDaoTest() {
         ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        orgDao = ctx.getBean("orgDao", OrgDao.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        orgDao = ctx.getBean("orgDao", OrgDao.class);
        
        List<Org> orgs = orgDao.retrieveAllOrgs();
        for (Org currentOrg : orgs){
            orgDao.removeOrg(currentOrg.getorgId());
        }
    }
    
    @After
    public void tearDown() {
//        List<Org> orgs = orgDao.retrieveAllOrgs();
//        for (Org currentOrg : orgs){
//            orgDao.removeOrg(currentOrg.getorgId());
//        }
    }

    @Test
    public void testAddGetOneOrg() {
        Org org = helper.createOrg(1);
        
        Org fromDb = orgDao.retrieveOrgById(org.getorgId());
        assertEquals(org, fromDb);        
        
    }

    @Test
    public void testRemoveOrg() {
        Org org = helper.createOrg(1);
        
        Org fromDb = orgDao.retrieveOrgById(org.getorgId());
        assertEquals(org, fromDb); 
        
        orgDao.removeOrg(org.getorgId());
        assertNull(orgDao.retrieveOrgById(org.getorgId()));
        
    }

    @Test
    public void testUpdateOrg() {
        Org org = helper.createOrg(1);
        
        Org fromDb = orgDao.retrieveOrgById(org.getorgId());
        assertEquals(org, fromDb); 
        
        org.setDescription("New Description");
        orgDao.updateOrg(org);
        Org updateDb = orgDao.retrieveOrgById(org.getorgId());
        assertEquals("New Description", updateDb.getDescription());
    }    

    @Test
    public void testRetrieveAllOrgs() {
        List<Org> orgList = new ArrayList<Org>();
        for (int i = 0; i < 3; i++){
            Org org = helper.createOrg(i);
            orgList.add(org);        
        }
        
        List<Org> fromDb = orgDao.retrieveAllOrgs();
        assertEquals (orgList, fromDb);
        assertEquals(3, fromDb.size());
    }

    @Test
    public void testRetrieveOrgsByPowerId() {
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
        
        List<Org> orgListFmDao = orgDao.retrieveOrgsByPowerId(power.getPowerId());
        assertTrue(orgListFmDao.contains(org));
    }

    @Test
    public void testRetrieveOrgsByVillainId() {
        SuperVillain villain = helper.createVillain(1);
        Org org = helper.createOrg(1);
        List<Org> orgList = new ArrayList<Org>();
        orgList.add(org);
        villain.setOrgs(orgList);
        helper.updateVillain(villain);
        List<Org> orgListFmDao = orgDao.retrieveOrgsByVillainId(villain.getId());
        assertTrue(orgListFmDao.contains(org));
    }
    
}
