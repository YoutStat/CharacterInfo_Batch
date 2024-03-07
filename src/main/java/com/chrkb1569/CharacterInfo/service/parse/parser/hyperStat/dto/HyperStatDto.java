package com.chrkb1569.CharacterInfo.service.parse.parser.hyperStat.dto;

import lombok.Getter;

@Getter
public class HyperStatDto {
    private String statType;
    private int statLevel;

    public HyperStatDto(String statType, int statLevel) {
        this.statType = statType;
        this.statLevel = statLevel;
    }
}