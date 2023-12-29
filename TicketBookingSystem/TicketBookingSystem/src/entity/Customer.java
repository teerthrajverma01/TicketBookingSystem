package entity;

public class Customer {
	private String customer_name,email,phone_number;
	Customer(String customer_name,String email,String phone_number){
		this.customer_name=customer_name;
		this.email=email;
		this.phone_number=phone_number;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public void  display_customer_details() {
		System.out.println("Customer Name: " + customer_name);
		System.out.println("Customer Email: " + email);
		System.out.println("Customer Phone Number: " + phone_number);
	}
	
	@Override
	 public String toString() {
	        return "Customer Name: " + customer_name + "\n" +
	               "Customer Email: " + email + "\n" +
	               "Customer Phone Number: " + phone_number;
	    }
	
}

