package nextstep.subway.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    @Query(value = "select l from Line l left join fetch l.stations")
    List<Line> findAllWithStations();
}
