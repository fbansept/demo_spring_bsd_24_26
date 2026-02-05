package edu.ban7.demo_spring_bsd_24_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.view.AppUserView;
import edu.ban7.demo_spring_bsd_24_26.view.TicketView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonView({TicketView.class})
    protected String content;

    @ManyToOne(optional = false)
    @JsonView({TicketView.class})
    protected AppUser creator;

    @ManyToOne(optional = false)
    protected Ticket ticket;
}
