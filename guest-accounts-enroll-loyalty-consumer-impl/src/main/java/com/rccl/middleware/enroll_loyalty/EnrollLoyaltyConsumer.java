package com.rccl.middleware.enroll_loyalty;

import akka.Done;
import akka.stream.javadsl.Flow;
import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.rccl.middleware.common.logging.RcclLoggerFactory;
import com.rccl.middleware.common.validation.MiddlewareValidation;

import javax.inject.Inject;

public class EnrollLoyaltyConsumer {

    private static final Logger LOGGER = RcclLoggerFactory.getLogger(EnrollLoyaltyConsumer.class);

    private static final String GROUP_ID = "enroll_loyalty_group";

    private final SiebelService siebelService;
    private final PutService putService;

    @Inject
    public EnrollLoyaltyConsumer(SiebelService siebelService, ProducerService producerService, PutService putService) {


      /*  producerService.enrollLoyaltyTopic().subscribe()
             .withGroupId(GROUP_ID)
                .atLeastOnce(Flow.fromFunction(this::getEnrollLoyaltyNo));*/
        this.siebelService = siebelService;
        this.putService = putService;
    }

    private Done getEnrollLoyaltyNo(EnrollLoyaltyKafkaMessage message) {

        LOGGER.info("Received " + message.type + " Loyalty enrollment user email : " + message.enrollmentInformation.email);

        MiddlewareValidation.validate(message);

        siebelService.getLoyaltyAccountNumber()
                .invoke(message.enrollmentInformation)
                //TODO .exceptionally(ex -> {throw new RuntimeException("some unknown error occured");})
                .thenAccept(jsonNode -> {

                    LOGGER.info("Siebel Service has been invoked.");
                    putService.updateUserDetails(
                            extractLoyaltyAccountNumber(jsonNode))
                            .thenAccept(response ->
                                    LOGGER.info(String.format("%s is enrolled for loyalty successfully.",
                                            message.enrollmentInformation.email))
                            );
                });

        return Done.getInstance();
    }

    private String extractLoyaltyAccountNumber(JsonNode jsonNode) {
        String accountNo = jsonNode.get("Envelope").get("Body")
                .get("EnrollConsumerResponse")
                .get("ReturnMessage")
                .get("EnrollConsumerResult")
                .get("EnrollConsumer")
                .get("LoyaltyAccountNumber")
                .get("content").asText();

        return accountNo;
    }

}
