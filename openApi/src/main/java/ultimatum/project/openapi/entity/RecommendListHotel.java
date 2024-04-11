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
public class RecommendListHotel {

    @Id
    @GeneratedValue
    private Long recommendHotelId;
    private String recommendHotelTitle;
    private String recommendHotelntroduction;
    private String recommendHotelAllTag;
    private String recommendHotelTag;
    private String recommendHotelCategory;
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
