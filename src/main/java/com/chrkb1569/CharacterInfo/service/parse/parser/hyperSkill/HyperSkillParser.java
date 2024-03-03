package com.chrkb1569.CharacterInfo.service.parse.parser.hyperSkill;

import com.chrkb1569.CharacterInfo.service.api.APIService;
import com.chrkb1569.CharacterInfo.service.parse.parser.hyperSkill.dto.HyperSkillDto;
import com.chrkb1569.CharacterInfo.util.APIValidator;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HyperSkillParser {
    @Value("${api.request.hyperSkill.skillKey}")
    private String SKILL_KEY;

    @Value("${api.request.hyperSkill.skillName}")
    private String SKILL_NAME;

    @Value("${api.request.hyperSkill.skillLevel}")
    private String SKILL_LEVEL;

    @Value("${api.request.hyperSkill.skillEffect}")
    private String SKILL_EFFECT;

    @Value("${api.request.hyperSkill.skillImage}")
    private String SKILL_IMAGE;

    @Value("${api.request.hyperSkill.requiredPoint}")
    private int REQUIRED_POINT;

    @Value("${api.request.hyperSkill.url}")
    private String REQUEST_URL;

    private final APIService apiService;

    // 캐릭터의 하이퍼 스킬 정보 조회
    public List<HyperSkillDto> getHyperSkills(String characterIdentifier) {
        String apiData = apiService.requestData(REQUEST_URL, characterIdentifier);

        if(!APIValidator.checkAPIValidation(apiData)) return null;

        return parseDataToHyperSkill(apiData);
    }

    // 결과 데이터 파싱 - 캐릭터 하이퍼 스킬 조회
    private List<HyperSkillDto> parseDataToHyperSkill(String apiData) {
        List<HyperSkillDto> hyperSkills = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(apiData);

        JSONArray jsonArray = jsonObject.getJSONArray(SKILL_KEY);

        for(int index = 0; index < jsonArray.length(); index++) {
            JSONObject skillInfo = jsonArray.getJSONObject(index);
            int skillLevel = skillInfo.getInt(SKILL_LEVEL);

            if(REQUIRED_POINT != skillLevel) continue;

            HyperSkillDto hyperSkill = parseHyperSkillDto(skillInfo);

            hyperSkills.add(hyperSkill);
        }

        return hyperSkills;
    }

    private HyperSkillDto parseHyperSkillDto(JSONObject skillInfo) {
        String skillName = skillInfo.getString(SKILL_NAME);
        String skillEffect = skillInfo.getString(SKILL_EFFECT);
        String imageUrl = skillInfo.getString(SKILL_IMAGE);

        return new HyperSkillDto(skillName, skillEffect, imageUrl);
    }
}