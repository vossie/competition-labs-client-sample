package com.clabs.models;

import java.util.Date;

final public class Game {

    public Integer id;

    public String name;

    public String gameType;

    public Integer accountId;

    public String pointsStyle;

    public String description;

    public String adjustmentFactor;

    public Integer externalGameRefId;

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

    public Game setPointsStyle(String pointsStyle) {
        this.pointsStyle = pointsStyle;
        return this;
    }

    public Game setDescription(String description) {
        this.description = description;
        return this;
    }

    public Game setAdjustmentFactor(String adjustmentFactor) {
        this.adjustmentFactor = adjustmentFactor;
        return this;
    }

    public Game setExternalGameRefId(Integer externalGameRefId) {
        this.externalGameRefId = externalGameRefId;
        return this;
    }
}
