package com.chrkb1569.CharacterInfo.service.parse.dto;

import lombok.Getter;

@Getter
public class ParseDto {
    private String classType;
    private long combatPower;

    public ParseDto(String classType, String combatPower) {
        this.classType = classType;
        this.combatPower = Long.parseLong(combatPower);
    }
}