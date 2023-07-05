package com.bugil.hellosmallworld.controller;

import com.bugil.hellosmallworld.dto.CardDTO;
import com.bugil.hellosmallworld.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;


@Controller
@Slf4j
public class HomeController {
    @Autowired
    private CardService boardService;


    @RequestMapping("/cardlist")
    public String cardlist(Model model, @PageableDefault(page = 0, size = 25, sort = "name") Pageable pageable) throws FileNotFoundException, IOException{
        model.addAttribute("cards", boardService.getCards(pageable));
        return "cardlist";
    }

    @GetMapping("/small")
    public String small() {
        return "small";
    }

    @GetMapping("/card/cardname")
    public String card() {
        return "card";
    }

    @PostMapping("/small_world")
    public String Small_World(Model model,@PageableDefault(page = 0, size = 25, sort = "name") Pageable pageable, @RequestParam("start") String start, @RequestParam("end") String end) throws FileNotFoundException, IOException {
        model.addAttribute("cards", boardService.getSmallCenters(start, end, pageable));

        return "cardlist";
    }



}