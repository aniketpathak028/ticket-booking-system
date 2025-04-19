package ticketbooking.entities;

import lombok.Data;

import java.sql.Time;
import java.util.*;

@Data
public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, String> stationTime;
    private List<String> stations;
}
