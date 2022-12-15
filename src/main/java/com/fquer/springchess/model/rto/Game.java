package com.fquer.springchess.model.rto;

import com.fquer.springchess.service.MapService;
import lombok.Data;

@Data
public class Game {
    private Player player1;
    private Player player2;
    private MapService board;
}
