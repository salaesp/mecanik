package org.example.model;

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
public class Reminder implements HasDeleted, HasCreatedAt {
    private Long id;
    private Long carId;
    private ZonedDateTime dueDate;
    private boolean deleted;
    private ZonedDateTime createdAt;
}
