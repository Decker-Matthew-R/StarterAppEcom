package com.fortuna.metrics.testFactories;

import com.fortuna.metrics.controller.types.MetricEventDTO;
import com.fortuna.metrics.controller.types.MetricEventType;
import com.fortuna.metrics.repository.types.MetricEventEntity;
import net.minidev.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class MetricEventTestFactory {

    public static MetricEventDTO createMockMetricEventDTO(MetricEventType metricEventType, JSONObject eventMetadata){
        return MetricEventDTO.builder()
            .event(metricEventType)
            .eventMetadata(eventMetadata)
            .build();
    }

    public static MetricEventEntity convertMetricEventDTOTOMetricEventEntity(MetricEventDTO metricEventDTO){
        return MetricEventEntity.builder()
            .id(null)
            .event(metricEventDTO.getEvent().toString())
            .eventTime(new Timestamp(new Date().getTime()))
            .metadata(metricEventDTO.getEventMetadata().toString())
            .build();
    }
}
