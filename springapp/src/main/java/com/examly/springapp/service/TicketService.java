package com.examly.springapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Ticket;
import com.examly.springapp.repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public Ticket createTicket(Ticket ticket) {
        if (ticket.getCreatedAt() == null) {
            ticket.setCreatedAt(LocalDateTime.now());
        }
        return ticketRepository.save(ticket);
    }
    public Ticket updateTicket(Long ticketId, Ticket updatedTicket) {
        return ticketRepository.findById(ticketId).map(ticket -> {
            if (updatedTicket.getTitle() != null) {
                ticket.setTitle(updatedTicket.getTitle());
            }
            if (updatedTicket.getDescription() != null) {
                ticket.setDescription(updatedTicket.getDescription());
            }
            if (updatedTicket.getCategoryId() != null) {
                ticket.setCategoryId(updatedTicket.getCategoryId());
            }
            if (updatedTicket.getPriorityId() != null) {
                ticket.setPriorityId(updatedTicket.getPriorityId());
            }
            if (updatedTicket.getSubmitterId() != null) {
                ticket.setSubmitterId(updatedTicket.getSubmitterId());
            }
            if (updatedTicket.getAssignedAgentId() != null) {
                ticket.setAssignedAgentId(updatedTicket.getAssignedAgentId());
            }
            if (updatedTicket.getCreatedAt() != null) {
                ticket.setCreatedAt(updatedTicket.getCreatedAt());
            }
            if (updatedTicket.getResolvedAt() != null) {
                ticket.setResolvedAt(updatedTicket.getResolvedAt());
            }
            return ticketRepository.save(ticket);
        }).orElse(null);
    }

    public boolean deleteTicket(Long ticketId) {
        if (ticketRepository.existsById(ticketId)) {
            ticketRepository.deleteById(ticketId);
            return true;
        }
        return false;
    }
}
