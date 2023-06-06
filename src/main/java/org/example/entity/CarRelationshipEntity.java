package org.example.entity;

import lombok.Data;
import org.example.conditions.HasCreatedAt;
import org.example.conditions.HasDeleted;
import org.example.conditions.HasUpdatedAt;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "car_relationship", indexes = {
        @Index(name = "car_idx_user_id_and_rel", columnList = "user_id,id"),
        @Index(name = "car_idx_car", columnList = "user_id,car_id"),
        @Index(name = "car_idx_car", columnList = "car_id")}
)
@Data
public class CarRelationshipEntity implements HasDeleted, HasCreatedAt, HasUpdatedAt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "car_rel_gen")
    private Long id;
    private boolean deleted;
    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private CarEntity car;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUserEntity user;
}
