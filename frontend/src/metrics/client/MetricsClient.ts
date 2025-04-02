import axiosInstance from '../../../AxiosInstance/AxiosInstance';
import { API_ENDPOINTS } from '../../ApiEndpoints/API_ENDPOINTS';
import { MetricEventType } from '../model/MetricEventType';

export const saveMetricEvent = (metricEvent: MetricEventType): Promise<void> =>
  axiosInstance
    .post(API_ENDPOINTS.RECORD_METRIC_EVENT, metricEvent)
    .then(() => {})
    .catch(() => Promise.reject('Failed to capture metric event.'));
