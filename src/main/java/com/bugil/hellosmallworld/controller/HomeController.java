package com.bugil.hellosmallworld.controller;

import com.bugil.hellosmallworld.dto.CardDTO;
import com.bugil.hellosmallworld.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;


@Controller
@Slf4j
public class HomeController {
    @Autowired
    private CardService cardService;


    @RequestMapping("/")
    public String home() {
        return "cardlist";
    }

    @RequestMapping("/cardlist")
    public String cardlist(Model model, @PageableDefault(page = 0, size = 25, sort = "name") Pageable pageable) throws FileNotFoundException, IOException{

        model.addAttribute("cards", cardService.getCards(pageable));

        return "cardlist";
    }

    @GetMapping("/small")
    public String small() {
        return "small";
    }

    @GetMapping("/card")
    public String card(Model model, @RequestParam("cardname") String cardname) {
        model.addAttribute("card", cardService.getCard(cardname));

        return "card";
    }


    @PostMapping("/small_world")
    public String Small_World(Model model,@PageableDefault(page = 0, size = 25, sort = "name") Pageable pageable, @RequestParam("start") String start, @RequestParam("end") String end) throws FileNotFoundException, IOException {
        model.addAttribute("cards", cardService.getSmallCenters(start, end, pageable));

        return "cardlist";
    }


    @PostMapping("/cardmodify/{cardname}")
    public String cardmodify(Model model, @PathVariable("cardname") String cardname) {
        model.addAttribute("card", cardService.getCard(cardname));

        return "cardmodify";
    }

    @PostMapping("/cardmodifysave/{cardname}")
    public String cardmodifysave(Model model, @PathVariable("cardname") String cardname, CardDTO cardDTO) {
        cardService.update(cardname, cardDTO);
        model.addAttribute("card", cardService.getCard(cardDTO.getName()));

        return "card";
    }


}