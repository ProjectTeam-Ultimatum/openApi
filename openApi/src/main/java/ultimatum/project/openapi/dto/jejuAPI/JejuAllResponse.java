package ultimatum.project.openapi.dto.jejuAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JejuAllResponse {

    private String result;
    private String resultMessage;
    private Integer totalCount;
    private Integer resultCount;
    private BigInteger pageSize;
    private Integer pageCount;
    private Integer currentPage;
    private List<Item> items;
    private Region1cd region1cd;
    private Region2cd region2cd;
    private RepPhoto repPhotol;

}

