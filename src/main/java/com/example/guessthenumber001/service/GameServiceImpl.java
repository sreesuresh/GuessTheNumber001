package com.example.guessthenumber001.service;

import com.example.guessthenumber001.model.Game;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepo;
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final int min = 1000;
    private final int max = 9999;
    Random rng = new Random();

    @Override
    public Game createGame(Game game) {
        game.setFinished(false);
        game.setCorrectGuess(this.generateCorrectGuess());
        return gameRepo.save(game);
    }

    @Override
    public Game findGame(Long id) {
        Optional<Game> gameOpt = gameRepo.findById(id);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            if (!(game.isFinished())) {
                game.setCorrectGuess(null);
            }
            return game;
        } else {
            return null;
        }
    }

    @Override
    public Iterable<Game> listGames() {
        Iterable<Game> games = gameRepo.findAll();
        for (Game game : games) {
            if (!(game.isFinished())) {
                game.setCorrectGuess(null);
            }
        }
        return games;
    }

    @Override
    public Game endGame(Long id) {
        Optional<Game> gameOpt = gameRepo.findById(id);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            game.setFinished(true);
            return gameRepo.save(game);
        } else {
            return null;
        }
    }

    private Integer generateCorrectGuess() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            digits.add(i);
        }

        Collections.shuffle(digits);

        int offset = 6;
        String result = "";
        for (int i = 0; i < 4; i++) {

            if (i == 0 && digits.get(i) == 0) {//if a leading zero is about to be created get a different digit
                result += digits.get(i + offset).toString();
            } else {
                result += digits.get(i).toString();
            }
        }
        Integer resultInt = Integer.parseInt(result);

        return resultInt;
    }
}
