package ultimatum.project.openapi.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendHotelResponse {

    private Long recommendHotelId;
    private String recommendHotelTitle;
    private String recommendHotelIntroduction;
    private String recommendHotelAllTag;
    private String recommendHotelTag;
    private String recommendCategory;
    private String recommendHotelAddress;
    private String recommendHotelRegion;
    private Time recommendHotelOpentime;
    private Time recommendHotelClosetime;
    private Long recommendHotelStar;
    private Long recommendHotelLike;
    private String recommendHotelLatitude;
    private String recommendHotelLongitude;
    private Integer recommendHotelPhoneno;
    private String recommendHotelImgPath;
    private String recommendHotelBudget;
}
