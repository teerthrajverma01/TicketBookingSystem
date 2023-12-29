package entity;

public class Sports extends Event {

    private String sportName;
    private String teamsName;

    
    public Sports(String eventName, String eventDate, String eventTime, Venue venue, int totalSeats, int availableSeats,
			double ticketPrice, EventType eventType) {
		super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
	}

	public Sports(String eventName, String eventDate, String eventTime, Venue venue,
                  int totalSeats, int availableSeats, double ticketPrice, EventType eventType,
                  String sportName, String teamsName) {
        super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
        this.sportName = sportName;
        this.teamsName = teamsName;
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
        System.out.println("Sport Name: " + sportName);
        System.out.println("Teams Name: " + teamsName);
    }

    @Override
    public void calculateBookingCost(int numTickets) {
        double totalCost = numTickets * getTicketPrice();
        System.out.println("Total Cost: $" + totalCost);
    }
}
