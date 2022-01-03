package com.railwayBooking.TicketBooking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

	@RestController
	public class MainController {
		
		@GetMapping("/Ticket")
		public String ticket()
		{
			return "Ticket booking service for user";
		}


}
