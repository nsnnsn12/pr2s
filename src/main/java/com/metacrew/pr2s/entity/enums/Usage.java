package com.metacrew.pr2s.entity.enums;

public enum Usage {
    // 모임
    PARTY_ROOM("파티룸"),
    STUDY_ROOM("스터디룸"),

    //연습실
    PRACTICE_ROOM("연습실"),

    //행사
    CONCERT_ROOM("공연실"),
    CONFERENCE_ROOM("컨퍼런스");

    private final String name;

    Usage(String name)  { this.name = name; }

    public String getCode() { return name; }
}
