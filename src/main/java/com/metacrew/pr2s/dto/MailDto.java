package com.metacrew.pr2s.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MailDto {
    private String address;
    private String title;
    private String content;

    public MailDto(String address, String title, String content){
        this.address = address;
        this.title = title;
        this.content = content;
    }
}
