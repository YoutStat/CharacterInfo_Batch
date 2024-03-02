package com.chrkb1569.CharacterInfo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "CHARACTER_INFO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterInfo {
    @Id
    @Column(nullable = false, name = "character_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String characterIdentifier; // 캐릭터 식별자

    @Embedded
    private Ability ability; // 캐릭터 어빌리티

    @Enumerated(value = EnumType.STRING)
    private CharacterClass characterClass; // 캐릭터 직업

    @Column(nullable = false)
    private Long combatPower; // 전투력

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "characterInfo", orphanRemoval = true)
    private List<Core> cores = new ArrayList<>(); // 5차 강화 코어

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "characterInfo", orphanRemoval = true)
    private List<HyperStat> hyperStats = new ArrayList<>(); // 하이퍼 스텟

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "characterInfo", orphanRemoval = true)
    private List<HyperSkill> hyperSkills = new ArrayList<>(); // 하이퍼 스킬
}