INSERT INTO migrations.metering_points (customer_id, address)
VALUES
    ((SELECT id FROM migrations.customers WHERE username='testuser' LIMIT 1), 'Energia 4-52'),
    ((SELECT id FROM migrations.customers WHERE username='testuser' LIMIT 1), 'Energia 4-53'),
    ((SELECT id FROM migrations.customers WHERE username='testuser2' LIMIT 1), 'Energia 5-12'),
    ((SELECT id FROM migrations.customers WHERE username='testuser2' LIMIT 1), 'Energia 5-13')