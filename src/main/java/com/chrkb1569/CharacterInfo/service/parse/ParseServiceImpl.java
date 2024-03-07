package com.chrkb1569.CharacterInfo.service.parse;

import com.chrkb1569.CharacterInfo.service.parse.parser.classInfo.dto.ClassInfoDto;
import com.chrkb1569.CharacterInfo.service.parse.parser.ability.AbilityParser;
import com.chrkb1569.CharacterInfo.service.parse.parser.classInfo.ClassInfoParser;
import com.chrkb1569.CharacterInfo.service.parse.parser.core.CoreParser;
import com.chrkb1569.CharacterInfo.service.parse.parser.core.dto.CoreDto;
import com.chrkb1569.CharacterInfo.service.parse.parser.hyperSkill.HyperSkillParser;
import com.chrkb1569.CharacterInfo.service.parse.parser.hyperSkill.dto.HyperSkillDto;
import com.chrkb1569.CharacterInfo.service.parse.parser.hyperStat.HyperStatParser;
import com.chrkb1569.CharacterInfo.service.parse.parser.hyperStat.dto.HyperStatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParseServiceImpl implements ParseService {
    private final ClassInfoParser classInfoParser;
    private final AbilityParser abilityParser;
    private final HyperSkillParser hyperSkillParser;
    private final CoreParser coreParser;
    private final HyperStatParser hyperStatParser;

    @Override
    public ClassInfoDto getClassInfo(String characterIdentifier) {
        return classInfoParser.getClassInfo(characterIdentifier);
    }

    @Override
    public List<String> getAbility(String characterIdentifier) {
        return abilityParser.getAbility(characterIdentifier);
    }

    @Override
    public List<HyperSkillDto> getHyperSkills(String characterIdentifier) {
        return hyperSkillParser.getHyperSkills(characterIdentifier);
    }

    @Override
    public List<CoreDto> getCores(String characterIdentifier) {
        return coreParser.getCores(characterIdentifier);
    }

    @Override
    public List<HyperStatDto> getHyperStats(String characterIdentifier) {
        return hyperStatParser.getHyperStats(characterIdentifier);
    }
}