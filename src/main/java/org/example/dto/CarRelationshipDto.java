package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;
import org.example.conditions.HasUpdatedAt;
import org.example.model.Car;
import org.example.model.User;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRelationshipDto implements HasCreatedAt, HasUpdatedAt, HasDeleted {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean deleted;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime updatedAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User user;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Car car;
}
