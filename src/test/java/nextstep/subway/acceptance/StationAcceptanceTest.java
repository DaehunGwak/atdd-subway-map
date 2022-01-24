package nextstep.subway.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("지하철역 관리 기능")
class StationAcceptanceTest extends AcceptanceTest {
    /**
     * When 지하철역 생성을 요청 하면
     * Then 지하철역 생성이 성공한다.
     */
    @DisplayName("지하철역 생성")
    @Test
    void createStation() {
        // given
        String 강남역_이름 = "강남역";

        // when
        ExtractableResponse<Response> response = StationTestStep.지하철역_생성하기(강남역_이름);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    /**
     * Given 지하철역 생성을 요청 하고
     * Given 새로운 지하철역 생성을 요청 하고
     * When 지하철역 목록 조회를 요청 하면
     * Then 두 지하철역이 포함된 지하철역 목록을 응답받는다
     */
    @DisplayName("지하철역 목록 조회")
    @Test
    void getStations() {
        /// given
        String 강남역 = "강남역";
        ExtractableResponse<Response> createResponse1 = StationTestStep.지하철역_생성하기(강남역);

        String 역삼역 = "역삼역";
        ExtractableResponse<Response> createResponse2 = StationTestStep.지하철역_생성하기(역삼역);

        // when
        ExtractableResponse<Response> response = StationTestStep.지하철역_목록_조회하기();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<String> stationNames = response.jsonPath().getList("name");
        assertThat(stationNames).contains(강남역, 역삼역);
    }

    /**
     * Given 지하철역 생성을 요청 하고
     * When 생성한 지하철역 삭제를 요청 하면
     * Then 생성한 지하철역 삭제가 성공한다.
     */
    @DisplayName("지하철역 삭제")
    @Test
    void deleteStation() {
        // given
        String 강남역_이름 = "강남역";
        ExtractableResponse<Response> createResponse = StationTestStep.지하철역_생성하기(강남역_이름);
        Integer stationIntegerId = createResponse.jsonPath().get("id");
        Long stationId = stationIntegerId.longValue();

        // when
        ExtractableResponse<Response> response = StationTestStep.지하철역_삭제하기(stationId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
