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
@Table(name = "HYPER_STAT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HyperStat {
    @Id
    @Column(nullable = false, name = "hyper_stat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String statType; // 스텟 이름

    @Column(nullable = false)
    private int statLevel; // 스텟 레벨

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "charcter_info_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CharacterInfo characterInfo;
}