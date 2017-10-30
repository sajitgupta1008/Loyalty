package com.rccl.middleware.enroll_loyalty;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class EnrollLoyaltyModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(EnrollLoyaltyConsumerService.class, EnrollLoyaltyConsumerServiceImpl.class);
        //TODO    bindClient(SiebelService.class);
//TODO         bindClient(ProducerService.class);
//TODO          bindClient(PutService.class);

    }
}
