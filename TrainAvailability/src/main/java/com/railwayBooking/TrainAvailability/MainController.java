package com.railwayBooking.TrainAvailability;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

	
	@RestController
	public class MainController {
		
		@GetMapping("/Availability")
		public String availability()
		{
			return "Availability details of train";
		}

	}

