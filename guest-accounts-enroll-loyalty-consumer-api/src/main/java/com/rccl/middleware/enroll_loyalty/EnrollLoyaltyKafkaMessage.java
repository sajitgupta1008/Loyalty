package com.rccl.middleware.enroll_loyalty;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollLoyaltyKafkaMessage {
    String type;
    LoyaltyEnrollmentInformation enrollmentInformation;

}
