package com.tut.camelmicroservicea.beans;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyLoggerBeanProcessor implements Processor {

    private Logger logger = LoggerFactory.getLogger(MyLoggerBeanProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("MyLoggerBeanProcessor {}", exchange.getMessage().getBody());

    }
}
