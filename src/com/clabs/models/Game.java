package com.clabs.models;

import java.util.Date;

final public class Game {

    public Integer id;

    public String name;

    public String gameType;

    public Integer accountId;

    public String description;

    public Float adjustmentFactor;

    public String gameRefId;

    public Date created;

    public String getName() {
        return name;
    }

    public Game setName(String name) {
        this.name = name;
        return this;
    }

    public Game setGameType(String gameType) {
        this.gameType = gameType;
        return this;
    }

    public Game setDescription(String description) {
        this.description = description;
        return this;
    }

    public Game setAdjustmentFactor(Float adjustmentFactor) {
        this.adjustmentFactor = adjustmentFactor;
        return this;
    }

    public Game setGameRefId(String externalGameRefId) {
        this.gameRefId = externalGameRefId;
        return this;
    }
}
