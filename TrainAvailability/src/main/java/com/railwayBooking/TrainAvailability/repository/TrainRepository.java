package com.railwayBooking.TrainAvailability.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.railwayBooking.TrainAvailability.model.Trains;

public interface TrainRepository extends MongoRepository<Trains, String> {

	Optional<Trains> findByStartStation(String startStation);

	List<Trains> findByEndStation(String endStation);

	Optional<Trains> findById(String trainid);

	void deleteById(String trainid);

}
 