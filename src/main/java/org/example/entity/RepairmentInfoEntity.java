package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.conditions.HasDeleted;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "repairment_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairmentInfoEntity implements HasDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private ReminderEntity reminder;
    @OneToOne
    private CarEntity car;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<AppointmentEntity> appointment;
    @OneToMany
    @JoinColumn(name = "work_done")
    @Cascade(CascadeType.ALL)
    private List<WorkDoneEntity> workDone;
    @OneToOne
    @JoinColumn(name = "payment_summary")
    private WorkDoneEntity paymentSummary;
    private boolean deleted;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updated;
}
