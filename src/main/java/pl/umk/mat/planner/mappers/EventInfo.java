package pl.umk.mat.planner.mappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import pl.umk.mat.planner.subject.Subject;

import java.time.OffsetDateTime;

public interface EventInfo {
    Long getId();
    @JsonProperty("start")
    OffsetDateTime getDateFrom();

    @JsonProperty("end")
    OffsetDateTime getDateTo();

    @JsonProperty("title")
    @Value("#{target.connector.subject.name}")
    String getSubjectName();
}
