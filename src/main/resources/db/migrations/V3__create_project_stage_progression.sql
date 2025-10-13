-- ============================================================
--  Flyway Migration: V3__create_project_stage_progression.sql
--  Description: Tracks stage progression for each project
--  Author: Ninad
-- ============================================================

CREATE TABLE project_stage_progression (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    stage_id INT NOT NULL,
    status VARCHAR(50) DEFAULT 'IN_PROGRESS',  -- e.g. IN_PROGRESS, COMPLETED, ON_HOLD
    remarks VARCHAR(255),
    started_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_on TIMESTAMP NULL,
    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_progression_project FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
    CONSTRAINT fk_progression_stage FOREIGN KEY (stage_id) REFERENCES stage(id)
);
