package ticketbooking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticketbooking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;
    private List<User> usersList;

    private static final String USERS_PATH = "app/src/main/java/ticketbooking/localDb/users.json";
    private static final ObjectMapper objectMapper= new ObjectMapper();

    public UserBookingService(User user) throws IOException {
        this.user= user;
        File users = new File(USERS_PATH);
        usersList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }
}
