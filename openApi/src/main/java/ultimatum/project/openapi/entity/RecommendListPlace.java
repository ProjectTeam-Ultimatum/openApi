package ultimatum.project.openapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Time;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendListPlace {

    @Id
    @GeneratedValue
    private Long recommendPlaceId;
    private String recommendPlaceTitle;
    private String recommendPlaceIntroduction;
    private String recommendPlaceAllTag;
    private String recommendPlaceTag;
    private String recommendPlaceCategory;
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
    private String recommendPlaceudget;

}
