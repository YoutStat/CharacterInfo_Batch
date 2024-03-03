package com.chrkb1569.CharacterInfo.service.parse.parser.classInfo.dto;

import lombok.Getter;

@Getter
public class ClassInfoDto {
    private String classType;
    private String combatPower;

    public ClassInfoDto(String classType, String combatPower) {
        this.classType = classType;
        this.combatPower = combatPower;
    }
}