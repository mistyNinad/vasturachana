-- ============================================================
--  Flyway Migration: V1__create_user_role_and_userdetails_tables.sql
--  Description: Base schema for user, role, and user_details
--  Author: Ninad
--  Created: 2025-10-04
-- ============================================================

-- ============================================================
-- ROLE TABLE
-- ============================================================
CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    display_name VARCHAR(100),
    status BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Seed default roles
INSERT INTO role (name, display_name, status, created_by)
VALUES 
('ADMIN', 'Administrator', TRUE, 'system'),
('MANAGER', 'Manager', TRUE, 'system'),
('USER', 'Standard User', TRUE, 'system');

-- ============================================================
-- USER TABLE
-- ============================================================
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255) NOT NULL,
    mobile_number DECIMAL(12,0) UNIQUE NOT NULL,
    adhar_number DECIMAL(20,0) UNIQUE,
    pan_number VARCHAR(10) UNIQUE,
    role_id INT,
    status BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role(id)
);

-- ============================================================
-- USER DETAILS TABLE
-- ============================================================
CREATE TABLE user_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    email VARCHAR(100),
    whatsapp_number DECIMAL(12,0),
    address VARCHAR(255),
    notes VARCHAR(255),
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_userdetails_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

-- ============================================================
-- Sample Data
-- ============================================================
INSERT INTO user (name, username, password, mobile_number, role_id, status, created_by)
VALUES 
('System Admin', 'admin', '$2a$10$hashed_password_here', 9999999999, 1, TRUE, 'system');
