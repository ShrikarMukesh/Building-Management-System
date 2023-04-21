INSERT INTO testdb.building (name, address, out_of_order) VALUES
('Building A', '123 Main St, City A', false),
('Building B', '456 Elm St, City B', true),
('Building C', '789 Oak St, City C', false);

INSERT INTO testdb.company (name, building_id, num_emp) VALUES
('Company 1', 1, 10),
('Company 2', 2, 20),
('Company 3', 3, 30);

INSERT INTO testdb.employee (name, company_id) VALUES
('Employee 1', 1),
('Employee 2', 1),
('Employee 3', 1),
('Employee 4', 2),
('Employee 5', 2),
('Employee 6', 2),
('Employee 7', 3),
('Employee 8', 3),
('Employee 9', 3),
('Employee 10', 3);
