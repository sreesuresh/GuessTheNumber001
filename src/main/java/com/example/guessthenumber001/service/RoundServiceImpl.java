package com.example.guessthenumber001.service;

import com.example.guessthenumber001.model.Game;
import com.example.guessthenumber001.model.Round;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@Log
@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    private RoundRepository roundRepo;
    @Autowired
    private GameServiceImpl gameServiceImpl;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final String winCondition = "e:4:p:0";

    @Override
    public Round createRound(Round round) {
        Game game = gameServiceImpl.findGame(round.getGameId());
        int answer = game.getCorrectGuess();

        String result = this.calcResult(round.getGuess(), answer);

        round.setResultOfGuess(result);
        round.setTimeOfGuess(dtf.format(LocalDateTime.now()));


        if (result.equals(winCondition)) {
            System.out.println("Game won");
            gameServiceImpl.endGame(game.getId());
        }

        return roundRepo.save(round);
    }

    @Override
    public Collection<Round> listRounds(Long id) {
        Collection<Round> result = new ArrayList<>();
        for (Round round : roundRepo.findAll()) {
            if (round.getGameId() == id) {
                result.add(round);
            }
        }
        return result;
    }

    private String calcResult(int guess, int answer) {
        String guessStr = String.valueOf(guess);
        String answerStr = String.valueOf(answer);

        int exactGuesses = 0;
        int partialGuesses = 0;
        for (int i = 0; i < guessStr.length(); i++) {
            if (answerStr.charAt(i) == guessStr.charAt(i)) {
                exactGuesses++;
            } else if (answerStr.contains("" + guessStr.charAt(i))) {
                partialGuesses++;
            }
        }
        String result = "e:" + exactGuesses + ":p:" + partialGuesses;

        return result;
    }


}
