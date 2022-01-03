package com.railwayBooking.AdminSecurity.model;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reservation")
public class TicketReservation 
{
	
	@Id 
	public String id;
	public String noOfTickets;
	public Collection<Trains> trains;
	public TicketReservation(String id, String noOfTickets, Collection<Trains> train) {
		super();
		this.id = id;
		this.noOfTickets = noOfTickets;
		this.trains = trains;
	}
	public TicketReservation() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(String noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public Collection<Trains> getTrain() {
		return trains;
	}
	public void setTrain(Collection<Trains> trains) {
		this.trains = trains;
	}
	@Override
	public String toString() {
		return "TicketReservation [id=" + id + ", noOfTickets=" + noOfTickets + ", train=" + trains + "]";
	}
	
	
	
}