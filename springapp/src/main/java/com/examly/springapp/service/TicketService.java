package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Ticket;
import com.examly.springapp.repository.TicketRepository;

@Service

public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket>getAllTickets(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket>getTicketById(Long id){
        return ticketRepository.findById(id);
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    // public Ticket updateTicket(Long id,Ticket updatedTicket){
    //     return ticketRepository.findById(id).map(ticket -> {
    //         ticket.setemail(updatedTicket.getemail());
    //         ticket.setpasswordhash(updatedTicket.getpasswordhash());
    //         ticket.setname(updatedTicket.getname());
    //         ticket.setcreatedAt(updatedTicket.getcreatedAt());
    //     }).orElse(null);
    // }

    public boolean deleteTicket(Long id){
        if(ticketRepository.existsById(id)){
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
