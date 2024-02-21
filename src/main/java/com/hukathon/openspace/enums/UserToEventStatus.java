package com.hukathon.openspace.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserToEventStatus {
    NOT_INTERESTED,
    INTERESTED,
    NOT_ATTEND,
    ATTEND
}
