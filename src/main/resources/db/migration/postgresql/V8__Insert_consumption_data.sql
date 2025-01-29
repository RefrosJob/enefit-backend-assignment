INSERT INTO migrations.consumption (metering_point_id, amount, amount_unit, consumption_time)
VALUES
    ((SELECT mp.id
      FROM migrations.metering_points AS mp
               LEFT OUTER JOIN migrations.customers c on c.id = mp.customer_id
      WHERE mp.customer_id=c.id AND c.username='testuser'
      ORDER BY mp.address DESC
      LIMIT 1), 200, 'kwh', timestamptz('20240601')),
    ((SELECT mp.id
      FROM migrations.metering_points AS mp
               LEFT OUTER JOIN migrations.customers c on c.id = mp.customer_id
      WHERE mp.customer_id=c.id AND c.username='testuser'
      ORDER BY mp.address DESC
      LIMIT 1), 224, 'kwh', timestamptz('20240701')),
    ((SELECT mp.id
      FROM migrations.metering_points AS mp
               LEFT OUTER JOIN migrations.customers c on c.id = mp.customer_id
      WHERE mp.customer_id=c.id AND c.username='testuser'
      ORDER BY mp.address
      LIMIT 1), 130, 'kwh', timestamptz('20240601')),
    ((SELECT mp.id
      FROM migrations.metering_points AS mp
               LEFT OUTER JOIN migrations.customers c on c.id = mp.customer_id
      WHERE mp.customer_id=c.id AND c.username='testuser'
      ORDER BY mp.address
      LIMIT 1), 212, 'kwh', timestamptz('20240701')),
    ((SELECT mp.id
      FROM migrations.metering_points AS mp
               LEFT OUTER JOIN migrations.customers c on c.id = mp.customer_id
      WHERE mp.customer_id=c.id AND c.username='testuser2'
      ORDER BY mp.address DESC
      LIMIT 1), 235, 'kwh', timestamptz('20240601')),
    ((SELECT mp.id
      FROM migrations.metering_points AS mp
               LEFT OUTER JOIN migrations.customers c on c.id = mp.customer_id
      WHERE mp.customer_id=c.id AND c.username='testuser2'
      ORDER BY mp.address DESC
      LIMIT 1), 235, 'kwh', timestamptz('20240701')),
    ((SELECT mp.id
      FROM migrations.metering_points AS mp
               LEFT OUTER JOIN migrations.customers c on c.id = mp.customer_id
      WHERE mp.customer_id=c.id AND c.username='testuser2'
      ORDER BY mp.address
      LIMIT 1), 122, 'kwh', timestamptz('20240601'))