/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.controller;

import com.sg.baseball.model.Manufactuer;
import com.sg.baseball.service.CardService;
import com.sg.baseball.service.ManufactService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author n0149245
 */
@Controller
public class BaseballController {
    CardService cardService;
    ManufactService manufactService;
    
    @Inject
    public BaseballController(CardService cardService, ManufactService manufactService) {
        this.cardService = cardService;
        this.manufactService = manufactService;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayHomePage(Model model) {
        return "home";
    }
    
    @RequestMapping(value="/manufactInfo", method=RequestMethod.GET)
    public String manufactInfo(Model model) {
        List<Manufactuer> manufactList = manufactService.retrieveAllManufactuers();
        model.addAttribute("manufactList", manufactList);
        return "manufactInfo";
    }
}
