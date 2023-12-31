package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;
import org.example.conditions.HasUpdatedAt;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car implements HasDeleted, HasCreatedAt, HasUpdatedAt {
    private Long id;
    private String brand;
    private String model;
    private String year;
    private String plate;
    private String chassisNumber;
    private String version;
    private boolean deleted;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
