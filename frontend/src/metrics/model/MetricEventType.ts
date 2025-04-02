import { MetadataType } from '../model/MetadataType';
import { METRIC_EVENT_TYPE } from '../model/METRIC_EVENT_TYPE';

export type MetricEventType = {
  event: METRIC_EVENT_TYPE;
  eventMetadata: MetadataType;
};
