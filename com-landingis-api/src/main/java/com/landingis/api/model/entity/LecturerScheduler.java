package com.landingis.api.model.entity;

import com.landingis.api.model.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lecturer_schedulers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerScheduler extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private Long lecturerId;

    @Column(nullable = false)
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    private Period period;
}
