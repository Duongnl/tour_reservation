INSERT INTO `customer`(`customer_id`, `account_id`, `relationship_id`, `relationship_name`, `customer_name`, `customer_type`, `sex`, `phone_number`, `email`, `address`, `birthday`, `visa_expire`, `status`)
VALUES
    (14, 1, 'Self', 'Customer 1', 'Individual', 1, '0123456789', 'customer1@email.com', 'Address 1', '2000-01-01', '2025-12-31', 1),
    (20, 2, 'Spouse', 'Customer 2', 'Individual', 0, '9876543210', 'customer2@email.com', 'Address 2', '1995-05-15', '2026-03-31', 1),
    (3, 3, 'Child', 'Customer 3', 'Individual', 1, '1234567890', 'customer3@email.com', 'Address 3', '2010-10-20', NULL, 1),
    (4, 4, 'Parent', 'Customer 4', 'Individual', 0, '9876543210', 'customer4@email.com', 'Address 4', '1970-03-12', NULL, 1),
    (5, 5, 'Sibling', 'Customer 5', 'Individual', 1, '1234567890', 'customer5@email.com', 'Address 5', '1998-07-25', NULL, 1),
    (6, 6, 'Self', 'Customer 6', 'Individual', 0, '9876543210', 'customer6@email.com', 'Address 6', '1985-11-10', '2027-09-30', 1),
    (7, 7, 'Spouse', 'Customer 7', 'Individual', 1, '1234567890', 'customer7@email.com', 'Address 7', '1990-02-28', '2028-06-15', 1),
    (8, 8, 'Child', 'Customer 8', 'Individual', 0, '9876543210', 'customer8@email.com', 'Address 8', '2015-04-05', NULL, 1),
    (9, 9, 'Parent', 'Customer 9', 'Individual', 1, '1234567890', 'customer9@email.com', 'Address 9', '1965-09-22', NULL, 1),
    (10, 10, 'Sibling', 'Customer 10', 'Individual', 0, '9876543210', 'customer10@email.com', 'Address 10', '1988-12-18', NULL, 1),
    (11, 11, 'Self', 'Customer 11', 'Individual', 1, '1234567890', 'customer11@email.com', 'Address 11', '2002-06-13', '2029-03-31', 1),
    (12, 12, 'Spouse', 'Customer 12', 'Individual', 0, '9876543210', 'customer12@email.com', 'Address 12', '1997-09-04', '2030-01-15', 1),
    (13, 13, 'Child', 'Customer 13', 'Individual', 1, '1234567890', 'customer13@email.com', 'Address 13', '2012-11-27', NULL, 1);


INSERT INTO `customer`(`account_id`, `relationship_id`, `relationship_name`, `customer_name`, `customer_type`, `sex`, `phone_number`, `email`, `address`, `birthday`, `visa_expire`, `status`)
VALUES
    (3, NULL, 'Child', 'Customer 3', 'Individual', 1, '1234567890', 'customer3@email.com', 'Address 3', '2010-10-20', NULL, 1),
    (4, NULL, 'Parent', 'Customer 4', 'Individual', 0, '9876543210', 'customer4@email.com', 'Address 4', '1970-03-12', NULL, 1),
    (5, NULL, 'Sibling', 'Customer 5', 'Individual', 1, '1234567890', 'customer5@email.com', 'Address 5', '1998-07-25', NULL, 1),
    (6, NULL, 'Self', 'Customer 6', 'Individual', 0, '9876543210', 'customer6@email.com', 'Address 6', '1985-11-10', '2027-09-30', 1),
    (7, NULL, 'Spouse', 'Customer 7', 'Individual', 1, '1234567890', 'customer7@email.com', 'Address 7', '1990-02-28', '2028-06-15', 1),
    (8, NULL, 'Child', 'Customer 8', 'Individual', 0, '9876543210', 'customer8@email.com', 'Address 8', '2015-04-05', NULL, 1),
    (9, NULL, 'Parent', 'Customer 9', 'Individual', 1, '1234567890', 'customer9@email.com', 'Address 9', '1965-09-22', NULL, 1),
    (10, NULL, 'Sibling', 'Customer 10', 'Individual', 0, '9876543210', 'customer10@email.com', 'Address 10', '1988-12-18', NULL, 1);

    (1, 1, 'Self', 'Customer 1', 'Individual', 1, '0123456789', 'customer1@email.com', 'Address 1', '2000-01-01', '2025-12-31', 1),
    (2, 2, 'Spouse', 'Customer 2', 'Individual', 0, '9876543210', 'customer2@email.com', 'Address 2', '1995-05-15', '2026-03-31', 1),

    (11, 11, 'Self', 'Customer 11', 'Individual', 1, '1234567890', 'customer11@email.com', 'Address 11', '2002-06-13', '2029-03-31', 1),
    (12, 12, 'Spouse', 'Customer 12', 'Individual', 0, '9876543210', 'customer12@email.com', 'Address 12', '1997-09-04', '2030-01-15', 1),
    (13, 13, 'Child', 'Customer 13', 'Individual', 1, '1234567890', 'customer13@email.com', 'Address 13', '2012-11-27', NULL, 1);

