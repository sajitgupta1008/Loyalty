package com.rccl.middleware.enroll_loyalty;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface EnrollLoyaltyConsumerService extends Service {

    // final String KAFKA_TOPIC_NAME = "enroll_loyalty";

    @Override
    default Descriptor descriptor() {
        return Service.named("enroll_loyalty")
                .withCalls(restCall(Method.GET, "/health", this::healthCheck))
                .withAutoAcl(true);
    }

    ServiceCall<NotUsed, String> healthCheck();

}
