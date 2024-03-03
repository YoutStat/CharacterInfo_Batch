package com.chrkb1569.CharacterInfo.service.parse;

import com.chrkb1569.CharacterInfo.service.parse.dto.ParseDto;

import java.util.List;

public interface ParseService {
    ParseDto getClassInfo(String characterIdentifier);
    List<String> getAbility(String characterIdentifier);
}