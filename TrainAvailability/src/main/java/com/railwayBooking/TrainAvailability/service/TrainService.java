package com.railwayBooking.TrainAvailability.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railwayBooking.TrainAvailability.model.Trains;
import com.railwayBooking.TrainAvailability.repository.TrainRepository;

@Service
public class TrainService {
	
	@Autowired
	private TrainRepository trainRepository;
	
	public Trains addTrain (Trains train) {
		return trainRepository.save(train);
	}


	public void deleteTrain(Trains train) {
		trainRepository.delete(train);
	}

	public Optional<Trains> getTrainbyId(String id) {
		return trainRepository.findById(id);	}

}