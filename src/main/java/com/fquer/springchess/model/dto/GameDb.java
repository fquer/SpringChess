package com.fquer.springchess.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
public class GameDb {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private String id;
    private String gameId;
    private String player1;
    private String player2;
    private String winner;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private Date finishDate;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
