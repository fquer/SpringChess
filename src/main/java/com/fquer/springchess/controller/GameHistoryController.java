package com.fquer.springchess.controller;

import com.fquer.springchess.model.dto.*;
import com.fquer.springchess.service.GameHistoryService;
import com.fquer.springchess.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/gameHistory")
@CrossOrigin(origins = "http://localhost:8080",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class GameHistoryController {
    private final GameHistoryService gameHistoryService;

    @PostMapping("/getMatchHistory")
    public GamePlayDb getMatchHistory(@RequestBody GameHistory request) {
        log.info("getMatchHistory request: {}", request);
        return gameHistoryService.getMatchHistory(request.getGameId(), request.getMoveOrder());
    }

    @GetMapping("/getMatches")
    public List<GameDb> getMatches() {
        log.info("getMatches request");
        return gameHistoryService.getMatches();
    }

    @PostMapping("/getMatchPlayHistory")
    public Game getMatchPlayHistory(@RequestBody GameHistory request) {
        log.info("getMatchHistory request: {}", request);
        return gameHistoryService.playHistory(request.getMoveOrder(), request.getGameId());
    }
}
