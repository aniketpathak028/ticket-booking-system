package ticketbooking.entities;
import lombok.Data;

import java.util.*;

@Data
public class User{
    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

    public void printTickets() {
        ticketsBooked.forEach(ticket -> {
            System.out.println(ticket.ticketInfo());
        });
    }
}