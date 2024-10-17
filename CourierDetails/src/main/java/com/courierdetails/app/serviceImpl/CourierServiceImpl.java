package com.courierdetails.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courierdetails.app.entity.Courier;
import com.courierdetails.app.exceptionhandler.CourierNotFoundException;
import com.courierdetails.app.exceptionhandler.InvalidInputException;
import com.courierdetails.app.repository.CourierRepository;
import com.courierdetails.app.service.CourierService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourierServiceImpl implements CourierService {

	@Autowired
	private CourierRepository courierRepository;

	// Retrieve operation
	@Override
	public List<Courier> getCouriers() {
		try {
			log.info("Courier details fetched successfully!!!");
			List<Courier> couriers = courierRepository.findAll();
			return couriers;
		} catch (Exception e) {
			log.error("Error occured for getCouriers!!!");
			throw new CourierNotFoundException(e.getMessage());
		}
	}

	// Retrieve operation
	@Override
	public Optional<Courier> getCourierById(int courierId) {
		try {
			log.info("Courier details fetched successfully for Id: {} " + courierId);
			Optional<Courier> courierObj = courierRepository.findByCourierId(courierId);
			return courierObj;
		} catch (Exception e) {
			log.error("Error occured for getCourierById: " + courierId);
			throw new CourierNotFoundException(e.getMessage());
		}
	}

	// Create operation
	@Override
	public Courier insertCourier(Courier courier) {
		try {
			log.info("Courier details are inserted successfully!!!");
			Courier savedCourier = courierRepository.save(courier);
			return savedCourier;
		} catch (Exception e) {
			log.error("Error occured for insertCourier!!!");
			throw new InvalidInputException(e.getMessage());
		}
	}

	// Update operation
	@Override
	public Courier updateCourier(Courier courier, int courierId) {
		try {
			Courier courierResponse = null;
			Optional<Courier> response = courierRepository.findById(courierId);
			if (response.isPresent()) {
				Courier courierObj = response.get();
				courierObj.setAddress(courier.getAddress());
				courierObj.setRecipient(courier.getRecipient());
				courierObj.setSender(courier.getSender());
				courierObj.setStatus(courier.getStatus());
				courierObj.setStopType(courier.getStopType());
				courierObj.setTrackingNum(courier.getTrackingNum());
				courierResponse = courierRepository.save(courierObj);
			}
			log.info("Courier details are updated for Id: " + courierId + " successfully!!");
			return courierResponse;
		} catch (Exception e) {
			log.error("Error occured for updateCourier Id: " + courierId);
			throw new InvalidInputException(e.getMessage());
		}
	}

	// Delete operation
	@Override
	@Transactional
	public void deleteCourier(int courierId) {
		try {
			log.info("Courier details deleted successfully for Id: " + courierId);
			courierRepository.deleteByCourierId(courierId);
		} catch (Exception e) {
			log.error("Error occured for deleteCourier Id: " + courierId);
			throw new CourierNotFoundException(e.getMessage());
		}
	}
}
