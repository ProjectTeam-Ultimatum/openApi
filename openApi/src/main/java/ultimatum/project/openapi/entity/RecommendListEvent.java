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
public class RecommendListEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendEventId;
    private String recommendEventContentsId;
    private String recommendEventTitle;
    private String recommendEventIntroduction;
    private String recommendEventAllTag;
    private String recommendEventTag;
    private String recommendEventCategory;
    private String recommendEventAddress;
    private String recommendEventRegion;
    private Time recommendEventOpentime;
    private Time recommendEventClosetime;
    private Long recommendEventStar;
    private Long recommendEventLike;
    private String recommendEventLatitude;
    private String recommendEventLongitude;
    private String recommendEventPhoneNo;
    private String recommendEventImgPath;
    private String recommendEventBudget;

}
