package ultimatum.project.openapi.dto.food;

import lombok.*;
import ultimatum.project.openapi.entity.RecommendListFood;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendListFoodResponse {

    private String recommendFoodContentsId;
    private String recommendFoodTitle;
    private String recommendFoodIntroduction;
    private String recommendFoodAllTag;
    private String recommendFoodTag;
    private String recommendFoodCategory;
    private String recommendFoodAddress;
    private String recommendFoodRegion;
    private String recommendFoodLatitude;
    private String recommendFoodLongitude;
    private String recommendFoodPhoneNo;
    private String recommendFoodImgPath;

    //build 패턴으로 메소드
    //recommendListFood entity
    public RecommendListFoodResponse(RecommendListFood recommendListFood) {

        this.recommendFoodContentsId = recommendListFood.getRecommendFoodContentsId();
        this.recommendFoodTitle = recommendListFood.getRecommendFoodTitle();
        this.recommendFoodIntroduction = recommendListFood.getRecommendFoodIntroduction();
        this.recommendFoodAllTag = recommendListFood.getRecommendFoodAllTag();
        this.recommendFoodTag = recommendListFood.getRecommendFoodTag();
        this.recommendFoodCategory = recommendListFood.getRecommendFoodCategory();
        this.recommendFoodAddress = recommendListFood.getRecommendFoodAddress();
        this.recommendFoodRegion = recommendListFood.getRecommendFoodRegion();
        this.recommendFoodLatitude = recommendListFood.getRecommendFoodLatitude();
        this.recommendFoodLongitude = recommendListFood.getRecommendFoodLongitude();
        this.recommendFoodPhoneNo = recommendListFood.getRecommendFoodPhoneNo();
        this.recommendFoodImgPath = recommendListFood.getRecommendFoodImgPath();;
    }
}
