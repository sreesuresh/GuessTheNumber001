package com.example.guessthenumber001.service;

import com.example.guessthenumber001.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
