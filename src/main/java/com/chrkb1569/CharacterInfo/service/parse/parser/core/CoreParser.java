package com.chrkb1569.CharacterInfo.service.parse.parser.core;

import com.chrkb1569.CharacterInfo.service.api.APIService;
import com.chrkb1569.CharacterInfo.service.parse.parser.core.dto.CoreDto;
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
public class CoreParser {
    @Value("${api.request.core.coreKey}")
    private String CORE_KEY;

    @Value("${api.request.core.coreType}")
    private String CORE_TYPE;

    @Value("${api.request.core.requiredType}")
    private String REQUIRED_CORE_TYPE;

    @Value("${api.request.core.firstCoreKey}")
    private String FIRST_CORE_KEY;

    @Value("${api.request.core.secondCoreKey}")
    private String SECOND_CORE_KEY;

    @Value("${api.request.core.thirdCoreKey}")
    private String THIRD_CORE_KEY;

    @Value("${api.request.core.url}")
    private String REQUEST_URL; // API를 요청하기 위한 URL

    private final APIService apiService;

    public List<CoreDto> getCores(String characterIdentifier) {
        String apiData = apiService.requestData(REQUEST_URL, characterIdentifier);

        if(!APIValidator.checkAPIValidation(apiData)) return null;

        return parseDataToCores(apiData);
    }

    private List<CoreDto> parseDataToCores(String apiData) {
        List<CoreDto> cores = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(apiData);

        JSONArray jsonArray = jsonObject.getJSONArray(CORE_KEY);

        for(int index = 0; index < jsonArray.length(); index++) {
            JSONObject coreInfos = jsonArray.getJSONObject(index);

            if(coreInfos.isNull(CORE_TYPE)) continue;

            String coreType = coreInfos.getString(CORE_TYPE);

            if(!coreType.equals(REQUIRED_CORE_TYPE)) continue;

            CoreDto core = parseCoreDto(coreInfos);

            cores.add(core);
        }

        return cores;
    }

    private CoreDto parseCoreDto(JSONObject coreInfos) {
        String firstCore = coreInfos.getString(FIRST_CORE_KEY);
        String secondCore = coreInfos.getString(SECOND_CORE_KEY);
        String thirdCore = coreInfos.getString(THIRD_CORE_KEY);

        return new CoreDto(firstCore, secondCore, thirdCore);
    }
}