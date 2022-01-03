package com.railwayBooking.TicketBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railwayBooking.TicketBooking.model.TicketReservation;
import com.railwayBooking.TicketBooking.repository.TicketReservationRepository;

@Service
public class TicketReservationService {

	@Autowired
	private TicketReservationRepository ticketReservationRepository;
	
	public List<TicketReservation> getTicketsDetails()
	{
		return ticketReservationRepository.findAll();
	}

	public TicketReservation addReservation(TicketReservation reserve) {
		
		return ticketReservationRepository.insert(reserve);
	}

	public Optional<TicketReservation> findById(String id) {
		
		return ticketReservationRepository.findById(id);
	}

	public TicketReservation update(TicketReservation order) {

		return ticketReservationRepository.save(order);
	}

	public String deleteTicketById(String id) {
		ticketReservationRepository.deleteById(id);
		return "Reservation Cancelled for Id: "+id;
	}


	
	



	
	
	
}