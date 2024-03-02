package com.chrkb1569.CharacterInfo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "HYPER_SKILL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HyperSkill {
    @Id
    @Column(nullable = false, name = "hyper_skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String skillName; // 하이퍼 스킬명

    @Column(nullable = false)
    private String skillEffect; // 하이퍼 스킬 효과

    @Column(nullable = false)
    private String imageUrl; // 이미지 URL

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "character_info_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CharacterInfo characterInfo;
}