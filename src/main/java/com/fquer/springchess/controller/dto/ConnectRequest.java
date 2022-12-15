package com.fquer.springchess.controller.dto;

import com.fquer.springchess.model.rto.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
}
