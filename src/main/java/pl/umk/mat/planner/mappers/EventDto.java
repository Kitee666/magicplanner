package pl.umk.mat.planner.mappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class EventDto implements Serializable {

    private final Long id;
    @JsonProperty("start")
    private final OffsetDateTime dateFrom;
    @JsonProperty("end")
    private final OffsetDateTime dateTo;
    @JsonProperty("title")
    private final String title;
}
