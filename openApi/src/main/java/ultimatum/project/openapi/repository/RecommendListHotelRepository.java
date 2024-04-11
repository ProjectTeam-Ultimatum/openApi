package ultimatum.project.openapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ultimatum.project.openapi.entity.RecommendListFood;
import ultimatum.project.openapi.entity.RecommendListHotel;

public interface RecommendListHotelRepository extends JpaRepository<RecommendListHotel, Long> {
}
