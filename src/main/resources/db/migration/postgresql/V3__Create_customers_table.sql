CREATE TABLE IF NOT EXISTS migrations.customers (
                                                 id uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
                                                 first_name VARCHAR,
                                                 last_name VARCHAR,
                                                 username VARCHAR NOT NULL UNIQUE,
                                                 password VARCHAR NOT NULL
    );