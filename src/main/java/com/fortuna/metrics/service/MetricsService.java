package com.fortuna.metrics.service;

import com.fortuna.metrics.controller.types.MetricEventDTO;
import com.fortuna.metrics.repository.MetricsRepository;
import com.fortuna.metrics.repository.types.MetricEventEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@AllArgsConstructor
public class MetricsService {

    private final MetricsRepository metricsRepository;

    public void saveMetricEvent(MetricEventDTO metricEventDTO) {
        MetricEventEntity metricEventEntity = metricEventDTOToMetricEventEntityConversion(metricEventDTO);
        metricsRepository.save(metricEventEntity);
    }

    private MetricEventEntity metricEventDTOToMetricEventEntityConversion(MetricEventDTO metricEventDTO){
        return MetricEventEntity.builder()
            .id(null)
            .event(metricEventDTO.getEvent().toString())
            .eventTime(new Timestamp(new Date().getTime()))
            .metadata(metricEventDTO.getEventMetadata().toString())
            .build();
    }
}
