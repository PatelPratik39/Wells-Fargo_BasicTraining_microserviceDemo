-- Create the database (optional, run this only once)
CREATE DATABASE productdb;

-- Connect to productdb (run this separately if you're not already connected)
-- \c productdb

-- Drop table if it exists (for reset purposes)
DROP TABLE IF EXISTS product;

-- -- Create the product table
-- CREATE TABLE product (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(100) NOT NULL,
--     description TEXT,
--     price NUMERIC(10,2) NOT NULL,
--     quantity INT NOT NULL,
--     available BOOLEAN DEFAULT TRUE
-- );
--
-- -- Insert sample products
-- INSERT INTO product (name, description, price, quantity, available) VALUES
-- ('iPhone 15 Pro', 'Latest Apple flagship smartphone', 1199.99, 10, TRUE),
-- ('Samsung Galaxy S24', 'Flagship Android phone with new features', 999.99, 15, TRUE),
-- ('Dell XPS 15', 'High-end developer laptop', 1799.00, 5, TRUE),
-- ('Sony WH-1000XM5', 'Noise Cancelling Headphones', 349.99, 20, TRUE),
-- ('Amazon Echo Dot', 'Smart speaker with Alexa', 49.99, 50, TRUE);