package com.fortuna.metrics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortuna.metrics.testFactories.MetricEventTestFactory;
import com.fortuna.metrics.controller.types.MetricEventDTO;
import com.fortuna.metrics.controller.types.MetricEventType;
import com.fortuna.metrics.service.MetricsService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MetricsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = MetricsController.class)
class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MetricsService mockMetricsService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("when a metric request is received, the service is called")
    void whenMetricEventDTOIsReceived_MetricsServiceIsCalled_NoContentIsReturned() throws Exception {
        JSONObject eventMetadata = new JSONObject();
        eventMetadata.put("buttonId", "submit");
        eventMetadata.put("screen", "login");

        MetricEventDTO metricEventDTO =
            MetricEventTestFactory.createMockMetricEventDTO(MetricEventType.BUTTON_CLICK, eventMetadata);

        String requestJson = objectMapper.writeValueAsString(metricEventDTO);

        mockMvc.perform(
                post("/api/save-metric")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
                    .with(csrf()))
            .andExpect(status().isNoContent())
            .andReturn()
            .getResponse()
            .getContentAsString();

        verify(mockMetricsService, times(1)).saveMetricEvent(metricEventDTO);
    }
}
