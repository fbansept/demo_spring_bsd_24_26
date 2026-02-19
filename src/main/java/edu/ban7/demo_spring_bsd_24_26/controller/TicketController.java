package edu.ban7.demo_spring_bsd_24_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.dao.TicketDao;
import edu.ban7.demo_spring_bsd_24_26.model.Ticket;
import edu.ban7.demo_spring_bsd_24_26.security.IsAdmin;
import edu.ban7.demo_spring_bsd_24_26.security.IsUser;
import edu.ban7.demo_spring_bsd_24_26.view.TicketView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TicketController {

    @Autowired
    TicketDao ticketDao;

    @GetMapping("/ticket/list")
    @JsonView(TicketView.class)
    @IsUser
    List<Ticket> getAll() {
        return ticketDao.findAll();
    }

    @GetMapping("/ticket/{id}")
    @JsonView(TicketView.class)
    @IsUser
    ResponseEntity<Ticket> get(@PathVariable int id) {

        Optional<Ticket> optionalTicket = ticketDao.findById(id);

        //si le ticket n'existe pas
        if(optionalTicket.isEmpty()) {
            //return ResponseEntity.notFound();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //return ResponseEntity.ok(optionalTicket.get());
        return new ResponseEntity<>(optionalTicket.get(), HttpStatus.OK);

    }

    @PostMapping("/ticket")
    @IsUser
    ResponseEntity<Ticket> create(@RequestBody Ticket nouveauTicket) {
        
        ticketDao.save(nouveauTicket);
        
        return new ResponseEntity<>(nouveauTicket, HttpStatus.CREATED);
        
    }
    
    @DeleteMapping("/ticket/{id}")
    @IsAdmin
    ResponseEntity<Void> delete(@PathVariable int id) {
        
        Optional<Ticket> optionalTicket = ticketDao.findById(id);

        //si le ticket n'existe pas
        if(optionalTicket.isEmpty()) {
            //return ResponseEntity.notFound();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        ticketDao.deleteById(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }

    @PutMapping("/ticket/{id}")
    @IsAdmin
    ResponseEntity<Void> update(
            @RequestBody Ticket ticket, 
            @PathVariable int id) {
        
        //on force l'id du ticket à modifier à être l'id récupéré dans l'url
        ticket.setId(id);

        Optional<Ticket> optionalTicket = ticketDao.findById(id);

        //si le ticket n'existe pas
        if(optionalTicket.isEmpty()) {
            //return ResponseEntity.notFound();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        ticketDao.save(ticket);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }

}
