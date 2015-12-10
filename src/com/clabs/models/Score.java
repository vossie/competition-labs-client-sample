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

    public Float adjustedValue;

    public Integer externalMemberRefId;

    public Integer externalGameRefId;

    public Integer externalScoreRefId;

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

    public Score setExternalMemberRefId(Integer externalMemberRefId) {
        this.externalMemberRefId = externalMemberRefId;
        return this;
    }

    public Score setExternalGameRefId(Integer externalGameRefId) {
        this.externalGameRefId = externalGameRefId;
        return this;
    }

    public Score setExternalScoreRefId(Integer externalScoreRefId) {
        this.externalScoreRefId = externalScoreRefId;
        return this;
    }

    public Score setTransactionTimestamp(Date transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
        return this;
    }
}
