package com.fortuna.metrics.repository.types;

import com.fortuna.metrics.controller.types.MetricEventType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "metrics")
public class MetricEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String event;
    Timestamp eventTime;
    String metadata;
}
