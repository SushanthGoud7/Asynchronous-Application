package com.courierdetails.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courierdetails.app.entity.Courier;
import com.courierdetails.app.jms.JmsPublisher;
import com.courierdetails.app.serviceImpl.CourierServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("courier")
public class CourierController {

	@Autowired
	private CourierServiceImpl courierService;

	@Autowired
	private JmsPublisher jmsPublisher;
	/**
	 * This method is used to fetch the courier list
	 * 
	 * @return getCouriers
	 */
	@GetMapping(path = "/getcourier", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "This API fetches the couriers")
	public ResponseEntity<List<Courier>> viewCouriers() {

		List<Courier> getCouriers = courierService.getCouriers();
		return new ResponseEntity<List<Courier>>(getCouriers, HttpStatus.OK);

	}

	/**
	 * This method is used to fetch the courier by Id
	 * 
	 * @return getCourierById
	 */
	@GetMapping(path = "/getcourier/{courierId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "This API fetch the courier")
	public ResponseEntity<Optional<Courier>> viewCourierById(@PathVariable("courierId") int courierId) {

		Optional<Courier> getCourierById = courierService.getCourierById(courierId);
		return new ResponseEntity<Optional<Courier>>(getCourierById, HttpStatus.OK);

	}

	/**
	 * This method is used to create the courier
	 * 
	 * @param courier
	 * @return savedCourier
	 */
	@PostMapping(path = "/insertcourier", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "This API inserts the couriers")
	public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {

		Courier savedCourier = courierService.insertCourier(courier);
		return new ResponseEntity<Courier>(savedCourier, HttpStatus.CREATED);

	}

	/**
	 * This method is used to create the published courier(JMS calling**)
	 * 
	 * @param courier
	 * @return savedCourier
	 */
	@PostMapping(path = "/publishcourier", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "This API inserts the published courier")
	public ResponseEntity<Courier> publishCourier(@RequestBody Courier courier) {

		Courier savedCourier = courierService.insertCourier(courier);
		jmsPublisher.sendCourierMessage(savedCourier);
		return new ResponseEntity<Courier>(savedCourier, HttpStatus.OK);
	}

	/**
	 * This method is used to update the courier
	 * 
	 * @param courier
	 * @param courierId
	 * @return updatedCourier
	 */
	@PutMapping(path = "/updatecourier/{courierId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "This API update the couriers")
	public ResponseEntity<Courier> updateCourier(@RequestBody Courier courier,
			@PathVariable("courierId") int courierId) {

		Courier updatedCourier = courierService.updateCourier(courier, courierId);
		return new ResponseEntity<Courier>(updatedCourier, HttpStatus.ACCEPTED);

	}

	/**
	 * This method is used to delete the courier
	 * 
	 * @param courierId
	 * @return
	 */
	@DeleteMapping(path = "/deletecourier/{courierId}")
	@ApiOperation(value = "This API delete the couriers")
	public ResponseEntity<Void> deleteCourier(@PathVariable("courierId") int courierId) {
		courierService.deleteCourier(courierId);
		return ResponseEntity.noContent().build();
	}

}
