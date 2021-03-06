package com.railwayBooking.UserSecurity;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.railwayBooking.UserSecurity.model.AuthenticationRequest;
import com.railwayBooking.UserSecurity.model.AuthenticationResponse;
import com.railwayBooking.UserSecurity.model.TicketReservation;
import com.railwayBooking.UserSecurity.model.Trains;
import com.railwayBooking.UserSecurity.model.UserModel;
import com.railwayBooking.UserSecurity.repository.UserRepository;
import com.railwayBooking.UserSecurity.service.UserService;
import com.railwayBooking.UserSecurity.utils.JwtUtils;


@RestController
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtils jwtUtils;
	
	//========================================User on  Train Service===================================================
	  
	  public List<Trains> show()
	  {
		  return userService.displayAll(); 
	  }
	
	  @GetMapping("/searchTrainByStartDestination/{startStation}")
		public List<Trains> searchTrainByStartDestination(@PathVariable("startStation") String startStation )
		{
			return userService.SearchByStartLocation(startStation);
		}
	  
	  @GetMapping("/searchTrainByEndDestination/{endStation}")
		public List<Trains>searchTrainByEndDestination(@PathVariable("endStation") String endStation)
		{
			return userService.searchTrainByEndLocation(endStation);	
		}

	//========================================User on  Reservation Service===================================================
	  @GetMapping("/getreservation")
	  public List<TicketReservation> getTicketsDetails()
	  {
		return userService.getReservation();
		  
	  }
	  
	  @PostMapping("/add")
	  public String add(@RequestBody TicketReservation reserve)
	  {
		  return userService.add(reserve);
	  }
	  
	  @DeleteMapping("/deleteReservation/{id}")
	  public String deleteReservation(@PathVariable String id)
	  {
		  return userService.deleteReservationByAdmin(id);
	  }
	
	
	@GetMapping("/dashboard")
	private String testingToken()
	{
		return "Welcome User" +SecurityContextHolder.getContext().getAuthentication().getName(); //from jwtfilterresquest
	}
	
	@PostMapping("/subs")
	private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest)
	{
		String username =authenticationRequest.getUsername();
		String password =authenticationRequest.getPassword();
		UserModel userModel=new UserModel();
		userModel.setUsername(username);
		userModel.setPassword(password);
		try {
			
			userRepository.save(userModel);
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during User Subscription: "+username));
		}
	
		return ResponseEntity.ok(new AuthenticationResponse("Succesfull sing-in as user: "+username));
	}
	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest)
	{
		String username =authenticationRequest.getUsername();
		String password =authenticationRequest.getPassword();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (Exception e) //badCrendtiablexception
		{
			return ResponseEntity.ok(new AuthenticationResponse("Error during client authentication: "+username));
		}		
		//loading user from database
		UserDetails loadedUser=userService.loadUserByUsername(username);
		String generatedToken = jwtUtils.generateToken(loadedUser);
		
		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
//		String generatedToken=jwtUtils.generateToken(loadedUser);
//		return ResponseEntity.ok(new AuthenticationResponse("Successfull authentication of user: "+username));
	}

	
}