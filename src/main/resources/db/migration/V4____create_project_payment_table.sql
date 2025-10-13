-- ============================================================
-- PROJECT PAYMENT TABLE
-- ============================================================

CREATE TABLE project_payment (
    id INT AUTO_INCREMENT PRIMARY KEY,

    project_id INT NOT NULL,
    payer_id INT NOT NULL,

    amount DECIMAL(12,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    payment_mode VARCHAR(50),              -- e.g. CASH, BANK_TRANSFER, CHEQUE, UPI
    reference_number VARCHAR(100),         -- transaction id, cheque number, etc.
    remarks VARCHAR(255),

    status_id INT,                         -- link to status table

    created_by VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_project FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
    CONSTRAINT fk_payment_user FOREIGN KEY (payer_id) REFERENCES user(id),
    CONSTRAINT fk_payment_status FOREIGN KEY (status_id) REFERENCES status(id)
);
