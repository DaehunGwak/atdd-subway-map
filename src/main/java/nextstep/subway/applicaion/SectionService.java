package nextstep.subway.applicaion;

import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.applicaion.dto.SectionResponse;
import nextstep.subway.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SectionService {

    private SectionRepository sectionRepository;
    private StationRepository stationRepository;
    private LineRepository lineRepository;

    public SectionService(SectionRepository sectionRepository,
                          StationRepository stationRepository,
                          LineRepository lineRepository) {
        this.sectionRepository = sectionRepository;
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
    }

    public SectionResponse saveSection(SectionRequest request, Long lineId) {
        Line line = lineRepository.findById(lineId)
                .orElseThrow(() -> new IllegalArgumentException("구간 저장 중 관련 노선을 찾을 수 없습니다. lineId:" + lineId));
        Station upStation = stationRepository.findById(request.getUpStationId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "구간 저장 중 상행선역을 찾을 수 없습니다. upStationId:" + request.getUpStationId()));
        Station downStation = stationRepository.findById(request.getDownStationId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "구간 저장 중 하행선역을 찾을 수 없습니다. downStationId:" + request.getDownStationId()));

        Section section = sectionRepository.save(new Section(line, upStation, downStation, request.getDistance()));
        return new SectionResponse(section);
    }
}