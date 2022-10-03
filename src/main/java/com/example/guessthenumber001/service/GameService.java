package com.example.guessthenumber001.service;

import com.example.guessthenumber001.model.Game;

public interface GameService {
    Game createGame(Game game);
    Game findGame(Long id);
    Iterable<Game> listGames();
    Game endGame(Long id);
}
