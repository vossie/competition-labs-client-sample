package com.clabs.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

final public class Member {

    public Integer id;

    public Integer accountId;

    public String memberType;

    public String name;

    public Integer externalRefId;

    public String email;

    public String picture;

    public List<String> teamMembers;

    public List<String> groups;

    public Map<String,String> metadata;

    public Date created;

    public Member setMemberType(String memberType) {
        this.memberType = memberType;
        return this;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public Member setExternalRefId(Integer externalRefId) {
        this.externalRefId = externalRefId;
        return this;
    }

    public Member setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Member setGroups(List<String> groups) {
        this.groups = groups;
        return this;
    }

    public Member setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }
}
