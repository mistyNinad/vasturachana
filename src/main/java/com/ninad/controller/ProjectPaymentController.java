package com.ninad.controller;

import com.ninad.dao.entity.ProjectPayment;
import com.ninad.service.ProjectPaymentService;
import com.ninad.to.PaymentTO;
import com.ninad.to.ProjectPaymentSummaryTO;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectPaymentController {

    private final ProjectPaymentService paymentService;

    public ProjectPaymentController(ProjectPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 🔹 Create a new payment
    @PostMapping
    public PaymentTO createPayment(@RequestBody PaymentTO request) {
    	ProjectPayment payment =  paymentService.createPayment(
                request.getProjectId(),
                request.getPayerId(),
                request.getAmount(),
                request.getPaymentMode(),
                request.getReferenceNumber(),
                request.getRemarks(),
                request.getStatusCode() != null ? request.getStatusCode() : "COMPLETED"
        );
    	return convertToTO(payment);
    }

    // 🔹 Get all payments for a given project
    @GetMapping("/project/{projectId}")
    public ProjectPaymentSummaryTO getPaymentsByProject(@PathVariable int projectId) {
        //return paymentService.getPaymentsByProjet(projectId);
    	return paymentService.getProjectPaymentSummaryTO(projectId);
    }
 

    // 🔹 Get a single payment by ID
    @GetMapping("/{id}")
    public ProjectPayment getPaymentById(@PathVariable int id) {
        return paymentService.getPaymentById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    // 🔹 Delete payment
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
    }
    
    private PaymentTO convertToTO(ProjectPayment payment) {
        PaymentTO to = new PaymentTO();
        to.setProjectId(payment.getProject().getId());
        to.setPayerId(payment.getPayer().getId());
        to.setAmount(payment.getAmount());
        to.setPaymentMode(payment.getPaymentMode());
        to.setReferenceNumber(payment.getReferenceNumber());
        to.setRemarks(payment.getRemarks());
        to.setStatusCode(payment.getStatus().getCode()); // ✅ assuming Status entity has `code`
        return to;
    }
}
