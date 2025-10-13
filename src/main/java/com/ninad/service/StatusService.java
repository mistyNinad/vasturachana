package com.ninad.service;


import com.ninad.dao.entity.Status;
import com.ninad.dao.entity.repo.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Optional<Status> getStatusById(int id) {
        return statusRepository.findById(id);
    }


    public Optional<Status> getStatusByCode(String code) {
        return statusRepository.findByCode(code.toUpperCase());
    }

}
