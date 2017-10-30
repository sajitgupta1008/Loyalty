package com.rccl.middleware.enroll_loyalty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rccl.middleware.common.validation.validator.ValidatorConstants;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import com.rccl.middleware.common.validation.validator.Birthdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoyaltyEnrollmentInformation {

    @NotNull(message = "A first name is required.")
    @Size(min = 1, max = 100, message = "The first name must be at least one (1) character.")
    String firstName;

    @NotNull(message = "A last name is required.")
    @Size(min = 1, max = 100, message = "The last name must be at least one (1) character.")
    String lastName;

    @NotBlank(message = "An email is required.")
    @Size(min = 5, max = 256, message = "The email can only have up to 256 characters.")
    @Email(regexp = ValidatorConstants.EMAIL_REGEXP, message = "The email is invalidly formatted.")
    String email;

    @NotEmpty(message = "Date of birth is required.")
    @Birthdate
    String birthdate;

    @NotBlank(message = "Loyalty program code is required.")
    String loyaltyProgramCode;


}
