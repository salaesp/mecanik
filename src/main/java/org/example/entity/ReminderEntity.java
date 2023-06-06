package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reminder", indexes = {
        @Index(name = "REMINDERS_BY_CAR", columnList = "car_id"),
        @Index(name = "REMINDERS_BY_CAR_AND_DELETED", columnList = "car_id,deleted")})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderEntity implements HasDeleted, HasCreatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "reminder_gen")
    private Long id;
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "due_date")
    private ZonedDateTime dueDate;
    private boolean deleted;
    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
}
