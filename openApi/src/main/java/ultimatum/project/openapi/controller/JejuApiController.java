package ultimatum.project.openapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ultimatum.project.openapi.dto.JejuAllResponse;
import ultimatum.project.openapi.service.JejuApiService;

@Tag(name = "recommendList", description = "제주도API")
@Log4j2
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/jejuapi")
public class JejuApiController {

    //    private final WebClient webClient;
    private final JejuApiService jejuApiService;
    // 클래스 상단에 Logger 인스턴스를 정의
    private static final Logger logger = LogManager.getLogger(JejuApiController.class);
    private final WebClient webClient;
    // API 키를 application.yml로부터 주입받음
    @Value("${jejuapi.api-key}")
    private String apiKey;


    @Autowired
    public JejuApiController(WebClient.Builder webClientBuilder, JejuApiService jejuApiService) {
        this.webClient = webClientBuilder.baseUrl("https://api.visitjeju.net").build();
        this.jejuApiService = jejuApiService;
    }

    @GetMapping("/all")
    public Mono<ResponseEntity<JejuAllResponse>> getSights(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "pageSize", defaultValue = "12") int pageSize) {
        logger.info("Requesting Jeju sights for page {} with page size {}", page, pageSize);
//        String apiKey = "nc3v2w57zkiafreu"; // 실제 API 키를 여기에 입력하세요.
//        String locale = "kr";

        Mono<JejuAllResponse> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vsjApi/contents/searchList")
                        .queryParam("apiKey", apiKey) // 주입받은 API 키를 사용
                        .queryParam("locale", "kr")
                        .queryParam("page", page)
                        .queryParam("pageSize", pageSize)
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
}

