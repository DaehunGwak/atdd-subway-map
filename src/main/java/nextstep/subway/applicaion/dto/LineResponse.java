package nextstep.subway.applicaion.dto;

import nextstep.subway.domain.Line;

import java.time.LocalDateTime;

public class LineResponse {
    private Long id;
    private String name;
    private String color;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public LineResponse(Long id, String name, String color, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public LineResponse(Line line) {
        this.id = line.getId();
        this.name = line.getName();
        this.color = line.getColor();
        this.createdDate = line.getCreatedDate();
        this.modifiedDate = line.getModifiedDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
