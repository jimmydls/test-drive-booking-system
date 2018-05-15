package com.tss.tdbs.controller;

import com.tss.tdbs.exception.ResourceNotFoundException;
import com.tss.tdbs.form.ClientForm;
import com.tss.tdbs.model.Client;
import com.tss.tdbs.model.ClientBooking;
import com.tss.tdbs.model.Dealer;
import com.tss.tdbs.model.DealerAgent;
import com.tss.tdbs.repository.ClientBookingRepository;
import com.tss.tdbs.repository.ClientRepository;
import com.tss.tdbs.repository.DealerAgentRepository;
import com.tss.tdbs.repository.DealerRepository;
import com.tss.tdbs.util.email.Email;
import com.tss.tdbs.util.email.EmailService;
import com.tss.tdbs.util.email.EmailTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tdbs/api")
public class TdbsController {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ClientBookingRepository clientBookingRepository;
	
	@Autowired
	DealerRepository dealerRepository;
	
	@Autowired
	DealerAgentRepository dealerAgentRepository;
	
	// Get All Clients
	@GetMapping("/clients")
	public List<Client> getAllClients() {
	    return clientRepository.findAll();
	}
	
	// Create a new client
	@PostMapping("/newClient")
	public Client createClient(@Valid @ModelAttribute  ClientForm clientForm) {
		Client client = new Client();
		
		try {
			client.setClientId(clientForm.getClientId());
			client.setClientPassword(clientForm.getClientPassword());
			client.setFirstName(clientForm.getFirstName());
			client.setLastName(clientForm.getLastName());
			client.setEmail(clientForm.getEmail());
			if(clientForm.getDrivingLicensePhoto() != null)
				client.setDrivingLicensePhoto(clientForm.getDrivingLicensePhoto().getBytes());
			client.setGender(clientForm.getGender());
			client.setAddress1(clientForm.getAddress1());
			client.setAddress2(clientForm.getAddress2());
			client.setAddress3(clientForm.getAddress3());
			client.setState(clientForm.getState());
			client.setContactNumber(clientForm.getContactNumber());
			client.setSubscribe(clientForm.isSubscribe());
			client.setPostCode(clientForm.getPostCode());
			client.setCity(clientForm.getCity());
		}catch(IOException iox) {
			iox.printStackTrace();	
			throw new ResourceNotFoundException("Client", "DrivingLicensePhoto","");
		}
		
	    return clientRepository.save(client);
	}
	
