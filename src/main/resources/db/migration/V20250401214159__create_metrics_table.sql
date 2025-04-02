CREATE TABLE if not exists metrics
(
    id     INT GENERATED ALWAYS AS IDENTITY,
    event VARCHAR,
    event_time   timestamptz,
    metadata    VARCHAR,
    CONSTRAINT METRIC_EVENT_PK PRIMARY KEY (id)
);
