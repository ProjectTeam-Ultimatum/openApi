package ultimatum.project.openapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import ultimatum.project.openapi.dto.food.RecommendFoodRequest;
import ultimatum.project.openapi.dto.food.RecommendFoodResponse;
import ultimatum.project.openapi.dto.hotel.RecommendHotelResponse;
import ultimatum.project.openapi.dto.jejuAPI.Item;
import ultimatum.project.openapi.dto.jejuAPI.JejuAllResponse;
import ultimatum.project.openapi.dto.place.RecommendPlaceResponse;
import ultimatum.project.openapi.repository.RecommendListFoodRepository;
import ultimatum.project.openapi.repository.RecommendListHotelRepository;
import ultimatum.project.openapi.repository.RecommendListPlaceRepository;

@Log4j2
@Service
@RequiredArgsConstructor //공통
@Transactional
public class JejuApiService {


    private final RecommendListFoodRepository recommendListFoodRepository;
    private final RecommendListPlaceRepository recommendListPlaceRepository;
    private final RecommendListHotelRepository recommendListHotelRepository;

    //조회
    public Mono<JejuAllResponse> processJejuApiResponse(Mono<JejuAllResponse> jejuAllResponseMono) {

        //jejuAllResponse 전체조회
        return jejuAllResponseMono.doOnNext(jejuAllResponse -> {
            log.info("Result: {}", jejuAllResponse.getResult());
            log.info("Result Message: {}", jejuAllResponse.getResultMessage());
            log.info("Total Count: {}", jejuAllResponse.getTotalCount());

            if (jejuAllResponse.getItems() != null) {
                jejuAllResponse.getItems().forEach(item -> {
                    String label = item.getContentscd().getLabel();

                    switch (label) {
                        case "음식점":
                            RecommendFoodResponse foodResponse = createFoodResponse(item);
                            log.info("Food Response Created: {}", foodResponse.getRecommendFoodTitle());
                            break;
                        case "관광지":
                            RecommendPlaceResponse placeResponse = createPlaceResponse(item);
                            log.info("Place Response Created: {}", placeResponse.getRecommendPlaceTitle());
                            break;
                        case "숙소":
                            RecommendHotelResponse hotelResponse = createHotelResponse(item);
                            log.info("Hotel Response Created: {}", hotelResponse.getRecommendHotelTitle());
                            break;
                        default:
                            log.info("Other Category: {}", label);
                            break;
                    }

                    // Item 필드 로그 출력
//                    log.info("Contentsid: {}", item.getContentsid());
//                    log.info("Title: {}", item.getTitle());
//                    log.info("Introduction: {}", item.getIntroduction());
//                    log.info("Alltagg: {}", item.getAlltag());
//                    log.info("Tag: {}", item.getTag());
//                    log.info("Category: {}", item.getContentscd().getLabel());
//                    log.info("Address: {}", item.getAddress());
//                    log.info("Roadaddress: {}", item.getRoadaddress());
//                    log.info("지역코드: {}", item.getRegion1cd().getLabel());
//                    log.info("Region2cd: {}", item.getRegion2cd());
//                    log.info("Latitude: {}", item.getLatitude());
//                    log.info("Longitude: {}", item.getLongitude());
//                    log.info("Postcode: {}", item.getPostcode());
//                    log.info("Phoneno: {}", item.getPhoneno());
//                    log.info("RepPhoto: {}", item.getRepPhoto());
                });
            }
        });
    }


    private RecommendFoodResponse createFoodResponse(Item item){
        RecommendFoodResponse foodResponse = RecommendFoodResponse.builer()
                .recommendContentsid(item.getContentsid())
                .recommendFoodTitle(item.getTitle())
                .recommendFoodIntroduction(item.getIntroduction())
                .recommendFoodAllTag(item.getAlltag())
                .recommendFoodTag(item.getTag())
                .recommendCategory("음식점") // 카테고리 직접 설정
                .recommendFoodAddress(item.getAddress())
                .recommendFoodRegion(item.getRegion1cd().getLabel())
                .recommendFoodLatitude(item.getLatitude().toString())
                .recommendFoodLongitude(item.getLongitude().toString())
                .recommendFoodPhoneno(Integer.parseInt(item.getPhoneno())) // 문자열을 정수로 변환
                .build();

        // 생성된 RecommendFoodResponse 객체를 리포지토리에 저장합니다.
        return recommendListFoodRepository.save(foodResponse);
    }

    private RecommendPlaceResponse createPlaceResponse(Item item) {
        RecommendPlaceResponse placeResponse = new RecommendPlaceResponse();
        // 여기에 필드 설정 로직 추가...
        return placeResponse;
    }

    private RecommendHotelResponse createHotelResponse(Item item) {
        RecommendHotelResponse hotelResponse = new RecommendHotelResponse();
        // 여기에 필드 설정 로직 추가...
        return hotelResponse;
    }

}
