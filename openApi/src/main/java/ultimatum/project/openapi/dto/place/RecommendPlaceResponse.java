package ultimatum.project.openapi.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ultimatum.project.openapi.entity.RecommendListFood;
import ultimatum.project.openapi.entity.RecommendListPlace;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendPlaceResponse {

    private String recommendPlaceTitle;
    private String recommendPlaceIntroduction;
    private String recommendPlaceAllTag;
    private String recommendPlaceTag;
    private String recommendPlaceCategory;
    private String recommendPlaceAddress;
    private String recommendPlaceRegion;
    private String recommendPlaceLatitude;
    private String recommendPlaceLongitude;
    private String recommendPlacePhoneNo;
    private String recommendPlaceImgPath;

    public RecommendPlaceResponse(RecommendListPlace recommendListPlace) {

        this.recommendPlaceTitle = recommendListPlace.getRecommendPlaceTitle();
        this.recommendPlaceIntroduction = recommendListPlace.getRecommendPlaceIntroduction();
        this.recommendPlaceAllTag = recommendListPlace.getRecommendPlaceAllTag();
        this.recommendPlaceTag = recommendListPlace.getRecommendPlaceTag();
        this.recommendPlaceCategory = recommendListPlace.getRecommendPlaceCategory();
        this.recommendPlaceAddress = recommendListPlace.getRecommendPlaceAddress();
        this.recommendPlaceRegion = recommendListPlace.getRecommendPlaceRegion();
        this.recommendPlaceLatitude = recommendListPlace.getRecommendPlaceLatitude();
        this.recommendPlaceLongitude = recommendListPlace.getRecommendPlaceLongitude();
        this.recommendPlacePhoneNo = recommendListPlace.getRecommendPlacePhoneNo();
        this.recommendPlaceImgPath = recommendListPlace.getRecommendPlaceImgPath();
    }
}
