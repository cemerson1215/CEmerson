/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.controller;

import com.sg.baseball.model.Card;
import com.sg.baseball.model.Manufactuer;
import com.sg.baseball.service.CardService;
import com.sg.baseball.service.ManufactService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        Card card = new Card();
        model.addAttribute("card", card);
        List<Manufactuer> manufactList = manufactService.retrieveAllManufactuers();
        model.addAttribute("manufactList", manufactList);
        List<Card> cards = cardService.retrieveAllCards();
        model.addAttribute("cards", cards);
        return "home";
    }
    
    @RequestMapping(value="/manufactInfo", method=RequestMethod.GET)
    public String manufactInfo(Model model) {
        List<Manufactuer> manufactList = manufactService.retrieveAllManufactuers();
        model.addAttribute("manufactList", manufactList);
        Manufactuer manufact = new Manufactuer();
        model.addAttribute("Manufact", manufact);
        return "manufactInfo";
    }
    
    @RequestMapping(value="/createManufact", method=RequestMethod.POST)
    public String createManufact(@ModelAttribute("Manufact") Manufactuer manufact) {
        manufactService.addManufactuer(manufact);
        return "redirect:manufactInfo";
    }
    
    @RequestMapping(value="/createCard", method=RequestMethod.POST)
    public String createCard(@ModelAttribute("card") Card card, HttpServletRequest request){
        String manufactId = request.getParameter("manufactId");
        int manufId = Integer.parseInt(manufactId);
        card.setManufactuer(manufactService.retrieveManufById(manufId));
        cardService.addCard(card);
        return "redirect:/";
    }
}
