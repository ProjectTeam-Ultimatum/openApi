package ultimatum.project.openapi.dto.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendFoodRequest {

    private Long recommendFoodId;
    private String recommendFoodTitle;
    private String recommendFoodIntroduction;
    private String recommendFoodAllTag;
    private String recommendFoodTag;
    private String recommendCategory;
    private String recommendFoodAddress;
    private String recommendFoodRegion;
    private Time recommendFoodOpentime;
    private Time recommendFoodClosetime;
    private Long recommendFoodStar;
    private Long recommendFoodLike;
    private String recommendFoodLatitude;
    private String recommendFoodLongitude;
    private Integer recommendFoodPhoneno;
    private String recommendFoodImgPath;
    private String recommendFoodBudget;
}
