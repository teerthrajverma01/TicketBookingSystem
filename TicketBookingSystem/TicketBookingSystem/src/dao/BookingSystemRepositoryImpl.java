package dao;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import entity.Booking;
import entity.Concert;
import entity.Event;
import entity.Event.EventType;
import entity.Movie;
import entity.Sports;
import entity.Venue;
import util.DBUtil;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository{
	
	static Connection connection;
	
	public BookingSystemRepositoryImpl() {
		try {
			connection=DBUtil.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String create_event(String event_name, String date, String time, int total_seats, float ticket_price,
			String event_type, Venue venu) {
		// TODO Auto-generated method stub
		
		try {
			
			String query = "INSERT INTO Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps1 = connection.prepareStatement(query);
			//set values to query
			ps1.setString(1, event_name);
			
			//parse values string into date and time 
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date parseDate = null;
	        Time parseTime = null;
	        parseDate = sdf.parse(date);
	        java.sql.Date sqlParseDate = new java.sql.Date(parseDate.getTime());
			ps1.setDate(2, sqlParseDate);
			parseDate = sdf.parse(date+" "+time);
			parseTime = new Time(parseDate.getTime());
			ps1.setTime(3, parseTime);
			ps1.setInt(4, 3);
			ps1.setInt(5, total_seats);
			ps1.setInt(6, total_seats);
			ps1.setFloat(7, ticket_price);
			ps1.setString(8, event_type);
			
            // Execute the update
            int rowsAffected = ps1.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event created successfully!");
            } else {
                System.out.println("Failed to insert Time.");
            }
            
		}catch(Exception e) {
			System.out.println("Exception occured in Create Event : "+e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<Event> getEventDetails() {
		// TODO Auto-generated method stub
		
		try {
			
			ArrayList<Event> events = new ArrayList<>();
			 
			String sql = "SELECT * FROM event";
	        PreparedStatement statement1 = connection.prepareStatement(sql) ;
	        ResultSet resultSet = statement1.executeQuery();
	        while (resultSet.next()) {
	        	
	                    
	                    String sql2 = "SELECT * FROM venue WHERE venue_id = ?";
	                    PreparedStatement statement2 = connection.prepareStatement(sql2) ;
	                    statement2.setInt(1,resultSet.getInt("venue_id"));
	                    ResultSet resultSet2 = statement2.executeQuery();
						Venue v=null;
						
						while (resultSet2.next()) {
							v =new Venue(resultSet2.getString("venue_name"),
							resultSet2.getString("address")
							);
						}
						
						if(resultSet.getString("event_type").equals("Movie")) {
						//  convert string into enum;
						EventType eventType=EventType.valueOf(resultSet.getString("event_type").toUpperCase());
	                    Event event = new Movie( resultSet.getString("event_name"),
	                            resultSet.getString("event_date"),
	                            resultSet.getString("event_time"),
	                            v,
	                            resultSet.getInt("total_seats"),
	                            resultSet.getInt("available_seats"),
	                            resultSet.getFloat("ticket_price"),
	                            eventType
	                                );
	                    events.add(event);
						}else if(resultSet.getString("event_type").equals("Sports")) {
							//  convert string into enum;
							EventType eventType=EventType.valueOf(resultSet.getString("event_type").toUpperCase());
		                    Event event = new Sports( resultSet.getString("event_name"),
		                            resultSet.getString("event_date"),
		                            resultSet.getString("event_time"),
		                            v,
		                            resultSet.getInt("total_seats"),
		                            resultSet.getInt("available_seats"),
		                            resultSet.getFloat("ticket_price"),
		                            eventType
		                                );
		                    events.add(event);
							}else if(resultSet.getString("event_type").equals("Concert")) {
								//  convert string into enum;
								EventType eventType=EventType.valueOf(resultSet.getString("event_type").toUpperCase());
			                    Event event = new Concert( resultSet.getString("event_name"),
			                            resultSet.getString("event_date"),
			                            resultSet.getString("event_time"),
			                            v,
			                            resultSet.getInt("total_seats"),
			                            resultSet.getInt("available_seats"),
			                            resultSet.getFloat("ticket_price"),
			                            eventType
			                                );
			                    events.add(event);
								}
	          
	         }
	        if(events.isEmpty() || events==null) {
	        	System.out.println("No events found");
	        }else {
	        	return events;
	        }
	  
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception occured in getEventDetails : "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getAvailableNoOfTickets(int event_id) {
		// TODO Auto-generated method stub
		try {
			
			String sql = "SELECT available_seats FROM event where event_id = ?";
	        PreparedStatement statement1 = connection.prepareStatement(sql) ;
	        statement1.setInt(1, event_id);
	        ResultSet resultSet = statement1.executeQuery();
	        if(resultSet.next()) {
	        	return resultSet.getInt("available_seats");
	        }
	        
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception "+e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return 0;
	}

	@Override
	public float calculateBookingCost(int numTickets,int event_id) {
		// TODO Auto-generated method stub
		float totalCost=0;
		try {
			
			String sql = "SELECT ticket_price FROM event WHERE event_id = ?";
			   PreparedStatement statement = connection.prepareStatement(sql);
			      statement.setInt(1, event_id);

			     ResultSet resultSet = statement.executeQuery();
			     if (resultSet.next()) {
			          float ticketPrice = resultSet.getFloat("ticket_price");
			           totalCost = numTickets * ticketPrice;
			                //setTotalCostInBooking(totalCost);
			           System.out.println("Total cost "+totalCost);
			    }else {
			    	System.out.println("Event Not Found");
			    }
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception "+e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return totalCost;
	}

	@Override
	public void bookTickets(String eventName, int numTickets, int customerId) {
		// TODO Auto-generated method stub
	
		try {
			//check customer is present in dtabase or not
			String sql = "SELECT * FROM customer where customer_id = ?";
	        PreparedStatement statement1 = connection.prepareStatement(sql) ;
	        statement1.setInt(1, customerId);
	        ResultSet resultSet2 = statement1.executeQuery();
	        
	        if(resultSet2.next()) {
	        	//check event is present or not
				String sql2 = "SELECT * FROM event where event_name = ?";
		        PreparedStatement statement2 = connection.prepareStatement(sql2) ;
		        statement2.setString(1, eventName);
		        ResultSet resultSet = statement2.executeQuery();
		        
	        	if(resultSet.next()) {

	        		int eventId=resultSet.getInt("event_id");
	        		int availableSeats=getAvailableNoOfTickets(eventId);
	        		float totalCoast=calculateBookingCost(numTickets, eventId);
	                Date currentDate = new Date();
	                java.sql.Date parseCurrentDate = new java.sql.Date(currentDate.getTime());
	        		
	        		if(availableSeats>=numTickets) {
	        			
	        			String sql3 = "INSERT INTO BOOKING (customer_id,event_id,num_tickets,total_cost,booking_date) values (?, ?, ?, ?, ?)";
	        	        PreparedStatement statement3 = connection.prepareStatement(sql3) ;
	        	        statement3.setInt(1, customerId);
	        	        statement3.setInt(2, eventId);
	        	        statement3.setInt(3, numTickets);
	        	        statement3.setFloat(4,totalCoast);
	        	        statement3.setDate(5,parseCurrentDate);
	        	        int affectedRows3 = statement3.executeUpdate();
	        	        
	        	        if(affectedRows3>0) {
	        	        	
		        			String sql4 = "UPDATE event SET available_seats = ? WHERE event_id = ?";
		        	        PreparedStatement statement4 = connection.prepareStatement(sql4) ;
		        	        statement4.setInt(1, availableSeats-numTickets);
		        	        statement4.setInt(2, eventId);
		        	        int affectedRows4 = statement4.executeUpdate();
		        	        
		        	        if(affectedRows4>0) {
		        	        	System.out.println("Booking successFul");
		        	        	return;
		        	        }else {
		        	        	System.out.println("Failed to book");
		        	        	return;
		        	        }
	        	        }else {
	        	        	System.out.println("Failed to book");
	        	        	return;
	        	        }
	        	        
	        		}else {
	        			System.out.println(availableSeats+" tickects can be booked ");
	        			System.out.println("Booking Failed Try again");
	        			return;
	        		}
	        	}else {
	        		System.out.println("Invalid Event Name");
	        		return;
	        	}
	        }else {
	        	System.out.println("Invalid Customer ID");
	        	return;
	        }
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void cancelBooking(int bookingId) {
		
        try{

            // Retrieve event details and number of tickets from the booking
            String query = "SELECT e.event_id, b.num_tickets FROM Booking b JOIN event e ON b.event_id = e.event_id WHERE b.booking_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bookingId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int eventId = resultSet.getInt("event_id");
                        int numTickets = resultSet.getInt("num_tickets");

                        // Delete the booking
                        String deleteBookingQuery = "DELETE FROM Booking WHERE booking_id = ?";
                        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteBookingQuery)) {
                            deleteStatement.setInt(1, bookingId);
                            int rowsAffected = deleteStatement.executeUpdate();
                            
                            if(rowsAffected>0) {
                                // Update available seats in the Event table
                                String updateSeatsQuery = "UPDATE event SET available_seats = available_seats + ? WHERE event_id = ?";
                                try (PreparedStatement updateStatement = connection.prepareStatement(updateSeatsQuery)) {
                                    updateStatement.setInt(1, numTickets);
                                    updateStatement.setInt(2, eventId);
                                    int rowsAffected2 = updateStatement.executeUpdate();
                                    if(rowsAffected2>0) {
                                    	System.out.println("Booking canceled successfully.");
                                    }else {
                                    	System.out.println("Available seats updated");
                                    }
                                }catch(Exception e) {
                                	e.printStackTrace();
                                }
                            }else {
                            	System.out.println("Booking not found.");
                            	return;
                            }
                        }

                        System.out.println("Booking canceled successfully.");
                    } else {
                        System.out.println("Booking not found.");
                    }
                }
            }

        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

	@Override
	public void getBookingDetails(int bookingId) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM Booking " +
                    "JOIN Customer ON Booking.customer_id = Customer.customer_id " +
                    "JOIN Event ON Booking.event_id = Event.event_id " +
                    "JOIN Venue ON Event.venue_id = Venue.venue_id " +
                    "WHERE Booking.booking_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bookingId);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Process the result set
                    while (resultSet.next()) {
                        // Retrieve data from the result set
                        //int bookingId2 = resultSet.getInt("booking_id");
                        int customerId = resultSet.getInt("customer_id");
                        String customerName = resultSet.getString("customer_name");
                        String email = resultSet.getString("email");
                        String phoneNumber = resultSet.getString("phone_number");
                        int eventId = resultSet.getInt("event_id");
                        String eventName = resultSet.getString("event_name");
                        // Retrieve other fields similarly

                        // Print or use the retrieved data as needed
                        System.out.println("Booking ID: " + bookingId);
                        System.out.println("Customer ID: " + customerId);
                        System.out.println("Customer Name: " + customerName);
                        System.out.println("Email: " + email);
                        System.out.println("Phone Number: " + phoneNumber);
                        System.out.println("Event ID: " + eventId);
                        System.out.println("Event Name: " + eventName);
                        // Print other fields similarly
                    }
                }
			
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}

  }
}
