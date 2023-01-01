package com.fquer.springchess.model.dto;

import com.fquer.springchess.service.MapService;
import lombok.Data;

@Data
public class GameHistory {
    private String gameId;
    private String moveOrder;
}
