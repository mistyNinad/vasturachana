-- ============================================================
--  Flyway Migration: V2__create_project_tables.sql
--  Description: Creates Project, Land Details, Blueprint Details, and Stage tables
--  Author: Ninad
-- ============================================================

-- ============================================================
-- STAGE TABLE
-- ============================================================
CREATE TABLE stage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parent_id INT DEFAULT NULL,
    name VARCHAR(100) NOT NULL,
    display_name VARCHAR(150),
    description VARCHAR(255),
    order_index INT,
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_stage_parent FOREIGN KEY (parent_id) REFERENCES stage(id) ON DELETE SET NULL
);

-- ============================================================
-- LAND DETAILS TABLE
-- ============================================================
CREATE TABLE land_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    zone VARCHAR(100),
    ubl VARCHAR(100),
    plot_no VARCHAR(50),
    land_type VARCHAR(50),
    area_type VARCHAR(50),
    fsi DECIMAL(4,2),
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================================
-- BLUEPRINT DETAILS TABLE
-- ============================================================
CREATE TABLE blueprint_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pmay_scheme BOOLEAN DEFAULT FALSE,
    type VARCHAR(50),
    nationalized_bank_loan BOOLEAN DEFAULT FALSE,
    proposal_code VARCHAR(50),
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================================
-- PROJECT TABLE
-- ============================================================
CREATE TABLE project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    overview TEXT,
    location VARCHAR(255),
    estimated_cost DECIMAL(12,2),
    current_stage_id INT,
    user_id INT,
    land_details_id INT UNIQUE,
    blueprint_details_id INT UNIQUE,
    status BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_project_stage FOREIGN KEY (current_stage_id) REFERENCES stage(id),
    CONSTRAINT fk_project_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_project_land FOREIGN KEY (land_details_id) REFERENCES land_details(id),
    CONSTRAINT fk_project_blueprint FOREIGN KEY (blueprint_details_id) REFERENCES blueprint_details(id)
);
INSERT INTO `stage` (`id`, `name`, `display_name`, `order_index`) VALUES ('1', 'Discussion', 'Discussion', '1');
INSERT INTO `stage` (`id`, `name`, `display_name`, `order_index`) VALUES ('2', 'Requirements', 'Requirements Gathering', '2');
INSERT INTO `stage` (`id`, `name`, `display_name`, `order_index`) VALUES ('3', 'Plan', 'Planning', '3');

INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('4', '3', 'design', 'Design', '1');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('5', '3', 'design revision', 'Design Revision', '2');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('6', '3', 'verification', 'Verification', '3');

INSERT INTO `stage` (`id`, `name`, `display_name`, `order_index`) VALUES ('7', 'sanctioning', 'Sanctioning', '4');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('8', '7', 'Document verification', 'Document Verification', '1');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('9', '7', 'BPMS project creation', 'BPMS Project Creation', '2');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('10', '7', 'TP Client tracing', 'TP Client Tracing', '3');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('11', '7', 'BPMS Submission', 'BPMS Submission', '4');

INSERT INTO `stage` (`id`, `name`, `display_name`, `order_index`) VALUES ('12', 'Structural Drawing', 'Structural Drawing', '5');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('13', '12', 'column & footings', 'Column & Footings', '1');
INSERT INTO `stage` (`id`, `parent_id`, `name`, `display_name`, `order_index`) VALUES ('14', '12', 'slab', 'Slab Drawing', '2');

INSERT INTO `stage` (`id`, `name`, `display_name`, `order_index`) VALUES ('15', '3D', '3D Design', '6');
