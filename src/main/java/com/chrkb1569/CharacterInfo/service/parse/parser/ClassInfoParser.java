package com.chrkb1569.CharacterInfo.service.parse.parser;

import com.chrkb1569.CharacterInfo.domain.CharacterClass;
import com.chrkb1569.CharacterInfo.service.api.APIService;
import com.chrkb1569.CharacterInfo.service.parse.dto.ParseDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.chrkb1569.CharacterInfo.util.APIValidator.*;

@Component
@RequiredArgsConstructor
public class ClassInfoParser {
    @Value("${api.info.classKey}")
    private String CLASS_KEY;

    @Value("${api.info.statInfoKey}")
    private String STAT_INFO_KEY;

    @Value("${api.info.statNameKey}")
    private String STAT_NAME_KEY;

    @Value("${api.info.statValueKey}")
    private String STAT_VALUE_KEY;

    @Value("${api.info.combatPowerKey}")
    private String COMBAT_POWER_KEY;

    private final APIService apiService;

    // 캐릭터의 직업 및 전투력 조회
    public ParseDto getClassInfo(String characterIdentifier) {
        String apiData = apiService.getCharacterClassInfo(characterIdentifier);

        if(!checkAPIValidation(apiData)) return null;

        ParseDto responseData = parseDataToParseDto(apiData);

        if(!checkDtoValidation(responseData)) return null;

        return responseData;
    }

    // 파싱 데이터 유효성 검사
    private boolean checkDtoValidation(ParseDto parseDto) {
        String classType = parseDto.getClassType();
        String combatPower = parseDto.getCombatPower();

        if(!CharacterClass.checkValidation(classType)) return false;

        return !combatPower.isEmpty() && !combatPower.isBlank();
    }

    // 결과 데이터 파싱 - 캐릭터 직업 및 전투력 반환
    private ParseDto parseDataToParseDto(String apiData) {
        JSONObject jsonObject = new JSONObject(apiData);

        String classType = jsonObject.getString(CLASS_KEY);

        JSONArray jsonArray = jsonObject.getJSONArray(STAT_INFO_KEY);

        for(int index = 0; index < jsonArray.length(); index++) {
            JSONObject object = jsonArray.getJSONObject(index);

            String statName = object.getString(STAT_NAME_KEY);
            String statValue = object.getString(STAT_VALUE_KEY);

            if(!statName.equals(COMBAT_POWER_KEY)) continue;

            return new ParseDto(classType, statValue);
        }

        return new ParseDto(classType, null);
    }
}