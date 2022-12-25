package com.fquer.springchess.repository;

import com.fquer.springchess.model.dto.GameDb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<GameDb,String> {
    GameDb findByGameId(String gameId);
}
