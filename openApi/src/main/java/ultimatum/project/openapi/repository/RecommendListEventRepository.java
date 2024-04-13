package ultimatum.project.openapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ultimatum.project.openapi.entity.RecommendListEvent;

public interface RecommendListEventRepository extends JpaRepository<RecommendListEvent, Long> {
}
