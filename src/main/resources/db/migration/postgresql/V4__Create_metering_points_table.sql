CREATE TABLE IF NOT EXISTS migrations.metering_points (
                                                 id uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
                                                 customer_id uuid,
                                                 address VARCHAR,
                                                 FOREIGN KEY (customer_id) REFERENCES migrations.customers(id)
    );