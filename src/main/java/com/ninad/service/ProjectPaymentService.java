package com.ninad.service;

import com.ninad.dao.entity.*;
import com.ninad.dao.entity.repo.*;
import com.ninad.to.PaymentTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectPaymentService {

    private final ProjectPaymentRepository paymentRepo;
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;
    private final StatusRepository statusRepo;

    public ProjectPaymentService(ProjectPaymentRepository paymentRepo,
                                 ProjectRepo projectRepo,
                                 UserRepo userRepo,
                                 StatusRepository statusRepo) {
        this.paymentRepo = paymentRepo;
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
        this.statusRepo = statusRepo;
    }

    @Transactional
    public ProjectPayment createPayment(int projectId, int payerId, BigDecimal amount,
                                        String mode, String refNo, String remarks, String statusCode) {

        Project project = projectRepo.findById(projectId);

        User payer = userRepo.findById(payerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Status status = statusRepo.findByCode(statusCode)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        ProjectPayment payment = new ProjectPayment();
        payment.setProject(project);
        payment.setPayer(payer);
        payment.setAmount(amount);
        payment.setPaymentMode(mode);
        payment.setReferenceNumber(refNo);
        payment.setRemarks(remarks);
        payment.setStatus(status);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepo.save(payment);
    }

    public List<ProjectPayment> getPaymentsByProject(int projectId) {
        Project project = projectRepo.findById(projectId);
        return paymentRepo.findByProject(project);
    }

    public Optional<ProjectPayment> getPaymentById(int id) {
        return paymentRepo.findById(id);
    }

    @Transactional
    public void deletePayment(int id) {
        paymentRepo.deleteById(id);
    }
    
    public List<PaymentTO> getPaymentsByProjet(int projectId) {
        return paymentRepo.findByProjectId(projectId)
                .stream()
                .map(this::convertToTO)
                .toList();
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
