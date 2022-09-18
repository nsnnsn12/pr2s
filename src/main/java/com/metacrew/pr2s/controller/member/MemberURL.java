package com.metacrew.pr2s.controller.member;

import lombok.Getter;

@Getter
public enum MemberURL {
    LOGIN_URL("auth/body/login")
    ,REGISTER_URL("auth/body/register")
    ,ALERT_URL("auth/body/alert");

    String url;
    MemberURL(String url){
        this.url = url;
    }
}
