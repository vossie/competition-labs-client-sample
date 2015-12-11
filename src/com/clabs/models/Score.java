package com.clabs.models;

import java.util.Date;

final public class Score {

    public Integer id;

    public Integer competitionId;

    public Integer contestId;

    public Integer accountId;

    public Integer memberId;

    public Integer gameId;

    public Float sourceValue;

    public Float points;

    public String memberRefId;

    public String gameRefId;

    public String scoreRefId;

    public Date transactionTimestamp;

    public Date created;

    public Score setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
        return this;
    }

    public Score setContestId(Integer contestId) {
        this.contestId = contestId;
        return this;
    }

    public Score setMemberId(Integer memberId) {
        this.memberId = memberId;
        return this;
    }

    public Score setGameId(Integer gameId) {
        this.gameId = gameId;
        return this;
    }

    public Score setSourceValue(Float sourceValue) {
        this.sourceValue = sourceValue;
        return this;
    }

    public Score setMemberRefId(String memberRefId) {
        this.memberRefId = memberRefId;
        return this;
    }

    public Score setGameRefId(String gameRefId) {
        this.gameRefId = gameRefId;
        return this;
    }

    public Score setScoreRefId(String scoreRefId) {
        this.scoreRefId = scoreRefId;
        return this;
    }

    public Score setTransactionTimestamp(Date transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
        return this;
    }
}
