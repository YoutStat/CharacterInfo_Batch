package com.chrkb1569.CharacterInfo.service.parse.parser.hyperSkill.dto;

import lombok.Getter;

@Getter
public class HyperSkillDto {
    private String skillName; // 하이퍼 스킬 이름
    private String skillEffect; // 하이퍼 스킬 효과
    private String imageUrl; // 스킬 이미지 URL

    public HyperSkillDto(String skillName, String skillEffect, String imageUrl) {
        this.skillName = skillName;
        this.skillEffect = skillEffect;
        this.imageUrl = imageUrl;
    }
}