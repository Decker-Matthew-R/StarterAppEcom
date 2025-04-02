import { waitFor } from '@testing-library/react';
import { describe, expect, vi } from 'vitest';
import { MetricEventType } from '../../model/MetricEventType';
import { METRIC_EVENT_TYPE } from '../../model/METRIC_EVENT_TYPE';
import { MetadataType } from '../../model/MetadataType';
import { server } from '../../../setupTests';
import { rest } from 'msw';
import { API_ENDPOINTS } from '../../../ApiEndpoints/API_ENDPOINTS';
import { saveMetricEvent } from '../../../metrics/client/MetricsClient';
import { axiosInstance } from '../../../../AxiosInstance/AxiosInstance';

describe('Metrics Client', () => {
  const metadata: MetadataType = { triggerId: 'React Button', screen: 'Home' };

  const metricEventType: MetricEventType = {
    event: METRIC_EVENT_TYPE.BUTTON_CLICK,
    eventMetadata: metadata,
  };

  it('should post a request when a metric event is recorded', async () => {
    server.use(
      rest.post(API_ENDPOINTS.RECORD_METRIC_EVENT, (req, res, ctx) =>
        res(ctx.status(201), ctx.json(metricEventType)),
      ),
    );
    await saveMetricEvent(metricEventType);
  });
});
