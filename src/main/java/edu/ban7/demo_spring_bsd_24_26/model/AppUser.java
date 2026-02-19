package edu.ban7.demo_spring_bsd_24_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.view.AppUserView;
import edu.ban7.demo_spring_bsd_24_26.view.TicketView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({AppUserView.class, TicketView.class})
    protected Integer id;

    @JsonView({AppUserView.class, TicketView.class})
    protected String email;

    protected String password;

    @OneToMany(mappedBy = "creator")
    @JsonView(AppUserView.class)
    protected List<Ticket> createdtickets;

    protected boolean admin;

}
