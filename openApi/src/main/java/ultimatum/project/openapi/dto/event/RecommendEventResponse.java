package ultimatum.project.openapi.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ultimatum.project.openapi.entity.RecommendListEvent;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendEventResponse {

    private String recommendEventContentsId;
    private String recommendEventTitle;
    private String recommendEventIntroduction;
    private String recommendEventAllTag;
    private String recommendEventTag;
    private String recommendEventCategory;
    private String recommendEventAddress;
    private String recommendEventRegion;
    private String recommendEventLatitude;
    private String recommendEventLongitude;
    private String recommendEventPhoneNo;
    private String recommendEventImgPath;

    //build 패턴으로 메소드
    //recommendListEvent entity
    public RecommendEventResponse(RecommendListEvent recommendListEvent) {

        this.recommendEventContentsId = recommendListEvent.getRecommendEventContentsId();
        this.recommendEventTitle = recommendListEvent.getRecommendEventTitle();
        this.recommendEventIntroduction = recommendListEvent.getRecommendEventIntroduction();
        this.recommendEventAllTag = recommendListEvent.getRecommendEventAllTag();
        this.recommendEventTag = recommendListEvent.getRecommendEventTag();
        this.recommendEventCategory = recommendListEvent.getRecommendEventCategory();
        this.recommendEventAddress = recommendListEvent.getRecommendEventAddress();
        this.recommendEventRegion = recommendListEvent.getRecommendEventRegion();
        this.recommendEventLatitude = recommendListEvent.getRecommendEventLatitude();
        this.recommendEventLongitude = recommendListEvent.getRecommendEventLongitude();
        this.recommendEventPhoneNo = recommendListEvent.getRecommendEventPhoneNo();
        this.recommendEventImgPath = recommendListEvent.getRecommendEventImgPath();;
    }
}
