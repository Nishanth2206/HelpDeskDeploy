package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @Column(name = "status_change_from")
    private String statusChangeFrom;
    @Column(name = "status_change_to")
    private String statusChangeTo;

    private LocalDateTime timestamp;

    public History(){
        
    }

    public History(Long id, String comment, String statusChangeFrom, String statusChangeTo, LocalDateTime timestamp) {
        this.id = id;
        this.comment = comment;
        this.statusChangeFrom = statusChangeFrom;
        this.statusChangeTo = statusChangeTo;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatusChangeFrom() {
        return statusChangeFrom;
    }

    public void setStatusChangeFrom(String statusChangeFrom) {
        this.statusChangeFrom = statusChangeFrom;
    }

    public String getStatusChangeTo() {
        return statusChangeTo;
    }

    public void setStatusChangeTo(String statusChangeTo) {
        this.statusChangeTo = statusChangeTo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
