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

import com.examly.springapp.model.History;
import com.examly.springapp.service.HistoryService;

@RestController
@RequestMapping("/api/ticket-history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public List<History> getAllHistories() {
        return historyService.getAllHistories();
    }

    @GetMapping("/{id}")
    public Optional<History> getHistoryById(@PathVariable("id") Long historyId) {
        return historyService.getHistoryById(historyId);
    }

    @PostMapping
    public History createHistory(@RequestBody History history) {
        return historyService.createHistory(history);
    }

    @PutMapping("/{id}")
    public History updateHistory(@PathVariable("id") Long historyId, @RequestBody History history) {
        return historyService.updateHistory(historyId, history);
    }

    @DeleteMapping("/{id}")
    public String deleteHistory(@PathVariable("id") Long historyId) {
        boolean isDeleted = historyService.deleteHistory(historyId);
        return isDeleted ? "History deleted successfully!" : "History not found!";
    }
}
/*{
  "comment": "Initial ticket created",
  "statusChangeFrom": "Open",
  "statusChangeTo": "In Progress",
  "timestamp": "2025-08-07T14:30:00"
}
 */
