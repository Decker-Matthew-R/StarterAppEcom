package com.fortuna.metrics.service;

import com.fortuna.metrics.testFactories.MetricEventTestFactory;
import com.fortuna.metrics.controller.types.MetricEventDTO;
import com.fortuna.metrics.controller.types.MetricEventType;
import com.fortuna.metrics.repository.MetricsRepository;
import com.fortuna.metrics.repository.types.MetricEventEntity;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MetricsServiceTest {

    @Mock
    MetricsRepository mockMetricsRepository;

    @InjectMocks
    MetricsService metricsService;

    @Test
    @DisplayName("when a metric request is received, the repository is called")
    void whenMetricEventDTOIsReceived_MetricRepositoryIsCalled_NoContentReturned() {

        JSONObject eventMetadata = new JSONObject();
        eventMetadata.put("buttonId", "submit");
        eventMetadata.put("screen", "login");

        MetricEventDTO metricEventDTO =
            MetricEventTestFactory.createMockMetricEventDTO(MetricEventType.BUTTON_CLICK, eventMetadata);

        metricsService.saveMetricEvent(metricEventDTO);

        ArgumentCaptor<MetricEventEntity> captor = ArgumentCaptor.forClass(MetricEventEntity.class);
        verify(mockMetricsRepository).save(captor.capture());

        MetricEventEntity capturedEntity = captor.getValue();

        assertEquals(MetricEventType.BUTTON_CLICK.toString(), capturedEntity.getEvent());
        assertEquals(eventMetadata.toString(), capturedEntity.getMetadata());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        assertTrue(Math.abs(capturedEntity.getEventTime().getTime() - now.getTime()) < 1000,
            "Timestamp should be within 1 second of the current time");
    }
}
