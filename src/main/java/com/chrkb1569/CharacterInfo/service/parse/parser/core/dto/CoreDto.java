package com.chrkb1569.CharacterInfo.service.parse.parser.core.dto;

import lombok.Getter;

@Getter
public class CoreDto {
    private String firstCore;
    private String secondCore;
    private String thirdCore;

    public CoreDto(String firstCore, String secondCore, String thirdCore) {
        this.firstCore = firstCore;
        this.secondCore = secondCore;
        this.thirdCore = thirdCore;
    }
}