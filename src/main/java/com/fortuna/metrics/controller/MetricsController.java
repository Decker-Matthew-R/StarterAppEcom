package com.fortuna.metrics.controller;

import com.fortuna.metrics.controller.types.MetricEventDTO;
import com.fortuna.metrics.service.MetricsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
