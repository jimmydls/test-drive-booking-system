package com.tss.tdbs.controller;

import com.tss.tdbs.exception.ResourceNotFoundException;
import com.tss.tdbs.form.ClientForm;
import com.tss.tdbs.model.Client;
import com.tss.tdbs.model.ClientBooking;
import com.tss.tdbs.model.Dealer;
import com.tss.tdbs.model.DealerAgent;
import com.tss.tdbs.model.TestDrive;
import com.tss.tdbs.model.TestDriveScreening;
import com.tss.tdbs.repository.ClientBookingRepository;
import com.tss.tdbs.repository.ClientRepository;
import com.tss.tdbs.repository.DealerAgentRepository;
import com.tss.tdbs.repository.DealerRepository;
import com.tss.tdbs.repository.TestDriveRepository;
import com.tss.tdbs.repository.TestDriveScreeningRepository;
import com.tss.tdbs.util.Constants;
import com.tss.tdbs.util.email.Email;
import com.tss.tdbs.util.email.EmailService;
import com.tss.tdbs.util.email.EmailTemplate;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tdbs/api")
public class TdbsController {
	
	final static Logger logger = LoggerFactory.getLogger(TdbsController.class);
	
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
	
	@Autowired
	TestDriveScreeningRepository testDriveScreeningRepository;
	
	@Autowired
	TestDriveRepository testDriveRepository;
	
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
	
	// Send email to user
	@GetMapping("/sendUserEmail/{id}")
	public String sendUserEmail(@PathVariable(value = "id") Long bookingId) {
		
		ClientBooking clientBooking = clientBookingRepository.findById(bookingId)
	            .orElseThrow(() -> new ResourceNotFoundException("ClientBooking", "bookingId", bookingId));
		
		Client client = clientRepository.findById(clientBooking.getClientId())
	            .orElseThrow(() -> new ResourceNotFoundException("Client", "clientId", clientBooking.getClientId()));
		
		TestDrive testDrive = testDriveRepository.findByBookingId(bookingId);
		
		Dealer dealer = dealerRepository.findById(testDrive.getDealerId())
				.orElseThrow(() -> new ResourceNotFoundException("Dealer", "dealerId", testDrive.getDealerId()));
		
		String from = "jimmydiong@gmail.com";
		String to = client.getEmail();
		String subject = "Mercedes-Benz test drive confirmation";
		 
		EmailTemplate template = new EmailTemplate("index.html");
		
		String venue = dealer.getAddress1() + "\n" + dealer.getAddress2() + "\n" + dealer.getAddress3();
		 
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", client.getFirstName());
		replacements.put("branch", dealer.getBranchName());
		replacements.put("venue", venue);
		replacements.put("postcode", dealer.getPostCode());
		replacements.put("city", dealer.getCity());
		replacements.put("state", dealer.getState());
		replacements.put("time", testDrive.getDateTimeFrom().toString());
		replacements.put("testDriveId", testDrive.getTestDriveId().toString());
		 
		String message = template.getTemplate(replacements);
		 
		Email email = new Email(from, to, subject, message);
		email.setHtml(true);
		emailService.send(email);
		logger.info("sendEmail success");
		testDrive.setStatusId(Constants.PENDING_CLIENT);
		testDriveRepository.save(testDrive);
		return "success";
	}
	
