package com.ninad.to;

import java.math.BigDecimal;

public class PaymentTO {

	  private int projectId;
      private int payerId;
      private BigDecimal amount;
      private String paymentMode;
      private String referenceNumber;
      private String remarks;
      private String statusCode;

      // --- Getters & Setters ---
      public int getProjectId() { return projectId; }
      public void setProjectId(int projectId) { this.projectId = projectId; }

      public int getPayerId() { return payerId; }
      public void setPayerId(int payerId) { this.payerId = payerId; }

      public BigDecimal getAmount() { return amount; }
      public void setAmount(BigDecimal amount) { this.amount = amount; }

      public String getPaymentMode() { return paymentMode; }
      public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

      public String getReferenceNumber() { return referenceNumber; }
      public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

      public String getRemarks() { return remarks; }
      public void setRemarks(String remarks) { this.remarks = remarks; }

      public String getStatusCode() { return statusCode; }
      public void setStatusCode(String statusCode) { this.statusCode = statusCode; }
}
