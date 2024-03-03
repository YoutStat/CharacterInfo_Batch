package com.chrkb1569.CharacterInfo.service.parse;

import com.chrkb1569.CharacterInfo.service.parse.parser.classInfo.dto.ClassInfoDto;
import com.chrkb1569.CharacterInfo.service.parse.parser.hyperSkill.dto.HyperSkillDto;

import java.util.List;

public interface ParseService {
    ClassInfoDto getClassInfo(String characterIdentifier);
    List<String> getAbility(String characterIdentifier);
    List<HyperSkillDto> getHyperSkills(String characterIdentifier);
}