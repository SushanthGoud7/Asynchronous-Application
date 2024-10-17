package com.courierdetails.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courierdetails.app.entity.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Integer> {

	Optional<Courier> findByCourierId(int courierId);

	void deleteByCourierId(int courierId);

}
