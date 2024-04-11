package ultimatum.project.openapi.dto.jejuAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String contentsid;
    private String title;
    private String tag;
    private String alltag;
    private String introduction;
    private Contentscd contentscd;
    private String address;
    private String roadaddress;
    private Region1cd region1cd;
    private Region2cd region2cd;
    private Double latitude;
    private Double longitude;
    private String postcode;
    private String phoneno;
    private RepPhoto repPhoto;

    // Getters and setters...

}
