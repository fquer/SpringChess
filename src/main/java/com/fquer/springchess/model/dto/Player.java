package com.fquer.springchess.model.dto;

import com.fquer.springchess.model.enums.ColorEnum;
import lombok.Data;

@Data
public class Player {
    private String login;
    private ColorEnum color;
}
