-- ============================================================
--  Flyway Migration: V3__create_project_stage_progression.sql
--  Description: Tracks stage progression for each project
--  Author: Ninad
-- ============================================================
CREATE TABLE status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,   -- e.g. 'NOT_STARTED', 'IN_PROGRESS', 'COMPLETED', 'ON_HOLD', 'BLOCKED'
    display_name VARCHAR(100) NOT NULL, -- e.g. 'Not Started', 'In Progress', etc.
    description VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE
);

-- Seed data (optional for Flyway V3)
INSERT INTO status (code, display_name, description) VALUES
('NOT_STARTED', 'Not Started', 'Stage not yet started'),
('IN_PROGRESS', 'In Progress', 'Stage is currently being worked on'),
('COMPLETED', 'Completed', 'Stage has been completed successfully'),
('ON_HOLD', 'On Hold', 'Stage is temporarily paused'),
('BLOCKED', 'Blocked', 'Stage is blocked due to dependency or issue');


CREATE TABLE project_stage_progression (
    id INT AUTO_INCREMENT PRIMARY KEY,

    project_id INT NOT NULL,
    stage_id INT NOT NULL,
    status_id INT NOT NULL,  -- references stage_status(id)
    
    remarks VARCHAR(500),

    started_on TIMESTAMP NULL DEFAULT NULL,
    completed_on TIMESTAMP NULL DEFAULT NULL,

    completed_by INT NULL,   -- references user(id)

    created_by VARCHAR(100),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_progression_project 
        FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,

    CONSTRAINT fk_progression_stage 
        FOREIGN KEY (stage_id) REFERENCES stage(id),

    CONSTRAINT fk_progression_status 
        FOREIGN KEY (status_id) REFERENCES status(id),

    CONSTRAINT fk_progression_completed_by 
        FOREIGN KEY (completed_by) REFERENCES user(id)
);

