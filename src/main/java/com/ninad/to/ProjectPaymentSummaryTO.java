package com.ninad.to;

import java.math.BigDecimal;
import java.util.List;

public class ProjectPaymentSummaryTO {
    private int projectId;
    private BigDecimal estimatedCost;
    private BigDecimal totalPaid;
    private BigDecimal percentCompleted;
    private BigDecimal dueAmount;
    private List<PaymentTO> payments;
    private BigDecimal totalDueAmount;
    

    // Getters & Setters
    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public BigDecimal getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(BigDecimal estimatedCost) { this.estimatedCost = estimatedCost; }

    public BigDecimal getTotalPaid() { return totalPaid; }
    public void setTotalPaid(BigDecimal totalPaid) { this.totalPaid = totalPaid; }

    public BigDecimal getDueAmount() { return dueAmount; }
    public void setDueAmount(BigDecimal dueAmount) { this.dueAmount = dueAmount; }

    public List<PaymentTO> getPayments() { return payments; }
    public void setPayments(List<PaymentTO> payments) { this.payments = payments; }
	public BigDecimal getPercentCompleted() {
		return percentCompleted;
	}
	public void setPercentCompleted(BigDecimal percentCompleted) {
		this.percentCompleted = percentCompleted;
	}
	public BigDecimal getTotalDueAmount() {
		return totalDueAmount;
	}
	public void setTotalDueAmount(BigDecimal totalDueAmount) {
		this.totalDueAmount = totalDueAmount;
	}
   
}

