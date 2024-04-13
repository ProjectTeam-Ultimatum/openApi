package ultimatum.project.openapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ultimatum.project.openapi.dto.event.RecommendListEventResponse;
import ultimatum.project.openapi.dto.food.RecommendListFoodResponse;
import ultimatum.project.openapi.dto.hotel.RecommendListHotelResponse;
import ultimatum.project.openapi.dto.jejuAPI.Item;
import ultimatum.project.openapi.dto.jejuAPI.JejuAllResponse;
import ultimatum.project.openapi.dto.place.RecommendListPlaceResponse;
import ultimatum.project.openapi.entity.RecommendListEvent;
import ultimatum.project.openapi.entity.RecommendListFood;
import ultimatum.project.openapi.entity.RecommendListHotel;
import ultimatum.project.openapi.entity.RecommendListPlace;
import ultimatum.project.openapi.repository.RecommendListEventRepository;
import ultimatum.project.openapi.repository.RecommendListFoodRepository;
import ultimatum.project.openapi.repository.RecommendListHotelRepository;
import ultimatum.project.openapi.repository.RecommendListPlaceRepository;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor //공통
@Transactional
public class JejuApiService {


    private final RecommendListFoodRepository recommendListFoodRepository;
    private final RecommendListPlaceRepository recommendListPlaceRepository;
    private final RecommendListHotelRepository recommendListHotelRepository;
    private final RecommendListEventRepository recommendListEventRepository;
    private final WebClient webClient;
    @Value("${jejuapi.api-key}")
    private String apiKey;

    @Autowired
    public JejuApiService( WebClient.Builder webClientBuilder, RecommendListFoodRepository recommendListFoodRepository,
                           RecommendListPlaceRepository recommendListPlaceRepository,
                           RecommendListHotelRepository recommendListHotelRepository,
                           RecommendListEventRepository recommendListEventRepository
                           )
    {
        this.recommendListFoodRepository = recommendListFoodRepository;
        this.recommendListPlaceRepository = recommendListPlaceRepository;
        this.recommendListHotelRepository = recommendListHotelRepository;
        this.recommendListEventRepository = recommendListEventRepository;
        this.webClient = webClientBuilder.baseUrl("https://api.visitjeju.net").build();
    }

    //제주 음식점 정보 요청
    public Mono<ResponseEntity<List<RecommendListFoodResponse>>> getRecommendFoods() {
        log.info("제주 음식점 추천 정보를 API에서 검색 중입니다.");
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vsjApi/contents/searchList")
                        .queryParam("apiKey", apiKey)
                        .queryParam("locale", "kr")
                        .queryParam("category", "c4") // 음식점
                        .build())
                .retrieve()
                .bodyToMono(JejuAllResponse.class)
                .flatMapMany(response -> {
                    log.info("제주 음식점총개수 : {}개", response.getItems().size());
                    return Flux.fromIterable(response.getItems());
                })
                .map(this::createFoodResponse)
                .collectList()  // Flux<RecommendFoodResponse>를 List<RecommendFoodResponse>로 변환
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(error -> log.error("제주 음식점 추천 정보 응답 처리 중 오류 발생", error));
    }

    //제주 관광지 정보 요청
    public Mono<ResponseEntity<List<RecommendListPlaceResponse>>> getRecommendPlaces() {
        log.info("제주 관광지API 검색 중....");
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vsjApi/contents/searchList")
                        .queryParam("apiKey", apiKey)
                        .queryParam("locale", "kr")
                        .queryParam("category", "c1") // 관광지
                        .build())
                .retrieve()
                .bodyToMono(JejuAllResponse.class)
                .flatMapMany(response -> {
                    log.info("제주 관광지총개수 : {}개", response.getItems().size());
                    return Flux.fromIterable(response.getItems());
                })
                .map(this::createPlaceResponse)
                .collectList()  // Flux<RecommendFoodResponse>를 List<RecommendFoodResponse>로 변환
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(error -> log.error("제주 관광지API 응답 처리 중 오류 발생", error));
    }

    //제주 숙박 정보 요청
    public Mono<ResponseEntity<List<RecommendListHotelResponse>>> getRecommendHotels() {
        log.info("숙박API 검색 중....");
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vsjApi/contents/searchList")
                        .queryParam("apiKey", apiKey)
                        .queryParam("locale", "kr")
                        .queryParam("category", "c3") // 숙박
                        .build())
                .retrieve()
                .bodyToMono(JejuAllResponse.class)
                .flatMapMany(response -> {
                    log.info("제주 숙박총개수 : {}개", response.getItems().size());
                    return Flux.fromIterable(response.getItems());
                })
                .map(this::createHotelResponse)
                .collectList()  // Flux<RecommendFoodResponse>를 List<RecommendFoodResponse>로 변환
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(error -> log.error("숙박API 응답 처리 중 오류 발생", error));
    }

    //제주 축제/행사 정보 요청
    public Mono<ResponseEntity<List<RecommendListEventResponse>>> getRecommendEvents() {
        log.info("축제행사API 검색 중....");
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vsjApi/contents/searchList")
                        .queryParam("apiKey", apiKey)
                        .queryParam("locale", "kr")
                        .queryParam("category", "c5") // 축제행사
                        .build())
                .retrieve()
                .bodyToMono(JejuAllResponse.class)
                .flatMapMany(response -> {
                    log.info("제주 축제행사총개수 : {}개", response.getItems().size());
                    return Flux.fromIterable(response.getItems());
                })
                .map(this::createEventResponse)
                .collectList()  // Flux<RecommendFoodResponse>를 List<RecommendFoodResponse>로 변환
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(error -> log.error("축제행사API 응답 처리 중 오류 발생", error));
    }


    //jejuAPI 요청 응답
