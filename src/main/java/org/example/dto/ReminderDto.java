package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderDto implements HasDeleted, HasCreatedAt {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private ZonedDateTime dueDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean deleted;
}
