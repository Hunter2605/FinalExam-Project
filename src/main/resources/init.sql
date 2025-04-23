CREATE DATABASE IF NOT EXISTS phone_repair;
USE phone_repair;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    user_type VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS parts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS repairs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    device_type VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    manufacturer VARCHAR(50) NOT NULL,
    description TEXT,
    quantity INT NOT NULL,
    repair_cost DECIMAL(10,2) NOT NULL,
    required_materials TEXT,
    repair_days INT NOT NULL,
    client_id INT,
    status VARCHAR(20) DEFAULT 'pending',
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (client_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS ordered_parts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    part_id INT NOT NULL,
    quantity INT NOT NULL,
    order_date DATE,
    status VARCHAR(20) DEFAULT 'ordered',
    FOREIGN KEY (part_id) REFERENCES parts(id)
    );

CREATE TABLE IF NOT EXISTS delivered_parts (
    id INT AUTO_INCREMENT PRIMARY KEY,part_id INT NOT NULL,
    quantity INT NOT NULL,
    delivery_date DATE,
    supplier_id INT,
    FOREIGN KEY (part_id) REFERENCES parts(id),
    FOREIGN KEY (supplier_id) REFERENCES users(id)
    );


INSERT INTO users (username, password, user_type) VALUES
    ('client', 'pa$$w0rd123', 'client'),
    ('repair', 'pa$$w0rd123', 'repair'),
    ('worker', 'worker123', 'worker'),
    ('supplier', '$upplier123', 'supplier');

INSERT INTO parts (name, quantity, price) VALUES
    ('Батарея', 10, 25.00),
    ('Дисплей', 15, 50.00),
    ('Процессор', 5, 100.00),
    ('Материнская плата', 8, 80.00),
    ('Оперативная память', 20, 30.00);