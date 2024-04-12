package ultimatum.project.openapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendHotelId;
    private String recommendHotelContentsId;
    private String recommendHotelTitle;
    private String recommendHotelIntroduction;
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
    private String recommendHotelPhoneNo;
    private String recommendHotelImgPath;
    private String recommendHotelBudget;
}
