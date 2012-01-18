package com.starpoint.business;

import org.slf4j.Logger;

/**
 */
public class SlowInitSvcImpl implements SlowInitSvc {
    
    private final Logger logger;


    public SlowInitSvcImpl(Logger logger) {        
        this.logger = logger;
        logger.info("Enter slowInitSvc");
        try {
            Thread.sleep(15000L);
        } catch (InterruptedException ie) {
            // don't care
        }
        logger.info("Exit slowIntSvc");
    }

    @Override
    public void doNothing() {
        logger.info("Ok, I don't do nothing, but I only log nonsense");
    }

    @Override
    public void handleShutdown() {
        logger.info("Handling service shutdown");
    }
}
