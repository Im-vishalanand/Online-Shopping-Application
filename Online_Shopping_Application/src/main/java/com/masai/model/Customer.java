package com.masai.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Validated
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@NotEmpty(message = "firstName cannot be empty")
	@NotNull(message = "firstName cannot be null")
	@NotBlank(message = "firstName cannot be blank")
	@Size(min = 3, max = 20, message = "firstname should be greater then 3 and less the 20")
	private String firstName;

	@NotEmpty(message = "lastName cannot be empty")
	@NotNull(message = "lastName cannot be null")
	@NotBlank(message = "lastName cannot be blank")
	@Size(min = 2, max = 20, message = "lastname should be greater then 2 and less the 20")
	private String lastName;

	@NotNull(message = "please provide the mobile number")
	@Size(min = 10, max = 10, message = "your mobile number length not appropreate")
	@Column(unique = true)
	private String mobileNumber;

	@Email(message = "please provide the correct email")
	@NotEmpty(message = "email cannot be empty")
	@NotNull(message = "email cannot be null")
	@NotBlank(message = "email cannot be blank")
	@Column(unique = true)
	private String email;

	@NotEmpty(message = "role cannot be empty")
	@NotNull(message = "role cannot be null")
	@NotBlank(message = "role cannot be blank")
	private String role;

	@NotEmpty(message = "password cannot be empty")
	@NotNull(message = "password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Size(min = 6, max = 15, message = "the password length is not apropriate")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Address> addresses = new HashSet<>();

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private Cart cart = new Cart();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Orders> orders = new ArrayList<>();

	

}
