package com.railwayBooking.PaymentServices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.railwayBooking.PaymentServices.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Integer> {
	

}
