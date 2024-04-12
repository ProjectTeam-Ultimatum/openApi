package ultimatum.project.openapi.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ultimatum.project.openapi.entity.RecommendListFood;
import ultimatum.project.openapi.entity.RecommendListHotel;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendHotelResponse {

    private String recommendHotelContentsId;
    private String recommendHotelTitle;
    private String recommendHotelIntroduction;
    private String recommendHotelAllTag;
    private String recommendHotelTag;
    private String recommendHotelCategory;
    private String recommendHotelAddress;
    private String recommendHotelRegion;
    private String recommendHotelLatitude;
    private String recommendHotelLongitude;
    private String recommendHotelPhoneNo;
    private String recommendHotelImgPath;

    public RecommendHotelResponse(RecommendListHotel recommendListHotel) {

        this.recommendHotelContentsId = recommendListHotel.getRecommendHotelContentsId();
        this.recommendHotelTitle = recommendListHotel.getRecommendHotelTitle();
        this.recommendHotelIntroduction = recommendListHotel.getRecommendHotelIntroduction();
        this.recommendHotelAllTag = recommendListHotel.getRecommendHotelAllTag();
        this.recommendHotelTag = recommendListHotel.getRecommendHotelTag();
        this.recommendHotelCategory = recommendListHotel.getRecommendHotelCategory();
        this.recommendHotelAddress = recommendListHotel.getRecommendHotelAddress();
        this.recommendHotelRegion = recommendListHotel.getRecommendHotelRegion();
        this.recommendHotelLatitude = recommendListHotel.getRecommendHotelLatitude();
        this.recommendHotelLongitude = recommendListHotel.getRecommendHotelLongitude();
        this.recommendHotelPhoneNo = recommendListHotel.getRecommendHotelPhoneNo();
        this.recommendHotelImgPath = recommendListHotel.getRecommendHotelImgPath();;
    }
}
