package com.rccl.middleware.enroll_loyalty;

import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;

import java.util.concurrent.CompletableFuture;

public class EnrollLoyaltyConsumerServiceImpl implements EnrollLoyaltyConsumerService {

    @Override
    public HeaderServiceCall<NotUsed, String> healthCheck() {
        return (requestHeader, request) -> {
            String quote = "Here's to tall ships. "
                    + "Here's to small ships. "
                    + "Here's to all the ships on the sea. "
                    + "But the best ships are friendships, so here's to you and me!";

            return CompletableFuture.completedFuture(new Pair<>(ResponseHeader.OK, quote));
        };
    }




}
