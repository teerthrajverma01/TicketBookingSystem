package entity;

public class Movie extends Event {

    private String genre;
    private String actorName;
    private String actressName;

    
    public Movie(String eventName, String eventDate, String eventTime, Venue venue, int totalSeats, int availableSeats,
			double ticketPrice, EventType eventType) {
		super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
	}

	public Movie(String eventName, String eventDate, String eventTime, Venue venue,
                 int totalSeats, int availableSeats, double ticketPrice, EventType eventType,
                 String genre, String actorName, String actressName) {
        super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
        this.genre = genre;
        this.actorName = actorName;
        this.actressName = actressName;
    }

    @Override
    public double calculateTotalRevenue() {
        int numTickets = getTotalSeats() - getAvailableSeats();
        return getTicketPrice() * numTickets;
    }

    @Override
    public int getBookedNoOfTickets() {
        return getTotalSeats() - getAvailableSeats();
    }

    @Override
    public void bookTickets(int numTickets) {
        setAvailableSeats(getAvailableSeats() - numTickets);
        System.out.println("Tickets Booked");
    }

    @Override
    public void cancelBooking(int numTickets) {
        setAvailableSeats(getAvailableSeats() + numTickets);
        System.out.println("Tickets Cancelled");
    }

    @Override
    public void displayEventDetails() {
        System.out.println("Movie Name: " + getEventName());
        System.out.println("Movie Genre: " + genre);
        System.out.println("Movie Actor: " + actorName);
        System.out.println("Movie Actress: " + actressName);
    }

    @Override
    public void calculateBookingCost(int numTickets) {
        double totalCost = numTickets * getTicketPrice();
        System.out.println("Total Cost: $" + totalCost);
    }
}

