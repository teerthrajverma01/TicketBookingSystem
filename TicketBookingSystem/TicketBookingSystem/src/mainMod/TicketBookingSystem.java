package mainMod;

import java.util.ArrayList;
import java.util.Scanner;

import dao.BookingSystemRepositoryImpl;
import entity.Event;
import entity.Venue;
public class TicketBookingSystem  {

    public void startBookingSystem() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Event");
            System.out.println("2. Display Event Details");
            System.out.println("3. Book Tickets");
            System.out.println("4. Cancel Tickets");
            System.out.println("5. Get Available Seats");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Event Name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Time: ");
                    String time = scanner.nextLine();
                    System.out.print("Enter Total Seats: ");
                    int totalSeats = scanner.nextInt();
                    System.out.print("Enter Ticket Price: ");
                    float ticketPrice = scanner.nextFloat();
                    System.out.print("Enter Event Type (MOVIE, SPORTS, CONCERT): ");
                    String eventType = scanner.next().toUpperCase();
                    System.out.print("Enter Venue Name: ");
                    String venueName = scanner.next();
                    scanner.nextLine();
                    System.out.print("Enter Venue Address: ");
                    String venueAddress = scanner.nextLine();
                    Venue venue = new Venue(venueName, venueAddress);

                    BookingSystemRepositoryImpl obj=new BookingSystemRepositoryImpl();
                    obj.create_event(eventName,date,time,totalSeats,ticketPrice,eventType,venue);
                    System.out.println("Event created successfully");
                    break;

                case 2:
                	BookingSystemRepositoryImpl obj2=new BookingSystemRepositoryImpl();
                	ArrayList<Event> eventList=new ArrayList<>();
                	eventList=obj2.getEventDetails();
                	for(Event event:eventList) {
                		System.out.println(event);
                	}
                    break;

                case 3:
                    System.out.print("Enter Event Name: ");
                    String bookEventName = scanner.nextLine().trim();
                    System.out.print("Enter number of tickets to book : ");
                    int numTicket=scanner.nextInt();
                    System.out.print("Enter Customer Id : ");
                    int customerID=scanner.nextInt();
                    BookingSystemRepositoryImpl obj3=new BookingSystemRepositoryImpl();
                    obj3.bookTickets(bookEventName, numTicket, customerID);
                    break;

                case 4:
                    System.out.print("Enter Booking ID : ");
                    int bookingId=scanner.nextInt();
                    BookingSystemRepositoryImpl obj4=new BookingSystemRepositoryImpl();
                    obj4.cancelBooking(bookingId);
                    break;

                case 5:
                    System.out.print("Enter Event Id: ");
                    int eventId=scanner.nextInt();
                    BookingSystemRepositoryImpl obj5=new BookingSystemRepositoryImpl();
                    int availabeNoOfTickets=obj5.getAvailableNoOfTickets(eventId);
                    System.out.println("Available ticket for gibe event id is "+availabeNoOfTickets);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        bookingSystem.startBookingSystem();
    }
}

