CREATE TABLE IF NOT EXISTS migrations.consumption (
                                                          id uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
                                                          metering_point_id uuid,
                                                          amount NUMERIC,
                                                          amount_unit VARCHAR,
                                                          consumption_time TIMESTAMP WITH TIME ZONE,
                                                          FOREIGN KEY (metering_point_id) REFERENCES migrations.metering_points(id)
);