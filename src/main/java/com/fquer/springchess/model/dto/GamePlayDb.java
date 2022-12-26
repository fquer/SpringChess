package com.fquer.springchess.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class GamePlayDb {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private String id;
    private String gameId;
    private String player;
    private String moveOrder;
    private String selectedCoordinate;
    private String moveToCoordinate;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
