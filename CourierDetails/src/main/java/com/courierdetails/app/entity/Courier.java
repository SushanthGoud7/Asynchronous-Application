package com.courierdetails.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COURIER_DETAILS")
@Builder
public class Courier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COURIER_ID")
	private int courierId;

	@Column(name = "COURIER_NAME")
	@NotNull(message = "tracking name cannot be null")
	private String trackingNum;

	@Column(name = "COURIER_SENDER_DETAILS")
	@NotNull(message = "sender details cannot be null")
	private String sender;

	@Column(name = "COURIER_RECIPIENT_DETAILS")
	@NotNull(message = "recipient details cannot be null")
	private String recipient;

	@Column(name = "COURIER_STATUS")
	@NotNull(message = "courier status details cannot be null")
	private String status;

	@Column(name = "COURIER_STOPTYPE")
	@NotNull(message = "courier stoptype details cannot be null")
	private Character stopType;

	@Column(name = "COURIER_ADDRESS")
	@NotNull(message = "address details cannot be null")
	private String address;
}
