package com.chrkb1569.CharacterInfo.domain;

import java.util.Arrays;

public enum CharacterClass {
    CLASS_HERO("히어로"),
    CLASS_DARK_KNIGHT("다크나이트"),
    CLASS_PALADIN("팔리딘"),
    CLASS_BISHOP("비숍"),
    CLASS_ARCH_MAGE_ICE_LIGHTING("아크메이지(썬,콜)"),
    CLASS_ARCH_MAGE_FIRE_POISON("아크메이지(불,독)"),
    CLASS_BOW_MASTER("보우마스터"),
    CLASS_MARKSMAN("신궁"),
    CLASS_PATH_FINDER("패스파인더"),
    CLASS_NIGHT_LORD("나이트로드"),
    CLASS_SHADOWER("섀도어"),
    CLASS_DUAL_BLADE("듀얼블레이더"),
    CLASS_VIPER("바이퍼"),
    CLASS_CAPTAIN("캡틴"),
    CLASS_CANNON_SHOOTER("캐논마스터"),
    CLASS_MIHILE("미하일"),
    CLASS_SOUL_MASTER("소울마스터"),
    CLASS_FLAME_WIZARD("플레임위자드"),
    CLASS_WIND_BREAKER("윈드브레이커"),
    CLASS_NIGHT_WALKER("나이트워커"),
    CLASS_STRIKER("스트라이커"),
    CLASS_BLASTER("블래스터"),
    CLASS_DEMON_AVENGER("데몬어벤저"),
    CLASS_DEMON_SLAYER("데몬슬레이어"),
    CLASS_BATTLE_MAGE("배틀메이지"),
    CLASS_WILD_HUNTER("와일드헌터"),
    CLASS_XENON("제논"),
    CLASS_MECHANIC("메카닉"),
    CLASS_MERCEDES("메르세데스"),
    CLASS_ARAN("아란"),
    CLASS_PHANTOM("팬텀"),
    CLASS_LUMINOUS("루미너스"),
    CLASS_EVAN("에반"),
    CLASS_SHADE("은월"),
    CLASS_KAISER("카이저"),
    CLASS_KAIN("카인"),
    CLASS_CADENA("카데나"),
    CLASS_ANGELIC_BUSTER("엔젤릭 버스터"),
    CLASS_ADELE("아델"),
    CLASS_ILLIUM("일리움"),
    CLASS_KHALI("칼리"),
    CLASS_ARK("아크"),
    CLASS_LARA("라라"),
    CLASS_HOYOUNG("호영"),
    CLASS_ZERO("제로"),
    CLASS_KINESIS("키네시스")
    ;

    private String type;

    CharacterClass(String type) {
        this.type = type;
    }

    public static boolean checkValidation(String classType) {
        return Arrays.stream(values())
                .anyMatch(characterClass -> characterClass.type.equals(classType));
    }

    public static CharacterClass getClassType(String classType) {
        return Arrays.stream(values())
                .filter(characterClass -> characterClass.type.equals(classType))
                .findFirst().get();
    }
}