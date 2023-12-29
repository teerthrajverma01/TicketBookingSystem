package entity;


public class Concert extends Event {

    public enum ConcertType { THEATRICAL, CLASSICAL, ROCK, RECITAL }

    private String artist;
    private ConcertType concertType;

    
    
    public Concert(String eventName, String eventDate, String eventTime, Venue venue, int totalSeats,
			int availableSeats, double ticketPrice, EventType eventType) {
		super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
	}

	public Concert(String eventName, String eventDate, String eventTime, Venue venue,
                   int totalSeats, int availableSeats, double ticketPrice, EventType eventType,
                   String artist, ConcertType concertType) {
        super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
        this.artist = artist;
        this.concertType = concertType;
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
        System.out.println("Concert Name: " + getEventName());
        System.out.println("Artist Name: " + artist);
        System.out.println("Concert Type: " + concertType);
    }

    @Override
    public void calculateBookingCost(int numTickets) {
        double totalCost = numTickets * getTicketPrice();
        System.out.println("Total Cost: $" + totalCost);
    }
}
