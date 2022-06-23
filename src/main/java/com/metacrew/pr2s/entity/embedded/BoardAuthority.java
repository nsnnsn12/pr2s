package com.metacrew.pr2s.entity.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardAuthority {
    private int writeLevel;
    private int readLevel;
    private int updateLevel;
    private int deleteLevel;
}
