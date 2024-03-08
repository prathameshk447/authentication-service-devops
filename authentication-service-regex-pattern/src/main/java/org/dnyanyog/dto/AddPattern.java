package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class AddPattern {

	@NotNull(message = "Password is mandatory")
	@NotBlank(message = "Password should not be blank")
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z]).{8}", message = "Password should be at least 8 characters, with 1 lowercase, 1 uppercase, and 1 special character")
	private String password;

	@NotNull(message = "Email id is mendatory")
	@NotBlank(message = "Email id should not be blank")
	@Pattern(regexp = "[a-z]{3,50}(@)[a-z]{3,50}(.)[a-z]{2,3}", message = "Invalid email format")
	private String email;

	@NotNull(message = " PANNumber is manadatory")
	@NotBlank(message = " PANNumber Should Not blank")
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "PANNumber must be 10 digits")
	private String PANNumber;

	@NotNull(message = "Phone Number is manadatory")
	@NotBlank(message = "Phone Number Should Not blank")
	@Pattern(regexp = "[0-9]{10}", message = "Invalid phone Number")
	private String phoneNumber;

	@NotNull(message = "AdhaarNumber is manadatory")
	@NotBlank(message = "AdhaarNumber Should Not blank")
	@Pattern(regexp = "[0-9]{12}", message = " AdhaarNumber must be 12 digits")
	private String AdhaarNumber;

	@NotNull(message = "Username is manadatory")
	@NotBlank(message = "Username Should Not blank")
	@Pattern(regexp = "[a-zA-Z0-9]{5,20}", message = "Invalid username format")
	private String username;

	@Pattern(regexp = "[1-9]d+", message = "Positive integer only")
	private String positiveInteger;

	@NotNull(message = "ATMNumber is manadatory")
	@NotBlank(message = "ATMNumber Should Not blank")
	@Pattern(regexp = "[0-9]{12}", message = " ATMNumber must be 12 digits")
	private String ATMNumber;

}
