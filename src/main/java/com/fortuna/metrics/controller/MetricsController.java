package com.fortuna.metrics.controller;

import com.fortuna.metrics.controller.types.MetricEventDTO;
import com.fortuna.metrics.service.MetricsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @PostMapping("/api/save-metric")
    public ResponseEntity<Void> saveMetricEvent(@RequestBody MetricEventDTO metricEventDTO){
        metricsService.saveMetricEvent(metricEventDTO);
        return ResponseEntity.status(204).build();
    }

}
