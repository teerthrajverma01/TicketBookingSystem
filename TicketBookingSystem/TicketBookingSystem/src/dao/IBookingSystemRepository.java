package dao;

import java.util.ArrayList;
import entity.Event;
import entity.Venue;

public interface IBookingSystemRepository {
	
	String create_event(String event_name, String date,String time, int total_seats,float ticket_price, 
			String event_type, Venue venu);
	
    ArrayList<Event> getEventDetails();

    int getAvailableNoOfTickets(int event_id);

    float calculateBookingCost(int numTickets,int event_id);

    void bookTickets(String eventName, int numTickets, int customerId);

    void cancelBooking(int bookingId);

    void getBookingDetails(int bookingId);

}

