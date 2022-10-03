package com.example.guessthenumber001.service;

import com.example.guessthenumber001.model.Round;

import java.util.Collection;

public interface RoundService {
    Round createRound(Round round);

    Collection<Round> listRounds(Long id);
}
