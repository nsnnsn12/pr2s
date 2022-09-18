package com.metacrew.pr2s.controller.member;

import lombok.Getter;

@Getter
public enum Layer {
    ROOT("auth")
    ,REGISTER_URL("auth/body/register")
    ,ALERT_URL("auth/body/alert");

    final private String url;
    Layer(String url){
        this.url = url;
    }
}
