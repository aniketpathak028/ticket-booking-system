package ticketbooking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticketbooking.entities.Ticket;
import ticketbooking.entities.User;
import ticketbooking.utils.UserServiceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserBookingService {
    private User user;
    private List<User> users;

    private static final String FILE_PATH = "app/src/main/java/ticketbooking/localDb/users.json";
    private static final ObjectMapper objectMapper= new ObjectMapper();
    private static final File usersFile = new File(FILE_PATH);

    public UserBookingService(User user){
        try{
            this.user= user;
            this.users = objectMapper.readValue(usersFile, new TypeReference<List<User>>() {}); // DESERIALIZATION
        }catch (IOException ex){
            System.out.println("something went bad :/" + ex);
        }
    }

    public boolean loginUser(){
        Optional<User> foundUser = this.users.stream().filter(
                user -> Objects.equals(user.getName(), this.user.getName())
                        && UserServiceUtils.checkPassword(user.getPassword(), this.user.getHashPassword())).findFirst();
        return foundUser.isPresent();
    }

    public boolean signUp(User user){
        try{
            this.users.add(user);
            objectMapper.writeValue(usersFile, this.users); // SERIALIZATION (object -> json)
            return true;
        }catch (IOException ex){
            System.out.println("something went bad :/" + ex);
            return false;
        }
    }

    public void fetchTickets() {
        this.user.printTickets();
    }

    public boolean cancelTicket(String ticketId){
        // delete the ticket from user's ticket list
        this.user.setTicketsBooked(
                this.user.getTicketsBooked().stream()
                        .filter(ticket -> !Objects.equals(ticket.getTicketId(), ticketId))
                        .collect(Collectors.toList()));
        // write the user back to the userList
        this.users = this.users.stream().map(user -> {
            if(user.getUserId().equals(this.user.getUserId())){
                user.setTicketsBooked(this.user.getTicketsBooked());
            }
            return user;
        }).collect(Collectors.toList());

        try{
            objectMapper.writeValue(usersFile, this.users); // SERIALIZATION (object -> json)
            return true;
        }catch (IOException ex){
            System.out.println("something went bad :/" + ex);
            return false;
        }
    }

}
