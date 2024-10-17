package com.courierdetails.app.service;

import java.util.List;
import java.util.Optional;

import com.courierdetails.app.entity.Courier;

public interface CourierService {

	List<Courier> getCouriers();

	Optional<Courier> getCourierById(int courierId);

	Courier insertCourier(Courier courier);

	Courier updateCourier(Courier courier, int courierId);

	void deleteCourier(int courierId);

}
