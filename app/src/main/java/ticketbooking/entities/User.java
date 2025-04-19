package ticketbooking.entities;
import java.util.*;


public class User{
    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketsBooked;
    private String userId;
}