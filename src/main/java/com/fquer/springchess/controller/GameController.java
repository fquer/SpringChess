package com.fquer.springchess.controller;

import com.fquer.springchess.model.dto.*;
import com.fquer.springchess.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:8080",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class GameController {
    private final GameService gameService;

    @PostMapping(value = "/init")
    public void start() {
        log.info("init request");
        gameService.init();
    }

    @PostMapping(value = "/start", consumes = {"application/json"})
    public ResponseEntity<Game> start(@RequestBody Player player) {
        log.info("start game request: {}", player);
        return ResponseEntity.ok(gameService.createGame(player));
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer()));
    }

    @PostMapping("/gamestatus")
    public ResponseEntity<Game> gamestatus(@RequestBody ConnectRequest request) {
        return ResponseEntity.ok(gameService.gameStatus(request.getPlayer()));
    }

    @PostMapping("/checkLogin")
    public ResponseEntity<Logins> checkLogin() {
        log.info("checkLogin request");
        return ResponseEntity.ok(gameService.checkLogin());
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gameplay(@RequestBody GamePlay request) {
        log.info("gameplay request: {}", request);
        return ResponseEntity.ok(gameService.gamePlay(request.getPlayer(), request.getMoveToCoordinate()));
    }

    @PostMapping("/checkMoveableArea")
    public ResponseEntity<Game> checkMoveableArea(@RequestBody GamePlay request) {
        log.info("checkMoveableArea request: {}", request);
        return ResponseEntity.ok(gameService.checkMoveableArea(request.getPlayer(), request.getSelectedCoordinate()));
    }

    @PostMapping("/cancelSelectedCoordinate")
    public ResponseEntity<Game> cancelSelectedCoordinate(@RequestBody GamePlay request) {
        log.info("cancelSelectedCoordinate request: {}", request);
        return ResponseEntity.ok(gameService.cancelSelectedCoordinate(request.getPlayer(), request.getSelectedCoordinate()));
    }

    @PostMapping("/getMatchHistory")
    public GameDb getMatchHistory(@RequestBody Game request) {
        log.info("getMatchHistory request: {}", request);
        return gameService.getMatchHistory(request.getGameId());
    }

    @GetMapping("/getMatches")
    public List<GameDb> getMatches() {
        log.info("getMatches request");
        return gameService.getMatches();
    }
}
