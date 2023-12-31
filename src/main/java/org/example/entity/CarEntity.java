package org.example.entity;

import lombok.Data;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;
import org.example.conditions.HasUpdatedAt;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "car")
@Data
public class CarEntity implements HasDeleted, HasCreatedAt, HasUpdatedAt {
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
    private String version;
    private boolean deleted;
    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
}
