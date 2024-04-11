package ultimatum.project.openapi.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendPlaceRequest {

    private Long recommendPlaceId;
    private String recommendPlaceTitle;
    private String recommendPlaceIntroduction;
    private String recommendPlaceAllTag;
    private String recommendPlaceTag;
    private String recommendCategory;
    private String recommendPlaceAddress;
    private String recommendPlaceRegion;
    private Time recommendPlaceOpentime;
    private Time recommendPlaceClosetime;
    private Long recommendPlaceStar;
    private Long recommendPlaceLike;
    private String recommendPlaceLatitude;
    private String recommendPlaceLongitude;
    private Integer recommendPlacePhoneno;
    private String recommendPlaceImgPath;
    private String recommendPlaceBudget;
}
