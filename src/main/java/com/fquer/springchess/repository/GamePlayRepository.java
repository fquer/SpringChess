package com.fquer.springchess.repository;

import com.fquer.springchess.model.dto.GamePlay;
import com.fquer.springchess.model.dto.GamePlayDb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayRepository extends MongoRepository<GamePlayDb,String> {

}
