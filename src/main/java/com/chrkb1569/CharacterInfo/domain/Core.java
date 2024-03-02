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
@Table(name = "CORE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Core {
    @Id
    @Column(nullable = false, name = "core_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstCore;

    @Column(nullable = false)
    private String secondCore;

    @Column(nullable = false)
    private String thirdCore;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "charcter_info_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CharacterInfo characterInfo;
}