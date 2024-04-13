package ultimatum.project.openapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ultimatum.project.openapi.dto.event.RecommendListEventResponse;
import ultimatum.project.openapi.dto.food.RecommendListFoodResponse;
import ultimatum.project.openapi.dto.hotel.RecommendListHotelResponse;
import ultimatum.project.openapi.dto.place.RecommendListPlaceResponse;
import ultimatum.project.openapi.service.JejuApiService;

import java.util.List;

@Tag(name = "recommendList", description = "제주도API")
@Log4j2
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/jejuapi")
@Transactional
public class JejuApiController {

    private final JejuApiService jejuApiService;

    @Autowired
    public JejuApiController(JejuApiService jejuApiService) {
        this.jejuApiService = jejuApiService;
    }

    @GetMapping("/food")
    public Mono<ResponseEntity<List<RecommendListFoodResponse>>> getFoods() {
        log.info("제주 음식점 정보 요청 중");
        return jejuApiService.getRecommendFoods();
    }

    @GetMapping("/place")
    public Mono<ResponseEntity<List<RecommendListPlaceResponse>>> getPlaces() {
        log.info("제주 관광지 정보 요청 중");
        return jejuApiService.getRecommendPlaces();
    }

    @GetMapping("/hotel")
    public Mono<ResponseEntity<List<RecommendListHotelResponse>>> getHotels() {
        log.info("제주 숙박 정보 요청 중");
        return jejuApiService.getRecommendHotels();
    }

    @GetMapping("/event")
    public Mono<ResponseEntity<List<RecommendListEventResponse>>> getEvents() {
        log.info("제주 축제행사 정보 요청 중");
        return jejuApiService.getRecommendEvents();
    }

}

