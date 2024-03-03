package com.chrkb1569.CharacterInfo.service.parse;

import com.chrkb1569.CharacterInfo.service.parse.dto.ParseDto;
import com.chrkb1569.CharacterInfo.service.parse.parser.AbilityParser;
import com.chrkb1569.CharacterInfo.service.parse.parser.ClassInfoParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParseServiceImpl implements ParseService {
    private final ClassInfoParser classInfoParser;
    private final AbilityParser abilityParser;

    @Override
    public ParseDto getClassInfo(String characterIdentifier) {
        return classInfoParser.getClassInfo(characterIdentifier);
    }

    @Override
    public List<String> getAbility(String characterIdentifier) {
        return abilityParser.getAbility(characterIdentifier);
    }
}