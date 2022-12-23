package com.fquer.springchess.model.dto;

import lombok.Data;

@Data
public class GamePlay {
    private Player player;
    private String selectedCoordinate;
    private String moveToCoordinate;
}
