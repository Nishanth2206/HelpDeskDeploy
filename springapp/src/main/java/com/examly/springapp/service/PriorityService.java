package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Priority;
import com.examly.springapp.repository.PriorityRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    public Optional<Priority> getPriorityById(Long priorityId) {
        return priorityRepository.findById(priorityId);
    }

    public Priority createPriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    public Priority updatePriority(Long priorityId, Priority updatedPriority) {
        return priorityRepository.findById(priorityId).map(priority -> {
            if (updatedPriority.getName() != null) {
                priority.setName(updatedPriority.getName());
            }
            return priorityRepository.save(priority);
        }).orElse(null);
    }

    public boolean deletePriority(Long priorityId) {
        if (priorityRepository.existsById(priorityId)) {
            priorityRepository.deleteById(priorityId);
            return true;
        }
        return false;
    }
}
