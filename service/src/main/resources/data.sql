-- Initialize Store Data
INSERT INTO stores (address, country, state, city, zip_code, name, category) VALUES
('1234 Maple Street', 'US', 'TX', 'Houston', '77044', 'CVS', '{Health, Food}'),
('5678 Oak Avenue', 'US', 'TX', 'Houston', '77044', 'Walgreens', '{Health, Food}'),
('9101 Pine Road', 'US', 'TX', 'Houston', '77044', 'Walmart', '{Health, Food}'),
('2345 Elm Drive', 'US', 'TX', 'Houston', '77044', 'JCPenny', '{Clothing}'),
('6789 Cedar Lane', 'US', 'TX', 'Houston', '77044', 'Kroger', '{Health, Food}'),
('3456 Birch Boulevard', 'US', 'TX', 'Houston', '77044', 'HEB', '{Health, Food}'),
('7890 Spruce Way', 'US', 'TX', 'Houston', '77044', 'Target', '{Clothing, Food}'),
('4567 Redwood Circle', 'US', 'TX', 'Houston', '77044', 'Costco', '{Bulk, Food}'),
('8901 Willow Street', 'US', 'TX', 'Houston', '77044', 'Rite Aid', '{Health}');


-- Initialize Store Inventory Data
INSERT INTO store_inventory (store_id, item_name, price, quantity) VALUES
((SELECT id FROM stores WHERE name = 'CVS'), 'Toothpaste', 3.99, 39),
((SELECT id FROM stores WHERE name = 'CVS'), 'Paper Towels', 7.99, 10),
((SELECT id FROM stores WHERE name = 'CVS'), 'Painkillers', 10.99, 23),
((SELECT id FROM stores WHERE name = 'CVS'), 'Soap', 2.99, 29),
((SELECT id FROM stores WHERE name = 'CVS'), 'Water (64 oz)', 12.99, 5),
((SELECT id FROM stores WHERE name = 'CVS'), 'Batteries (12 pack)', 23.99, 15);