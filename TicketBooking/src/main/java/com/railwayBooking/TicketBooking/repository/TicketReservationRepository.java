package com.railwayBooking.TicketBooking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.railwayBooking.TicketBooking.model.TicketReservation;

public interface TicketReservationRepository extends MongoRepository<TicketReservation, String> {

}