package ultimatum.project.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ultimatum.project.openapi.dto.food.RecommendFoodResponse;
import ultimatum.project.openapi.dto.hotel.RecommendHotelResponse;
import ultimatum.project.openapi.dto.jejuAPI.Item;
import ultimatum.project.openapi.dto.jejuAPI.JejuAllResponse;
import ultimatum.project.openapi.dto.place.RecommendPlaceResponse;
import ultimatum.project.openapi.repository.RecommendListFoodRepository;
import ultimatum.project.openapi.service.JejuApiService;

@Tag(name = "recommendList", description = "제주도API")
@Log4j2
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/jejuapi")
@Transactional
public class JejuApiController {

    private final JejuApiService jejuApiService;
    private static final Logger logger = LogManager.getLogger(JejuApiController.class);
    private final WebClient webClient;
    // API 키를 application.yml로부터 주입받음
    @Value("${jejuapi.api-key}")
    private String apiKey;


    @Autowired
    public JejuApiController(RecommendListFoodRepository recommendListFoodRepository, WebClient.Builder webClientBuilder, JejuApiService jejuApiService) {
        this.webClient = webClientBuilder.baseUrl("https://api.visitjeju.net").build();
        this.jejuApiService = jejuApiService;
    }

    @Tag(name = "recommendList", description = "listAll")
    @GetMapping("/all")
    public Mono<ResponseEntity<JejuAllResponse>> getSights() {
        logger.info("Requesting all Jeju sights without pagination");
        Mono<JejuAllResponse> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vsjApi/contents/searchList")
                        .queryParam("apiKey", apiKey) // 주입받은 API 키를 사용
                        .queryParam("locale", "kr")
                        //.queryParam("category", "c1")
                        // 페이지 번호와 페이지 크기 매개변수를 제거
                        .build())
                .retrieve()
                .bodyToMono(JejuAllResponse.class)
                .doOnNext(response -> logger.info("Received response for Jeju API call"))
                .doOnError(error -> logger.error("Error calling Jeju API", error));

        return jejuApiService.processJejuApiResponse(responseMono)
                .map(response -> {
                    log.info("Processed Jeju API response successfully.");
                    return ResponseEntity.ok().body(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(error -> log.error("Error processing Jeju API response", error));
    }

     /** 5 JejuApiService의 createFoodResponse를 직접 호출
     * 요청 본문으로 받은 Item 정보를 처리
     * 결과를 ResponseEntity<RecommendFoodResponse> 형태로 반환
     * Food 저장
      * */
//    @Tag(name = "recommendList", description = "createFood")
//    @PostMapping("/createFood")
//    public ResponseEntity<RecommendFoodResponse> createRecommendFood(@RequestBody Item item) {
//        RecommendFoodResponse response = jejuApiService.createFoodResponse(item);
//        logger.info("음식점 정보 최종 저장");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    //관광지 저장
//    @Tag(name = "recommendList", description = "createPlace")
//    @PostMapping("/createPlace")
//    public ResponseEntity<RecommendPlaceResponse> createRecommendPlace(@RequestBody Item item) {
//        RecommendPlaceResponse response = jejuApiService.createPlaceResponse(item);
//        logger.info("관광지 정보 최종 저장");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @Tag(name = "recommendList", description = "createPlace")
//    @PostMapping("/createHotel")
//    public ResponseEntity<RecommendHotelResponse> createRecommendHotel(@RequestBody Item item) {
//        RecommendHotelResponse response = jejuApiService.createHotelResponse(item);
//        logger.info("숙박 정보 최종 저장");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }




}