//    public Mono<ResponseEntity<JejuAllResponse>> getAllSights() {
//        log.info("제주 관광 명소 정보를 API에서 검색 중입니다.");
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/vsjApi/contents/searchList")
//                        .queryParam("apiKey", apiKey)
//                        .queryParam("locale", "kr")
//                        .build())
//                .retrieve()
//                .bodyToMono(JejuAllResponse.class)
//                .flatMap(this::processJejuApiResponse) // JejuAllResponse 객체를 직접 처리
//                .map(response -> {
//                    log.info("제주 관광 명소 정보 검색 성공, 항목 수: {}개", response.getItems().size()); // 성공 메시지 추가
//                    return ResponseEntity.ok().body(response);
//                })
//                .defaultIfEmpty(ResponseEntity.notFound().build())
//                .doOnError(error -> log.error("제주 API 응답 처리 중 오류 발생", error));
//    }

    //jejuAPI 응답
    public Mono<JejuAllResponse> processJejuApiResponse(JejuAllResponse jejuAllResponse) {
        log.info("제주 API로부터의 응답을 처리 중입니다.");
        if (jejuAllResponse.getItems() == null) {
            log.warn("제주 API로부터 받은 항목이 없습니다.");
            return Mono.just(jejuAllResponse);
        }

        jejuAllResponse.getItems().forEach(item -> {
            createFoodResponse(item);

            log.info("콘텐츠 ID: {}", item.getContentsid());
            log.info("제목: {}", item.getTitle());
            log.info("소개: {}", item.getIntroduction());
            log.info("모든 태그: {}", item.getAlltag());
            log.info("태그: {}", item.getTag());
            log.info("카테고리: {}", item.getContentscd().getLabel());
            log.info("주소: {}", item.getAddress());
            log.info("도로명 주소: {}", item.getRoadaddress());
            log.info("지역 코드: {}", item.getRegion1cd().getLabel());
            log.info("세부 지역 코드: {}", item.getRegion2cd());
            log.info("위도: {}", item.getLatitude());
            log.info("경도: {}", item.getLongitude());
            log.info("전화번호: {}", item.getPhoneno());

            if (item.getRepPhoto() != null && item.getRepPhoto().getPhotoId() != null) {
                log.info("대표 사진: {}", item.getRepPhoto().getPhotoId().getThumbnailpath());
            } else {
                log.warn("해당 항목의 대표 사진 정보가 없습니다. 콘텐츠 ID: {}", item.getContentsid());
            }
        });

        return Mono.just(jejuAllResponse);
    }


    public RecommendListFoodResponse createFoodResponse(Item item){
        log.info("음식점 데이터 변환 및 저장을 시작합니다.");

        RecommendListFood recommendFood = RecommendListFood.builder() //builder 필드 채우기
                .recommendFoodContentsId(item.getContentsid())
                .recommendFoodTitle(item.getTitle())
                .recommendFoodIntroduction(item.getIntroduction())
                .recommendFoodAllTag(item.getAlltag())
                .recommendFoodTag(item.getTag())
                .recommendFoodCategory(item.getContentscd().getLabel()) // 카테고리 직접 설정
                .recommendFoodAddress(item.getAddress())
                .recommendFoodRegion(item.getRegion1cd().getLabel())
                .recommendFoodLatitude(item.getLatitude() != null ? item.getLatitude().toString() : "위도 정보 없음")
                .recommendFoodLongitude(item.getLongitude() != null ? item.getLongitude().toString() : "경도 정보 없음")
                .recommendFoodPhoneNo(item.getPhoneno())
                .recommendFoodPhoneNo(item.getPhoneno())
                .recommendFoodImgPath(item.getRepPhoto() != null && item.getRepPhoto().getPhotoId() != null ? item.getRepPhoto().getPhotoId().getThumbnailpath() : "이미지 없음")
                .build();

        RecommendListFood savedRecommendFood = recommendListFoodRepository.save(recommendFood);
        log.info("음식점 데이터 저장 완료");

        RecommendListFoodResponse recommendFoodResponse = new RecommendListFoodResponse(savedRecommendFood);
        log.info("음식점 응답 객체 생성 완료");
        return recommendFoodResponse;
    }

    // 관광지 저장
    public RecommendListPlaceResponse createPlaceResponse(Item item) {
        log.info("관광지 데이터 변환 및 저장을 시작합니다.");
        RecommendListPlace recommendPlace = RecommendListPlace.builder()
                .recommendPlaceContentsId(item.getContentsid())
                .recommendPlaceTitle(item.getTitle())
                .recommendPlaceIntroduction(item.getIntroduction())
                .recommendPlaceAllTag(item.getAlltag())
                .recommendPlaceTag(item.getTag())
                .recommendPlaceCategory(item.getContentscd().getLabel())
                .recommendPlaceAddress(item.getAddress())
                .recommendPlaceRegion(item.getRegion1cd().getLabel())
                .recommendPlaceLatitude(item.getLatitude() != null ? item.getLatitude().toString() : "위도 정보 없음")
                .recommendPlaceLongitude(item.getLongitude() != null ? item.getLongitude().toString() : "경도 정보 없음")
                .recommendPlacePhoneNo(item.getPhoneno())
                .recommendPlaceImgPath(item.getRepPhoto() != null && item.getRepPhoto().getPhotoId() != null ? item.getRepPhoto().getPhotoId().getThumbnailpath() : "이미지 없음")
                .build();

        RecommendListPlace savedRecommendPlace =recommendListPlaceRepository.save(recommendPlace);
        log.info("관광지 데이터 저장 완료");
        RecommendListPlaceResponse recommendPlaceResponse = new RecommendListPlaceResponse(savedRecommendPlace);
        log.info("관광지 응답 객체 생성 완료");

        return recommendPlaceResponse;
    }

    //숙박 저장
    public RecommendListHotelResponse createHotelResponse(Item item){
        log.info("숙박 데이터 변환 및 저장을 시작합니다.");
        RecommendListHotel recommendHotel = RecommendListHotel.builder() //builder 필드 채우기
                .recommendHotelContentsId(item.getContentsid())
                .recommendHotelTitle(item.getTitle())
                .recommendHotelIntroduction(item.getIntroduction())
                .recommendHotelAllTag(item.getAlltag())
                .recommendHotelTag(item.getTag())
                .recommendHotelCategory(item.getContentscd().getLabel())
                .recommendHotelAddress(item.getAddress())
                .recommendHotelRegion(item.getRegion1cd().getLabel())
                .recommendHotelLatitude(item.getLatitude() != null ? item.getLatitude().toString() : "위도 정보 없음")
                .recommendHotelLongitude(item.getLongitude() != null ? item.getLongitude().toString() : "경도 정보 없음")
                .recommendHotelPhoneNo(item.getPhoneno())
                .recommendHotelImgPath(item.getRepPhoto() != null && item.getRepPhoto().getPhotoId() != null ? item.getRepPhoto().getPhotoId().getThumbnailpath() : "이미지 없음")
                .build();

        RecommendListHotel savedRecommendHotel =recommendListHotelRepository.save(recommendHotel);
        log.info("숙박 데이터 저장 완료");
        RecommendListHotelResponse recommendHotelResponse = new RecommendListHotelResponse(savedRecommendHotel);
        log.info("숙박 응답 객체 생성 완료");
        return recommendHotelResponse;
    }

    //축제행사 저장
    public RecommendListEventResponse createEventResponse(Item item){
        log.info("축제행사 데이터 변환 및 저장을 시작합니다.");
        RecommendListEvent recommendEvent = RecommendListEvent.builder() //builder 필드 채우기
                .recommendEventContentsId(item.getContentsid())
                .recommendEventTitle(item.getTitle())
                .recommendEventIntroduction(item.getIntroduction())
                .recommendEventAllTag(item.getAlltag())
                .recommendEventTag(item.getTag())
                .recommendEventCategory(item.getContentscd().getLabel())
                .recommendEventAddress(item.getAddress())
                .recommendEventRegion(item.getRegion1cd().getLabel())
                .recommendEventLatitude(item.getLatitude() != null ? item.getLatitude().toString() : "위도 정보 없음")
                .recommendEventLongitude(item.getLongitude() != null ? item.getLongitude().toString() : "경도 정보 없음")
                .recommendEventPhoneNo(item.getPhoneno())
                .recommendEventImgPath(item.getRepPhoto() != null && item.getRepPhoto().getPhotoId() != null ? item.getRepPhoto().getPhotoId().getThumbnailpath() : "이미지 없음")
                .build();

        RecommendListEvent savedRecommendEvent = recommendListEventRepository.save(recommendEvent);
        log.info("축제행사 데이터 저장 완료");
        RecommendListEventResponse recommendEventResponse = new RecommendListEventResponse(savedRecommendEvent);
        log.info("축제행사 응답 객체 생성 완료");
        return recommendEventResponse;
    }


}
