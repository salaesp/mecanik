package org.example.entity;

import lombok.Data;
import org.example.conditions.HasDeleted;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "car", indexes = @Index(name = "car_idx_user_id_and_car", columnList = "user_id,id"))
@Data
public class CarEntity implements HasDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "car_gen")
    private Long id;
    private String brand;
    private String model;
    //Cannot use YEAR since its a reserved word
    @Column(name = "creation_year")
    private String creationYear;
    private String plate;
    @Column(name = "chassis_number")
    private String chassisNumber;
    @Column(name = "user_id")
    private Long userId;
    private boolean deleted;
    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated;
}
