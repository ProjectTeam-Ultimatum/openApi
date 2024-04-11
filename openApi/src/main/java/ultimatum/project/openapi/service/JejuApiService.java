package ultimatum.project.openapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import ultimatum.project.openapi.dto.JejuAllResponse;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor //공통
@Transactional
public class JejuApiService {

    //조회
    public Mono<JejuAllResponse> processJejuApiResponse(Mono<JejuAllResponse> jejuAllResponseMono) {

        return jejuAllResponseMono.doOnNext(jejuAllResponse -> {
            log.info("Result: {}", jejuAllResponse.getResult());
            log.info("Result Message: {}", jejuAllResponse.getResultMessage());
            log.info("Total Count: {}", jejuAllResponse.getTotalCount());
            // 필요한 만큼 다른 필드에 대해서도 로그를 출력할 수 있습니다.
            // 예를 들어, items 리스트에 대한 정보를 출력하려면:
            if (jejuAllResponse.getItems() != null) {
                jejuAllResponse.getItems().forEach(item -> {
                    // Item 내 다른 필드 로그 출력
                    log.info("Contentsid: {}", item.getContentsid());
                    log.info("Title: {}", item.getTitle());
                    log.info("Alltag: {}", item.getAlltag());
                    log.info("Tag: {}", item.getTag());
                    log.info("Introduction: {}", item.getIntroduction());
                    log.info("Contentscd: {}", item.getContentscd().getLabel());
                    log.info("Address: {}", item.getAddress());
                    log.info("Roadaddress: {}", item.getRoadaddress());
                    log.info("Region1cd: {}", item.getRegion1cd());
                    log.info("Region2cd: {}", item.getRegion2cd());
                    log.info("Latitude: {}", item.getLatitude());
                    log.info("Longitude: {}", item.getLongitude());
                    log.info("Postcode: {}", item.getPostcode());
                    log.info("Phoneno: {}", item.getPhoneno());
                    log.info("RepPhoto: {}", item.getRepPhoto());
                });
            }
        });
    }
}
