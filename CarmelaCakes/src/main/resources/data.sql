INSERT INTO cake (id, dough, filling, shape, size) VALUES (1, 'VANILLA', 'WHITE_CHOCOLATE', 'ROUND', 'LARGE');
INSERT INTO cake (id, dough, filling, shape, size) VALUES (2, 'CHOCOLATE', 'CHOCOLATE', 'SQUARED', 'MEDIUM');
INSERT INTO cake (id, dough, filling, shape, size) VALUES (3, 'NUTS', 'STRAWBERRY', 'TRIANGULAR', 'SMALL');

INSERT INTO cake_toppings (cake_id, toppings) VALUES (1, 'STRAWBERRY');
INSERT INTO cake_toppings (cake_id, toppings) VALUES (2, 'KIT_KAT');
INSERT INTO cake_toppings (cake_id, toppings) VALUES (3, 'KINDER_BUENO');
INSERT INTO cake_toppings (cake_id, toppings) VALUES (2, 'OREO');

INSERT INTO customer (id, address, name) VALUES (1, 'Actors Street', 'Marry E. Tanaka');
INSERT INTO customer (id, address, name) VALUES (2, 'Paulista Avenue', 'Alex R. Oswald');

INSERT INTO cake_order (id, delivery_date, is_delivery, order_date, price, status, cake_id, customer_id) VALUES (1, '2022-11-30', b'1', '2022-11-26', 150.00, 'FINALISED', 1, 1);
INSERT INTO cake_order (id, delivery_date, is_delivery, order_date, price, status, cake_id, customer_id) VALUES (2, '2022-12-02', b'0', '2022-11-26', 110.00, 'IN_PROGRESS', 2, 2);
INSERT INTO cake_order (id, delivery_date, is_delivery, order_date, price, status, cake_id, customer_id) VALUES (3, '2022-12-05', b'1', '2022-11-26', 90.00, 'RECEIVED', 3, 2);