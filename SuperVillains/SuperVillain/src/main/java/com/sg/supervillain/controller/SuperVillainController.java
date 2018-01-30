/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.controller;

import com.sg.supervillain.model.Org;
import com.sg.supervillain.model.Power;
import com.sg.supervillain.model.Sighting;
import com.sg.supervillain.model.SuperVillain;
import com.sg.supervillain.service.SightingsLocsServiceLayer;
import com.sg.supervillain.service.VillainPowerOrgServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author n0149245
 */
@Controller
public class SuperVillainController {
    VillainPowerOrgServiceLayer vpoService;
    SightingsLocsServiceLayer slService;
    
    @Inject
    public SuperVillainController(VillainPowerOrgServiceLayer vpoService, SightingsLocsServiceLayer slService) {
        this.vpoService = vpoService;
        this.slService = slService;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayDvdPage(Model model) {
        List<Sighting> sightList = slService.retrieveAllSightings();        
        model.addAttribute("sightList", sightList);
        return "home";
    }
    
    
    @RequestMapping(value = "/createvillain", method=RequestMethod.GET)
    public String displayCreateVillainPage (Model model) {
        List<SuperVillain> villainList = vpoService.retrieveAllVillains();
        model.addAttribute("villainList", villainList);
        SuperVillain villain = new SuperVillain();
        model.addAttribute("Villain", villain);
        return "newvillain";
    }
    
    @RequestMapping(value="/createpower", method=RequestMethod.GET)
    public String displayCreatePowerPage (Model model) {
        List<Power> powerList = vpoService.retrieveAllPowers();
        model.addAttribute("powerList", powerList);
        Power power = new Power();
        model.addAttribute("Power", power);
        return "newpower";
    }
    
    @RequestMapping(value="/createPowerForm", method=RequestMethod.POST)
    public String createPowerForm (@ModelAttribute("power") Power power, BindingResult result ) {
        vpoService.createPower(power);        
        return "rediect:createpower";
    }
    
    @RequestMapping(value="/createorg", method=RequestMethod.GET)
    public String displayCreateOrgPage (Model model) {
        List<Org> orgList = vpoService.retrieveAllOrgs();
        model.addAttribute("orgList", orgList);
        Org org = new Org();
        model.addAttribute("Org", org);
        return "neworg";
    }
    
    @RequestMapping(value="/createOrgForm", method=RequestMethod.POST)
    public String createOrgForm (@ModelAttribute("org") Org org, BindingResult result ) {
        vpoService.addOrg(org);
        
        return "rediect:createorg";
    }
    
    @RequestMapping(value="/createloc", method=RequestMethod.GET)
    public String displayCreateLocPage (Model model) {
        return "newloc";
    }
    
    @RequestMapping(value="/createsighting", method=RequestMethod.GET)
    public String displayCreateSightingPage (Model model) {
        return "newsighting";
    }
}
