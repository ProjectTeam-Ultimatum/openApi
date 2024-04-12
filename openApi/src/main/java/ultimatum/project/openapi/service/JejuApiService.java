package ultimatum.project.openapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ultimatum.project.openapi.dto.food.RecommendFoodRequest;
import ultimatum.project.openapi.dto.food.RecommendFoodResponse;
import ultimatum.project.openapi.dto.hotel.RecommendHotelResponse;
import ultimatum.project.openapi.dto.jejuAPI.Item;
import ultimatum.project.openapi.dto.jejuAPI.JejuAllResponse;
import ultimatum.project.openapi.dto.place.RecommendPlaceResponse;
import ultimatum.project.openapi.entity.RecommendListFood;
import ultimatum.project.openapi.entity.RecommendListHotel;
import ultimatum.project.openapi.entity.RecommendListPlace;
import ultimatum.project.openapi.repository.RecommendListFoodRepository;
import ultimatum.project.openapi.repository.RecommendListHotelRepository;
import ultimatum.project.openapi.repository.RecommendListPlaceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor //공통
@Transactional
public class JejuApiService {


    private final RecommendListFoodRepository recommendListFoodRepository;
    private final RecommendListPlaceRepository recommendListPlaceRepository;
    private final RecommendListHotelRepository recommendListHotelRepository;


    //API 요청 후 Response 담기
    //2 응답 처리
    // Jeju API로부터 받은 응답(Mono<JejuAllResponse>)을 processJejuApiResponse 메소드로 전달하여 처리
    //응답 데이터에 대해 반복 처리를 수행
    //Item의 contentscd 필드(카테고리)를 기준으로 음식점, 관광지, 숙소 등의 항목을 구분
    public Mono<JejuAllResponse> processJejuApiResponse(Mono<JejuAllResponse> jejuAllResponseMono) {

        //파라미터를 받아온 값을 이용해서 원하는 로직을 작성


        // 결과값이나옴

        // 결과값을 return해줄 타입으로 맞춰줌


        // return return 타입과 맞는결과
        // 현재 비즈니스 로직

        //jejuAllResponse 전체조회
        return jejuAllResponseMono.doOnNext(jejuAllResponse -> {
            log.info("Result: {}", jejuAllResponse.getResult());
            log.info("Result Message: {}", jejuAllResponse.getResultMessage());
            log.info("Total Count: {}", jejuAllResponse.getTotalCount());

            if (jejuAllResponse.getItems() != null) {
                jejuAllResponse.getItems().forEach(item -> {
                    createFoodResponse(item);

                    // Item 필드 로그 출력
                    log.info("ContentsId: {}", item.getContentsid());
                    log.info("Title: {}", item.getTitle());
                    log.info("Introduction: {}", item.getIntroduction());
                    log.info("AllTag: {}", item.getAlltag());
                    log.info("Tag: {}", item.getTag());
                    log.info("Category: {}", item.getContentscd().getLabel());
                    log.info("Address: {}", item.getAddress());
                    log.info("RoadAddress: {}", item.getRoadaddress());
                    log.info("지역코드: {}", item.getRegion1cd().getLabel());
                    log.info("Region2cd: {}", item.getRegion2cd());
                    log.info("Latitude: {}", item.getLatitude());
                    log.info("Longitude: {}", item.getLongitude());
                    //log.info("Postcode: {}", item.getPostcode());
                    log.info("PhoneNo: {}", item.getPhoneno());
                    log.info("RepPhoto: {}", item.getRepPhoto().getPhotoId().getThumbnailpath());
                });
            }
        });
    }


    // 3 데이터 변환 및 저장
    // 음식점(음식점) 카테고리에 대한 Item을 처리하기 위해 호출
    // Item의 정보를 RecommendListFood 엔티티에 매핑
    // RecommendListFoodRepository를 통해 데이터베이스에 저장
    // RecommendListFood 엔티티는 RecommendFoodResponse DTO로 변환되어 최종적으로 반환
    public RecommendFoodResponse createFoodResponse(Item item){
        RecommendListFood recommendFood = RecommendListFood.builder() //builder 필드 채우기
                .recommendFoodContentsId(item.getContentsid())
                .recommendFoodTitle(item.getTitle())
                .recommendFoodIntroduction(item.getIntroduction())
                .recommendFoodAllTag(item.getAlltag())
                .recommendFoodTag(item.getTag())
                .recommendFoodCategory("음식점") // 카테고리 직접 설정
                .recommendFoodAddress(item.getAddress())
                .recommendFoodRegion(item.getRegion1cd().getLabel())
                .recommendFoodLatitude(item.getLatitude().toString())
                .recommendFoodLongitude(item.getLongitude().toString())
                .recommendFoodPhoneNo(item.getPhoneno())
                .recommendFoodImgPath(item.getRepPhoto().getPhotoId().getThumbnailpath())
                .build();

        RecommendListFood savedRecommendFood =recommendListFoodRepository.save(recommendFood);

        RecommendFoodResponse recommendFoodResponse = new RecommendFoodResponse(savedRecommendFood);
        return recommendFoodResponse;
    }

    // Place 저장
    public RecommendPlaceResponse createPlaceResponse(Item item) {
        RecommendListPlace recommendPlace = RecommendListPlace.builder()
                .recommendPlaceContentsId(item.getContentsid())
                .recommendPlaceTitle(item.getTitle())
                .recommendPlaceIntroduction(item.getIntroduction())
                .recommendPlaceAllTag(item.getAlltag())
                .recommendPlaceTag(item.getTag())
                .recommendPlaceCategory("관광지") // 카테고리 직접 설정
                .recommendPlaceAddress(item.getAddress())
                .recommendPlaceRegion(item.getRegion1cd().getLabel())
                .recommendPlaceLatitude(item.getLatitude().toString())
                .recommendPlaceLongitude(item.getLongitude().toString())
                .recommendPlacePhoneNo(item.getPhoneno())
                .recommendPlaceImgPath(item.getRepPhoto().getPhotoId().getThumbnailpath())
                .build();

        RecommendListPlace savedRecommendPlace =recommendListPlaceRepository.save(recommendPlace);
        log.info("🎈 savedRecommendPlaceID : {}", savedRecommendPlace.getRecommendPlaceId());
        RecommendPlaceResponse recommendPlaceResponse = new RecommendPlaceResponse(savedRecommendPlace);

        return recommendPlaceResponse;

    }

    //숙소 저장

    public RecommendHotelResponse createHotelResponse(Item item){
        RecommendListHotel recommendHotel = RecommendListHotel.builder() //builder 필드 채우기
                .recommendHotelContentsId(item.getContentsid())
                .recommendHotelTitle(item.getTitle())
                .recommendHotelIntroduction(item.getIntroduction())
                .recommendHotelAllTag(item.getAlltag())
                .recommendHotelTag(item.getTag())
                .recommendHotelCategory("숙박") // 카테고리 직접 설정
                .recommendHotelAddress(item.getAddress())
                .recommendHotelRegion(item.getRegion1cd().getLabel())
                .recommendHotelLatitude(item.getLatitude().toString())
                .recommendHotelLongitude(item.getLongitude().toString())
                .recommendHotelPhoneNo(item.getPhoneno())
                .recommendHotelImgPath(item.getRepPhoto().getPhotoId().getThumbnailpath())
                .build();

        RecommendListHotel savedRecommendHotel =recommendListHotelRepository.save(recommendHotel);

        RecommendHotelResponse recommendHotelResponse = new RecommendHotelResponse(savedRecommendHotel);
        return recommendHotelResponse;
    }


}
