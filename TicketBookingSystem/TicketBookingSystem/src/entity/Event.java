package entity;

public abstract class Event {

    public enum EventType { MOVIE, SPORTS, CONCERT }

    private String eventName;
    private String eventDate;
    private String eventTime;
    private Venue venue;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private EventType eventType;

    public Event(String eventName, String eventDate, String eventTime, Venue venue,
                 int totalSeats, int availableSeats, double ticketPrice, EventType eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public abstract double calculateTotalRevenue();

    public abstract int getBookedNoOfTickets();

    public abstract void bookTickets(int numTickets);

    public abstract void cancelBooking(int numTickets);

    public abstract void displayEventDetails();

    public abstract void calculateBookingCost(int numTickets);

    @Override
    public String toString() {
        return " [ Event Name: " + eventName +
               ", Event Date: " + eventDate + 
               ", Event Time: " + eventTime + 
               ", Venue Name: " + venue.getVenue_name() + 
               ", Total Seats: " + totalSeats + 
               ", Available Seats: " + availableSeats +
               ", Ticket Price: $" + ticketPrice + 
               ", Event Type: " + eventType +" ]";
    }
}
