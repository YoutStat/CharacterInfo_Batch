package com.chrkb1569.CharacterInfo.service.parse;

import com.chrkb1569.CharacterInfo.domain.CharacterClass;
import com.chrkb1569.CharacterInfo.service.api.APIService;
import com.chrkb1569.CharacterInfo.service.parse.dto.ParseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.chrkb1569.CharacterInfo.util.APIExceptionMessage.getErrorMessageByType;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParseService {
    @Value("${api.info.errorKey}")
    private String ERROR_KEY;

    @Value("${api.info.errorType}")
    private String ERROR_TYPE;

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

    @Value("${api.info.abilityKey}")
    private String ABILITY_KEY;

    @Value("${api.info.abilityValue}")
    private String ABILITY_VALUE;

    private final APIService apiService;

    // 캐릭터의 직업 및 전투력 조회
    public ParseDto getClassInfo(String characterIdentifier) {
        String apiData = apiService.getCharacterClassInfo(characterIdentifier);

        if(!checkValidation(apiData)) return null;

        ParseDto responseData = parseDataToParseDto(apiData);

        if(!checkDtoValidation(responseData)) return null;

        return responseData;
    }

    // 캐릭터의 어빌리티 조회
    public List<String> getAbility(String characterIdentifier) {
        String apiData = apiService.getCharacterAbility(characterIdentifier);

        if(!checkValidation(apiData)) return null;

        return parseDataToAbility(apiData);
    }

    // 요청 데이터 유효성 확인
    private boolean checkValidation(String apiData) {
        JSONObject jsonObject = new JSONObject(apiData);

        if(!jsonObject.has(ERROR_KEY)) return true;

        JSONObject errorObject = (JSONObject)jsonObject.get(ERROR_KEY);
        String errorMessage = getErrorMessageByType((String)errorObject.get(ERROR_TYPE));

        log.info(errorMessage);

        return false;
    }

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

    // 결과 데이터 파싱 - 캐릭터 어빌리티 반환
    private List<String> parseDataToAbility(String apiData) {
        List<String> ability = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(apiData);

        JSONArray jsonArray = jsonObject.getJSONArray(ABILITY_KEY);

        for(int index = 0; index < jsonArray.length(); index++) {
            String ability_value = jsonArray.getJSONObject(index).getString(ABILITY_VALUE);
            ability.add(ability_value);
        }

        return ability;
    }
}