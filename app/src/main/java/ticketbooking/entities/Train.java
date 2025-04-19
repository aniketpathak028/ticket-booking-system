package ticketbooking.entities;

import java.sql.Time;
import java.util.*;

public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, Time> stationTime;
    private List<String> stations;
}