	@GetMapping("/schedule")
	public boolean schedule() {
		Boolean status = false;
		
		List<ClientBooking> clientBookings = clientBookingRepository.findByStatus("C");

		for(ClientBooking clientBooking : clientBookings ) {
			Long dealerId;			
			List<TestDriveScreening> testDriveScreenings = testDriveScreeningRepository.findMatchingTimeslot(clientBooking.getDateTimeFrom(), clientBooking.getCarModel(),false);
			
			if(!testDriveScreenings.isEmpty()) {
				
				//Determine which dealer is most nearer but will be mock at the moment
				for(TestDriveScreening testDriveScreening : testDriveScreenings) {
					dealerId = testDriveScreening.getDealerId();
					
					List<TestDrive> testDrives = testDriveRepository.findExistTestDrive(dealerId, clientBooking.getCarModel(), clientBooking.getDateTimeFrom());
					
					if(testDrives.size() >0) {
						if(clientBooking.isPreferGroup()) {
							TestDrive testDrive = new TestDrive();						
														
							testDrive.setBookingId(clientBooking.getBookingId());
							testDrive.setScreeningId(testDriveScreening.getScreening_id());
							testDrive.setCarModel(clientBooking.getCarModel());
							testDrive.setDateTimeFrom(clientBooking.getDateTimeFrom());
							testDrive.setDealerId(dealerId);
							testDrive.setStatusId(Constants.PENDING_DEALER);
							testDrive.setDateTimeFrom(clientBooking.getDateTimeFrom());
							testDrive.setDateTimeTo(DateUtils.addHours(testDrives.get(testDrives.size()-1).getDateTimeTo(), 1));
							testDrive.setPreferGroup(clientBooking.isPreferGroup());
							testDriveRepository.save(testDrive);
							
							
							
							TestDriveScreening testDriveScreeningFound = testDriveScreeningRepository.findByTimeslot(testDrives.get(0).getDateTimeTo(),clientBooking.getCarModel(), dealerId);
							
							testDriveScreeningFound.setReserved(true);
							testDriveScreeningRepository.save(testDriveScreeningFound);
							
							for(TestDrive currentTestDrive :testDrives) {
								currentTestDrive.setDateTimeTo(DateUtils.addHours(currentTestDrive.getDateTimeTo(), 1));
								 	testDriveRepository.save(currentTestDrive);
							}
							
							
							
						}else {
							logger.info("Client not prefer group");
							continue;
						}
						
					}else {
						logger.info("Found timeslot");
						TestDrive testDrive = new TestDrive();						
						
						
						testDrive.setBookingId(clientBooking.getBookingId());
						testDrive.setScreeningId(testDriveScreening.getScreening_id());
						testDrive.setCarModel(clientBooking.getCarModel());
						testDrive.setDateTimeFrom(clientBooking.getDateTimeFrom());
						testDrive.setDealerId(dealerId);
						testDrive.setStatusId(Constants.PENDING_DEALER);
						testDrive.setDateTimeFrom(clientBooking.getDateTimeFrom());
						testDrive.setDateTimeTo(DateUtils.addHours(clientBooking.getDateTimeFrom(), 1));
						testDrive.setPreferGroup(clientBooking.isPreferGroup());
						testDriveRepository.save(testDrive);
						
						if(!clientBooking.isPreferGroup()) {
							testDriveScreening.setReserved(true);
							testDriveScreeningRepository.save(testDriveScreening);
						}
						logger.info("Done insert");						
									
					}
					logger.info("Done insert");
					clientBooking.setStatusId(Constants.DONE);
					clientBookingRepository.save(clientBooking);
					break;
				}
				
			}else {
				logger.info("No matching found for Booking ID{}",clientBooking.getBookingId());
				clientBooking.setStatusId(Constants.NOT_FOUND);
				clientBookingRepository.save(clientBooking);
			}
			
		}
		status = true;
		return status;
	}
	
	// Send email to user
	@GetMapping("/invitation/{status}/{id}")
	public String updateInvitationStatus(@PathVariable(value = "status") String status, @PathVariable(value = "id") Long testDriveId) {
		TestDrive testDrive = testDriveRepository.findById(testDriveId)
	            .orElseThrow(() -> new ResourceNotFoundException("TestDrive", "testDriveId", testDriveId));
		
		testDrive.setStatusId(status);
		testDriveRepository.save(testDrive);
		
		if(status.equals(Constants.DECLINE) && !testDrive.isPreferGroup()) {
			TestDriveScreening testDriveScreening = testDriveScreeningRepository.findById(testDrive.getScreeningId())
					.orElseThrow(() -> new ResourceNotFoundException("TestDriveScreening", "testDriveId", testDrive.getScreeningId()));
			
			testDriveScreening.setReserved(false);
			testDriveScreeningRepository.save(testDriveScreening);
		}
		
		return "Thanks for your confirmation";
	}
	
	// Get All testDrive
	@GetMapping("/testDrive")
	public List<TestDrive> getAllTestDrive() {
	    return testDriveRepository.findAll();
	}

}
