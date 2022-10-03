package com.example.guessthenumber001.service;

import com.example.guessthenumber001.model.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {
}
