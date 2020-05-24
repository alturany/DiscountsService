INSERT INTO USER_TYPE (id, type, discount_percent) values
    (1, 'EMPLOYEE', 0.3),
    (2, 'AFFILIATE', 0.1),
    (3, 'NORMAL', 0.05);

INSERT INTO USER (id, join_date, type_id) values
    (1, '2012-09-17', 1),
    (2, '2012-09-17', 2),
    (3, '2012-09-17', 3),
    (4, '2018-12-14', 3);

INSERT INTO BILL (id, user_id) values
    (1, 1),
    (2, 2),
    (3, 3),
  --(4, 4),
    (5, 1),
    (6, 4);

INSERT INTO ITEM (id, price, category) values
    (1, 10.0, 'CLOTHING'),
    (2, 24.99, 'CLEANING'),
    (3, 5.0, 'GROCERIES'),
    (4, 299, 'ELECTRONICS');

INSERT INTO BILL_ITEM (id, bill_id, item_id) values
    (1, 1, 1), -- Employee bought clothing
    (2, 2, 1), -- Affiliate bought clothing
    (3, 5, 3), -- Employee bought groceries
    (4, 6, 4), -- Normal new customer bought electronics
    (5, 3, 4), -- Normal old customer bought electronics &
    (6, 3, 1); -- Clothing
