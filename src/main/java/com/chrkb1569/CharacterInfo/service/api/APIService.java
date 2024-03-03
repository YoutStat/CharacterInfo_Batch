package com.chrkb1569.CharacterInfo.service.api;

import com.chrkb1569.CharacterInfo.exception.APIRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Service
public class APIService {
    private final String API_KEY; // API를 호출하기 위한 Key값
    private final String HTTP_REQUEST_ABILITY_URL; // API를 요청하기 위한 URL
    private final String HTTP_REQUEST_CLASS_URL; // API를 요청하기 위한 URL
    private final String HTTP_REQUEST_CORE_URL; // API를 요청하기 위한 URL
    private final String HTTP_REQUEST_HYPER_SKILL_URL; // API를 요청하기 위한 URL
    private final String HTTP_REQUEST_HYPER_STAT_URL; // API를 요청하기 위한 URL
    private final String HTTP_REQUEST_METHOD; // API 요청시에 사용되는 HTTP Method
    private final String HTTP_REQUEST_HEADER; // API 요청시에 사용되는 HTTP 헤더값
    private final String HTTP_REQUEST_DATE; // API 요청시에 사용되는 날짜
    private final static int DATE_GAP = 1;

    public APIService(
            @Value("${api.request.key}") String apiKey,
            @Value("${api.request.ability.url}") String abilityUrl,
            @Value("${api.request.class.url}") String classUrl,
            @Value("${api.request.core.url}") String coreUrl,
            @Value("${api.request.hyperSkill.url}") String hyperSkillUrl,
            @Value("${api.request.hyperStat.url}") String hyperStatUrl,
            @Value("${api.info.requestMethod}") String requestMethod,
            @Value("${api.info.requestHeader}") String requestHeader
    ) {
        this.API_KEY = apiKey;
        this.HTTP_REQUEST_ABILITY_URL = abilityUrl;
        this.HTTP_REQUEST_CLASS_URL = classUrl;
        this.HTTP_REQUEST_CORE_URL = coreUrl;
        this.HTTP_REQUEST_HYPER_SKILL_URL = hyperSkillUrl;
        this.HTTP_REQUEST_HYPER_STAT_URL = hyperStatUrl;
        this.HTTP_REQUEST_METHOD = requestMethod;
        this.HTTP_REQUEST_HEADER = requestHeader;
        this.HTTP_REQUEST_DATE = LocalDate.now().minusDays(DATE_GAP).toString();
    }

    // 캐릭터 세부 능력치 정보 반환
    public String getCharacterClassInfo(String characterIdentifier) {
        try {
            HttpURLConnection connection = getURLConnection(HTTP_REQUEST_CLASS_URL, characterIdentifier);

            return getAPIResult(connection);
        } catch (IOException e) {
            throw new APIRequestException();
        }
    }

    // 캐릭터 어빌리티 정보 반환
    public String getCharacterAbility(String characterIdentifier) {
        try {
            HttpURLConnection connection = getURLConnection(HTTP_REQUEST_ABILITY_URL, characterIdentifier);

            return getAPIResult(connection);
        } catch (IOException e) {
            throw new APIRequestException();
        }
    }

    // UrlConnection 생성
    private HttpURLConnection getURLConnection(String requestURL, final String characterIdentifier) throws IOException {
        URL url = getRequestURL(requestURL, characterIdentifier);

        return getUrlConnection(url);
    }

    // 요청 파라미터를 파싱하여 요청 URL 생성
    private URL getRequestURL(String requestUrl, String characterIdentifier) throws IOException {
        return new URL(String.format(requestUrl, characterIdentifier, HTTP_REQUEST_DATE));
    }

    // HTTP connection 설정
    private HttpURLConnection getUrlConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(HTTP_REQUEST_METHOD);
        connection.setRequestProperty(HTTP_REQUEST_HEADER, API_KEY);

        return connection;
    }

    // 결과에 따른 데이터 반환
    private String getAPIResult(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();

        if(responseCode == HttpStatus.OK.value())
            return getResponseData(new BufferedReader(new InputStreamReader(connection.getInputStream())));
        return getResponseData(new BufferedReader(new InputStreamReader(connection.getErrorStream())));
    }

    // API 응답 데이터 파싱
    private String getResponseData(BufferedReader br) throws IOException {
        StringBuilder response = new StringBuilder();

        while (true) {
            String inputValue = br.readLine();

            if(inputValue == null) break;

            response.append(inputValue);
        }

        br.close();

        return response.toString();
    }
}