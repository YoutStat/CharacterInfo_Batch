package com.chrkb1569.CharacterInfo.service.parse.parser.hyperStat;

import com.chrkb1569.CharacterInfo.service.api.APIService;
import com.chrkb1569.CharacterInfo.service.parse.parser.hyperStat.dto.HyperStatDto;
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
public class HyperStatParser {
    @Value("${api.request.hyperStat.url}")
    private String REQUEST_URL; // API를 요청하기 위한 URL

    @Value("${api.request.hyperStat.presetKey}")
    private String PRESET_KEY;

    @Value("${api.request.hyperStat.firstPresetKey}")
    private String FIRST_PRESET_KEY;

    @Value("${api.request.hyperStat.secondPresetKey}")
    private String SECOND_PRESET_KEY;

    @Value("${api.request.hyperStat.thirdPresetKey}")
    private String THIRD_PRESET_KEY;

    @Value("${api.request.hyperStat.statType}")
    private String STAT_TYPE_KEY;

    @Value("${api.request.hyperStat.statLevel}")
    private String STAT_LEVEL_KEY;

    @Value("${api.request.hyperStat.minLevel}")
    private int MIN_LEVEL;

    private final APIService apiService;

    public List<HyperStatDto> getHyperStats(String characterIdentifier) {
        String apiData = apiService.requestData(REQUEST_URL, characterIdentifier);

        if(!APIValidator.checkAPIValidation(apiData)) return null;

        return parseDataToHyperStats(apiData);
    }

    private List<HyperStatDto> parseDataToHyperStats(String apiData) {
        List<HyperStatDto> hyperStats = new ArrayList<>();

        JSONArray hyperStatInfo = getDataArrayByPreset(apiData);

        for(int index = 0; index < hyperStatInfo.length(); index++) {
            JSONObject hyperStat = hyperStatInfo.getJSONObject(index);

            int statLevel = hyperStat.getInt(STAT_LEVEL_KEY);

            if(statLevel == MIN_LEVEL) continue;

            String statType = hyperStat.getString(STAT_TYPE_KEY);

            HyperStatDto hyperStatDto = parseDto(statLevel, statType);
            hyperStats.add(hyperStatDto);
        }

        return hyperStats;
    }

    private JSONArray getDataArrayByPreset(String apiData) {
        JSONObject jsonObject = new JSONObject(apiData);

        String usingPreset = jsonObject.getString(PRESET_KEY);

        if(usingPreset.equals("1")) return jsonObject.getJSONArray(FIRST_PRESET_KEY);
        if(usingPreset.equals("2")) return jsonObject.getJSONArray(SECOND_PRESET_KEY);
        return jsonObject.getJSONArray(THIRD_PRESET_KEY);
    }

    private HyperStatDto parseDto(int statLevel, String statType) {
        return new HyperStatDto(statType, statLevel);
    }
}