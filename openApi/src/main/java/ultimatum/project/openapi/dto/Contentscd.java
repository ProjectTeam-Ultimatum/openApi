package ultimatum.project.openapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contentscd {
    private String value;
    private String label; //음식점, 관광지, 테마여행
    private String refId;
    // Getters and setters...
}
