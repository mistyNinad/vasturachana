package com.ninad.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class ProjectPayment {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "project_id", nullable = false)
	    private Project project;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "payer_id", nullable = false)
	    private User payer;

	    @Column(nullable = false, precision = 12, scale = 2)
	    private BigDecimal amount;

	    @Column(name = "payment_date")
	    private LocalDateTime paymentDate;

	    @Column(name = "payment_mode")
	    private String paymentMode; // CASH, BANK_TRANSFER, etc.

	    @Column(name = "reference_number")
	    private String referenceNumber;

	    private String remarks;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "status_id")
	    private Status status;

	    private String createdBy;
	    private String updatedBy;

	    @Column(updatable = false)
	    private LocalDateTime createdOn = LocalDateTime.now();

	    private LocalDateTime updatedOn = LocalDateTime.now();

	    @PreUpdate
	    public void onUpdate() {
	        this.updatedOn = LocalDateTime.now();
	    }

	    // ===== Getters & Setters =====

	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public Project getProject() { return project; }
	    public void setProject(Project project) { this.project = project; }

	    public User getPayer() { return payer; }
	    public void setPayer(User payer) { this.payer = payer; }

	    public BigDecimal getAmount() { return amount; }
	    public void setAmount(BigDecimal amount) { this.amount = amount; }

	    public LocalDateTime getPaymentDate() { return paymentDate; }
	    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

	    public String getPaymentMode() { return paymentMode; }
	    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

	    public String getReferenceNumber() { return referenceNumber; }
	    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

	    public String getRemarks() { return remarks; }
	    public void setRemarks(String remarks) { this.remarks = remarks; }

	    public Status getStatus() { return status; }
	    public void setStatus(Status status) { this.status = status; }

	    public String getCreatedBy() { return createdBy; }
	    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

	    public String getUpdatedBy() { return updatedBy; }
	    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

	    public LocalDateTime getCreatedOn() { return createdOn; }
	    public LocalDateTime getUpdatedOn() { return updatedOn; }
}
