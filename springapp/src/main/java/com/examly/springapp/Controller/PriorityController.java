package com.examly.springapp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Priority;
import com.examly.springapp.service.PriorityService;

@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    @GetMapping
    public List<Priority> getAllPriorities() {
        return priorityService.getAllPriorities();
    }

    @GetMapping("/{id}")
    public Optional<Priority> getPriorityById(@PathVariable("id") Long priorityId) {
        return priorityService.getPriorityById(priorityId);
    }

    @PostMapping
    public Priority createPriority(@RequestBody Priority priority) {
        return priorityService.createPriority(priority);
    }

    @PutMapping("/{id}")
    public Priority updatePriority(@PathVariable("id") Long priorityId, @RequestBody Priority priority) {
        return priorityService.updatePriority(priorityId, priority);
    }

    @DeleteMapping("/{id}")
    public String deletePriority(@PathVariable("id") Long priorityId) {
        boolean isDeleted = priorityService.deletePriority(priorityId);
        return isDeleted ? "Priority deleted successfully!" : "Priority not found!";
    }
}

