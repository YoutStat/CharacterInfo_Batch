package com.chrkb1569.CharacterInfo.service.parse.parser.ability;

import com.chrkb1569.CharacterInfo.service.api.APIService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.chrkb1569.CharacterInfo.util.APIValidator.checkAPIValidation;

@Component
@RequiredArgsConstructor
public class AbilityParser {
    @Value("${api.request.ability.abilityKey}")
    private String ABILITY_KEY;

    @Value("${api.request.ability.abilityValue}")
    private String ABILITY_VALUE;

    @Value("${api.request.ability.url}")
    private String REQUEST_URL;

    private final APIService apiService;

    // 캐릭터의 어빌리티 조회
    public List<String> getAbility(String characterIdentifier) {
        String apiData = apiService.requestData(REQUEST_URL, characterIdentifier);

        if(!checkAPIValidation(apiData)) return null;

        return parseDataToAbility(apiData);
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