package com.example.guessthenumber001.controller;

import com.example.guessthenumber001.model.Round;
import com.example.guessthenumber001.service.RoundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public class RoundRestController {
    @Autowired
    private RoundServiceImpl roundServiceImpl;

    @PostMapping("/guess")
    public Round guess(@RequestBody Round round) {
        return roundServiceImpl.createRound(round);
    }

    @GetMapping("/rounds/{id}")
    public Collection<Round> findRounds(@PathVariable Long id) {
        return roundServiceImpl.listRounds(id);
    }
}
