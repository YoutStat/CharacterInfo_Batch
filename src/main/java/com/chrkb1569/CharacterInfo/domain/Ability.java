package com.chrkb1569.CharacterInfo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Ability {
    @Column(nullable = false)
    private String firstAbility;

    @Column(nullable = false)
    private String secondAbility;

    @Column(nullable = false)
    private String thirdAbility;
}