package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;
import org.example.conditions.HasUpdatedAt;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRelationship implements HasDeleted, HasCreatedAt, HasUpdatedAt {
    private Long id;
    private boolean deleted;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @Nullable
    private User user;
    private Car car;
}
