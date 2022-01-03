package com.railwayBooking.PaymentServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
	
	@RestController
	public class MainController {
		
		@GetMapping("/Payment")
		public String payment()
		{
			return "Payment services for user and admin";
		}


}
