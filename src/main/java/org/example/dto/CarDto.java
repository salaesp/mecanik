package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;
import org.example.conditions.HasUpdatedAt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto implements HasDeleted, HasCreatedAt, HasUpdatedAt {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotEmpty
    private String brand;
    @NotEmpty
    private String model;
    @Pattern(regexp = "^\\d{4}$")
    private String year;
    @NotEmpty
    private String plate;
    @NotEmpty
    private String chassisNumber;
    @NotEmpty
    private String version;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean deleted;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime updatedAt;
}
