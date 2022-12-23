package com.fquer.springchess.controller;

import com.fquer.springchess.model.dto.ConnectRequest;
import com.fquer.springchess.model.dto.Game;
import com.fquer.springchess.model.dto.GamePlay;
import com.fquer.springchess.model.dto.Logins;
import com.fquer.springchess.model.dto.Player;
import com.fquer.springchess.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:8080",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class GameController {
    private final GameService gameService;

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
}