	// Get a Single client
	@GetMapping("/client/{id}")
	public Client getClientById(@PathVariable(value = "id") String clientId) {
	    return clientRepository.findById(clientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Client", "clientId", clientId));
	}
	
	// Update a client
	@PostMapping("/client/{id}")
	public Client updateClient(@PathVariable(value = "id") String clientId,
	                                        @Valid @ModelAttribute  ClientForm clientForm) {

		Client client = clientRepository.findById(clientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Client", "clientId", clientId));
		
		try {
			client.setClientPassword(clientForm.getClientPassword());
			client.setFirstName(clientForm.getFirstName());
			client.setLastName(clientForm.getLastName());
			client.setEmail(clientForm.getEmail());
			if(clientForm.getDrivingLicensePhoto() != null)
				client.setDrivingLicensePhoto(clientForm.getDrivingLicensePhoto().getBytes());
			client.setGender(clientForm.getGender());
			client.setAddress1(clientForm.getAddress1());
			client.setAddress2(clientForm.getAddress2());
			client.setAddress3(clientForm.getAddress3());
			client.setState(clientForm.getState());
			client.setContactNumber(clientForm.getContactNumber());
			client.setSubscribe(clientForm.isSubscribe());
			client.setPostCode(clientForm.getPostCode());
			client.setCity(clientForm.getCity());
		}catch(IOException iox) {
			iox.printStackTrace();	
			throw new ResourceNotFoundException("Client", "DrivingLicensePhoto","");
		}

	    Client updatedClient = clientRepository.save(client);
	    return updatedClient;
	}
	
	// Delete a client
	@DeleteMapping("/client/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(value = "id") String clientId) {
	    Client client = clientRepository.findById(clientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Client", "clientId", clientId));

	    clientRepository.delete(client);

	    return ResponseEntity.ok().build();
	}
	
	// Get a Single client license
	@GetMapping("/clientLicense/{id}")
	public void getClientLicenseById(HttpServletResponse response, @PathVariable(value = "id") String clientId) {
	    Client client = clientRepository.findById(clientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Client", "clientId", clientId));
	    
	    byte[] imageInByte = client.getDrivingLicensePhoto();
        try {
        	if (imageInByte != null) {
                response.setContentType("image/png");
                response.getOutputStream().write(imageInByte);
            }
        }catch(IOException iox) {
			iox.printStackTrace();	
			throw new ResourceNotFoundException("Client", "DrivingLicensePhoto","");
		}
        
	}
	
	// Create a new client booking
	@PostMapping("/newClientBooking")
	public ClientBooking createClientBooking(@Valid @RequestBody ClientBooking clientBooking) {
	    return clientBookingRepository.save(clientBooking);
	}
	
	// Get All client booking
	@GetMapping("/clientBookings")
	public List<ClientBooking> getAllBookings() {
	    return clientBookingRepository.findAll();
	}
	
	// Get a Single client booking
	@GetMapping("/clientBooking/{id}")
	public ClientBooking getClientBookingsById(@PathVariable(value = "id") Long bookingId) {
	    return clientBookingRepository.findById(bookingId)
	            .orElseThrow(() -> new ResourceNotFoundException("ClientBooking", "bookingId", bookingId));
	}
	
	// Get All Dealers
	@GetMapping("/dealers")
	public List<Dealer> getAllDealers() {
	    return dealerRepository.findAll();
	}
	
	// Get a Single dealer
	@GetMapping("/dealer/{id}")
	public Dealer getDealerById(@PathVariable(value = "id") Long dealerId) {
	    return dealerRepository.findById(dealerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Dealer", "dealerId", dealerId));
	}
	
	// Create a new dealer
	@PostMapping("/newDealer")
	public Dealer createDealer(@Valid @RequestBody Dealer dealer) {
	    return dealerRepository.save(dealer);
	}
	
	// Update dealer
	@PostMapping("/updateDealer/{id}")
	public Dealer updateDealer(@PathVariable(value = "id") Long dealerId,
            @Valid @RequestBody Dealer dealerDetails)  {
		
		Dealer dealer = dealerRepository.findById(dealerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Dealer", "dealerId", dealerId));
		
		dealer.setPassword(dealerDetails.getPassword());
		dealer.setBranchName(dealerDetails.getBranchName());
		dealer.setContactNumber(dealerDetails.getContactNumber());
		dealer.setEmail(dealerDetails.getEmail());
		dealer.setAddress1(dealerDetails.getAddress1());
		dealer.setAddress2(dealerDetails.getAddress2());
		dealer.setAddress3(dealerDetails.getAddress3());
		dealer.setState(dealerDetails.getState());
		dealer.setPostCode(dealerDetails.getPostCode());
		dealer.setCity(dealerDetails.getCity());
		dealer.setCarModel(dealerDetails.getCarModel());
		dealer.setGroupMaxClient(dealerDetails.getGroupMaxClient());
		dealer.setDealerAvailability(dealer.getDealerAvailability());

		Dealer updatedDealer = dealerRepository.save(dealer);
	    return updatedDealer;
	}
	
	// Delete a dealer
	@DeleteMapping("/dealer/{id}")
	public ResponseEntity<?> deleteDealer(@PathVariable(value = "id") Long dealerId) {
	    Dealer dealer = dealerRepository.findById(dealerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Dealer", "dealerId", dealerId));

	    dealerRepository.delete(dealer);

	    return ResponseEntity.ok().build();
	}
	
	// Create a new agent
	//@PostMapping("/newAgent")
	//public DealerAgent createAgent(@Valid @RequestBody DealerAgent agent) {
	//    return dealerAgentRepository.save(agent);
	//}
	
	// Get a Single dealer
	@GetMapping("/sendEmail")
	public String sendEmail() {
		String from = "jimmydiong@gmail.com";
		String to = "jimmy_dls@yahoo.com";
		String subject = "Java Mail with Spring Boot";
		 
		EmailTemplate template = new EmailTemplate("index.html");
		 
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", "Pavan");
		replacements.put("today", String.valueOf(new Date()));
		 
		String message = template.getTemplate(replacements);
		 
		Email email = new Email(from, to, subject, message);
		email.setHtml(true);
		emailService.send(email);
		
		return "success";
	}

}
