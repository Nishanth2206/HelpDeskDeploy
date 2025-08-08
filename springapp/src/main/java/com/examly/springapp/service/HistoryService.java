package com.examly.springapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.History;
import com.examly.springapp.repository.HistoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    public Optional<History> getHistoryById(Long historyId) {
        return historyRepository.findById(historyId);
    }

    public History createHistory(History history) {
        if (history.getTimestamp() == null) {
            history.setTimestamp(LocalDateTime.now());
        }
        return historyRepository.save(history);
    }

    public History updateHistory(Long historyId, History updatedHistory) {
        return historyRepository.findById(historyId).map(history -> {
            if (updatedHistory.getComment() != null) {
                history.setComment(updatedHistory.getComment());
            }
            if (updatedHistory.getStatusChangeFrom() != null) {
                history.setStatusChangeFrom(updatedHistory.getStatusChangeFrom());
            }
            if (updatedHistory.getStatusChangeTo() != null) {
                history.setStatusChangeTo(updatedHistory.getStatusChangeTo());
            }
            if (updatedHistory.getTimestamp() != null) {
                history.setTimestamp(updatedHistory.getTimestamp());
            }
            return historyRepository.save(history);
        }).orElse(null);
    }

    public boolean deleteHistory(Long historyId) {
        if (historyRepository.existsById(historyId)) {
            historyRepository.deleteById(historyId);
            return true;
        }
        return false;
    }
}

