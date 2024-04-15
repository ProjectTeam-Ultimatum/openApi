package ultimatum.project.openapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Time;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendListFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendFoodId;
    private String recommendFoodContentsId;
    private String recommendFoodTitle;
    //콘텐츠 타입 길이/크기 16MB 세팅
    @Column(columnDefinition = "MEDIUMTEXT")
    private String recommendFoodIntroduction;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String recommendFoodAllTag;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String recommendFoodTag;
    private String recommendFoodCategory;
    private String recommendFoodAddress;
    private String recommendFoodRegion;
    private Time recommendFoodOpentime;
    private Time recommendFoodClosetime;
    private Long recommendFoodStar;
    private Long recommendFoodLike;
    private String recommendFoodLatitude;
    private String recommendFoodLongitude;
    private String recommendFoodPhoneNo;
    private String recommendFoodImgPath;
    private String recommendFoodBudget;

}