INSERT INTO `reserve`( `customer_id`, `schedule_id`, `employee_id`, `reserve_detail`, `adult_count`, `child_count`, `price`, `time`, `status`)
VALUES
    (70, 1, 1, 'Reservation Detail 1', 2, 1, 100, '2024-11-05 15:20:24', 1),
    (71, 2, 2, 'Reservation Detail 2', 3, 2, 150, '2024-11-06 10:00:00', 1),
    (72, 3, 3, 'Reservation Detail 3', 1, 0, 50, '2024-11-07 14:30:00', 1),
    (73, 4, 4, 'Reservation Detail 4', 4, 2, 200, '2024-11-08 18:00:00', 1),
    (71, 5, 5, 'Reservation Detail 5', 2, 1, 100, '2024-11-09 12:00:00', 1),
    (68, 6, 6, 'Reservation Detail 6', 3, 2, 150, '2024-11-10 09:30:00', 1),
    (68, 7, 1, 'Reservation Detail 7', 1, 0, 50, '2024-11-11 16:15:00', 1),
    (68, 8, 2, 'Reservation Detail 8', 4, 2, 200, '2024-11-12 20:00:00', 1),
    (68, 9, 3, 'Reservation Detail 9', 2, 1, 100, '2024-11-13 11:00:00', 1),
    (68, 10, 4, 'Reservation Detail 10', 3, 2, 150, '2024-11-14 08:45:00', 1),
    (68, 11, 5, 'Reservation Detail 11', 1, 0, 50, '2024-11-15 15:30:00', 1),
    (68, 12, 6, 'Reservation Detail 12', 4, 2, 200, '2024-11-16 19:00:00', 1),
    (68, 13, 1, 'Reservation Detail 13', 2, 1, 100, '2024-11-17 13:00:00', 1),
    (68, 14, 2, 'Reservation Detail 14', 3, 2, 150, '2024-11-18 10:15:00', 1),
    (68, 15, 3, 'Reservation Detail 15', 1, 0, 50, '2024-11-19 17:30:00', 1),
    (68, 16, 4, 'Reservation Detail 16', 4, 2, 200, '2024-11-20 21:00:00', 1),
    (68, 17, 5, 'Reservation Detail 17', 2, 1, 100, '2024-11-21 12:15:00', 1),
    (70, 18, 1, 'Reservation Detail 18', 3, 2, 150, '2024-11-22 09:00:00', 1),
    (70, 19, 2, 'Reservation Detail 19', 1, 0, 50, '2024-11-23 16:45:00', 1),
    (70, 20, 3, 'Reservation Detail 20', 4, 2, 200, '2024-11-24 20:30:00', 1);


